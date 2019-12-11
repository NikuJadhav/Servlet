package com.utils;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
	public static void send(String to, String sub, String msg) {
		// Get properties object
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream resourceStream = loader.getResourceAsStream("config.properties");
			final Properties properties = new Properties();
			properties.load(resourceStream);
			System.out.println(properties.getProperty("mail"));
			System.out.println(properties.getProperty("pass"));
			// get Session
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(properties.getProperty("mail"), properties.getProperty("pass"));
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(sub);
				message.setText(msg);
				// send message
				Transport.send(message);
				System.out.println("message sent successfully");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error While mail sent");
		}
	}
}
