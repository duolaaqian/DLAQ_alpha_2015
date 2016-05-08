package project.tank.alphe01;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame{
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
	}

	private static final long serialVersionUID = 1L;
	
	public static final int GAME_WIDTH = 800;//窗口宽度
	public static final int GAME_HEIGHT = 600;//窗口高度
	public static final int GAME_X = 260;//窗口位置
	public static final int GAME_Y = 80;//窗口位置
	
	Tank myTank = new Tank(50,50,this);//玩家控制坦克
	
	Image offScreenImage = null;
	
	List<Missile> missiles = new ArrayList<Missile>();
	
	public void myPaint(Graphics g){
		g.drawString("missiles count:" + missiles.size(), 10, 50);
		
		for(int i=0;i<missiles.size();i++){
			missiles.get(i).draw(g);
		}
		
		myTank.draw(g);
	}
	//真正刷新画面方法
	public void update(Graphics g){
		if(null == offScreenImage){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		myPaint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void lauchFrame(){
		this.setLocation(GAME_X, GAME_Y);//位置
		this.setSize(GAME_WIDTH, GAME_HEIGHT);//大小
		this.setResizable(false);//大小是否可改变
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//关闭窗口则退出程序
			}
		});
		this.addKeyListener(new KeyMonitor());
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	//键盘监听事件
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}
	}
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
