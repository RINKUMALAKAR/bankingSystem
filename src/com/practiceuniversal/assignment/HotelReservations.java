package com.practiceuniversal.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelReservations {
	
		private static final String url ="jdbc:mysql://localhost:3306/hotel_database";
		private static final String username="root";
		private static final String pass ="";

		public static void main(String[] args) throws Exception {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				Connection connection =DriverManager.getConnection(url,username,pass);
				while(true) {
					System.out.println();
					System.out.println("HOTEL MANAGEMENT SYSTEM");
					Scanner sc=new Scanner(System.in);
					System.out.println("1. Reserve a room");
					System.out.println("2. View Reservation");
					System.out.println("3. Get a room");
					System.out.println("4. Upadate Reservation");
					System.out.println("5. Delete Reservation");
					System.out.println("0. Exit");
					System.out.println("Choose an Option");
					int choice=sc.nextInt();
					switch(choice) {
					case 1 : reserveRoom(connection , sc);
					    break;
					case 2 : viewReservations(connection);
					break;
					case 3 : getRoomNumber(connection,sc);
					break;
					case 4 : updateReservation(connection,sc);
					break;
					case 5: deleteReservation(connection,sc);
					break;
					case 0: exit();
					sc.close();
					return;
					default : System.out.println("Invalid Choice . Try again.");}
					
					
						
					}
					

					
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
					
				}
				catch(InterruptedException e) {
					//System.out.println(e.getMessage());
					throw new RuntimeException(e);
				}
				
				
				
			}
		       
			   private static void reserveRoom(Connection connection,Scanner sc) {
				   try {
					   System.out.println("Enter Guest name :");
					   String guestName=sc.next();
					   sc.nextLine();
					   System.out.println("Enter Room no :");
					   int RoomNo=sc.nextInt();
					   System.out.println("Enter contact number : ");
					   String contactNo=sc.next();
					   
					   String sql ="insert into reservations(guest_name,room_no,contact_no) values"
					   		+ "(?,?,?)";
					   
					   try(PreparedStatement s=connection.prepareStatement(sql)){
						   s.setString(1, guestName);
						   s.setInt(2, RoomNo);
						   s.setString(3, contactNo);
						   int count =s.executeUpdate();
						   if(count>0) {
							   System.out.println("Reservation succesfull");
							   
						   }
						   else {
							   System.out.println("Reservation Failed");
						   }
						   
						   
					   }
					   catch(SQLException e) {
						   System.out.println(e.getMessage());
					   }
				   }
				   catch(Exception e) {
					   e.printStackTrace();
				   }
				   
		}
			   private static void viewReservations(Connection connection)throws Exception {
				   String sql="select * from reservations";
				   try(Statement s=connection.createStatement();
					ResultSet rs=s.executeQuery(sql)){
					   System.out.println("Current reservations :");
					   System.out.println("Reservation_id       guest           room_no       contact_no          reservation_date ");
					   while(rs.next()) {
						   int rid=rs.getInt(1);
						   System.out.println(rid);
						   String gName=rs.getString("guest_name");
						   System.out.print("                  "+gName);
						   int rNo=rs.getInt(3);
						   System.out.print("         "+rNo);
						   String cNo=rs.getString("contact_no");
						   System.out.print("         "+cNo);
						   String rDate=rs.getTimestamp("reservation_date").toString();
						   System.out.println("      "+rDate);
						   
					   }
					   System.out.println("+-----------------+--------------------+-----------------+-----------------+--------------+");
					   
					   
				   }
				   catch(Exception e) {
					   e.printStackTrace();
				   }
				   
				   
				   
			   }
			   
			   private static void getRoomNumber(Connection connection,Scanner sc)throws Exception {
				   try {
					   System.out.println("Enter reservation id :");
					   int rId=sc.nextInt();
					   System.out.println("Enter guest name :");
					   String gName=sc.next();
					   
					   String sql="select room_no  from reservations where reservation_id=? and guest_name=?";
					   try(PreparedStatement s=connection.prepareStatement(sql))
							   {
						   s.setInt(1, rId);
						   s.setString(2, gName);
						   ResultSet rs=s.executeQuery();
						   if(rs.next()) {
							   System.out.println("Room number for reservation id :"+rId +" and guest  :" 
						   +gName+ "  room number is : "+ rs.getInt("room_no"));
						   }
						   else {
							   System.out.println("Reservation is not found for this id or name .");
							   
						   }
					   }
					   
					   }
					   catch(Exception e) {
						   e.printStackTrace();
					   }
				   }
			   private static void updateReservation(Connection connection,Scanner sc){
				   try {
					   System.out.println("Enter the id u want to update :");
					   int rId=sc.nextInt();
					   //sc.nextLine();
					   
					   if(! reservationExists(connection , rId)) {
						   System.out.println("Reservation not found for this given id .");
						   return;
						   
						   
					   }
					   
					   System.out.println("Enter new guest name :");
					   String gName=sc.next();
					   
					   System.out.println("Enter new room no :");
					   int rno=sc.nextInt();
					   System.out.println("Enter new contact no : ");
					   String cno=sc.next();
					   
					   String sql ="update reservations set guest_name =?,room_no =? ,contact_no=? "+
					         " where reservation_id = ?";
					   try(PreparedStatement s=connection.prepareStatement(sql))
							  {
						      s.setString(1,gName);
						      s.setInt(2, rno);
						      s.setString(3, cno);
						      s.setInt(4, rId);
						   int count =s.executeUpdate();
						   if(count>0) {
							   System.out.println("Reservation updated succesfully !");
						   }
						   else {
							   System.out.println("Reservation not found or updation failed");
							   
						   }
					   }
					   
					   }
				   catch(Exception e ) {
					   e.printStackTrace();
					   
					   
				   }
				   
			   }
			   
			   private static void deleteReservation(Connection connection, Scanner sc) {
				   System.out.println("Enter the id u want to delete :");
				   int rId=sc.nextInt();
				   sc.nextLine();
				   if(! reservationExists(connection , rId)) {
					   System.out.println("Reservation not found for this given id .");
					   return; }
				   
				   String sql="delete from reservations where reservation_id= ?";
				   try(PreparedStatement s=connection.prepareStatement(sql))
						   {
					   s.setInt(1, rId);
					   int count =s.executeUpdate();
					   if(count>0) {
						   System.out.println("Reservation deleted succesfully !");
					   }
					   else {
						   System.out.println("Reservation does not found or deletion failed");
						   
					   }
				   }
				   
				   
			   catch(Exception e ) {
				   e.printStackTrace();
				   
				   
			   }
			   
				   
					   
					   
				   
				   
				   
			   }
			   private static boolean reservationExists(Connection connection,int rId) {
				   try {
					   String sql ="select reservation_id from reservations  where reservation_id =?";
					   try(PreparedStatement s=connection.prepareStatement(sql))
							   
							   {
						           s.setInt(1, rId);
						           ResultSet rs= s.executeQuery();
								   return rs.next();
							   }
				   }
				   catch(SQLException e) {
					   e.printStackTrace();
					   return false;
				   }
			   }
			   public static void exit() throws InterruptedException{
				   System.out.print("Existing system.");
				   int i=5;
				   while(i!=0) {
					   System.out.print(".");
					   Thread.sleep(450);
					   i--;
					   
				   }
				   System.out.println();
				   System.out.println("Thankyou for using hotel Reservation system .");
			   }
			   
			   
			   

			   

	}


