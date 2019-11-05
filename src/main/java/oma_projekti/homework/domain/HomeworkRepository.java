package oma_projekti.homework.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HomeworkRepository extends CrudRepository<Homework, Long>{
	
	List<Homework> findByTask(String task);
	
}
