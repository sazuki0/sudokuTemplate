import java.util.ArrayList;
import java.util.HashMap;

/* This class represents the puzzle grid of a sudoku puzzle's answer template.
 */

public class Template {

	private int[][] grid;
	private HashMap<Integer, boolean[]> rowVal, colVal, boxVal;

	public Template() {
		grid = new int[9][9];
		rowVal = new HashMap<Integer, boolean[]>();
		colVal = new HashMap<Integer, boolean[]>();
		boxVal = new HashMap<Integer, boolean[]>();
		for (int i = 0; i <= 8; i++) {
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
			if (i % 3 == 0) {
				System.out.print("-----------------------------");
				System.out.println();
			}
			for (int j = 0; j < grid[i].length; j++) {
				if (j % 3 == 0)
					System.out.print(" | ");
				System.out.print(grid[i][j] + " ");
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.print("-----------------------------");
	}

	// generate a complete key
	public void templateGenerator() {
		for (int i = 0; i < grid.length; i++) {
			ArrayList<Integer> gridnums = generateRandom();
			inputnum(gridnums, grid, i);
		}
	}

	private void inputnum(ArrayList<Integer> nums, int[][] grid, int i) {
		while(nums.size() > 0) {
			int num = nums.get(0);
			boolean filled = false;
			
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0 && val(num, i, j)) {
					grid[i][j] = num;
					fillin(num, i, j);
					nums.remove(0);
					filled = true;
					break;
				} 
			}
			
			
			// backtrack : put checknum back into numlist if num can be place into the spot 
			if(filled == false) {
				int j = (int)(Math.random()*grid[i].length);
					int checknum = grid[i][j];
					if (val(num, i, j)) {
						unfillin(checknum, i, j);
						grid[i][j] = num;
						nums.add(checknum);
						fillin(num,i,j);
						nums.remove(0);
					}
				
			}
			
			
			//test print
			displayGrid();
			System.out.println();
		}
	}

	private boolean val(int num, int i, int j) {
		if (!validateVertical(num, j))
			return false;
		if (!validateBoxes(num, i, j))
			return false;
		return true;
	}

	private ArrayList<Integer> generateRandom() {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i <= 10; i++) {
			int index = (int) (Math.random() * 9);
			int index2 = (int) (Math.random() * 9);

			int temp = nums[index];
			nums[index] = nums[index2];
			nums[index2] = temp;
		}

		ArrayList<Integer> numsr = new ArrayList<Integer>();
		for (int j = 0; j < nums.length; j++) {
			numsr.add(nums[j]);
		}
		return numsr;

	}

	private void fillin(int num, int i, int j) {
		boolean[] rowR = rowVal.get(i);
		boolean[] colC = colVal.get(j);
		boolean[] boxB = boxVal.get(getBox(i, j));

		rowR[num] = true;
		colC[num] = true;
		boxB[num] = true;

		rowVal.put(i, rowR);
		colVal.put(j, colC);
		boxVal.put(getBox(i, j), boxB);
	}

	private void unfillin(int num, int i, int j) {
		boolean[] rowR = rowVal.get(i);
		boolean[] colC = colVal.get(j);
		boolean[] boxB = boxVal.get(getBox(i, j));

		rowR[num] = false;
		colC[num] = false;
		boxB[num] = false;

		rowVal.put(i, rowR);
		colVal.put(j, colC);
		boxVal.put(getBox(i, j), boxB);
	}

	// validate a horizontal line
	public boolean validateHorizontal(int num, int r) {
		boolean[] rowR = rowVal.get(r);
		if (num != 0 && rowR[num]) {
			return false;
		}
		return true;
	}

	// validate a vertical line
	public boolean validateVertical(int num, int c) {
		boolean[] colC = colVal.get(c);
		if (num != 0 && colC[num]) {
			return false;
		}
		return true;
	}

	// validate 3x3s
	public boolean validateBoxes(int num, int r, int c) {
		int index = getBox(r, c);
		boolean[] boxIndex = boxVal.get(index);
		if (num != 0 && boxIndex != null && boxIndex[num]) {
			return false;
		}
		return true;
	}

	private int getBox(int r, int c) {
		r = r / 3;
		c = c / 3;

		if (r == 0)
			return r + c;
		else if (r == 1)
			return r + c + 2;
		else
			return r + c + 4;
	}

	// validate whole puzzle

}
