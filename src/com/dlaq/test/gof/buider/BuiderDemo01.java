package com.dlaq.test.gof.buider;

/**
 * 建造者模式
 * 
 * 	将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 
 * 	1、建造者接口，定义建造者需要提供的功能
 * 	2、指挥者类，定义了建造过程
 * 	3、具体建造者提供建造的具体实现
 * 
 * 优点：
 * 	1、客户端调用时不用知道内部细节，指定建造者即可
 * 	2、建造者之间是相互独立又可相互替换的，便于维护/扩展
 *
 * 与工厂模式的区别
 * 	工厂模式：侧重组装过程
 * 	建造者模式：侧重组装所需零件的生产过程
 * 
 * 工厂模式就像玩积木，原料都差不多，看你如何搭
 * 建造者模式像造汽车，整体框架都差不多，但是具体零件相差较大
 */
public class BuiderDemo01 {
	public static void main(String[] args) {
		ModuleDirector md1 = new ModuleDirector(new InternCoder());
		md1.createModule();
		ModuleDirector md2 = new ModuleDirector(new NormalCoder());
		md2.createModule();
		ModuleDirector md3 = new ModuleDirector(new GodlikeCoder());
		md3.createModule();
	}
}
/**
 * 模块建造者接口
 */
interface ModuleBuider{
	public void createJAVA();
	public void createJSP();
	public void createSQL();
}
/**
 * 指挥者，指挥建造者创建模块
 */
class ModuleDirector{
	private ModuleBuider mb;
	public ModuleDirector(ModuleBuider mb){
		this.mb = mb;
	}
	public void createModule(){
		mb.createJAVA();
		mb.createJSP();
		mb.createSQL();
	}
}

/**
 * 实习生
 */
class InternCoder implements ModuleBuider{
	@Override
	public void createJAVA() {
		System.out.println("实习生编写后台代码");
	}
	@Override
	public void createJSP() {
		System.out.println("实习生编写前台代码");
	}
	@Override
	public void createSQL() {
		System.out.println("实习生编写SQL语句");
	}
}
/**
 * 普通程序员
 */
class NormalCoder implements ModuleBuider{
	@Override
	public void createJAVA() {
		System.out.println("普通程序员编写后台代码");
	}
	@Override
	public void createJSP() {
		System.out.println("普通程序员编写前台代码");
	}
	@Override
	public void createSQL() {
		System.out.println("普通程序员编写SQL语句");
	}
}
/**
 * 大神
 */
class GodlikeCoder implements ModuleBuider{
	@Override
	public void createJAVA() {
		System.out.println("大神编写后台代码");
	}
	@Override
	public void createJSP() {
		System.out.println("大神编写前台代码");
	}
	@Override
	public void createSQL() {
		System.out.println("大神编写SQL语句");
	}
}



