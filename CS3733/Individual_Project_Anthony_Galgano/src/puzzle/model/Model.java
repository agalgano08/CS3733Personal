package puzzle.model;

import java.util.ArrayList;
import java.util.List;


public class Model {
	Puzzle puzzle;
	boolean gameOver;
	Tile selectedTile;
	Tile upTile;
	Tile downTile;
	Tile leftTile;
	Tile rightTile;

	public Model() {

	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setSelectedTile(Tile t) {
		selectedTile = t;
	}

	public void clearSelectedTile() {
		selectedTile = null;
	}

	public Tile getSelectedTile() {
		return selectedTile;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean flag) {
		gameOver = flag;
	}

	public void setPuzzle(Puzzle p) {
		puzzle = p;
		gameOver = false;
		selectedTile = null;

	}
	


	public boolean tryMove(MoveType dir) {
		int newValue;
		Tile newTile;
		if (selectedTile == null) {
			return false;
		}
		for (MoveType move : avaliableMoves()) {
			if (dir == move) {
				if (dir == MoveType.Up) {
					newValue = puzzle.multiplyTileValues(upTile, selectedTile);
					newTile = new Tile(1, 1, newValue, true);
					puzzle.remove(upTile);
					puzzle.replace(newTile, upTile.col, upTile.row);
					selectedTile.setInPlay(false);
				}

				else if (dir == MoveType.Left) {
					newValue = puzzle.subTileValues(leftTile, selectedTile);
					newTile = new Tile(1, 1, newValue, true);
					puzzle.remove(leftTile);
					puzzle.replace(newTile, leftTile.col, leftTile.row);
					selectedTile.setInPlay(false);
				}

				else if (dir == MoveType.Right) {
					newValue = puzzle.addTileValues(rightTile, selectedTile);
					newTile = new Tile(rightTile.height, rightTile.width, newValue, true);
					puzzle.remove(rightTile);
					puzzle.replace(newTile, rightTile.col, rightTile.row);
					selectedTile.setInPlay(false);
				}

				else if (dir == MoveType.Down) {
					newValue = puzzle.divideTileValues(downTile, selectedTile);
					newTile = new Tile(1, 1, newValue, true);
					puzzle.remove(downTile);
					puzzle.replace(newTile, downTile.col, downTile.row);
					selectedTile.setInPlay(false);
				}
				return true;
			}
		}

		return true;
	}

	public List<MoveType> avaliableMoves() {
		ArrayList<MoveType> moves = new ArrayList<>();
		if (selectedTile == null ) {
			return moves;
		}
		
		return avaliableMoves(selectedTile);
	}
	
	
	
	public List<MoveType> avaliableMoves(Tile t) {
		ArrayList<MoveType> moves = new ArrayList<>();
		if (selectedTile == null) {
			return moves;
		}
		findSurroundTiles(t);
		Coordinate tileCoord = t.getLocation();
		boolean available = true;
		//Up
		if (tileCoord.row > 0) {
			if(upTile == null) {
				available = false;
			}
			else if ((upTile.getValue() * t.getValue()) <= 0) {
				available = false;
			}
			if (available) {
				moves.add(MoveType.Up);
			}
		}
		
		//Left
		available = true;
		if (tileCoord.col > 0 ) {
			if(leftTile == null) {
				available = false;
			}
			else if ((leftTile.getValue() - t.getValue()) <= 0) {
				available = false;
			}
			if (available) {
				moves.add(MoveType.Left);
			}
		}
		
		//Right
		available = true;
		if (tileCoord.col + t.width < puzzle.numColumns) {

			if(rightTile == null) {
				available = false;
			}
			else if ((t.getValue() + rightTile.getValue()) <= 0) {
				available = false;
			}
			if (available) {
				moves.add(MoveType.Right);
			}
		}
		//Down
		available = true;
		if (tileCoord.row + t.height < puzzle.numColumns) {
			if(downTile == null) {
				available = false;
			}
			else if ((downTile.getValue() / t.getValue()) <= 0 || (downTile.getValue() % t.getValue() ) != 0) {
				available = false;
			}
			if (available) {
				moves.add(MoveType.Down);
			}
		}
		

		return moves;
	}
	
	
	
	
	public void findSurroundTiles(Tile t) {
		upTile = null;
		downTile = null;
		leftTile = null;
		rightTile = null;
		
		if (!t.getInPlay()) {
			return;
		}
		
		for (Tile tile : puzzle) {
			//Up
			if (t.getRow() - 1 >= 0) {
				if (tile.getRow() == t.getRow() - 1 && tile.getColumn() == t.getColumn() && tile.getInPlay()) {
					upTile = tile;
				}
				
			} else {
				upTile = null;
			}

			//Down
			if (t.getRow() + 1 < puzzle.numRows) {
				if (tile.getRow() == t.getRow() + 1 && tile.getColumn() == t.getColumn()&& tile.getInPlay()) {
					downTile = tile;
				}
			} else {
				downTile = null;
			}

			//Left
			if (t.getColumn() - 1 >= 0) {
				if (tile.getRow() == t.getRow() && tile.getColumn() == t.getColumn() - 1&& tile.getInPlay()) {
					leftTile = tile;
				}
			} else {
				leftTile = null;
			}
			
			//Right
			if (t.getColumn() + 1 < puzzle.numColumns) {
				if (tile.getRow() == t.getRow() && tile.getColumn() == t.getColumn() + 1&& tile.getInPlay()) {
					rightTile = tile;
				}
			} else {
				rightTile = null;
			}
			
		}
		
	
	}

	public void resetPuzzle() {
		puzzle.reset();
		selectedTile = null;
		gameOver = false;
		
	}

	public boolean isWinCondition() {
		if(selectedTile == null) { return false; }
	
		return puzzle.isWinner();
	}

	public boolean isLoseCondition() {
		if(selectedTile == null) { return false; }

		int movesLeft = 0;
		for (Tile t : puzzle) {
			System.out.println(movesLeft);
			if (t.getInPlay()) {
				movesLeft = movesLeft + avaliableMoves(t).size();
			}

		}
		if (movesLeft == 0) {
			return true;
		}
		
		return false;
	}




}
