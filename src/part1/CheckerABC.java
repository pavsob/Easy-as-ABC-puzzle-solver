package part1;

import mains.Grid;
import mains.Puzzle;

public class CheckerABC {

	public CheckerABC() {}

    public static boolean isConsistent(Puzzle puzzle, Grid grid) {
        return isConsistent(puzzle, grid.chars, grid.size());
	} 

    public static boolean isConsistent(Puzzle puzzle, char[][] chars, int gSize) {

		if (!puzzle.checkValid() || puzzle.size() != gSize) {
			return false;
		}

		if (sameLetterRows(chars, puzzle)) {
			return false;
		}

		if (sameLetterColumn(chars, puzzle)) {
			return false;
		}

		if (!clueConstraint(chars, puzzle)) {
			return false;
		}

		if (!isValidSymbol(chars, puzzle)) {
			return false;
		}

        return true;
    }

	private static boolean isValidSymbol(char[][] chars, Puzzle puzzle) {
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j < chars.length; j++) {
				if (!puzzle.validSymbol(chars[i][j]) && chars[i][j] != Puzzle.unfilledChar) {
					return false;
				}
			}
		}
		return true;
	}

	// Checks whether the rule for clues is obeyed
	private static boolean clueConstraint(char[][] chars, Puzzle puzzle) {
		int maxBlankSymbol = chars.length - puzzle.numLetters();

		// Left side clues
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j <= maxBlankSymbol; j++) {
				if (chars[i][j] == Puzzle.unfilledChar || chars[i][j] == puzzle.left[i] || puzzle.left[i] == Puzzle.unfilledChar) {
					break;
				} else if (chars[i][j] != Puzzle.blankSymbol) {
					return false;
				}
			}
		}

		// Right side clues
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j <= maxBlankSymbol; j++) {
				int backwardsIndex = (chars.length-1)-j;
				if (chars[i][backwardsIndex] == Puzzle.unfilledChar || chars[i][backwardsIndex] == puzzle.right[i] || puzzle.left[i] == Puzzle.unfilledChar) {
					break;
				} else if (chars[i][backwardsIndex] != Puzzle.blankSymbol) {
					return false;
				}
			}
		}

		// Top side clues
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j <= maxBlankSymbol; j++) {
				if (chars[j][i] == Puzzle.unfilledChar || chars[j][i] == puzzle.top[i] || puzzle.left[i] == Puzzle.unfilledChar) {
					break;
				} else if (chars[j][i] != Puzzle.blankSymbol) {
					return false;
				}

			}
		}

		// Bottom side clues
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j <= maxBlankSymbol; j++) {
				int backwardsIndex = (chars.length-1)-j;
				if (chars[backwardsIndex][i] == Puzzle.unfilledChar || chars[backwardsIndex][i] == puzzle.bottom[i] || puzzle.left[i] == Puzzle.unfilledChar) {
					break;
				} else if (chars[backwardsIndex][i] != Puzzle.blankSymbol) {
					return false;
				}
			}
		}
		return true;
	}


	private static boolean sameLetterRows(char[][] chars, Puzzle puzzle) {
		for (int i = 0; i < chars.length; i++) {
			String row = "";
			for (int j = 0; j < chars.length; j++) {
				row += chars[i][j];
			}
			if (sameLetter(row, puzzle)) {
				return true;
			}
		}
		return false;
	}

	private static boolean sameLetterColumn(char[][] chars, Puzzle puzzle) {
		for (int i = 0; i < chars.length; i++) {
			String column = "";
			for (int j = 0; j < chars.length; j++) {
				column += chars[j][i];
			}
			if (sameLetter(column, puzzle)) {
				return true;
			}
		}
		return false;
	}

	private static boolean sameLetter(String letters, Puzzle puzzle) {
		int maxBlankSymbol = letters.length() - puzzle.numLetters();
		for (char i : letters.toCharArray()) {
			int count = (int)letters.chars().filter(ch -> ch == i).count();
			int countBlank = (int)letters.chars().filter(ch -> ch == Puzzle.blankSymbol).count();

			if ((count > 1 && puzzle.validLetter(i)) || maxBlankSymbol < countBlank) {
				return true;
			}
		}
		return false;
	}



    // To find out whether it is properly filled
    public static boolean isFullGrid(Puzzle puzzle, Grid grid) {
		return isFullGrid(puzzle, grid.chars, grid.size());      
	}

    public static boolean isFullGrid(Puzzle puzzle, char[][] chars, int gSize ) {
		// Here is checked whether the grid is fully filled and ready for evaluation
		// Validity of the puzzle is checked

		if (!puzzle.checkValid() || puzzle.size() != gSize) { //zamyslet se jestli porovnavat size
			return false;
		}
		for(int i=0; i < gSize; i++) {
            for(int j=0; j < gSize; j++) {
                if(!puzzle.validSymbol(chars[i][j])) { 
                    return false;
                }
            }
		}
        return true;
	}

    public static boolean isSolution(Puzzle puzzle, Grid grid) {
		return isFullGrid(puzzle,grid) && isConsistent(puzzle,grid);
    }
}
