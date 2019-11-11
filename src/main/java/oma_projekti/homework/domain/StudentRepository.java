package oma_projekti.homework.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	Student findByUsername(String username);
	
}
