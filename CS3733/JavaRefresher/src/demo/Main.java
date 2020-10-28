package demo;

import java.awt.EventQueue;
import java.awt.Rectangle;

import demo.model.Model;
import demo.view.DemoApp;

public class Main {

	public static void main(String[] args) {
		Model m = new Model("Heads", "Tails");
		m.setWhere(new Rectangle(100, 100, 100, 50));

		DemoApp frame = new DemoApp(m);
		frame.setVisible(true);

		System.out.println(m.showing());
		m.flip();
		System.out.println(m.showing());
	}

}
