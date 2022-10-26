package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Admin;
import se331.rest.entity.Comment;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
