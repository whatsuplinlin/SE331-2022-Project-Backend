package se331.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.People;

public interface PeopleService {
    People save(People people);
    Page<People> getPeople(Integer perPage, Integer page);
    Page<People> getPeople(String name, Pageable pageable);
    People getPeople(Long id);
}
