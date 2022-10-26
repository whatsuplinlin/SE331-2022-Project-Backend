package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.dao.DoctorDao;
import se331.rest.entity.Doctor;

public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorDao doctorDao;
    @Override
    public Doctor save(Doctor doctor) {
        return doctorDao.save(doctor);
    }
}
