package example.sliding.controller;

import java.util.ArrayList;

import example.sliding.boundary.SlidingPuzzleApp;
import example.sliding.boundary.UpdateButtons;
import example.sliding.model.Model;
import example.sliding.model.MoveType;

public class MovePieceController {

	Model model;
	SlidingPuzzleApp app;
	
	
	public MovePieceController (Model model, SlidingPuzzleApp app) {
		this.model = model;
		this.app = app;
	}
	
	public boolean move(MoveType dir) {
		if(model.getSelectedPiece() == null) {return false;}
		
		if(model.isGameOver()) {return false;}
		
		if(model.isWinCondition(dir)) {
			model.setGameOver(true);
			app.repaint();
		}
		
		if(model.tryMove(dir)) {
			UpdateButtons.enableButtons(app, model.avaliableMoves());
			
			app.getActualMovesLabel().setText("" + model.getNumMoves());
			app.repaint();
		}
		
		
		return true;
	}


}
