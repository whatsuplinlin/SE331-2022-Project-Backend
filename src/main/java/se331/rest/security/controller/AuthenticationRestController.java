package se331.rest.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se331.rest.entity.Comment;
import se331.rest.entity.Doctor;
import se331.rest.entity.People;
import se331.rest.repository.PeopleRepository;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.JwtUser;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;
import se331.rest.security.service.UserService;
import se331.rest.security.service.util.JwtTokenUtil;
import se331.rest.service.DoctorService;
import se331.rest.service.PeopleService;
import se331.rest.util.CloudStorageHelper;
import se331.rest.util.LabMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    CloudStorageHelper cloudStorageHelper;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map result = new HashMap();
        result.put("token", token);
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if (user.getPeople() != null) {
            result.put("user", LabMapper.INSTANCE.getPeopleAuthDTO(user.getPeople()));
        }
        if (user.getAdmin() != null) {
            result.put("user", LabMapper.INSTANCE.getAdminAuthDTO(user.getAdmin()));
        }
        if (user.getDoctor() != null) {
            result.put("user", LabMapper.INSTANCE.getDoctorAuthDTO(user.getDoctor()));
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws AuthenticationException, ServletException, IOException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        authorityRepository.save(authUser);
//        System.out.println(user.getImage());
        User user2 = User.builder()
                .enabled(true)
                .email(user.getEmail())
                .age(user.getAge())
                .hometown(user.getHometown())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .gender(user.getGender())
                .password(encoder.encode(user.getPassword()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .image(user.getImage())
                .build();
        user2.getAuthorities().add(authUser);
        userRepository.save(user2);
//
//       User people = People.builder().name(user.getUsername()).build();
//        peopleRepository.save(people);
//
//        people.setUser(user2);
//        user2.setPeople(people);


        userService.save(user2);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(user2));
    }
}
