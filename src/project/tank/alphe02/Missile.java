package project.tank.alphe02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Missile {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	int x, y;
	Tank.Direction dir;
	
	private int teamNum;//所属队伍
	private boolean live = true;
	
	private TankClient tc;
	
	public Missile(int x, int y, Tank.Direction dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Missile(int x, int y, Tank.Direction dir, int teamNum, TankClient tc){
		this(x,y,dir);
		this.tc = tc;
		this.teamNum = teamNum;
	}
	
	public void drawMissile(Graphics g){
		if(!live){
			tc.missiles.remove(this);
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		move();
	}
	private void move(){
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
		
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
//			live = false;
//			tc.missiles.remove(this);
			this.setDeath();
		}
		
		if(tc.checkMissileTouch(this)){
			List<Tank> l = tc.getTouchItems(this);
			for(Tank t : l){
				if(!isFriendly(t.getTeamNum())){
					t.setDeath();
					this.setDeath();
				}
			}
		}
		
	}
	//提供自身的碰撞位置+大小
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	private boolean isFriendly(int teamNum){
		return this.teamNum == teamNum;
	}
	public void setDeath(){
		this.live = false;
		tc.missiles.remove(this);
		tc.explodes.add(new Explode(this.x,this.y,this.tc));
	}
	
}
