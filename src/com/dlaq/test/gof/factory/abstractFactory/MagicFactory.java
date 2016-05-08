package com.dlaq.test.gof.factory.abstractFactory;

/**
 * 产品系列2
 */
public class MagicFactory extends AbstractFactory{
	@Override
	public Weapon createWeapon() {
		return new MagicStick();
	}
	@Override
	public Vehicle createVehicle() {
		return new MagicBroom();
	}
	@Override
	public Food createFood() {
		return new MagicBread();
	}
}
class MagicStick extends Weapon{
	@Override
	public void fire() {
		System.out.println("魔法棒 变");
	}
}
class MagicBroom extends Vehicle{
	@Override
	public void run() {
		System.out.println("魔法扫帚 飞");
	}
}
class MagicBread extends Food{
	@Override
	public void eat() {
		System.out.println("魔法面包 呃");
	}
}