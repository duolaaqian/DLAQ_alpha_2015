package com.dlaq.test.thread.pro.em;

/**
 * 生产者(完成技能CD)
 */
public class War3 implements Runnable{
	private EM em;
	public War3(EM em){
		this.em = em;
	}
	@Override
	public void run() {
		while(true){
			em.skillCD();
		}
	}
}
