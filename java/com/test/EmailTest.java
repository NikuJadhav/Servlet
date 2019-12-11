package com.test;

import java.io.IOException;

import com.utils.EmailUtils;

public class EmailTest {

	public static void main(String[] args) {
		// from,password,to,subject,message

		EmailUtils.send("nikitajadhav2205@gmail.com", "test mail", "Hello How r u?");
		// change from, password and to
	}
}
