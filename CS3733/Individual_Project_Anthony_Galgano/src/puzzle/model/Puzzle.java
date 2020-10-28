package puzzle.model;

import java.util.ArrayList;
import java.util.Iterator;





public class Puzzle implements Iterable<Tile>{
	public final int numRows;
	public final int numColumns;
	Tile winningTile;

	ArrayList<Tile> tiles = new ArrayList<>();
	ArrayList<Tile> originals = new ArrayList<>();

	public Puzzle(int numColumns, int numRows) {
		this.numColumns = numColumns;
		this.numRows = numRows;
	}

	public void add(Tile t, int col, int row) {
		t.setColumn(col);
		t.setRow(row);

		tiles.add(t);
		originals.add(t);
	}
	
	public void remove(Tile t) {
		tiles.remove(t);
	}

	public void setWinningTile(Tile tile) {
		winningTile = tile;
	}
	
	public ArrayList<Tile>  getTiles() {
		return tiles;
	}
	
	public int addTileValues(Tile t1, Tile t2) {
		return t1.getValue()+t2.getValue();
	}
	public int subTileValues(Tile t1, Tile t2) {
		return (int) t1.getValue() - t2.getValue();
	}
	public int multiplyTileValues(Tile t1, Tile t2) {
		return (int) t1.getValue()*t2.getValue();
	}
	public int divideTileValues(Tile t1, Tile t2) {
		return (int) t1.getValue()/t2.getValue();
	}
	
	
	@Override
	public Iterator<Tile> iterator(){
		return tiles.iterator();
	}

	public void replace(Tile t, int col, int row) {
		t.setColumn(col);
		t.setRow(row);

		tiles.add(t);	
	}

	public void reset() {
		tiles.clear();
		for (Tile t : originals) {
			t.setInPlay(true);
			tiles.add(t.copy());
		}
		
	}

	public boolean isWinner() {
		int tilesInPlay = 0;
		Coordinate winningCoordinate = new Coordinate(1, 1);
		
		for (Tile t : tiles) {
			if(t.getInPlay()) {
				tilesInPlay++;
				winningTile = t;
				}
		}
		
		if(tilesInPlay == 1 && winningTile.getLocation().equals(winningCoordinate) ) {

			return true;
		}
		return false;
	}





}
