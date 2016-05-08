package com.dlaq.test.gof.facade;

/**
 * 外观模式
 * 	迪米特法则 -- 最少知道原则
 * 	为客户端提供简单的接口调用，减少客户端与其他类之间的耦合度
 * 
 * 并不存在某种特定环境来使用该模式
 * 在编程过程中经常会用到该思想
 * 
 * 优点：
 * 	客户端调用简单明了
 * 	解耦，方便维护
 * 缺点：
 * 	客户端不能接触到子系统，缺少一定的灵活性
 * 	设计不当则不好扩展(增加子系统=修改源码)
 */
public class FacadeDemo01 {

	public static void main(String[] args) {
//		test01();
		test02();
	}
	public static void test01(){
		Stock1 s1 = new Stock1();
		Stock2 s2 = new Stock2();
		Stock3 s3 = new Stock3();
		Bond b = new Bond();
		s1.buy();
		s2.buy();
		s3.buy();
		b.buy();
		s1.sell();
		s2.sell();
		s3.sell();
		b.sell();
	}
	public static void test02(){
		Fund f = new Fund();
		f.buy();
		f.sell();
	}	
}


class Stock1{
	public void buy(){System.out.println("买入股票1");}
	public void sell(){System.out.println("卖出股票1");}
}
class Stock2{
	public void buy(){System.out.println("买入股票2");}
	public void sell(){System.out.println("卖出股票2");}
}
class Stock3{
	public void buy(){System.out.println("买入股票3");}
	public void sell(){System.out.println("卖出股票3");}
}
class Bond{
	public void buy(){System.out.println("买入债券");}
	public void sell(){System.out.println("卖出债券");}
}

class Fund{
	private Stock1 s1;
	private Stock2 s2;
	private Stock3 s3;
	private Bond b;
	public Fund(){
		s1 = new Stock1();
		s2 = new Stock2();
		s3 = new Stock3();
		b = new Bond();
	}
	public void buy(){
		s1.buy();
		s2.buy();
		s3.buy();
		b.buy();
	}
	public void sell(){
		s1.sell();
		s2.sell();
		s3.sell();
		b.sell();
	}
}