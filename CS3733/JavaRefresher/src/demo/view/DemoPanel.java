package demo.view;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import demo.model.Model;

public class DemoPanel extends JPanel {
	
	Model model;

	/**
	 * Create the panel.
	 */
	public DemoPanel(Model model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		Rectangle r = model.getWhere();
		
		g.drawRect(r.x, r.y, r.width, r.height);
		g.drawString(model.showing(), r.x, r.y + (r.height/2));
	}
}
