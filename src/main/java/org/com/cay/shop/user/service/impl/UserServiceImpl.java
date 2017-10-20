package org.com.cay.shop.user.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.com.cay.shop.user.dao.IUserDao;
import org.com.cay.shop.user.entity.User;
import org.com.cay.shop.user.service.IUserService;
import org.com.cay.shop.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Value("${mail.from.email}")
	private String fromEmail;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Transactional(readOnly=false)
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setState(0);// 0：未激活，1：已激活
		user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());// 激活码
		userDao.save(user);

		// 启动一个线程来发送邮件用于激活邮件
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MimeMessage message = mailSender.createMimeMessage();

				try {
					MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
					helper.setFrom(fromEmail);
					helper.setTo(user.getEmail());
					helper.setSubject("欢迎注册网上商城，请先激活后即可登录购物！");
					helper.setText(
							"<h2>购物商城官方激活邮件！点击下面链接完成激活操作！<h2><h3><a href='http://192.168.1.104:8080/shop/user_active.action?code="
									+ user.getCode() + "'>http://192.168.1.104:8080/shop/user_active.action?code="
									+ user.getCode() + "</a></h3>",
							true);

					mailSender.send(message);

				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}

	@Override
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

}
