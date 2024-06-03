package com.bankingsystem.jdbc;
import java.sql.*;
import java.util.Scanner;
public class BankingApp {
	private static final String driver_class="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/bankingsystem";
	private static final String username="root";
	private static final String pass="";
	public static void main(String[] args) throws ClassNotFoundException , SQLException,InterruptedException{
		try {
			Class.forName(driver_class);
			Connection con = DriverManager.getConnection(url,username,pass);
			Scanner sc=new Scanner(System.in);
			User user= new User(con,sc);
			Accounts accounts=new Accounts(con,sc);
			AccountManager amanager=new AccountManager(con,sc);
			
			String email;
			long account_number;
			
			while(true) {
				System.out.println("***WELCOME TO THE BANKING SYSTEM***");	
				System.out.println();
				System.out.println("1. REGISTER  ");
				System.out.println("2. LOGIN");
				System.out.println("3. EXIT");
				System.out.println("ENTER YOUR CHOICE :");
				int choice1=sc.nextInt();
				switch(choice1) {
				case 1 : user.register();
//				       System.out.print("\003[H003[2J");
//				       System.out.flush();
				        break;
				case 2 : email=user.login();
				      if(email !=null) {
				    	  System.out.println();
				    	  System.out.println("USER LOGGED IN :");
				    	 if(  !accounts.accountExist(email)) {
				    		 System.out.println();
				    		 System.out.println("1. OPEN A NEW ACCOUNT");
				    		 System.out.println("2. EXIT");
				    		 System.out.println("ENTER YOUR CHOICE :");
				    		 if(sc.nextInt()==1) {
				    			 account_number=accounts.openAccount(email);
				    			 System.out.println("ACCOUNT CREATION SUCCESSFULLY");
				    			 System.out.println("YOUR ACCOUNT NUMBER IS :"+account_number);}
				    			 else {
				    				 break;
				    			 }
				    		 }
				    	 account_number=accounts.getAccountNumber(email);
				    	 int choice2=0;
				    	 while(choice2 !=5) {
				    		 System.out.println();
				    		 System.out.println("1. DEBIT MONEY");
				    		 System.out.println("2. CREDIT MONEY");
				    		 System.out.println("3. TRANSFER MONEY");
				    		 System.out.println("4. CHECK BALANCE");
				    		 System.out.println("5. LOG OUT");
				    		 System.out.println("ENTER YOUR CHOICE");
				    		 choice2=sc.nextInt();
				    		 switch(choice2) {
				    		 case 1 : amanager.debitMoney(account_number);
				    		 break;
				    		 case 2 : amanager.creditMoney(account_number);
				    		 break;
				    		 case 3 : amanager.transferMoney(account_number);
				    		 break;
				    		 case 4 : amanager.getBalance(account_number);
				    		 break;
				    		 case 5 : break;
				    		 default : System.out.println("ENTER VALID CHOICE");
				    		 break;
				    		 
				    		 }
				    		 
									
				    	 }
				    	 
				    		 
				    	 }
				      else {
				    	  System.out.println("INCORRECT EMAIL OR PASSWORD");
				    	  
				      }
				    	  
				      
				      //break;
				case 3 : System.out.print("Existing system.");
				   int i=5;
				   while(i!=0) {
					   System.out.print(".");
					   Thread.sleep(450);
					   i--;
					   
				   }
				   System.out.println();
					
					System.out.println("THANKYOU ! FOR USING BANKING SYSTEM !!");  
				 System.out.println();
				 return;
			
				 
				
			default : System.out.println("ENTER VALID CHOICE !!");
			break;
				
			}
				
			}
			
		}
		
			
			
			
		
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		

	}

}
