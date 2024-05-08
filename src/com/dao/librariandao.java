package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class librariandao 
{
//	public Connection con;
//	public PreparedStatement ps;
//	public ResultSet rs;
//	
//	public librariandao() 
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
////			System.out.println("Driver found");
//			
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234");
////			System.out.println("Connection Created");
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
	
	public boolean CheckIdPassOFAdmin(String uid, String upass)
	{
		connectionwithdb();
		boolean status = false;
		try {
			ps = con.prepareStatement("SELECT * FROM LIBRARIAN WHERE LIBRARY_ID = ? AND LIBRARY_PASS = ?");
			ps.setString(1, uid);
			ps.setString(2, upass);
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

}
