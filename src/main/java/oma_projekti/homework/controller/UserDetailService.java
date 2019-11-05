package oma_projekti.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import oma_projekti.homework.domain.Student;
import oma_projekti.homework.domain.StudentRepository;

@Service
public class UserDetailService implements UserDetailsService {
	private final StudentRepository studentRepo;

	@Autowired
	public UserDetailService(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	Student curruser = studentRepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    } 

}
