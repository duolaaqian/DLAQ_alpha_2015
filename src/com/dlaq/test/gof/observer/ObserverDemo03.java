package com.dlaq.test.gof.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 妈妈，他拿咱裤子了
 * 谁呀
 * 逗你玩
 */
public class ObserverDemo03 {
	public static void main(String[] args) throws InterruptedException {
		String[] arr = {"褂子","裤子","褥单子"};
		YiJia yijia = new YiJia(arr);
		XiaoTou dnw = new XiaoTou("逗你玩",yijia);
		XiaoHu hu = new XiaoHu();
		yijia.addObserver(hu);
		for(int i=0;i<arr.length;i++){
			dnw.tou();
			Thread.sleep(1000);
		}
	}
}
/**
 * 晾衣架
 */
class YiJia extends Observable{
	private int initSize;
	private String[] arr;
	public YiJia(String[] arr){
		this.arr = arr;
		this.initSize = arr.length;
	}
	public int getInitSize(){
		return initSize;
	}
	public void get(XiaoTou xt){
		int index = arr.length-1;
		String oldVal = arr[index];
		System.out.println("====["+xt.name+"]拿走了["+oldVal+"]");
		xt.hold = oldVal;
		String[] newArr = new String[index];
		System.arraycopy(arr, 0, newArr, 0, index);
		arr = newArr;
		setChanged();
		notifyObservers(xt);
	}
}
/**
 * 小偷(逗你玩)
 */
class XiaoTou{
	private YiJia yijia;
	String name;
	String hold;
	public XiaoTou(String name, YiJia yijia){
		this.name = name;
		this.yijia = yijia;
	}
	public void tou(){
		yijia.get(this);
	}
}
/**
 * 小虎
 */
class XiaoHu implements Observer{
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof XiaoTou){
			System.out.println("妈妈，他那咱*"+((XiaoTou)arg).hold+"*了!");
			System.out.println("谁呀？");
			System.out.println(((XiaoTou)arg).name);
		}
	}
}
