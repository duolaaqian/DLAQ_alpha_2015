package com.dlaq.test.thread.pro;

public class App {
	public static void main(String[] args) {
		//公共资源
		Movie m = new Movie();
		Player p = new Player(m);
		Watcher w = new Watcher(m);
		
		new Thread(w).start();
		new Thread(p).start();
	}
}
