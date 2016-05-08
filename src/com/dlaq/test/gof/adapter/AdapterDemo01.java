package com.dlaq.test.gof.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器模式
 * 	1、源角色(已有的角色)
 * 	2、目标角色(需要成为的角色)
 * 	3、适配器角色(将 原角色 转换成 目标角色)
 * 
 * 优点：
 * 	1、不用修改已有代码
 * 	2、复用已有代码
 * 缺点：
 * 	1、会造成类相互之间的关系不清晰
 *
 * 注意：
 *	属于"补丁"方法，尽量不要有机会使用
 *
 * 场景：
 * 	功能复合需求，但是调用跟需求不匹配时，中间通过一个转换类来实现两者之间的调用
 * 
 * 	NBA不可轻易改变，CBA不可轻易改变
 * 	CBA球员不会英语，听不懂attack，无法进入NBA球队
 * 	通过翻译翻译，CBA知道了attack就是jingong，可以进入NBA球队
 */
public class AdapterDemo01 {
	public static void main(String[] args) {
		NBAPlayer tmac = new NBAPlayer("T-mac");
		NBAPlayer brooks = new NBAPlayer("布鲁克斯");
		NBAPlayer mutombo = new NBAPlayer("穆托姆博");
		CBAPlayer ym = new CBAPlayer("姚明");
		CBAPlayer dazhizi = new CBAPlayer("郭艾伦");
		
		NBATeam rockets = new NBATeam();
		rockets.add(tmac);
		rockets.add(brooks);
		rockets.add(mutombo);
//		rockets.add(ym);//编译报错
//		rockets.add(dazhizi);//编译报错
		rockets.add(new Translator(ym));//编译通过
		rockets.add(new Translator(dazhizi));//编译通过
		rockets.play();
	}
}
interface NBA{
	public void defend();
	public void attack();
}
interface CBA{
	public void fangShou();
	public void jinGong();
}
class NBAPlayer implements NBA{
	String name;
	public NBAPlayer(String name){
		this.name = name;
	}
	@Override
	public void defend() {
		System.out.println(this.name + "  defend!!!");
	}
	@Override
	public void attack() {
		System.out.println(this.name + "  attack!!!");	
	}
}
class CBAPlayer implements CBA{
	private String name;
	public CBAPlayer(String name){
		this.name = name;
	}
	@Override
	public void fangShou() {
		System.out.println(this.name + "  防守！！！");
	}
	@Override
	public void jinGong() {
		System.out.println(this.name + "  进攻！！！");
	}
}
class NBATeam{
	private List<NBA> playerList = new ArrayList<NBA>();
	public void add(NBA player){
		playerList.add(player);
	}
	public void play(){
		for(NBA play : playerList){
			System.out.println("[NBA]");
			play.attack();
			play.defend();
		}
	}
}
/**
 * 翻译(适配器)
 */
class Translator implements NBA{
	public CBAPlayer player;
	public Translator(CBAPlayer p){
		this.player = p;
	}
	@Override
	public void defend() {
		System.out.println("====翻译翻译 defend 为 defend");
		player.fangShou();
	}
	@Override
	public void attack() {
		System.out.println("====翻译翻译 attack 为 jinGong");
		player.jinGong();
	}
}