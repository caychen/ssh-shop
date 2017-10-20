package org.com.cay.shop.user.dao.impl;

import org.com.cay.shop.user.dao.IUserDao;
import org.com.cay.shop.user.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		return (User) DataAccessUtils.singleResult(sessionFactory.getCurrentSession()
				.createQuery("from User where username = ?").setParameter(0, username).getResultList());
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return (User) DataAccessUtils.singleResult(sessionFactory.getCurrentSession()
				.createQuery("from User where code = ?").setParameter(0, code).getResultList());
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return (User) DataAccessUtils.singleResult(sessionFactory.getCurrentSession()
				.createQuery("from User where username = ? and password = ? and state = 1")
				.setParameter(0, user.getUsername()).setParameter(1, user.getPassword()).getResultList());
	}
}
