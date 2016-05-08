package com.dlaq.test.jdbc.spring;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringJdbcTest1 {
	
	public static ApplicationContext ctx;

	
	public static void main(String arg[]) throws SQLException{
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ctx = new ClassPathXmlApplicationContext("/com/dlaq/test/jdbc/spring/applicationContext.xml");
//		DataSource ds = (DataSource)ctx.getBean("dataSource");
		JdbcDao jdbcDao = (JdbcDao)ctx.getBean("jdbcDao");
		String sql = "SELECT COUNT(*) FROM t_cms_article";
		List<Map<String, Object>> queryForList = jdbcDao.getJdbcTemplate().queryForList(sql);
		System.out.println(queryForList);
		
/*		Connection conn = ds.getConnection();  
		Statement sm = conn.createStatement();
		String sql = "SELECT COUNT(*) FROM t_cms_article";  */
		
	}
	
	public static JdbcDao getJdbcDao(){
		ctx = new ClassPathXmlApplicationContext("/com/dlaq/test/jdbc/spring/applicationContext.xml");
		return (JdbcDao)ctx.getBean("jdbcDao");
	}

}
