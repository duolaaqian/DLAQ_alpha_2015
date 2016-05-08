package com.dlaq.test.gof.proxy;

/**
 * 通过接口方式实现静态代理(聚合)
 * 
 * 	1、定义公共接口(IMoveable)
 * 	2、定义真实对象 继承接口(IMoveable) 实现功能(Tank)
 * 	3、定义代理类 继承接口(IMoveable) 实现前后逻辑/业务逻辑(TankTimeProxy/TankLogProxy)
 * 
 * 调用的时候可以通过修改代理顺序来来改变逻辑实现顺序(test1/test2)
 * 优点：
 * 		业务类只关注逻辑本身，灵活，重用性较好
 * 缺点：
 * 		1)一个接口只能服务于一种功能类，多功能类就需要多接口+多代理类(此时代理类里的逻辑很可能是重复的)
 * 		2)接口不能随便修改，接口一修改，所有的真实对象和代理类都要改，维护性不好
 */
public class StaticProxy2 {
	public static void main(String[] args) {
		test1();
		System.out.println("-----------------------------");
		test2();
	}
	/**
	 * 先进行时间记录，再进行log记录
	 */
	public static void test1(){
		Tank t = new Tank();
		TankTimeProxy ttp = new TankTimeProxy(t);
		TankLogProxy tlp = new TankLogProxy(ttp);
		tlp.move();
	}
	/**
	 * 先进行log记录，再进行时间记录
	 */
	public static void test2(){
		Tank t = new Tank();
		TankLogProxy tlp = new TankLogProxy(t);
		TankTimeProxy ttp = new TankTimeProxy(tlp);
		ttp.move();
	}
}

/**
 * 公共接口
 */
interface IMoveable{
	public void move();
}
/**
 * 真实对象
 */
class Tank implements IMoveable{
	@Override
	public void move() {
		for(int i=1;i<5;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("坦克前行"+i*100+"米....");
		}
	}
}
/**
 * 记录时间代理类
 */
class TankTimeProxy implements IMoveable{
	IMoveable m;
	public TankTimeProxy(IMoveable m){
		this.m = m;
	}
	@Override
	public void move() {
		System.out.println("[TankTimeProxy]开始计时....");
		long start = System.currentTimeMillis();
		m.move();
		long end = System.currentTimeMillis();
		System.out.println("[TankTimeProxy]计时结束 共运行"+(end-start));
	}
}
/**
 * 记录日志代理类
 */
class TankLogProxy implements IMoveable{
	IMoveable m;
	public TankLogProxy(IMoveable m){
		this.m = m;
	}
	@Override
	public void move() {
		System.out.println("[TankLogProxy] print before log");
		m.move();
		System.out.println("[TankLogProxy] print after log");
	}
}
