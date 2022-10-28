package se331.rest.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.People;
import se331.rest.security.entity.User;
import se331.rest.security.repository.UserRepository;

@Profile("db")
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<User> getUser(Integer pageSize, Integer page) {
        return userRepository.findAll(PageRequest.of(page-1,pageSize));
    }
}
