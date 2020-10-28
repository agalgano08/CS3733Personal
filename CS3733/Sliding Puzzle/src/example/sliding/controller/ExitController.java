package example.sliding.controller;


import javax.swing.JOptionPane;

import example.sliding.boundary.SlidingPuzzleApp;
import example.sliding.boundary.UpdateButtons;
import example.sliding.model.Model;


public class ExitController {

	SlidingPuzzleApp app;
	
	
	public ExitController (SlidingPuzzleApp app) {
		this.app = app;
	}


	public void exit() {
		int c = JOptionPane.showConfirmDialog(app, "Do you want to exit application?");
		if (c== JOptionPane.OK_OPTION) {
			app.setVisible(false);
			app.dispose();
		}
	}
}
