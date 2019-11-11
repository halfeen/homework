package oma_projekti.homework.domain;

import java.security.Principal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HomeworkRepository extends CrudRepository<Homework, Long>{
	

	 List<Homework> findByOwner(String owner);
	 
	 /*public static String setOwnerRepo(String owner, Principal principal) {
		 String username = principal.getName();
		 owner = username;
		 return username;
	 }*/
	 
	 //Homework nameTheOwner(String owner);
	
}
