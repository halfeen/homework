package oma_projekti.homework.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Student {
	//attributes
	//id, username, password, role
	
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", nullable = false, updatable = false)
    //private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;
    
    @Column(name = "role", nullable = false)
    private String role;
    
    @OneToMany
    @JoinColumn(name="homework")
    private List<Homework> homework;

	public Student() {
		super();
	}

	public Student(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	//public void setId(Long id) {
		//this.id = id;
	//}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	//public Long getId() {
	//	return id;
	//}

	public String getUsername() {
		return username;
	}
	
	String getName() {
		String name = username;
		return name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}
    
    
    
    
    
    
}


