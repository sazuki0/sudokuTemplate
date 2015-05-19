import java.util.ArrayList;
import java.util.HashMap;

/* This class represents the puzzle grid of a sudoku puzzle.
 * 		Instance : int[][] grid
 * 		Method : displayGrid(), generatePuzzle(), validatePuzzle()
 */

public class Puzzle {

	private int[][] grid;
	private HashMap<Integer, boolean[]> rowVal, colVal, boxVal;

	public Puzzle() {
		grid = new int[9][9];
		rowVal = new HashMap<Integer, boolean[]>();
		colVal = new HashMap<Integer, boolean[]>();
		boxVal = new HashMap<Integer, boolean[]>();
		for(int i = 0; i <=8; i++) {
			rowVal.put(i, new boolean[10]);
			colVal.put(i, new boolean[10]);
			boxVal.put(i, new boolean[10]);
		}
	}

	public int[][] getGrid() {
		return grid;
	}


	public void displayGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	// generate a complete key
	public void puzzleGenerator() {
		for(int i = 0; i < grid.length; i++) {
			int num = 1;
			int vloc = 0;
			while(num < 10) {
				if(validateInd(num, i, vloc)) {
					grid[i][vloc] = num;
					fillin(num, i, vloc);
					num++;
				}
				vloc+=1;
				vloc = vloc % 9;
			}
			displayGrid();
			System.out.println();
		}
	}
	
	private void fillin(int num, int i, int j) {
		boolean[] rowR = rowVal.get(i);
		boolean[] colC =colVal.get(j);
		boolean[] boxB = boxVal.get(getBox(i,j));
		
		rowR[num] = true;
		colC[num]= true; 
		boxB[num] = true;
		
		rowVal.put(i, rowR);
		colVal.put(j, colC);
		boxVal.put(getBox(i,j), boxB);
	}
	
	private boolean validateInd(int num, int i, int j) {
		if(!validateVertical(num, j))
			return false;
//		if(!validateBoxes(num,i,j))
//			return false;
		return true;
	}

	// validate a horizontal line
	public boolean validateHorizontal(int num, int r) {
		boolean[] rowR = rowVal.get(r);
		if(num!=0 && rowR[num]) {
			return false;
		} 
		return true;
	}

	// validate a vertical line
	public boolean validateVertical(int num, int c) {
		boolean[] colC = colVal.get(c);
		if(num!=0 && colC[num]) {
			return false;
		} 
		return true;
	}

	// validate 3x3s
	public boolean validateBoxes(int num, int r, int c) {
		int index = getBox(r,c);
		boolean[] boxIndex = boxVal.get(index);
		if(num!=0 && num < boxIndex.length && boxIndex[num]) {
			return false;
		} 
		return true;
	}

	private int getBox(int r, int c) {
		r = r / 3;
		c = c / 3;
		
		if(r == 0)
			return r+c+1;
		else if(r==1)
			return r+c+3;
		else 
			return r+c+5;
	}
	
	// validate whole puzzle

}
