package se331.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Doctor;
import se331.rest.entity.People;

import javax.print.Doc;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
