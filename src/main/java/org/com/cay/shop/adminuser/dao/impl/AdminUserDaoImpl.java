package org.com.cay.shop.adminuser.dao.impl;

import org.com.cay.shop.adminuser.dao.IAdminUserDao;
import org.com.cay.shop.adminuser.entity.AdminUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserDaoImpl implements IAdminUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return (AdminUser) DataAccessUtils.singleResult(sessionFactory.getCurrentSession()
				.createQuery("from AdminUser where username = ? and password = ?")
				.setParameter(0, adminUser.getUsername())
				.setParameter(1, adminUser.getPassword())
				.getResultList());
	}
}
