package example.sliding.controller;


import example.sliding.boundary.SlidingPuzzleApp;
import example.sliding.boundary.UpdateButtons;
import example.sliding.model.Model;


public class ResetController {

	Model model;
	SlidingPuzzleApp app;
	
	
	public ResetController (Model model, SlidingPuzzleApp app) {
		this.model = model;
		this.app = app;
	}


	public void reset() {
		model.resetPuzzle();
		UpdateButtons.enableButtons(app, model.avaliableMoves());	
		app.getActualMovesLabel().setText("" + model.getNumMoves());
		app.repaint();
	}
}
