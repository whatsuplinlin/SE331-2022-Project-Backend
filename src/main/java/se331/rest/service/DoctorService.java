package se331.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Admin;
import se331.rest.entity.Doctor;
import se331.rest.entity.People;

import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);

    Page<Doctor> getDoctor(Integer perPage, Integer page);

    List<Doctor> getAllDoctor();

    Doctor getDoctor(Long id);
}

