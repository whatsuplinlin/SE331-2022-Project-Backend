package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Doctor;
import se331.rest.repository.DoctorRepository;
@Repository
public class DoctorDaoImpl implements DoctorDao{
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> getDoctor(Integer perPage, Integer page) {
        return doctorRepository.findAll(PageRequest.of(page-1,perPage));
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Doctor> getDoctor(Pageable pageRequest) {
        return doctorRepository.findAll(pageRequest);
    }


}
