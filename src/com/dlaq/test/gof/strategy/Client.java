package com.dlaq.test.gof.strategy;

/**
 * 策略模式
 * 
 * 	1、定义策略接口
 * 	2、定义策略上下文
 * 	3、客户端通过context来调用不同的策略
 * 
 * 具体策略都继承统一接口，策略之间可以任意替换
 * 增加新的策略不会对以前的策略造成影响
 */
public class Client {
	public static void main(String[] args) {
		double money = 1000;
		
		CashContext cc1 = new CashContext(new CashNormal());
		CashContext cc2 = new CashContext(new CashRebate(0.8));
		CashContext cc3 = new CashContext(new CashReturn(500,50));
		
		System.out.println("原价：		"+cc1.getResult(money));
		System.out.println("八折：		"+cc2.getResult(money));
		System.out.println("满500减50：	"+cc3.getResult(money));
	}
}
