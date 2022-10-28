package se331.rest.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.People;
import se331.rest.security.entity.User;

public interface UserDao {
    User save(User user);
    User getUser(Long id);
    Page<User> getUser(Integer pageSize, Integer page);


//    Page<People> getPeople(String name, Pageable pageable);
}
