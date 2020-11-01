package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.entity.Classes;
import com.mmit.entity.Registration;

public class RegistrationService {
	
	private EntityManager em;
	
	public RegistrationService(EntityManager em) {
		this.em = em;
	}
	
	public List<Registration> findAll() {
		
		TypedQuery<Registration> query = em.createNamedQuery("Registration.getAll", Registration.class);
		List<Registration> list = query.getResultList();
		return list;
	}

	public void save(Registration registration) {
		
		em.getTransaction().begin();
		if(registration.getId() == 0)
			em.persist(registration);
		else 
			em.merge(registration);
		em.getTransaction().commit();
		
	}

	public Registration findById(int id) {
		
		return em.find(Registration.class, id);
	}

	public void delete(int id) {
		
		em.getTransaction().begin();
		Registration item = em.find(Registration.class, id);
		em.remove(item);
		em.getTransaction().commit();
	}

}
