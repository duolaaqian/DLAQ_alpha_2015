package com.dlaq.test.gof.factory;

/**
 * 工厂方法
 * 
 * 	1、定义产品接口
 * 	2、定义工厂抽象类
 * 	3、通过工厂创建具体产品
 * 
 * 优点：
 * 	1、方便对产品进行更换
 * 	2、方便增加新的产品
 * 缺点：
 * 	1、产品多了类就会多 [一个产品=一个产品类+一个产品类工厂](类爆炸)
 * 	2、产品接口 修改后其他实现类都要改
 */
public class FactoryMethod {
	public static void main(String[] args) {
//		VehicleFactory v = new PlaneFactory();
		VehicleFactory v = new CarFactory();
		v.create().run();
	}
}
/**
 * 产品接口
 */
interface IMoveable{
	public void run();
}
/**
 * 工厂抽象类
 */
abstract class VehicleFactory{
	abstract IMoveable create();
}

/**
 * 以下为具体产品及相应工厂
 */
class Plane implements IMoveable{
	@Override
	public void run() {
		System.out.println("大飞机 呼呼呼");
	}
}
class PlaneFactory extends VehicleFactory{
	public IMoveable create(){
		return new Plane();
	}
}
class Car implements IMoveable{
	@Override
	public void run() {
		System.out.println("小汽车 嘀嘀嘀");
	}
}
class CarFactory extends VehicleFactory{
	public IMoveable create(){
		return new Car();
	}
}