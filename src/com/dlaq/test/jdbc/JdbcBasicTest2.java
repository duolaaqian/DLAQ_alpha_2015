package com.dlaq.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBasicTest2 {
	public static void main(String args[]){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring","root","root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user");
			
			ResultSetMetaData metaData = rs.getMetaData();

			
			
			System.out.println(metaData.getColumnType(1));
			System.out.println(metaData.getColumnType(2));
			System.out.println(metaData.getColumnType(3));
/*				while(rs.next()){
					System.out.print(rs.getString("username"));
					for(int i=0;i<(10-rs.getString("username").length());i++){
						System.out.print(" ");
					}
					System.out.println("[" + rs.getString("password") + "]");
				}*/
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(stmt!=null){
					stmt.close();
					stmt = null;
				}
				if(conn!=null){
					conn.close();
					conn = null;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
