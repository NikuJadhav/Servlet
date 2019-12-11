package com.controller;

import com.utils.ConnectionUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;


//@WebSe-rvlet(value = "/Login");
public class Login<HttpServletResponse, HttpServletRequest> extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws Exception, IOException
	{

		// get data
		String EMAIL =  request.getParameter("email");
		String PASSWORD =  request.getParameter("password");
		System.out.println(EMAIL);
		System.out.println(PASSWORD);

		// process data
		Connection con = ConnectionUtils.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM login where email=? and password=?");
			ps.setString(1, EMAIL);
			ps.setString(2, PASSWORD);

			ResultSet rs = ps.executeQuery();
			boolean datafound = rs.next();

			if (datafound == true) {
				System.out.println("Valid User");
				RequestDispatcher rd =  request.getRequestDispatcher("dashboard.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("Invalid User");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// navigate data

	}

}
