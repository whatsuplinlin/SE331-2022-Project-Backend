package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.entity.Doctor;
import se331.rest.repository.DoctorRepository;

public class DoctorDaoImpl implements DoctorDao{
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
