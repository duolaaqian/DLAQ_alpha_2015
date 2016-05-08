package com.dlaq.test.gof.decorator;

/**
 * 装饰模式
 * 
 * 类似于代理模式
 * 与代理的区别：
 * 1、真实角色持有(?)
 * 	代理：代理角色并不一定需要调用者提供真实角色(调用者不需要持有真实角色)
 * 	装饰：调用者需要持有真实角色，并提供给装饰器
 * 2、真实角色控制
 * 	代理：代理角色返回任意真实角色，甚至不返回真实角色
 * 	装饰：对传入的真实角色进行装饰，然后将装饰后的角色返回
 * 
 * 代理：我通过 代理(代理角色) 买一套精装修的房子
 * 装饰：我先买房，再找 人(装饰角色) 给我装修
 */
public class DecoratorDemo01 {
	public static void main(String[] args) {
		ICar car = new SuperCar();
		System.out.println("***********************");
		car.move();
		System.out.println("***********************");
		new FlyCar(car).move();
		System.out.println("***********************");
		new SwimCar(car).move();
		System.out.println("***********************");
		new FlyCar(new SwimCar(car)).move();
		System.out.println("***********************");
		new SwimCar(new FlyCar(car)).move();
		System.out.println("***********************");
	}
}
/**
 * 统一接口
 */
interface ICar{
	public void move();
}
/**
 * 被装饰对象
 */
class SuperCar implements ICar{
	@Override
	public void move() {
		System.out.println("汽车运行");
	}
}
/**
 * 一下为装饰器
 */
class FlyCar implements ICar{
	private ICar car;
	public FlyCar(ICar car){
		this.car = car;
	}
	@Override
	public void move(){
		System.out.println("汽车安装了翅膀");
		car.move();
	}
}
class SwimCar implements ICar{
	private ICar car;
	public SwimCar(ICar car){
		this.car = car;
	}
	@Override
	public void move(){
		System.out.println("汽车安装了气垫");
		car.move();
	}
}