package com.dlaq.test.gof.factory;

/**
 * 所有工厂模式都是为了让对象的调用者和对象创建过程分离，简化调用端代码，便于维护
 * 简单地对象创建则不需要使用工厂模式
 */

/**
 * 简单工厂(静态工厂)
 * 
 * 	1、创建产品接口
 * 	2、创建工厂类
 * 	3、工厂类通过传入参数的不同返回不同的产品
 * 
 * 优点：
 * 	1、代码调用简单，类之间的关系也较简单
 * 缺点：
 * 	1、拓展性稍差，如果需要增加新的产品，则需要修改已有代码(工厂类)，不符合开闭原则(可扩展、禁修改)
 */
public class SimpleFactroy {
	public static void main(String[] args) {
		ArsenalFactory.getArms("AK47").attack();
		ArsenalFactory.getArms("喀秋莎").attack();
		ArsenalFactory.getArms("如意金箍棒").attack();
	}
}

/**
 * 工厂类，通过该类获取产品实例
 */
class ArsenalFactory{
	public static IArm getArms(String name){
		if("AK47".equals(name)){
			return new AK47();
		}else if("喀秋莎".equals(name)){
			return new Katyusha();
		}else if("如意金箍棒".equals(name)){
			return new MonkeyKingBar();
		}else{
			return null;
		}
	}
}
/**
 * 产品接口
 */
interface IArm{
	public void attack();
}
/**
 * 以下为具体产品
 */
class AK47 implements IArm{
	@Override
	public void attack() {
		System.out.println("AK47 突突突");
	}
}
class Katyusha implements IArm{
	@Override
	public void attack() {
		System.out.println("喀秋莎 嗖嗖嗖");
	}
}
class MonkeyKingBar implements IArm{
	@Override
	public void attack() {
		System.out.println("妖怪 看棒");
	}
}