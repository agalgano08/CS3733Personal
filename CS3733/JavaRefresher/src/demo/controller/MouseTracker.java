package demo.controller;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import demo.model.Model;
import demo.view.DemoApp;
import demo.view.DemoPanel;

//Tracks mouse and reports location to the console
public class MouseTracker extends MouseAdapter{

	Random rnd = new Random();
	Model model;
	DemoApp app;
	
	public MouseTracker(Model m, DemoApp app) {
		this.model =m;
		this.app = app;
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		// IF PRESS WITHIN RECTANGLE FLIP AND RANDOMLY MOVE!!!
		Rectangle r = model.getWhere();
		if(r.contains(me.getPoint())){
			model.flip();
			
			DemoPanel panel = app.getPanel();
			int x = (int) (panel.getWidth()* rnd.nextDouble());
			int y = (int) (panel.getHeight() * rnd.nextDouble());
			r = new Rectangle(x,y,100,50);
			model.setWhere(r);
			app.repaint();
		}
	}
	
	
}
