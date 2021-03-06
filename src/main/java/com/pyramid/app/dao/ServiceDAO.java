package com.pyramid.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDAO<E> {

	private static final Log log = LogFactory.getLog(ServiceDAO.class);

	@PersistenceContext
	protected EntityManager entityManager;

	public void persist(E e) throws RuntimeException {
		log.info("Persisting Entity Instance");
		try {
			entityManager.persist(e);
			log.info("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(E e) throws RuntimeException {
		log.info("Removing Entity Instance");
		try {
			entityManager.remove(e);
			log.info("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public E merge(E e) throws RuntimeException {
		log.info("Merging Entity Instance");
		try {
			E result = entityManager.merge(e);
			log.info("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public E findById(Class<E> type, Integer id) {
		log.info("Getting Entity Instance with ID: " + id);
		E instance = null;
		try {
			instance = entityManager.find(type, id);
			log.info("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println(re);
			log.error("get failed for "+type.getName(), re);
		}
		return instance;
	}

}
