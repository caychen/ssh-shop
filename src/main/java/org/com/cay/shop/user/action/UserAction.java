package org.com.cay.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.user.entity.User;
import org.com.cay.shop.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User model = new User();
	
	private String checkcode;	

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Autowired
	private IUserService userService;

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	// 跳转到注册页面
	public String registerPage() {
		return "registerPage";
	}

	public String loginPage() {
		return "loginPage";
	}

	// 查找用户名是否已经存在
	public String findByName() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user = userService.findByUsername(model.getUsername());

		if (user != null) {
			response.getWriter().write("<font color='red'>用户名已经存在！</font>");
		} else {
			response.getWriter().write("<font color='green'>用户名可以使用！</font>");
		}
		return NONE;
	}

	// 注册
	public String register() {
		
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		
		if(!code.equalsIgnoreCase(checkcode)){
			this.addActionError("验证码有误!");
			return "input";
		}
		
		userService.save(model);
		ActionContext.getContext().getValueStack().set("message", "注册成功，请去邮箱进行激活！");
		return "msg";
	}

	// 激活用户
	public String active() {
		// 根据激活码查询用户
		User user = userService.findByCode(model.getCode());
		if (user == null) {
			ActionContext.getContext().getValueStack().set("message", "激活失败：激活码错误！");
		} else {
			user.setState(1);// 激活成功
			user.setCode(null);// 将激活码清空

			userService.update(user);
			ActionContext.getContext().getValueStack().set("message", "激活成功！");
		}
		return "msg";
	}

	// 登录
	public String login() {
		User user = userService.login(model);
		if (user == null) {
			// 登录失败
			return "loginPage";
		} else {
			// 登录成功
			// 将用户信息存到session
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "loginSuccess";
		}
	}

	public String logout() {
		// 使session失效
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}

}
