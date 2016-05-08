package project.tank.alphe02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {
	public static final int XSPEED = 1;//横向移动速度
	public static final int YSPEED = 1;//纵向移动速度
	
	public static final int WIDTH = 30;//坦克宽度
	public static final int HEIGHT = 30;//坦克长度
	
	private boolean live = true;//是否还活着
	TankClient tc;
	private int teamNum;//所属队伍
	private boolean isAI=true;//是否电脑控制
	private Color[] teamColor = {Color.RED,Color.BLUE};
	private Color[] barrelColor = {Color.WHITE,Color.WHITE};
	
	private int x, y;//所在坐标
	private int oldX, oldY;//上一次所在坐标
	
	private static Random r = new Random();//随机数生成器
	private int step = r.nextInt(12) + 3;//生成随机的步数(AI用)
	
	private boolean bL=false, bU=false, bR=false, bD = false;//是否按下某方向键
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};//8方向+停止 枚举
	
	private Direction dir = Direction.STOP;//初始化运动方向
	private Direction ptDir = Direction.D;//初始化方向
	
	public Tank(int x, int y, int teamNum, Direction dir, TankClient tc){
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.teamNum = teamNum;
		this.dir = dir;
		this.tc = tc;
	}
	public Tank(int x, int y, int teamNum, Direction dir, TankClient tc,boolean isAI){
		this(x,y,teamNum,dir,tc);
		this.isAI = isAI;
	}
	public void drawTank(Graphics g){
		if(!live){
			return;
		}
		
		Color c = g.getColor();
		g.setColor(teamColor[this.teamNum]);
		g.fillOval(x, y, WIDTH, HEIGHT);
		drawBarrel(g);
		g.setColor(c);
		move();
	}
	
	public void move(){
		this.oldX = x;
		this.oldY = y;
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		if(isAI){
			Direction[] dirs = Direction.values();
			if(step == 0) {
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
			}			
			step --;
			
//			if(r.nextInt(40) > 38) this.fire();
		}
		if(!checkMoveable()){
			stay();
		}
	}
	
	//画炮筒
	private void drawBarrel(Graphics g){
		Color c = g.getColor();
		g.setColor(barrelColor[this.teamNum]);
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
		g.setColor(c);
	}
	
	//判断是否可移动
	private boolean checkMoveable(){
		return checkMoveableX() && checkMoveableY() && tc.checkMoveable(this);
	}
	private boolean checkMoveableX(){
		if(x < 0){
			x = 0;
			return false;
		}
		if(x + Tank.WIDTH > TankClient.GAME_WIDTH){
			x = TankClient.GAME_WIDTH - Tank.WIDTH;
			return false;
		}
		return true;
	}
	private boolean checkMoveableY(){
		if(y < 30){
			y = 30;
			return false;
		}
		if(y + Tank.HEIGHT > TankClient.GAME_HEIGHT){
			y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
			return false;
		}
		return true;
	}
	private void stay(){
		this.x = this.oldX;
		this.y = this.oldY;
	}
	//提供自身的碰撞位置+大小
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT :
			bL = true;
			break;
		case KeyEvent.VK_UP :
			bU = true;
			break;
		case KeyEvent.VK_RIGHT :
			bR = true;
			break;
		case KeyEvent.VK_DOWN :
			bD = true;
			break;
		}
		locateDirection();
	}
	
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

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		case KeyEvent.VK_LEFT :
			bL = false;
			break;
		case KeyEvent.VK_UP :
			bU = false;
			break;
		case KeyEvent.VK_RIGHT :
			bR = false;
			break;
		case KeyEvent.VK_DOWN :
			bD = false;
			break;
		case KeyEvent.VK_A :
//			superFire();
			break;
		}
		locateDirection();		
	}
	
	public Missile fire(){
		if(!live) return null;
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, this.ptDir, this.teamNum, this.tc);
		tc.missiles.add(m);
		return m;
	}
	public int getTeamNum(){
		return this.teamNum;
	}
	public void setDeath(){
		this.live = false;
		tc.tanks.remove(this);
//		tc.explodes.add(new Explode(this.x,this.y,this.tc));
	}
	
	
}
