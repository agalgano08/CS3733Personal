package example.sliding.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

	Puzzle puzzle;
	boolean gameOver;
	Piece selectedPiece;
	int numMoves = 0;
	
	public Model() {

	}
	
	
	public boolean tryMove(MoveType dir) {
		if(selectedPiece == null) { return false;}
		
		for(MoveType move : avaliableMoves()) {
			if(dir == move) {
				selectedPiece.move(dir);
				numMoves++;
				return true;
			}
		}
		
		return true;
	}
	
	public List<MoveType> avaliableMoves() {
		ArrayList<MoveType> moves = new ArrayList<>();
		if (selectedPiece == null) {
			return moves;
		}
		return avaliableMoves(selectedPiece);
	}
	
	public List<MoveType> avaliableMoves(Piece p) {
		ArrayList<MoveType> moves = new ArrayList<>();
		Coordinate coord = p.getLocation();
		
		if(p.isWinner()) {
			int exitRow = puzzle.getExitRow();
			int exitCol = puzzle.getExitColumn();
			MoveType dir = puzzle.getExitDirection();
			Coordinate exitCoord = new Coordinate(exitCol, exitRow);
			if(coord.equals(exitCoord)) {
				moves.add(MoveType.Down);
			}
		}
		
		boolean available = true;
		if (coord.col > 0) {

			// Left
			for (int r = 0; r < p.height; r++) {
				if (puzzle.isCovered(new Coordinate(coord.col - 1, coord.row))) {
					available = false;
				}
			}
			if (available) {
				moves.add(MoveType.Left);
			}
		}
		
		if (coord.col + p.width< puzzle.numColumns) {
			available = true;
			for (int r = 0; r < p.height; r++) {
				if (puzzle.isCovered(new Coordinate(coord.col + p.width, coord.row))) {
					available = false;
				}
			}
			if (available) {
				moves.add(MoveType.Right);
			}
		}
		
		if (coord.row > 0) {
			available = true;
			// Left
			for (int c = 0; c < p.height; c++) {
				if (puzzle.isCovered(new Coordinate(coord.col, coord.row-1))) {
					available = false;
				}
			}
			if (available) {
				moves.add(MoveType.Up);
			}
		}
		
		if (coord.row +p.height < puzzle.numRows) {
			available = true;
			// Left
			for (int c = 0; c < p.height; c++) {
				if (puzzle.isCovered(new Coordinate(coord.col, coord.row+p.height))) {
					available = false;
				}
			}
			if (available) {
				moves.add(MoveType.Down);
			}
		}
		
		
		return moves;
		
	}
	
	public void setPuzzle(Puzzle p ) { 
		puzzle = p;
		numMoves = 0;
		gameOver = false;
		selectedPiece = null;
		
	}
	public Puzzle getPuzzle( ) {  return puzzle;}
	
	public void setSelectedPiece(Piece p ) { selectedPiece = p;}
	public void clearSelectedPiece() { selectedPiece = null;}
	public Piece getSelectedPiece() {  return selectedPiece;}
	
	public boolean isGameOver() {return gameOver;}
	public void setGameOver( boolean flag) {gameOver = flag;}


	public int getNumMoves() {
		return numMoves;
	}
	
	public void resetPuzzle() {
		puzzle.reset();
		selectedPiece = null;
		numMoves = 0;
		gameOver = false;
		
	}


	public boolean isWinCondition(MoveType dir) {
		if(selectedPiece == null) { return false; }
		if(!selectedPiece.isWinner) {return false;}
		
		
		return puzzle.isWinCondition(selectedPiece.getColumn(), selectedPiece.getRow(), dir);
	}
	
}
