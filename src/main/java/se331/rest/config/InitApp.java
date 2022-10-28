package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.rest.entity.*;
import se331.rest.repository.*;
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

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addUser();
        Vaccine vaccine = null;
        Comment comment=null;
        Admin admin = null;
        People people = null;
        Doctor doctor1,doctor2;


        admin = adminRepository.save(Admin.builder()
                        .name("Hin")
                        .surname("Tipnuan")
                .build());
        setInfoAdmin(user1,admin);
//        admin.setUser(user1);
//        user1.setFirstname(admin.getName());
//        user1.setLastname(admin.getSurname());
//        user1.setAdmin(admin);

        doctor1 = doctorRepository.save(Doctor.builder()
                        .name("Doctor")
                        .surname("Jupmua")
                        .image("https://thumbs.dreamstime.com/b/fun-friendly-doctor-1043544.jpg")
                        .age("60")
                        .gender("Male")
                        .hometown("London, UK")
                .build());
        setInfoDoctor(user3,doctor1);
        doctor2 = doctorRepository.save(Doctor.builder()
                .name("Jummai")
                .surname("Dai")
                        .image("https://thumbs.dreamstime.com/b/male-chinese-doctor-14540892.jpg")
                .gender("Male")
                .hometown("London, UK")
                .age("60")
                .build());
        setInfoDoctor(user4,doctor2);

//        doctor.setUser(user3);
//        user3.setFirstname(doctor.getName());
//        user3.setLastname(doctor.getSurname());
//        user3.setDoctor(doctor);

        people = peopleRepository.save(People.builder()
                .name("Harry")
                .surname("Potter")
                .age("16")
                .gender("Male")
                .status("3rd dose")
                .hometown("London, UK")
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

        people.getCommentList().add(comment);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                        .number(1)
                        .date("31-07-2021")
                .build());

        people.getVaccineList().add(vaccine);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                .number(2)
                .date("21-08-2021")
                .build());

        people.getVaccineList().add(vaccine);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                .number(3)
                .date("29-01-2022")
                .build());

        people.getVaccineList().add(vaccine);
        setInfoPeople(user2,people);
        doctor1.getPeopleList().add(people);
        people.setDoctor(doctor1);
