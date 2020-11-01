package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Classes;

public class ClassService {
	
	private EntityManager em;
	
	public ClassService(EntityManager em) {
		this.em = em;
	}
	
	public List<Classes> findAll() {
		
		TypedQuery<Classes> query = em.createNamedQuery("Classes.getAll", Classes.class);
		List<Classes> list = query.getResultList();
		return list;
	}

	public void save(Classes classes) {
		
		em.getTransaction().begin();
		if(classes.getId() == 0)
			em.persist(classes);
		else 
			em.merge(classes);
		em.getTransaction().commit();
		
	}

	public Classes findById(int id) {
		
		return em.find(Classes.class, id);
	}

	public void delete(int id) {
		
		em.getTransaction().begin();
		Classes item = em.find(Classes.class, id);
		em.remove(item);
		em.getTransaction().commit();
	}

}
