package org.com.cay.shop.user.service;

import org.com.cay.shop.user.entity.User;

public interface IUserService {

	User findByUsername(String username);

	void save(User user);

	User findByCode(String code);

	void update(User user);

	User login(User user);
}
