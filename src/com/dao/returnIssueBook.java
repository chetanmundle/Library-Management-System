package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class returnIssueBook
{
//	public static Connection con;
//	public static PreparedStatement ps;
//	public static ResultSet rs;
//	
//	public returnIssueBook() 
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234");
//		} catch (ClassNotFoundException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	
	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static String uname, pass, ip, port, database;
	
	public static void connectionwithdb() {
		ip = "192.168.29.154";
		database = "library";
		uname = "sa";
		pass = "1234";
		port = "1433";
		
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connectonurl = "jdbc:jtds:sqlserver://"+ ip +":"+ port + ";"+"databasename="+database+";user="+uname+";password="+pass+";";
			con = DriverManager.getConnection(connectonurl);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Faild");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkingRollNumberExistOrNot(String rollNumber)
	{
		connectionwithdb();
		boolean status = false;
		try {
			ps = con.prepareStatement("SELECT * FROM STUDENTS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollNumber);
			rs = ps.executeQuery();
			if(rs.next())
			{
				status = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ResultSet getStudentData(String rollnum)
	{
		connectionwithdb();
		try {
			ps = con.prepareStatement(" SELECT * FROM STUDENTS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollnum);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet bookIssuedToSt(String rollnum)
	{
		connectionwithdb();
		try {
			ps = con.prepareStatement("SELECT * FROM ISSHUEDBOOKS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollnum);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
