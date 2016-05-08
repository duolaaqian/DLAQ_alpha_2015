package com.dlaq.test.gof.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 * 
 * 	1、源发器类(具体的业务类/需要备份的类)
 * 	2、备忘录类(拷贝类，用于恢复)
 * 	3、负责人类(管理所有的拷贝)
 * 
 * 将对象在某个时间点信息保存起来，方便以后查看
 * 
 * 优点：
 * 	1、具体的备份过程交给实体类，保持了封装性
 * 	2、备份保存在实体类以外(负责人类)，实现了单一职能
 * 缺点：
 * 	1、一般的备份都是多备份，所以对资源的消耗还是很大的
 * 
 * 场景：
 * 	游戏存盘读盘、文档编辑撤销、JDBC回滚
 */
public class MementoDemo01 {
	public static void main(String[] args) {
		SaveManager sm = new SaveManager();
		Player p = new Player("李逍遥");
		p.setPlace("余杭镇盛渔村");
		System.out.println("初始化：");
		System.out.println(p);
		sm.add(p.memento());
		
		p.setPlace("仙灵岛");
		p.setLevel(3);
		p.setMoney(500);
		System.out.println("存储点1：");
		System.out.println(p);
		sm.add(p.memento());
		
		p.setPlace("苏州城外");
		p.setLevel(15);
		p.setMoney(1100);
		System.out.println("存储点2：");
		System.out.println(p);
		sm.add(p.memento());
		
		System.out.println();
		
		System.out.println("获取初始化：");
		p.recovery(sm.get(0));
		System.out.println(p);
		System.out.println("获取存储点1：");
		p.recovery(sm.get(1));
		System.out.println(p);
		System.out.println("获取存储点2：");
		p.recovery(sm.get(2));
		System.out.println(p);
	}
}
/**
 * 源发器类(需要备份历史记录的类)
 */
class Player{
	private String name;
	private int level=1;
	private int money=0;
	private String place;
	
	public Save memento(){//进行备份
		return new Save(this);
	}
	public void recovery(Save s){//还原备份
		this.name = s.getName();
		this.level = s.getLevel();
		this.money = s.getMoney();
		this.place = s.getPlace();
	}
	public Player(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "["+this.name+"][等级："+this.level+"  钱："+this.money+"  地点："+this.place+"]";
	}
}
/**
 * 备忘录类(需要保存对象的拷贝)
 */
class Save{
	private String name;
	private int level=1;
	private int money=0;
	private String place;
	public Save(Player p) {
		this.name = p.getName();
		this.level = p.getLevel();
		this.money = p.getMoney();
		this.place = p.getPlace();
	}
	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}
	public int getMoney() {
		return money;
	}
	public String getPlace(){
		return this.place;
	}
}
/**
 * 负责人类(管理拷贝)
 */
class SaveManager{
	List<Save> list = new ArrayList<Save>();
	public void add(Save s){
		list.add(s);
	}
	public Save get(int i){
		return list.get(i);
	}
}