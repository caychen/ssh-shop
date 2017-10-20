package org.com.cay.shop.category.dao.impl;

import java.util.List;

import org.com.cay.shop.category.dao.ICategoryDao;
import org.com.cay.shop.category.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return (List<Category>) sessionFactory.getCurrentSession().createQuery("from Category").getResultList();
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Category.class, cid);
	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(category);
	}
}
