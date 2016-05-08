package com.dlaq.test.juc.demo01;

public class Processor implements Runnable{

	private String name;
	
	Processor(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++){
			System.out.println("["+name+"]"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
