package com.dlaq.test.gof.factory.abstractFactory;
/**
 * 抽象工厂类
 */
public abstract class AbstractFactory {
	public abstract Weapon createWeapon();
	public abstract Vehicle createVehicle();
	public abstract Food createFood();
}
/**
 * 抽象工厂对应的抽象产品类
 */
abstract class Weapon{
	public abstract void fire();
}
abstract class Vehicle{
	public abstract void run();
}
abstract class Food{
	public abstract void eat();
}