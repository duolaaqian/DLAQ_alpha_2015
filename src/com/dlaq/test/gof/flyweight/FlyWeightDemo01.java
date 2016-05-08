package com.dlaq.test.gof.flyweight;

import java.util.Hashtable;
import java.util.Map;

/**
 * 享元模式
 * 
 * 如果程序中存在大量的对象，并且对象之间也较为类似
 * 这样，对象之间类似的部分就可以共享出来，这样就减少了内存开销
 * 
 * 1、具体实体类(大量对象中可共享部分)
 * 2、工厂类(共享池，返回已有对象或创建新对象)
 * 3、外部状态(大量对象中可不共享部分)
 * 
 * 优点：
 * 	1、节省内存开销
 * 缺点：
 * 	1、增加了系统度
 * 	2、虽然节省了内存，但增加了运行时长
 * 
 * 场景：
 * 	1、五子棋
 * 		共享部分：颜色(黑、白)
 * 		分共享部分：位置(1...361)
 * 	2、String 常量池
 */
public class FlyWeightDemo01 {
	public static void main(String[] args) {
		IChess c1 = ChessFactory.getChess("黑");
		IChess c2 = ChessFactory.getChess("黑");
		IChess c3 = ChessFactory.getChess("红");
		IChess c4 = ChessFactory.getChess("红");
		IChess c5 = ChessFactory.getChess("黑");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);
		c1.display(new Coordinate(10,10));
		c2.display(new Coordinate(20,20));
		c3.display(new Coordinate(30,30));
		c4.display(new Coordinate(40,40));
		c5.display(new Coordinate(50,50));
	}
}
/**
 * 棋子接口
 */
interface IChess{
	void setColor(String color);
	void display(Coordinate c);
}
/**
 * 位置类
 */
class Coordinate{
	int x,y;
	public Coordinate(int x,int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
/**
 * 具体棋子实现类
 */
class ConcreteChess implements IChess{
	String color;
	@Override
	public void setColor(String color) {
			this.color = color;
	}
	@Override
	public void display(Coordinate c) {
		System.out.println("棋子：["+this.color+"]["+c.getX()+"]["+c.getY()+"]");
	}
}
/**
 * 棋子工厂
 */
class ChessFactory{
	private static Map<String,IChess> map = new Hashtable<String,IChess>();
	public static IChess getChess(String color){
		if( null!=map.get(color) ){
			return map.get(color);
		}else{
			IChess c = new ConcreteChess();
			c.setColor(color);
			map.put(color, c);
			return c;
		}
	}
}
