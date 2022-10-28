package se331.rest.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.People;
import se331.rest.security.entity.User;

public interface UserService {
    User save(User user);
    Page<User> getUser(Integer perPage, Integer page);
    User getUser(Long id);
//    Page<People> getPeople(String name, Pageable pageable);

}
