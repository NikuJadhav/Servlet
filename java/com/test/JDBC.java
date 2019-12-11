package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.utils.ConnectionUtils;
import com.utils.EmailUtils;

public class JDBC {

	public static void main(String[] args) {
		try {

			Connection con = ConnectionUtils.getConnection(); // 3.Create Statement
			Statement st = con.createStatement();
			// 4.Excute Query
			ResultSet rs = st.executeQuery("select * from emp");
			while (rs.next()) {
				System.out
						.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "  " + rs.getString(4));
				EmailUtils.send(rs.getString(4).toString(), "Why not present ?", "Bhai class me q nahi aayee?");
			}
			// 5.Close The Connection
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
