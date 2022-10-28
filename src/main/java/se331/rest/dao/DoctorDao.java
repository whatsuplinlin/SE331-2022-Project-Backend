package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Admin;
import se331.rest.entity.Doctor;
import se331.rest.repository.AdminRepository;
import se331.rest.repository.DoctorRepository;

public interface DoctorDao {
    Doctor save(Doctor doctor);
    Page<Doctor> getDoctor(Integer perPage, Integer page);
    Doctor getDoctor(Long id);

    Page<Doctor>getDoctor(Pageable pageRequest);

}
