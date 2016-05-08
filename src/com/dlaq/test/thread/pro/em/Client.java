package com.dlaq.test.thread.pro.em;

/**
 * 生产者消费者问题
 * 	同一场景，同一资源
 * 
 * DOTA1 灰烬之灵(火猫)的 火之余烬(大招) 技能
 * 
 * 生产者：war3，当技能个数不足3时，则会技能技能CD，产生 火之余烬 技能
 * 消费者：玩家，当技能格数不为0时，可以发动技能
 * 同一场景：英雄 火猫
 * 同一资源：技能 火之余烬
 */
public class Client {
	public static void main(String[] args) throws InterruptedException {
		EM em = new EM();
		War3 war3 = new War3(em);
		Player p = new Player(em);
		
		new Thread(war3).start();
		Thread.sleep(2000);//学完大招后等待充能
		new Thread(p).start();
	}
}
