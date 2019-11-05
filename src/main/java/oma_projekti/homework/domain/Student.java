package oma_projekti.homework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	//attributes
	//id, username, password, role
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;
    
    @Column(name = "role", nullable = false)
    private String role;

	public Student() {
		super();
	}

	public Student(Long id, String username, String passwordHash, String role) {
		super();
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	//constructor without Id
	public Student(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}
    
    
    
    
    
    
}


