/* This class represents the puzzle grid of a sudoku puzzle.
 * 		Instance : int[][] grid
 * 		Method : displayGrid(), generatePuzzle(), validatePuzzle()
 */

public class Puzzle {

	private int[][] grid;

	public Puzzle() {
		grid = new int[9][9];
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
		
		for(int r = 0; r < grid.length; r+=3) {
			boolean[] check = new boolean[10];
			boolean done = false;
			while(!done) {
				for(int c = 0; c < grid[r].length; c+=3) {
					int a = 0;
				}
			}
		}
	}
	
	// validate a horizontal line
	public boolean validateHorizontal(int[][] grid, int r) {
		boolean[] check = new boolean[10];
		for (int j = 0; j < grid[r].length; j++) {
			if (grid[r][j] == 0)
				break;
			if (check[grid[r][j]])
				return false;
			else
				check[grid[r][j]] = !check[grid[r][j]];
		}
		return true;
	}

	// validate a vertical line
	public boolean validateVertical(int[][] grid, int c) {
		boolean[] check = new boolean[10];
		for (int i = 0; i < grid[i].length; i++) {
			if (grid[i][c] == 0)
				break;
			if (check[grid[i][c]])
				return false;
			else
				check[grid[i][c]] = !check[grid[i][c]];
		}
		return true;
	}

	// validate 3x3s
	public boolean validateBoxes(int[][] grid, int r, int c) {
		boolean[] check = new boolean[10];
		r = boxLocator(r);
		c = boxLocator(c);
		for (int i = r; i <= r + 2; i++) {
			for (int j = c; j <= c + 2; j++) {
				if (grid[i][j] == 0)
					break;
				if (check[grid[i][j]])
					return false;
				else
					check[grid[i][j]] = !check[grid[i][j]];
			}
		}
		return true;
	}

	// converts r and c location to 3x3 boxes
	public int boxLocator(int i) {
		if (i < 3)
			return 0;
		if (i < 6)
			return 3;
		return 6;
	}

	// validate whole puzzle
	public boolean validatePuzzle() {
		return validatePuzzle(this.getGrid());
	}

	public boolean validatePuzzle(int[][] grid) {
		// horizontal
		for (int i = 0; i < grid.length; i++) {
			if (validateHorizontal(grid, i) == false)
				return false;
		}

		// vertical
		for (int j = 0; j < grid.length; j++) {
			if (validateVertical(grid, j) == false)
				return false;
		}
		// 3x3
		for (int i = 0; i < grid.length; i += 3) {
			for (int j = 0; j < grid.length; j += 3) {
				if (validateBoxes(grid, i, j) == false)
					return false;
			}
		}
		return true;
	}

}
