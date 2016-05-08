package com.dlaq.test.gof.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * IPhone6s一共有16种版本
 * 
 *  1、三星14nm＋海力士MLC＋LG＋三星
 *  2、三星14nm＋海力士MLC＋LG＋海力士
 *  3、三星14nm＋海力士MLC＋夏普＋三星
 *  4、三星14nm＋海力士MLC＋夏普＋海力士
 *  5、三星14nm＋东芝TLC＋LG＋三星
 *  6、三星14nm＋东芝TLC＋LG＋海力士
 *  7、三星14nm＋东芝TLC＋夏普＋三星
 *  8、三星14nm＋东芝TLC＋夏普＋海力士
 *  9、台积电16nm＋海力士MLC＋LG＋三星
 *  10、台积电16nm＋海力士MLC＋LG＋海力士
 *  11、台积电16nm＋海力士MLC＋夏普＋三星
 *  12、台积电16nm＋海力士MLC＋夏普＋海力士
 *  13、台积电16nm＋东芝TLC＋LG＋三星
 *  14、台积电16nm＋东芝TLC＋LG＋海力士
 *  15、台积电16nm＋东芝TLC＋夏普＋三星
 *  16、台积电16nm＋东芝TLC＋夏普＋海力士
 *  
 * 如果使用继承或每一种IPhone6s定义一个类，则需要定义16个类
 * (而且每个品牌都生产4中配件的话，则类会更多)
 * 使用桥接模式我们只需要定义10(4组件+6配置)个类
 * 看似差的不多(6个而已)，好的，我们还有4种不同的颜色
 * 	16*4=64
 * 	16+4=20
 */
public class BridgeDemo02 {
	public static void main(String[] args) {
		TechBrand samsung = new Samsung();
		TechBrand tsmc = new TSMC();
		TechBrand hynix = new Hynix();
		TechBrand toshiba = new Toshiba();
		TechBrand lg = new LG();
		TechBrand sharp = new Sharp();
		
		List<IPhone6s> l = new ArrayList<IPhone6s>(16);
		l.add(new IPhone6s(new CPU(samsung),new Flash(hynix),new Screen(lg),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(hynix),new Screen(lg),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(hynix),new Screen(sharp),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(hynix),new Screen(sharp),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(toshiba),new Screen(lg),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(toshiba),new Screen(lg),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(toshiba),new Screen(sharp),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(samsung),new Flash(toshiba),new Screen(sharp),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(hynix),new Screen(lg),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(hynix),new Screen(lg),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(hynix),new Screen(sharp),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(hynix),new Screen(sharp),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(toshiba),new Screen(lg),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(toshiba),new Screen(lg),new RAM(hynix)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(toshiba),new Screen(sharp),new RAM(samsung)));
		l.add(new IPhone6s(new CPU(tsmc),new Flash(toshiba),new Screen(sharp),new RAM(hynix)));
		
		for(IPhone6s p : l){
			System.out.println("配置："+p.getInfo());	
		}
		System.out.println("*************************************************");
		System.out.println("您购买的IPhone6s配置为：" + l.get(new Random().nextInt(16)).getInfo());
	}
}
class IPhone6s{
	private String info;
	public IPhone6s(CPU cpu, Flash flash, Screen screen, RAM ram){
		this.info = cpu.getName()+" + "+flash.getName()+" + "+ screen.getName()+" + "+ram.getName();
	}
	public String getInfo(){
		return info;
	}
}
/**
 * 配件
 */
abstract class Component{
	TechBrand tb;
	public Component(TechBrand tb){
		this.tb = tb;
	}
	public abstract String getType();
	public String getName(){
		return tb.getName()+"的"+getType();
	}
}
/**
 * 品牌
 */
interface TechBrand{
	String getName();
}

/**
 * 配件具体实现类
 */
class CPU extends Component{
	public CPU(TechBrand tb) {
		super(tb);
	}
	public String getType(){
		return "CPU";
	}
}
class Flash extends Component{
	public Flash(TechBrand tb) {
		super(tb);
	}
	public String getType(){
		return "闪存";
	}
}
class Screen extends Component{
	public Screen(TechBrand tb) {
		super(tb);
	}
	public String getType(){
		return "屏幕";
	}
}
class RAM extends Component{
	public RAM(TechBrand tb) {
		super(tb);
	}
	public String getType(){
		return "内存";
	}
}
/**
 * 品牌具体实现类
 */
class Samsung implements TechBrand{
	@Override
	public String getName() {
		return "三星";
	}
}
class TSMC implements TechBrand{
	@Override
	public String getName() {
		return "台积电";
	}
}
class Hynix implements TechBrand{
	@Override
	public String getName() {
		return "海力士";
	}
}
class Toshiba implements TechBrand{
	@Override
	public String getName() {
		return "东芝";
	}
}
class LG implements TechBrand{
	@Override
	public String getName() {
		return "LG";
	}
}
class Sharp implements TechBrand{
	@Override
	public String getName() {
		return "夏普";
	}
}