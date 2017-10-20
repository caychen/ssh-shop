package org.com.cay.shop.adminuser.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.adminuser.entity.AdminUser;
import org.com.cay.shop.adminuser.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	@Autowired
	private IAdminUserService adminUserService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AdminUser model = new AdminUser();

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String login() {
		AdminUser existAdminUser = adminUserService.login(model);
		if (existAdminUser == null) {
			// 登录失败
			ServletActionContext.getContext().getValueStack().set("error_msg", "亲，您的用户名或密码错误，请重试！");
			return "loginFail";
		} else {
			// 登录成功
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", existAdminUser);
			return "loginSuccess";
		}

	}

	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("loginUser", null);
		session.invalidate();
		return "login";
	}

}
