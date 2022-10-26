package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.dao.AdminDao;
import se331.rest.entity.Admin;

public class AdminServiceImpl implements  AdminService{
    @Autowired
    AdminDao adminDao;
    @Override
    public Admin save(Admin admin) {
        return adminDao.save(admin);
    }
}
