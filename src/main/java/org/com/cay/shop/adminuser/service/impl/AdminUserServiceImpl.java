package org.com.cay.shop.adminuser.service.impl;

import org.com.cay.shop.adminuser.dao.IAdminUserDao;
import org.com.cay.shop.adminuser.entity.AdminUser;
import org.com.cay.shop.adminuser.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AdminUserServiceImpl implements IAdminUserService {

	@Autowired
	private IAdminUserDao adminUserDao;

	@Override
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}
}
