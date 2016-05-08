package com.dlaq.test.gof.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 缺省适配模式
 * 
 * 	1、标准接口
 * 	2、适配器父类
 * 	3、具体实现类
 * 
 * 有时候我们继承父类，但是只实现了部分功能，其他功能只是空的实现
 * 我们定义一个专门实现空方法的父类，之后子类继承该父类，避免了子类去继承空方法的麻烦
 * 
 * 场景：
 * 	2015男篮亚锦赛决赛
 * 	会打球可以进入菲律宾男篮，会打架也可以，
 * 	所以菲男篮就有三个接口(throwBall、throwBall、scuffle)
 * 	但有的球员只会打球，有的只会打架，
 * 	所以为了让球员更关注与自己的工作，我们定义缺省适配器
 * 	这样，各个球员只关注他们的主要工作就可以了
 */
public class AdapterDemo02 {
	public static void main(String[] args) {
		SaucePlayer blch = new SaucePlayer("布拉彻");
		CorePlayer dacb = new CorePlayer("兰迪尔-迪-奥坎波");//33锁喉
		CorePlayer dh = new CorePlayer("东多-汉迪弗罗斯");//25断子绝孙腿
		CorePlayer ka = new CorePlayer("卡尔文-阿布埃瓦");//8锁喉
		
		PhilippineNationalTeam pnt = new PhilippineNationalTeam();
		pnt.add(blch);
		pnt.add(dacb);
		pnt.add(dh);
		pnt.add(ka);
		pnt.play();
	}
}
/**
 * 菲律宾国家队
 */
class PhilippineNationalTeam{
	private List<PBA> playerList = new ArrayList<PBA>();
	public void add(PBA player){
		playerList.add(player);
	}
	public void play(){
		for(PBA play : playerList){
			play.throwBall();
			play.eyeDefense();
			play.scuffle();
		}
	}
}
/**
 * 菲律宾联赛
 */
interface PBA{
	public void throwBall();
	public void eyeDefense();
	public void scuffle();
}
/**
 * 缺省适配器
 */
abstract class DefaultPBA implements PBA{
	public void throwBall(){};
	public void eyeDefense(){};
	public void scuffle(){};
}
/**
 * 普通球员
 */
class SaucePlayer extends DefaultPBA{
	private String name;
	public SaucePlayer(String name){
		this.name = name;
	}
	public void throwBall(){
		System.out.println("普通球员["+this.name+"] 进攻");
	};
	public void eyeDefense(){
		System.out.println("普通球员["+this.name+"] 防守");
	};
}
/**
 * 主要球员
 */
class CorePlayer extends DefaultPBA{
	private String name;
	public CorePlayer(String name){
		this.name = name;
	}
	public void scuffle(){
		System.out.println("核心球员["+this.name+"] 干倒了一名对方球员");
	};
}