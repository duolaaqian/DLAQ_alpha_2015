package com.dlaq.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 三种批处理方法
 */
public class JdbcBasicTest3 {
	public static void main(String args[]){
		test01();
	}
	
	public static void test01(){
		MyJDBCTemplate.execute(new ConnectionCallback(){
			@Override
			public void doExecute(Connection conn) throws SQLException {
				String sql = "insert into book (kind, name) values (?,?)"; 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "java");
				pstmt.setString(2, "jjjj");
				pstmt.addBatch();//添加一次预定义参数
				pstmt.setString(1, "ccc");
				pstmt.setString(2, "dddd");
				pstmt.addBatch();//再添加一次预定义参数
				//批量执行预定义SQL
				pstmt.executeBatch();
			}
		});
	}
	
	public static void test02(){
		MyJDBCTemplate.execute(new ConnectionCallback(){
			@Override
			public void doExecute(Connection conn) throws SQLException {
				String sql = "insert into book (kind, name) values (?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "java");
				pstmt.setString(2, "jjjj");
				pstmt.addBatch();//添加一次预定义参数
				pstmt.setString(1, "ccc");
				pstmt.setString(2, "dddd");
				pstmt.addBatch();//再添加一次预定义参数
				//添加一次静态SQL
				pstmt.addBatch("update book set kind = 'JAVA' where kind='java'");
				//批量执行预定义SQL
				pstmt.executeBatch();
			}
		});
	}
	
	public static void test03(){
		MyJDBCTemplate.execute(new ConnectionCallback(){
			@Override
			public void doExecute(Connection conn) throws SQLException {
				Statement stmt = conn.createStatement();
				//连续添加多条静态SQL
				stmt.addBatch("insert into book (kind, name) values ('java', 'java in aciton')");
				stmt.addBatch("insert into book (kind, name) values ('c', 'c in aciton')");
				stmt.addBatch("delete from book where kind ='C#'");
				stmt.addBatch("update testdb.book set kind = 'JAVA' where kind='java'");
				//批量执行不支持Select语句
				//stmt.addBatch("select count(*) from testdb.book");
				//执行批量执行
				stmt.executeBatch();
			}
		});
	}
	
}
