package com.planetsystems.core.meeting.daos.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import com.planetsystems.core.meeting.daos.CommitteeBaseDao;

@Repository("CommitteeBaseDao")
public class CommitteeBaseDaoImpl<T> extends GenericDAOImpl<T, String> implements CommitteeBaseDao<T> {

	@SuppressWarnings("unused")
	private EntityManager entityManager;

	@Autowired
	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	@Autowired
	public void setSearchProcessor(JPASearchProcessor searchProcessor) {
		super.setSearchProcessor(searchProcessor);
	}

	public T findOne(String id) {
		return super.find(id);
	}

	public List<T> find() {
		Search search = new Search();
		search.copy();
		return search(search);
	}

	public void create(T entity) {
		super.save(entity);
	}

	public T update(T entity) {
		return super.save(entity);
	}

	public void delete(T entity) {
		super.remove(entity);
	}

	public void deleteById(String entityId) {
		super.removeById(entityId);
	}

	public T[] update(T[] entities) {
		return super.save(entities);
	}

}
