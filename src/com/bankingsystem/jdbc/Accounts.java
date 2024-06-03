package com.bankingsystem.jdbc;

import java.util.Scanner;
import java.sql.*;

public class Accounts {
	private Connection con;
	private Scanner sc;
	
	public Accounts(Connection con, Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	
	public boolean accountExist(String email) {
		String query="select account_number from accounts where email=?";
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
	
	
	public long openAccount(String email) {
		if(!accountExist(email)) {
			
		String open_acc_query="insert into accounts(account_number,full_name,"
				+ "email,balance,security_pin) values(?,?,?,?,?)";
		sc.nextLine();
		System.out.print("ENTER FULL NAME :");
		String name=sc.nextLine();
		System.out.print("Enter Intial Amount :");
		double balance=sc.nextDouble();
		sc.nextLine();
		System.out.print("ENTER SECURITY PIN HERE :");
		String PIN=sc.nextLine();
		try {
			long account_num = generateAccountNumber();
			PreparedStatement pst=con.prepareStatement(open_acc_query);
			pst.setLong(1,account_num);
			pst.setString(2,name);
			pst.setString(3, email);
			pst.setDouble(4,balance);
			pst.setString(5,PIN);
			int count=pst.executeUpdate();
			if(count>0) {
				return account_num;
			}
			else {
				System.out.println("ACCOUNT CREATION FAILED !!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		}
		throw new RuntimeException("ACCOUNT ALREADY exist !!");
		
		
	}
	
	private long generateAccountNumber() {
		String query="SELECT account_number FROM accounts ORDER BY account_number DESC LIMIT 1";
		try {
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next()) {
				long last_account_num =rs.getLong("account_number");
				return last_account_num+1;
				
			}
			else {
				return 10000100;
				
			}
				
			}
		catch(SQLException e) {
		e.printStackTrace();}
		return 10000100;
			
	}
	
	public long getAccountNumber(String email) {
		String query="select account_number from accounts where email=?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,email);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getLong("account_number");
			}
			
				
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Account number does'nt exist !!");
		
	}
	
	
	

}