//        user2.setPeople(people);
//        user2.setFirstname(people.getName());
//        user2.setLastname(people.getSurname());
//        System.out.println(user1.getAuthorities());
        people = peopleRepository.save(People.builder()
                .name("Charlie")
                .surname("Puth")
                .age("30")
                .gender("Male")
                .status("2nd dose")
                .hometown("New Jersey, US")
                .image("https://m.media-amazon.com/images/M/MV5BMzM2MDk5ODAyNV5BMl5BanBnXkFtZTgwNzIwNjcxNzE@._V1_UY264_CR103,0,178,264_AL_.jpg")
                .build());
        comment = commentRepository.save(Comment.builder()
                .patient_name("Charlie")
                .patient_surname("Puth")
                .name("Michael Jackson")
                .comment("Drink a lot of water")
                .date("25-03-2021").build());

        people.getCommentList().add(comment);

        comment = commentRepository.save(Comment.builder()
                .patient_name("Charlie")
                .patient_surname("Puth")
                .name("Michael Jackson")
                .comment("Drink a lot of water")
                .date("25-03-2021").build());

        people.getCommentList().add(comment);


        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Moderna")
                .number(1)
                .date("04-03-2021")
                .build());

        people.getVaccineList().add(vaccine);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Moderna")
                .number(2)
                .date("25-03-2021")
                .build());

        people.getVaccineList().add(vaccine);

        setInfoPeople(user5,people);
        doctor1.getPeopleList().add(people);
        people.setDoctor(doctor1);

        people = peopleRepository.save(People.builder()
                .name("Peter")
                .surname("Parker")
                .age("20")
                .gender("Male")
                .status("2nd dose")
                .hometown("New York, US")
                .image("https://media.vanityfair.com/photos/5d178e5f2bdf8300094c7f12/2:3/w_805,h_1208,c_limit/spider-verse-tom-holland.jpg")
                .build());

        comment = commentRepository.save(Comment.builder()
                .patient_name("Peter")
                .patient_surname("Parker")
                .name("Tony Stark")
                .comment("Eat healthy and sleep early")
                .date("25-03-2021").build());

        people.getCommentList().add(comment);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                .number(1)
                .date("18-10-2021")
                .build());

        people.getVaccineList().add(vaccine);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Moderna")
                .number(2)
                .date("09-11-2021")
                .build());

        people.getVaccineList().add(vaccine);

        setInfoPeople(user6,people);
        doctor1.getPeopleList().add(people);
        people.setDoctor(doctor1);

        people = peopleRepository.save(People.builder()
                .name("Shawn")
                .surname("Mendes")
                .age("25")
                .gender("Male")
                .status("2nd dose")
                .hometown("Toronto, Canada")
                .image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXPqn-nQ6jmOEVyUz5JemqOe2b0Jt1X_T3og&usqp=CAU")
                .build());

        comment = commentRepository.save(Comment.builder()
                .patient_name("Shawn")
                .patient_surname("Mendes")
                .name("Michael Jackson")
                .comment("Drink a lot of water")
                .date("23-09-2021").build());

        people.getCommentList().add(comment);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                .number(1)
                .date("02-09-2021")
                .build());

        people.getVaccineList().add(vaccine);

        vaccine = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer")
                .number(2)
                .date("23-09-2021")
                .build());

        people.getVaccineList().add(vaccine);

        setInfoPeople(user7,people);
        doctor1.getPeopleList().add(people);
        people.setDoctor(doctor1);

        people = peopleRepository.save(People.builder()
                .name("Ariana")
                .surname("Grande")
                .age("29")
                .gender("Female")
                .status("4th dose")
                .hometown("Florida, US")
                .image("https://www.biography.com/.image/t_share/MTQ3MzM3MTcxNjA5NTkzNjQ3/ariana_grande_photo_jon_kopaloff_getty_images_465687098.jpg")
                .build());
        setInfoPeople(user8,people);
        people = peopleRepository.save(People.builder()
                .name("John")
                .surname("Legend")
                .age("43")
                .gender("Male")
                .status("1st dose")
                .hometown("Ohio, US")
                .image("https://www.ryt9.com/img/files/20200212/iqa3a8a3111a3bd971ede8df897841dee9.jpg")
                .build());
        setInfoPeople(user9,people);


    }
    User user1,user2,user3,user4,user5,user6,user7,user8,user9;
    private  void addUser(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user1 = User.builder()
                .username("admin")
                .firstname("")
                .lastname("")
                .password(encoder.encode("admin"))
                .email("admin@admin.com")
                .image("https://cdn.discordapp.com/attachments/850725114702069790/1035112042606112768/124193899_2855996104622612_5328700923059881732_n.jpg")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user2 = User.builder()
                .username("user")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user"))
                .email("user@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("doctor")
                .firstname("")
                .lastname("")
                .password(encoder.encode("doctor"))
                .email("doctor@doctor.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user4 = User.builder()
                .username("doctor2")
                .firstname("")
                .lastname("")
                .password(encoder.encode("doctor2"))
                .email("doctor2@doctor.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user5 = User.builder()
                .username("user5")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user5"))
                .email("user5@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user6 = User.builder()
                .username("user6")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user6"))
                .email("user6@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user7 = User.builder()
                .username("user7")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user7"))
                .email("user7@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user8 = User.builder()
                .username("user8")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user8"))
                .email("user8@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user9 = User.builder()
                .username("user9")
                .firstname("")
                .lastname("")
                .password(encoder.encode("user9"))
                .email("user9@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authPatient);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);

        user1.getAuthorities().add(authAdmin);
        userRepository.save(user1);

        user2.getAuthorities().add(authPatient);
        userRepository.save(user2);

        user3.getAuthorities().add(authDoctor);
        userRepository.save(user3);

        user4.getAuthorities().add(authDoctor);
        userRepository.save(user4);

        user5.getAuthorities().add(authPatient);
        userRepository.save(user5);

        user6.getAuthorities().add(authPatient);
        userRepository.save(user6);

        user7.getAuthorities().add(authPatient);
        userRepository.save(user7);

        user8.getAuthorities().add(authPatient);
        userRepository.save(user8);

        user9.getAuthorities().add(authPatient);
        userRepository.save(user9);

    }

    private void setInfoPeople(User user,People people){
        user.setAge(people.getAge());
        people.setUser(user);
        user.setGender(people.getGender());
        user.setImage(people.getImage());
        user.setHometown(people.getHometown());
        user.setPeople(people);
        user.setFirstname(people.getName());
        user.setLastname(people.getSurname());

    }
    private void setInfoAdmin(User user,Admin admin){
        admin.setUser(user);
        admin.setImage(user.getImage());
        user.setAdmin(admin);
        user.setFirstname(admin.getName());
        user.setLastname(admin.getSurname());
    }
    private void setInfoDoctor(User user,Doctor doctor){
        doctor.setUser(user);
        user.setImage(doctor.getImage());
        user.setDoctor(doctor);
        user.setFirstname(doctor.getName());
        user.setLastname(doctor.getSurname());
    }
}
