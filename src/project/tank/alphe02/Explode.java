package project.tank.alphe02;

import java.awt.Color;
import java.awt.Graphics;

//爆炸效果类
public class Explode {
	private int x, y;
	private boolean live = true;
	private TankClient tc;
	
	int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
	int step = 0;
	
	public Explode(int x,int y,TankClient tc){
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			setDeath();
			return;
		}
		if(step == diameter.length) {
			setDeath();
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(c);
		
		step ++;
	}
	
	public void setDeath(){
		this.live = false;
		tc.explodes.remove(this);
	}
	
}
