package oma_projekti.homework.domain;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Homework {
	
	//attributes
	//id, task, deadline, course, owner
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long homeId;
	private String task;
	private String deadline;
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)...
	private String owner;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	
	public Homework() {
		super();
	}

	public Homework(Long homeId, String task, String deadline, Course course, String owner) {
		super();
		this.homeId = homeId;
		this.task = task;
		this.deadline = deadline;
		this.course = course;
		this.owner = owner;

	}
	
	//konstruktori ilman id:tä
	public Homework(String task, String deadline, Course course, String owner) {
		this.task = task;
		this.deadline = deadline;
		this.course = course;
		this.owner = owner;
	}

	public Long getHomeId() {
		return homeId;
	}

	public String getTask() {
		return task;
	}

	public String getDeadline() {
		return deadline;
	}

	public Course getCourse() {
		return course;
	}
	
	public String getOwner() {
		return owner;
	}


	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	public void setTask(String task) {
		this.task = task; 
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
		
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	//lisää yritystä lisätä käyttäjä uuteen läksyyn 
	public void setLoggedOwner1(String owner, Principal principal) {
		String username = principal.getName();
		this.owner = username;
	}
	
	//osa yhdestä yrityksestä lisätä käyttäjä uuteen läksyyn...
	public String getUsername(Principal principal) {
		String username = principal.getName();
		return username;
	}
	
	//public Homework(String task, String deadline, Course course, String username) {
	//	this.task = task;
	//	this.deadline = deadline;
	//	this.course = course;
	//	this.owner = username;
	//}
	
	public void setLoggedOwner2(String username) {
		this.owner = username;
	}
	

	
	@Override
	public String toString() {
		return "Homework [homeId=" + homeId + ", task=" + task + ", deadline=" + deadline + ", course=" + course + ", student="+ owner + "]";
	}
	
}
