package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.dao.DoctorDao;
import se331.rest.entity.Comment;
import se331.rest.entity.Doctor;
import se331.rest.entity.People;
import se331.rest.repository.DoctorRepository;
import se331.rest.repository.PeopleRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;
import se331.rest.security.service.UserService;
import se331.rest.service.DoctorService;
import se331.rest.service.PeopleService;
import se331.rest.util.LabMapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @Autowired
    PeopleService peopleService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "name" , required = false) String name) {
        perPage = perPage == null ? 4 : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput;
//        if(name==null){
            pageOutput = userService.getUser(perPage,page);
//        }else{
//            pageOutput = peopleService.getPeople(name, PageRequest.of(page-1,perPage));
//        }
//        pageOutput = peopleService.getPeople(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/changeRole")
    public ResponseEntity<?> changeRoleToPatient(@RequestBody User user) throws AuthenticationException, ServletException, IOException {
        User fakeuser = userRepository.findById(user.getId()).orElse(null);
        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        authorityRepository.save(authPatient);
//        System.out.println(user.getImage());
        People changeUser = People.builder()
                .name(fakeuser.getFirstname())
                .surname(fakeuser.getLastname())
                .image(fakeuser.getImage())
                .age(fakeuser.getAge())
                .gender(fakeuser.getGender())
                .hometown(fakeuser.getHometown())
                .build();
        changeUser.setUser(fakeuser);
        fakeuser.getAuthorities().clear();
        fakeuser.getAuthorities().add(authPatient);
        userRepository.save(fakeuser);
        fakeuser.setPeople(changeUser);
        peopleService.save(changeUser);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPeopleDto(changeUser));


    }

    @PostMapping("/changeRoleToDoctor")
    public ResponseEntity<?> changeRoleToDoctor(@RequestBody User user) throws AuthenticationException, ServletException, IOException {
        User fakeuser = userRepository.findById(user.getId()).orElse(null);
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        authorityRepository.save(authDoctor);
//        System.out.println(user.getImage());
        Doctor changeUser = Doctor.builder()
                .name(fakeuser.getFirstname())
                .surname(fakeuser.getLastname())
                .image(fakeuser.getImage())
                .age(fakeuser.getAge())
                .build();
        changeUser.setUser(fakeuser);
        fakeuser.getAuthorities().clear();
        fakeuser.getAuthorities().add(authDoctor);
        userRepository.save(fakeuser);
        doctorService.save(changeUser);
        return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDto(changeUser));
    }
//    @PostMapping("/setDoctorToUser/{id}")
//    public ResponseEntity<?> setDoctorToPatient(@PathVariable("id") Long id ,@RequestPart Long doctorId){
//        People user= peopleService.getPeople(id);
//        Doctor doctor = doctorService.getDoctor(doctorId);
//        user.setDoctor(doctor);
//        doctor.getPeopleList().add(user);
//        return ResponseEntity.ok(LabMapper.INSTANCE.getPeopleDto(user));
//    }
@PostMapping("/setDoctorToUser")
public ResponseEntity<?> setDoctorToPatient(@RequestBody People people){
    People user= peopleRepository.findById(people.getId()).orElse(null);
    Doctor doctor = doctorRepository.findById(people.getDoctor().getId()).orElse(null);
    user.setDoctor(doctor);
    doctor.getPeopleList().add(user);
    peopleService.save(user);
    doctorService.save(doctor);
    return ResponseEntity.ok(LabMapper.INSTANCE.getPeopleDto(user));
}
}
