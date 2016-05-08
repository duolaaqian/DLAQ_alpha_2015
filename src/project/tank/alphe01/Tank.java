package project.tank.alphe01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {
	public static final int XSPEED = 2;//X方向速度
	public static final int YSPEED = 2;//Y方向速度
	
	public static final int WIDTH = 30;//坦克宽
	public static final int HEIGHT = 30;//坦克宽
	
	private int x,y;//坦克位置
	
	private boolean bL=false, bU=false, bR=false, bD = false;//是否按下 左、上、右、下 按钮
	
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};//运行方向类型
	private Direction dir = Direction.STOP;//坦克初始为停止
	private Direction ptDir = Direction.D;//坦克炮筒初始为向下
	
	private TankClient tc;
	
	public Tank(int x,int y){
		this.x = x;
		this.y = y;
	}
	public Tank(int x,int y,TankClient tc){
		this(x,y);
		this.tc = tc;
	}
	
	//画坦克
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		switch(ptDir) {
		case L:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y);
			break;
		case U:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
			break;
		case RU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y);
			break;
		case R:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT/2);
			break;
		case RD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT);
			break;
		case D:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y + Tank.HEIGHT);
			break;
		case LD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT);
			break;
		default:
			break;
		}
		
		move();
	}
	
	//每次画都移动
	void move(){
		switch(dir){
		case L:{
			x -= XSPEED;
			break;
		}
		case LU:{
			x -= XSPEED;
			y -= YSPEED;
			break;
		}
		case U:{
			y -= YSPEED;
			break;
		}
		case RU:{
			x += XSPEED;
			y -= YSPEED;
			break;
		}
		case R:{
			x += XSPEED;
			break;
		}
		case RD:{
			x += XSPEED;
			y += YSPEED;
			break;
		}
		case D:{
			y += YSPEED;
			break;
		}
		case LD:{
			x -= XSPEED;
			y += YSPEED;
			break;
		}
		case STOP:{
			break;
		}
		}
		
		if(dir != Direction.STOP){
			ptDir = dir;
		}
		
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x+Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if(y+Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
		
		
	}
	
	//按键按下事件
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:{
			bL = true;
			break;
		}
		case KeyEvent.VK_UP:{
			bU = true;
			break;
		}
		case KeyEvent.VK_RIGHT:{
			bR = true;
			break;
		}
		case KeyEvent.VK_DOWN:{
			bD = true;
			break;
		}
		}
		
		locateDirection();
	}
	
	//运行方向
	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}
	//释放按键事件
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :{
			bL = false;
			break;
		}
		case KeyEvent.VK_UP :{
			bU = false;
			break;
		}
		case KeyEvent.VK_RIGHT :{
			bR = false;
			break;
		}
		case KeyEvent.VK_DOWN :{
			bD = false;
			break;
		}
		case KeyEvent.VK_CONTROL :{
			fire();
			break;
		}
		}
		locateDirection();
	}
	
	void fire(){
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, ptDir, this.tc);
		tc.missiles.add(m);
	}
	
}
