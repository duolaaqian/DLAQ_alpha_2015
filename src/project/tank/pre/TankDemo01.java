package project.tank.pre;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankDemo01 {
	public static void main(String[] args) {
		new TankClient().launchFrame();
	}
}

class TankClient extends Frame{
	
	private static final long serialVersionUID = 1L;
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final int GAME_X = 260;
	public static final int GAME_Y = 80;
	
	int x=50,y=50;
	
	Image offScreenImage = null;
	
	public void launchFrame(){
		this.setLocation(GAME_X, GAME_Y);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
		
		this.setBackground(Color.GREEN);
		
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();
	}
	public void paint1(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
		g.setColor(c);
	}
	public void update(Graphics g){
		if(null == offScreenImage){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint1(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	//键盘监听事件
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_LEFT:
				x -=5;
				break;
			case KeyEvent.VK_RIGHT:
				x +=5;
				break;
			case KeyEvent.VK_UP:
				y -=5;
				break;
			case KeyEvent.VK_DOWN:
				y +=5;
				break;
			}
		}
	}
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}