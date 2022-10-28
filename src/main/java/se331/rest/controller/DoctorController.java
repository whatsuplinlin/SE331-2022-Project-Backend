package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.dto.PeopleDTO;
import se331.rest.entity.Comment;
import se331.rest.entity.Doctor;
import se331.rest.entity.People;
import se331.rest.repository.DoctorRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;
import se331.rest.service.DoctorService;
import se331.rest.util.LabMapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;
    @GetMapping("/doctor")
    public ResponseEntity<?> getUser(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "name" , required = false) String name) {
        perPage = perPage == null ? 4 : perPage;
        page = page == null ? 1 : page;
        Page<Doctor> pageOutput;
//        if(name==null){
        pageOutput = doctorService.getDoctor(perPage,page);
//        }else{
//            pageOutput = peopleService.getPeople(name, PageRequest.of(page-1,perPage));
//        }
//        pageOutput = peopleService.getPeople(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getDoctorDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
    @GetMapping("/doctors")
    ResponseEntity<?> getDoctors() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDto(doctorService.getAllDoctor()));
    }
    @GetMapping("doctor/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable("id") Long id) {
        Doctor output = doctorService.getDoctor(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @GetMapping("/doctor/{id}/patient")
    public ResponseEntity<?> getDoctorPatient(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getDoctor(id);
        List<People> peopleList = doctor.getPeopleList();
        return ResponseEntity.ok(LabMapper.INSTANCE.getPeopleDto(peopleList));
    }

}
