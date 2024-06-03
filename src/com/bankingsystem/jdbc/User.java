package com.bankingsystem.jdbc;
import java.util.*;
import java.sql.*;
public class User {
	private Connection con;
	private Scanner sc;
	
	public User(Connection con, Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	
	public void register() {
		sc.nextLine();
		System.out.print("Full Name :");
		String full_name=sc.nextLine();
		System.out.print("EMAIL :");
		String email=sc.nextLine();
		System.out.print("PASSWORD :");
		String pass=sc.nextLine();
		if(user_exist(email)) {
			System.out.println("User already registered for this email or "
					+ "already exist for this email address please use another email :");
			return ;
			
		}
		String reg_query="insert into user1(full_name,email,pass) values(?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(reg_query);
			pst.setString(1,full_name);
			pst.setString(2, email);
			pst.setString(3, pass);
			int count=pst.executeUpdate();
			if(count>0) {
				System.out.println("REGISTRATION SUCCESSFULL !!");
				
			}
			else {
				System.out.println("REGISTRATION FAILED !!");
				
			}
			
		}
		
		catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	
	public String login() {
		sc.nextLine();
		System.out.print("ENTER EMAIL :");
		String email = sc.nextLine();
		System.out.print("ENTER PASSWORD :");
		String PASS = sc.nextLine();
		
		String login_query="select * from user1 where email=? and pass=?";
		try {
			PreparedStatement pst=con.prepareStatement(login_query);
			pst.setString(1, email);
			pst.setString(2, PASS);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return email;
			}
			else {
				return null;
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	
		
		
	}
	public boolean user_exist(String email) {
		String query="select * from user1 where email=?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,email);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

}
