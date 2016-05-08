package com.dlaq.test.gof.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 桥接模式
 * 	解决多维度可变属性造成的多类问题
 * 	(用到了组合，但与 组合模式 没关系)
 * 
 * 场景：
 * 	各大品牌生产电子产品
 * 	品牌：联想、戴尔、苹果
 * 	产品：台式机、笔记本、pad
 * 
 * 如果采用继承的方式实现类结构，那类的数量则是一个乘积的关系
 * (联想、戴尔、苹果)*(台式机、笔记本、pad)
 * 这样会产生一些问题：
 * 	1、产生大量的类，类之间还会有重复的功能(冗余)
 * 	2、品牌 和 类型 之间是有关系的(没有解耦)
 * 	3、添加其中一个实现，则需要添加一定数量的另一个(不好维护)
 * 
 * 如果采用桥接的方式，那类的数量则只是一个相加关系
 * (联想、戴尔、苹果)+(台式机、笔记本、pad)
 * 优点：
 * 	1、大大减少了类的数量
 * 	2、解除了类之间的耦合关系，增加了复用性和可维护性
 */
public class BridgeDemo01 {
	public static void main(String[] args) {
		List<Computer> l = new ArrayList<Computer>();
		l.add(new Desktop(new Dell()));
		l.add(new Desktop(new Lenovo()));
		l.add(new Desktop(new Apple()));
		l.add(new Laptop(new Dell()));
		l.add(new Laptop(new Lenovo()));
		l.add(new Laptop(new Apple()));
		l.add(new Pad(new Dell()));
		l.add(new Pad(new Lenovo()));
		l.add(new Pad(new Apple()));
		for(Computer c : l){
			c.sale();
		}
	}
}
/**
 * 电脑抽象类
 */
abstract class Computer{
	private Brand brand;
	public Computer(Brand brand){
		this.brand = brand;
	}
	abstract String getType();
	public void sale(){
		System.out.println("销售["+brand.getName()+"]牌的"+"["+getType()+"]");
	}
}
/**
 * 品牌接口
 */
interface Brand{
	String getName();
}

/**
 * 电脑具体实现类
 */
class Desktop extends Computer{
	public Desktop(Brand brand) {
		super(brand);
	}
	@Override
	String getType() {
		return "台式机";
	}
}
class Laptop extends Computer{
	public Laptop(Brand brand) {
		super(brand);
	}
	@Override
	String getType() {
		return "笔记本电脑";
	}
}
class Pad extends Computer{
	public Pad(Brand brand) {
		super(brand);
	}
	@Override
	String getType() {
		return "平板电脑";
	}
}
/**
 * 品牌具体实现类
 */
class Dell implements Brand{
	@Override
	public String getName() {
		return "戴尔";
	}
}
class Lenovo implements Brand{
	@Override
	public String getName() {
		return "联想";
	}
}
class Apple implements Brand{
	@Override
	public String getName() {
		return "苹果";
	}
}
