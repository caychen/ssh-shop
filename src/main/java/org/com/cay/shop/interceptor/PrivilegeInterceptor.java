package org.com.cay.shop.interceptor;

import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.adminuser.entity.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//判断session中是否保存了后台登录的用户
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(adminUser == null){
			//未登录
			
			return "login";
		}else{
			//已登录
			//放行
			return invocation.invoke();
		}
	}

}
