package oma_projekti.homework.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//this class will be public for everyone to see

@Entity
public class Course {
	
	//attributes
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long courseId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="course")
	private List<Homework> homeworks;
	
	
	public Course() {
		super();
	}

	//konstruktori ilman id:tä
	public Course(String name) {
		super();
		this.name = name;
	}
	
	public Course(Long couId, String name) {
		super();
		this.courseId = couId;
		this.name = name;
	}


	public Long getCourseId() {
		return courseId;
	}


	public String getName() {
		return name;
	}


	public void setCourseId(Long couId) {
		this.courseId = couId;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Course [couId=" + courseId + ", name=" + name + "]";
	}
	
	
	
	
	

}
