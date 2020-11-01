package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Course;
import com.mmit.entity.Student;

public class StudentService {
	private EntityManager em;

	public StudentService(EntityManager em) {
		super();
		this.em = em;
	}

	public List<Student> findAll() {
		TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
		List<Student> students = query.getResultList();
		return students;
	}

	public Student findById(int id) {
		return em.find(Student.class, id);
	}

	public void save(Student s) {
		em.getTransaction().begin();
		if(s.getId() == 0) 
			em.persist(s);
		else 
			em.merge(s);
		em.getTransaction().commit();
		
	}

	public void delete(int id) {
		em.getTransaction().begin();
		Student student = em.find(Student.class, id);
		em.remove(student);
		em.getTransaction().commit();
	}
	
}
