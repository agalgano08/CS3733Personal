package demo.model;

import java.awt.Rectangle;

public class Model {
	boolean headsUp = true;	
	String up;
	String down;
	Rectangle where;
	
	public String getUp() { return up; }
	public String getDown() { return down; }
	public boolean isHeadsUp() { return headsUp; }
	
	public Rectangle getWhere() { return where; }
	public void setWhere(Rectangle r) {  where = r; }
	
	//public void setHeadsUp(boolean b) {headsUp = b; }
	public void flip() {
		if(headsUp) {
			headsUp = false;
		}
		else {
			headsUp = true;
		}

	}
	
	public Model(String up, String down){
		this.up = up;
		this.down = down;
	}
	
	public String showing() {
		if(headsUp) {
			return up;
		}
		else return down;
		
	}
}
