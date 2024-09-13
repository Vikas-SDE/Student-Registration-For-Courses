package in.ashokit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import in.ashokit.entity.Student;

@Component
public interface StudentRepo extends JpaRepository<Student,Integer>{

	
	Optional<Student>findByEmail(String email);
}
