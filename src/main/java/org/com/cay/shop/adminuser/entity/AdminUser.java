package org.com.cay.shop.adminuser.entity;

import java.io.Serializable;

/**
 * 后台用户管理
 * 
 * @author Cay
 *
 */
public class AdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uid;
	private String username;
	private String password;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
