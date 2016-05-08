package com.dlaq.test.thread.pro.em;

/**
 * 灰烬之灵EM(同一场景、同一资源)
 */
public class EM {
	/**
	 * 最大充能数量
	 */
	private final int MAX_F_COUNT = 3;
	/**
	 * 火之余烬F 充能个数
	 */
	private int count;
	/**
	 * 火之余烬 个数是否已满
	 */
	private boolean isFull(){
		return count >= MAX_F_COUNT;
	}
	/**
	 * 火之余烬 个数是否不为0
	 */
	private boolean isHas(){
		return count > 0;
	}
	/**
	 * 放技能
	 */
	public synchronized void skillRelease(){
		if(!isHas()){
			try {
				System.out.println("技能CD，不能释放技能");
				this.wait();//玩家等待技能CD(此处放在Player类中可能更恰当)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);//技能前摇^_^
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count--;
		System.out.println("-释放1个火之灰烬[剩余："+count+"]");
		this.notify();
	}
	/**
	 * 技能CD
	 */
	public synchronized void skillCD(){
		if(isFull()){
			try {
				System.out.println("能量充满");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
		System.out.println("+完成1个火之灰烬充能[剩余："+count+"]");
		this.notify();
	}
}
