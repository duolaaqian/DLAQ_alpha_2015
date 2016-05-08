package com.dlaq.test.jdbc.mybatis.test01;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BasicBatis {
	public static void test1(){
        //mybatis的配置文件
        String resource = "com/dlaq/test/jdbc/mybatis/test01/conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Client.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        /*
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        */
        
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        String statement = "com.dlaq.test.jdbc.mybatis.test01.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        TUser user = session.selectOne(statement, "402881d64ffdb55c014ffdb599690000");
        System.out.println(user);
        System.out.println();
        
        statement = "com.dlaq.test.jdbc.mybatis.test01.userMapper.getAllUser";
        List<TUser> l = session.selectList(statement);
        System.out.println(l);
	}
}
