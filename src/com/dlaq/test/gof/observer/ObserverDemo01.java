package com.dlaq.test.gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟 观察者模式
 * 场景：
 * 	小孩睡觉，家长工作
 * 	小孩醒来，家长喂饭
 */
public class ObserverDemo01 {
	public static void main(String[] args) throws InterruptedException {
		MyBaby b = new MyBaby();
		MyParent p1 = new MyParent("爸爸");
		MyParent p2 = new MyParent("妈妈");
		MyParent p3 = new MyParent("姥姥");
		MyParent p4 = new MyParent("奶奶");
		b.addObserver(p1);
		b.addObserver(p2);
		b.addObserver(p3);
		b.addObserver(p4);
		for(int i=0;i<5;i++){
			Thread.sleep(1000);
			if(b.isAwake()){
				b.sleep();
			}else{
				b.wakeUp();
			}
		}
	}
}

/**
 * 监听者
 */
interface IMyObserver{
	void update(MyObservable obs);
}
/**
 * 被监听者
 */
class MyObservable{
	protected List<IMyObserver> list = new ArrayList<IMyObserver>();
	
	public void addObserver(IMyObserver obs){
		list.add(obs);
	}
	public void removeObserver(IMyObserver obs){
		list.add(obs);
	}
	public void notifyAllObservers(){
		for (IMyObserver obs : list) {
			obs.update(this);
		}
	}
}
/**
 * 以下为监听者、被监听者实例
 */
class MyBaby extends MyObservable{
	private boolean isAwake = false;
	public boolean isAwake(){
		return this.isAwake;
	}
	public void wakeUp(){
		System.out.println("baby醒了");
		this.isAwake = true;
		notifyAllObservers();
	}
	public void sleep(){
		System.out.println("baby睡了");
		this.isAwake = false;
		notifyAllObservers();
	}
}
class MyParent implements IMyObserver{
	private String name;
	public MyParent(String name){
		this.name = name;
	}
	@Override
	public void update(MyObservable obs) {
		if( ((MyBaby)obs).isAwake() ){
			System.out.println("["+name+"]来喂饭");
		}else{
			System.out.println("["+name+"]去工作");
		}
	}
}
