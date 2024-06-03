package com.bankingsystem.jdbc;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class AccountManager {
	private Connection con;
	private Scanner sc;
	
	public AccountManager(Connection con, Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	
	
	//for debit money
	public void debitMoney(long account_num) {
		sc.nextLine();
		System.out.print("Enter amount :");
		double amt=sc.nextDouble();
		System.out.println();
		System.out.println("Enter Security Pin :");
		String pin =sc.next();
		String query="select * from accounts where account_number=? and security_pin=?";
		try {
			
			con.setAutoCommit(false);
			if(account_num !=0) {
				PreparedStatement pst=con.prepareStatement(query);
				pst.setLong(1, account_num);
				pst.setString(2, pin);
				ResultSet rs =pst.executeQuery();
				if(rs.next()) {
					double current_balance=rs.getDouble("balance");
					if(amt<=current_balance) {
						String debitquery="update accounts set balance= balance - ? where account_number=?";
						PreparedStatement pst1=con.prepareStatement(debitquery);
						pst1.setDouble(1, amt);
						pst1.setLong(2, account_num);
						int count=pst1.executeUpdate();
						if(count>0) {
							System.out.println("Rs."+amt+" debited successfully !");
							con.commit();
							con.setAutoCommit(true);
							return;
						}
						else {
							System.out.println("Transaction failed");
							con.rollback();
							con.setAutoCommit(true);
							
						}
					}
					else {
						System.out.println("Insufficiant balance !!");
					}
				}
					else{
						System.out.println("Invalid Pin !!");
					}
				}
			else {
				System.out.println("Invalid account number");
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
				
			}
		
	}
	
	//for credit money
	public void creditMoney(long account_num) {
		sc.nextLine();
		System.out.print("Enter amount :");
		double amt=sc.nextDouble();
		System.out.println("Enter Security Pin :");
		String pin =sc.next();
		String query="select * from accounts where account_number=? and security_pin=?";
		try {
			
			con.setAutoCommit(false);
			if(account_num !=0) {
				PreparedStatement pst=con.prepareStatement(query);
				pst.setLong(1, account_num);
				pst.setString(2, pin);
				ResultSet rs =pst.executeQuery();
				if(rs.next()) {
					//double current_balance=rs.getDouble("balance");
					
						String creditquery="update accounts set balance= balance + ? where account_number=?";
						PreparedStatement pst1=con.prepareStatement(creditquery);
						pst1.setDouble(1, amt);
						pst1.setLong(2, account_num);
						int count=pst1.executeUpdate();
						if(count>0) {
							System.out.println("Rs."+amt+" credited successfully !");
							con.commit();
							con.setAutoCommit(true);
							return;
						}
						else {
							System.out.println("Transaction failed");
							con.rollback();
							con.setAutoCommit(true);
							
						}
					}
					
					else{
						System.out.println("Invalid Pin !!");
					}
				}
			else {
				System.out.println("Invalid account number");
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
				
			}
	}
	
	//for transfer money
	
	public void transferMoney(long sender_account_num) {
		sc.nextLine();
		System.out.print("Enter receiver account number :");
		long receiver_account_num=sc.nextLong();
		System.out.print("Enter amount :");
		double amt=sc.nextDouble();
		System.out.println("Enter Security Pin :");
		String pin =sc.next();
		String query="select * from accounts where account_number=? and security_pin=?";
		try {
			
			con.setAutoCommit(false);
			if(sender_account_num !=0 && receiver_account_num !=0) {
				PreparedStatement pst=con.prepareStatement(query);
				pst.setLong(1, sender_account_num);
				pst.setString(2, pin);
				ResultSet rs =pst.executeQuery();
				if(rs.next()) {
					double current_balance=rs.getDouble("balance");
					if(amt<=current_balance) {
						String debitquery="update accounts set balance= balance - ? where account_number=?";
						String creditquery="update accounts set balance= balance + ? where account_number=?";

						PreparedStatement pst1=con.prepareStatement(debitquery);
						pst1.setDouble(1, amt);
						pst1.setLong(2, sender_account_num);
						PreparedStatement pst2=con.prepareStatement(creditquery);
						pst2.setDouble(1, amt);
						pst2.setLong(2, receiver_account_num);
						int count1=pst1.executeUpdate();
						int count2=pst2.executeUpdate();
						if(count1>0 && count2>0) {
							System.out.println("Rs."+amt+" transferred successfully in account number");
							con.commit();
							con.setAutoCommit(true);
							return;
						}
						else {
							System.out.println("Transaction failed");
							con.rollback();
							con.setAutoCommit(true);
							
						}
					}
					else {
						System.out.println("Insufficiant balance !!");
					}
				}
					else{
						System.out.println("Invalid Pin !!");
					}
				}
			else {
				System.out.println("Invalid account number");
			}
		}
		
			catch(SQLException e) {
				e.printStackTrace();
				
			}
	
		
		
		
	}
	
	
	//for check balance
	public void getBalance(long account_num) {
		sc.nextLine();
		String get_b_query="select balance from accounts where account_number =? and security_pin=?";
		System.out.print("ENTER SECURITY PIN :");
		String pin=sc.nextLine();
		try {
			PreparedStatement pst=con.prepareStatement(get_b_query);
			pst.setLong(1, account_num);
			pst.setString(2, pin);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				double balance=rs.getDouble("balance");
				System.out.println("Balance :"+balance);
				
				
			}
			else {
				System.out.print("INVALID PIN !! ");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	
	   
}
