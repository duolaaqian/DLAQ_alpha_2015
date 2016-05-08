package com.dlaq.test.jdbc.mybatis.test01;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;




public class Client {
	public static void main(String[] args) {
//		BasicBatis.test1();
		test2();
	}
	
	public static void test2(){
	       //mybatis的配置文件
        String resource = "com/dlaq/test/jdbc/mybatis/test01/conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Client.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        
        TestHandler th = new TestHandler(session);
        SqlSession thProxy = (SqlSession)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{SqlSession.class}, th);
        TUser user = thProxy.selectOne("key","402881d64ffdb55c014ffdb599690000");
        System.out.println(user);
        
/*        String statement = "com.dlaq.test.jdbc.mybatis.test01.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        TUser user = session.selectOne(statement, "402881d64ffdb55c014ffdb599690000");
        System.out.println(user);
        System.out.println();
        
        statement = "com.dlaq.test.jdbc.mybatis.test01.userMapper.getAllUser";
        List<TUser> l = session.selectList(statement);
        System.out.println(l);*/
	}
	
}

class TestHandler implements InvocationHandler{
	Object obj;
	public TestHandler(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = null;
		String statement = "com.dlaq.test.jdbc.mybatis.test01.userMapper.getUser";
		object = method.invoke(obj, statement,args[1]);
		return object;
	}
	
}
