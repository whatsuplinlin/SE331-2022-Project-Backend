package se331.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.entity.People;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.User;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Page<User> getUser(Integer perPage, Integer page) {
        return userDao.getUser(perPage,page);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

//    @Override
//    public Page<People> getPeople(String name, Pageable pageable) {
//        return ;
//    }
}
