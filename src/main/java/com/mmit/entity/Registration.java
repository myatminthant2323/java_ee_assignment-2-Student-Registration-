package com.mmit.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import static javax.persistence.TemporalType.DATE;

/**
 * Entity implementation class for Entity: Registration
 *
 */
@Entity
@NamedQuery(name = "Registration.getAll" , query = "SELECT r FROM Registration r")
public class Registration implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reg_id")
	private int id;
	private LocalDate registrationDate;
	private int paidAmount;
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id")
	private Student student;
	@ManyToOne(optional = false)
	@JoinColumn(name = "class_id", referencedColumnName = "class_id")
	private Classes classes;
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Registration() {
		super();
	}
   
}
