package oma_projekti.homework.domain;


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
	//owner
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	
	public Homework() {
		super();
	}

	public Homework(Long homeId, String task, String deadline, Course course) {
		super();
		this.homeId = homeId;
		this.task = task;
		this.deadline = deadline;
		this.course = course;

	}
	
	//konstruktori ilman id:tä
	public Homework(String task, String deadline, Course course) {
		this.task = task;
		this.deadline = deadline;
		this.course = course;
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

	@Override
	public String toString() {
		return "Homework [homeId=" + homeId + ", task=" + task + ", deadline=" + deadline + ", course=" + course + "]";
	}
	
}
