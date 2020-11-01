package com.mmit.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.TemporalType.DATE;

/**
 * Entity implementation class for Entity: Classes
 *
 */
@Entity
@NamedQuery(name="Classes.getAll",query="SELECT c FROM Classes c")
public class Classes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private int id;
	private String name;
	private LocalDate start_date;
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", referencedColumnName = "course_id")
	private Course course;
	@OneToMany(mappedBy = "classes", cascade = REMOVE)
	private List<Registration> registration;
	
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Registration> getRegistration() {
		return registration;
	}

	public void setRegistration(List<Registration> registration) {
		this.registration = registration;
	}
	
	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Classes() {
		super();
	}
   
}
