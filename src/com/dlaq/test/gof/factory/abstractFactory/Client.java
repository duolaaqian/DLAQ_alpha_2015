package com.dlaq.test.gof.factory.abstractFactory;

/**
 * 抽象工厂
 * 	主要解决 产品系列 的功能
 * 
 * 	1、定义 产品系列工厂 抽象类
 * 	2、定义每个产品的抽象类
 * 	3、定义具体产品、具体产品工厂
 * 
 * 	AbstractFactory --> 定义产品的标准(方法)
 * 	DefaultFactory、MagicFactory.... --> 实现具体的产品(方法)
 * 
 * 优点：
 * 	1、方便对产品系列进行增加、替换
 * 缺点：
 * 	1、新增产品的话，每个工厂实现都要改
 */
public class Client {
	public static void main(String[] args) {
//		AbstractFactory f = new DefaultFactory();
		AbstractFactory f = new MagicFactory();
		f.createWeapon().fire();
		f.createVehicle().run();
		f.createFood().eat();
	}
}
