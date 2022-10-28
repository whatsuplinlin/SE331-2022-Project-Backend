package se331.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.People;

public interface PeopleDao {
    People save (People people);
    People getPeople(Long id);
    Page<People> getPeople(Integer pageSize, Integer page);
    Page<People> getPeople(String name,Pageable page);
}
