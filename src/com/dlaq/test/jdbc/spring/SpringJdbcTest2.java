package com.dlaq.test.jdbc.spring;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringJdbcTest2 {
	
	public static ApplicationContext ctx;

	
	public static void main(String arg[]) throws SQLException{
		JdbcDao jdbcDao = getJdbcDao();
		String sql = "SELECT COUNT(*) FROM t_cms_article";
		
		
		
		List<Map<String, Object>> queryForList = jdbcDao.getJdbcTemplate().queryForList(sql);
		System.out.println(queryForList);
	}
	
	public static JdbcDao getJdbcDao(){
		ctx = new ClassPathXmlApplicationContext("/com/dlaq/test/jdbc/spring/applicationContext.xml");
		return (JdbcDao)ctx.getBean("jdbcDao");
	}

}
