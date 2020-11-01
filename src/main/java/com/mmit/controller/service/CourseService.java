package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Course;

public class CourseService {
	private EntityManager em;

	public CourseService(EntityManager em) {
		super();
		this.em = em;
	}

	public List<Course> findAll() {
		TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
		List<Course> courses = query.getResultList();
		courses.forEach(c->{
			System.out.println(c.getFees() + "\t" + c.getDuration());
		});
		return courses;
	}

	public Course findById(int id) {
		return em.find(Course.class, id);
	}

	public void save(Course c) {
		em.getTransaction().begin();
		if(c.getId() == 0) 
			em.persist(c);
		else 
			em.merge(c);
		em.getTransaction().commit();
		
	}

	public void delete(int id) {
		em.getTransaction().begin();
		Course course = em.find(Course.class, id);
		em.remove(course);
		em.getTransaction().commit();
	}
	
}
