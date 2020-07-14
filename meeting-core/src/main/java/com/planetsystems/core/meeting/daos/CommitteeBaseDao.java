package com.planetsystems.core.meeting.daos;

import java.util.List;
import com.googlecode.genericdao.dao.jpa.GenericDAO;

public interface CommitteeBaseDao<T> extends GenericDAO<T, String> {

	public T findOne(String id);

	public void create(T entity);

	public T update(T entity);

	public T[] update(T[] entities);

	public void delete(T entity);

	public void deleteById(String entityId);

	public List<T> find();

}
