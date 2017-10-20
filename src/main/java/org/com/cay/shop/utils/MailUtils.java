package org.com.cay.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String to, String code) {
		try {

			Properties props = new Properties();
			props.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(props.getProperty("mail.from.email"), props.getProperty("mail.from.password"));
				}
			});
			session.setDebug(true);// 启用调试模式,可以在控制台输出smtp协议的应答过程

			// 创建一个MimeMessage格式的邮件
			MimeMessage message = new MimeMessage(session);

			// 设置发送邮件的地址
			message.setFrom(new InternetAddress(props.getProperty("mail.from.email")));

			// 设置接收邮件的地址
			message.addRecipient(RecipientType.TO, new InternetAddress(to));

			// 设置邮件的主题
			message.setSubject("欢迎注册网上商城，请先激活后即可登录购物！");
			// 设置邮件的内容
			message.setText(
					"<h2>购物商城官方激活邮件！点击下面链接完成激活操作！<a href='http://192.168.1.104:8080/shop/user_active.action?code="
							+ code + "'>http://192.168.1.104:8080/shop/user_active.action?code=" + code + "</a><h2>");

			// 保存邮件
			message.saveChanges();

			// 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		sendMail("412425870@qq.com", "111111111111111");
	}
}
