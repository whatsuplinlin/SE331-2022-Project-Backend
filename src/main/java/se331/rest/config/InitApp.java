package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.rest.entity.Admin;
import se331.rest.entity.Comment;
import se331.rest.entity.People;
import se331.rest.entity.Vaccine;
import se331.rest.repository.AdminRepository;
import se331.rest.repository.CommentRepository;
import se331.rest.repository.PeopleRepository;
import se331.rest.repository.VaccineRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
   @Autowired
    PeopleRepository peopleRepository;
   @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Vaccine vaccine1, vaccine2,vaccine3;
        addUser();
        Vaccine vaccine = null;

//        vaccine2 = vaccineRepository.save(Vaccine.builder()
//                .type("Moderna")
//                .build());
//        vaccine3 = vaccineRepository.save(Vaccine.builder()
//                .type("Pfizer")
//                .build());
        Comment comment=null;
        Admin admin = null;
        People people = null;
        admin = adminRepository.save(Admin.builder()
                        .name("Hin")
                        .surname("Tipnuan")
                .build());
        admin.setUser(user1);
        user1.setAdmin(admin);
        user1.setPeople(null);

        people = peopleRepository.save(People.builder()
                .name("Harry")
                .surname("Potter")
                .age(18)
                .gender("male")
                .status("3 dose")
                .hometown("Test")
                .image("https://upload.wikimedia.org/wikipedia/en/d/d7/Harry_Potter_character_poster.jpg")
               .build());
        comment = commentRepository.save(Comment.builder()
                .patient_name("Harry")
                .patient_surname("Potter")
                .name("Albus Dumbledore")
                .comment("Take medicine everyday")
                .date("29-01-2022").build());
        people.getCommentList().add(comment);
        comment = commentRepository.save(Comment.builder()
                .patient_name("Harry")
                .patient_surname("Potter")
                .name("Hin")
                .comment("Take medicine everyday dua sus")
                .date("29-01-2022").build());
        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("AZ")
                        .number(1)
                        .date("Test1")
                .build());
        people.getVaccineList().add(vaccine);
        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("AZ")
                .number(2)
                .date("Test2")
                .build());
        people.getVaccineList().add(vaccine);

//        people.getVaccineList().add(vaccine1);
        people.getCommentList().add(comment);
//        people.setUser(user1);
//        user1.setPeople(people);
        System.out.println(user1.getAuthorities());
        people = peopleRepository.save(People.builder()
                .name("Nitipoom")
                .surname("IPoom")
                .age(18)
                .gender("male")
                .status("3 dose")
                .hometown("Test")
                .image("")
                .build());
        people = peopleRepository.save(People.builder()
                .name("Yasintorn")
                .surname("IJo")
                .age(18)
                .gender("male")
                .status("3 dose")
                .hometown("Test")
                .image("")
                .build());
        people = peopleRepository.save(People.builder()
                .name("Yasintorn222")
                .surname("IJo2222")
                .age(18)
                .gender("male22")
                .status("3 dos22e")
                .hometown("Tes222")
                .image("")
                .build());
        people = peopleRepository.save(People.builder()
                .name("Yasintorn222")
                .surname("IJo2222")
                .age(18)
                .gender("male22")
                .status("3 dos22e")
                .hometown("Tes222")
                .image("")
                .build());
        people = peopleRepository.save(People.builder()
                .name("Yasintorn222")
                .surname("IJo2222")
                .age(18)
                .gender("male22")
                .status("3 dos22e")
                .hometown("Tes222")
                .image("")
                .build());


    }
    User user1;
    private  void addUser(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        user1.getAuthorities().add(authAdmin);
        userRepository.save(user1);
    }
}
