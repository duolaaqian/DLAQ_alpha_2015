package com.dlaq.test.gof.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式(JDK提供了相应的工具类)
 * 
 * 	1、创建被观察者(baby)继承 java.util.Observable
 * 		在需要观察的动作(方法)完成后调用setChanged()方法和notifyObservers()方法通知观察者
 * 	2、创建观察者(parent)继承 java.util.Observer 重写 update() 方法
 * 	3、创建双方实例并将 观察者 注册到 被观察者 中(addObserver()方法)
 * 
 * 	实现观察/广播效果，避免了使用线程实现带来的大量开销
 * 	观察者/被观察者之间的关系也容易维护
 * 
 * 注意：
 * 	1、观察者实例销毁前要通过deleteObserver()方法从监听列表中删除掉
 * 		否则对象之间依然会保持引用关系 对象未销毁 广播依然有效
 * 	2、避免循环依赖(死循环)
 * 	3、多线程下要小心
 * 
 * 应用场景：
 * 	网络编程、AWT、Android
 */
public class ObserverDemo02 {
	public static void main(String[] args) throws InterruptedException {
		Baby b = new Baby();
		Parent p1 = new Parent("爸爸");
		Parent p2 = new Parent("妈妈");
		Parent p3 = new Parent("姥姥");
		Parent p4 = new Parent("奶奶");
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
class Baby extends Observable{
	private boolean isAwake = false;
	public boolean isAwake(){
		return this.isAwake;
	}
	public void wakeUp(){
		System.out.println("baby醒了");
		this.isAwake = true;
		setChanged();
		notifyObservers();
	}
	public void sleep(){
		System.out.println("baby睡了");
		this.isAwake = false;
		setChanged();
		notifyObservers();
	}
}
class Parent implements Observer{
	private String name;
	public Parent(String name){
		this.name = name;
	}
	@Override
	public void update(Observable o, Object arg) {
		if( ((Baby)o).isAwake() ){
			System.out.println("["+name+"]来喂饭");
		}else{
			System.out.println("["+name+"]去工作");
		}
	}
}