package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.entity.Admin;
import se331.rest.repository.AdminRepository;

public class AdminDaoImpl implements AdminDao{
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
