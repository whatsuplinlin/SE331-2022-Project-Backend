package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.entity.Admin;
import se331.rest.entity.Doctor;
import se331.rest.repository.AdminRepository;
import se331.rest.repository.DoctorRepository;

public interface DoctorDao {
    Doctor save(Doctor doctor);
}
