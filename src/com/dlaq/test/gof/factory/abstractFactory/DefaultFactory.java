package com.dlaq.test.gof.factory.abstractFactory;

/**
 * 产品系列1
 */
public class DefaultFactory extends AbstractFactory{
	@Override
	public Weapon createWeapon() {
		return new AK47();
	}
	@Override
	public Vehicle createVehicle() {
		return new Tank();
	}
	@Override
	public Food createFood() {
		return new Beef();
	}
}

class AK47 extends Weapon{
	@Override
	public void fire() {
		System.out.println("AK47 嗒嗒嗒");
	}
}
class Tank extends Vehicle{
	@Override
	public void run() {
		System.out.println("坦克 咕隆隆");
	}
}
class Beef extends Food{
	@Override
	public void eat() {
		System.out.println("吃牛肉");
	}
}