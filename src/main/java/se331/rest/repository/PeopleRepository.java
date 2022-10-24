package se331.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.People;

public interface PeopleRepository extends JpaRepository<People,Long> {
 Page<People>findByNameContaining(String name, Pageable pageRequest);
}
