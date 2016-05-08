package com.dlaq.test.jdbc.mybatis.test02;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Client1 {
	public static void main(String[] args) {
        String resource = "com/dlaq/test/jdbc/mybatis/test02/conf.xml";
        InputStream is = Client1.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        
        
        
        String statement = "com.dlaq.test.jdbc.mybatis.test02.userMapper.getMap";
        JSONObject obj = new JSONObject();
        
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("id", "4");
        m.put("username", "fan");
        
//		Map<?,?> map = session.selectOne(statement, m);
//		obj.put("result", map);
//		System.out.println(obj);
        
        statement = "com.dlaq.test.jdbc.mybatis.test02.userMapper.getAllMap";
        List<?> list = session.selectList(statement, m);
        obj = new JSONObject();
        obj.put("result", list);
        obj.put("total", list.size());
        System.out.println(obj);
        
        
        String sql = sessionFactory.getConfiguration().getMappedStatement(statement).getBoundSql(m).getSql();
        String sqlSource = sessionFactory.getConfiguration().getMappedStatement(statement).getSqlSource().getBoundSql(m).getSql();
        System.out.println("[sql]:");
        System.out.println(sql);
        System.out.println("***********************");
        System.out.println(sqlSource);
	}
}
