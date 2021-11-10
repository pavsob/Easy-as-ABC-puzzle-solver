package part2;

import java.util.ArrayList;

import mains.Grid;
import mains.Puzzle;
import mains.gridToString;

public class ProceduralABC {

	public ProceduralABC() {}

    public static Grid differentCorners(Puzzle puzzle, Grid grid) {
        int begIdx = 0;
        int lastIdx = puzzle.size() - 1;
        // Checks whether there are different letters, if so it assigns blankSymbol to the appropriate place in the grid (Unless one of the clues is infilledChar)
        // Top left corner
        if (puzzle.left[begIdx] != puzzle.top[begIdx] && (puzzle.left[begIdx] != puzzle.unfilledChar && puzzle.top[begIdx] != puzzle.unfilledChar)) {
            grid.chars[begIdx][begIdx] = puzzle.blankSymbol;
        }
        // Left bottom
        if (puzzle.left[lastIdx] != puzzle.bottom[begIdx] && (puzzle.left[lastIdx] != puzzle.unfilledChar && puzzle.bottom[begIdx] != puzzle.unfilledChar)) {
            grid.chars[lastIdx][begIdx] = puzzle.blankSymbol;
        }
        // Top right
        if (puzzle.right[begIdx] != puzzle.top[lastIdx] && (puzzle.right[begIdx] != puzzle.unfilledChar && puzzle.top[lastIdx] != puzzle.unfilledChar)) {
            grid.chars[begIdx][lastIdx] = puzzle.blankSymbol;
        }
        // Bottom right
        if (puzzle.right[lastIdx] != puzzle.bottom[lastIdx] && (puzzle.right[lastIdx] != puzzle.unfilledChar && puzzle.bottom[lastIdx] != puzzle.unfilledChar)) {
            grid.chars[lastIdx][lastIdx] = puzzle.blankSymbol;
        }

        return grid;
    }

    public static Grid onlyPlaceForLetterRow(Puzzle puzzle, Grid grid) {
        // Defines whether it is resolving rows or colums
        boolean rowsDirection = true;
        // Chars to String ArrayList
        ArrayList<String> charsList = gridToString.gToString(grid, rowsDirection);
        return fillingEmptySpaceLetter(puzzle, grid, charsList, rowsDirection);
	}

    public static Grid onlyPlaceForLetterCol(Puzzle puzzle, Grid grid) {
        // Defines whether it is resolving rows or colums
        boolean rowsDirection = false;
        // Chars to String ArrayList
        ArrayList<String> charsList = gridToString.gToString(grid, rowsDirection);
        return fillingEmptySpaceLetter(puzzle, grid, charsList, rowsDirection);
	}

    // Function used for looking for empty spaces and filling them with letters
    private static Grid fillingEmptySpaceLetter(Puzzle puzzle, Grid grid, ArrayList<String> charsList, Boolean rowsDir) {
        // rowsDir decides if it is for rows or columns
        if (rowsDir) {
            int i = 0;
            for (String row : charsList) {
                int count = (int)row.chars().filter(ch -> ch == puzzle.unfilledChar).count();
                if (count == 1) {
                    int j = row.indexOf(puzzle.unfilledChar);
                    for (char letter : puzzle.letters) {
                        int missing = (int)row.chars().filter(ch -> ch == letter).count();
                        if (missing == 0) {
                            grid.chars[i][j] = letter;
                            break;
                        }
                    }
                }
                i++;
            }
        } else {
            int j = 0;
            for (String row : charsList) {
                int count = (int)row.chars().filter(ch -> ch == puzzle.unfilledChar).count();
                if (count == 1) {
                    int i = row.indexOf(puzzle.unfilledChar);
                    for (char letter : puzzle.letters) {
                        int missing = (int)row.chars().filter(ch -> ch == letter).count();
                        if (missing == 0) {
                            grid.chars[i][j] = letter;
                            break;
                        }
                    }
                }
                j++;
            }
        }
        return grid;
    }

 
	public static Grid fillInBlanksRow(Puzzle puzzle, Grid grid) { 
        // Defines whether it is resolving rows or colums
        boolean rowsDirection = true;
        // Chars to String ArrayList
        ArrayList<String> charsList = gridToString.gToString(grid, rowsDirection);
        return fillingEmptySpaceBlank(puzzle, grid, charsList, rowsDirection);
	}

	public static Grid fillInBlanksCol(Puzzle puzzle, Grid grid) { 
        // Defines whether it is resolving rows or colums
        boolean rowsDirection = false;
        // Chars to String ArrayList
        ArrayList<String> charsList = gridToString.gToString(grid, rowsDirection);
        return fillingEmptySpaceBlank(puzzle, grid, charsList, rowsDirection);
	}

    // Function used for looking for empty spaces and filling them with blankSymbols
    private static Grid fillingEmptySpaceBlank(Puzzle puzzle, Grid grid, ArrayList<String> charsList, Boolean rowsDir) {
        int maxBlank = grid.size() - puzzle.numLetters();
        // rowsDir decides if it is for rows or columns
        if (rowsDir) {
            int i = 0;
            for (String row : charsList) {
                int count = (int)row.chars().filter(ch -> ch == puzzle.unfilledChar).count();
                if (count <= maxBlank) {

                    // To find out whether all possible characters are already filled in
                    int matchCount = 0;
                    for (char letter : puzzle.letters) {
                        int puzzleLetter = (int)row.chars().filter(ch -> ch == letter).count();
                        if (puzzleLetter == 1) {
                            matchCount++;
                        }
                    }
                    if (matchCount == puzzle.numLetters()) {
                        int j = 0;
                        for (int a = 0; a < count; a++) {
                            j = row.indexOf(puzzle.unfilledChar, j);
                            grid.chars[i][j] = puzzle.blankSymbol;
                            j++;
                        }
                    }
                }
                i++;
            }
        } else {
            int j = 0;
            for (String row : charsList) {
                int count = (int)row.chars().filter(ch -> ch == puzzle.unfilledChar).count();
                if (count <= maxBlank) {

                    // To find out whether all possible characters are already filled in
                    int matchCount = 0;
                    for (char letter : puzzle.letters) {
                        int puzzleLetter = (int)row.chars().filter(ch -> ch == letter).count();
                        if (puzzleLetter == 1) {
                            matchCount++;
                        }
                    }
                    if (matchCount == puzzle.numLetters()) {
                        int i = 0;
                        for (int a = 0; a < count; a++) {
                            i = row.indexOf(puzzle.unfilledChar, i);
                            grid.chars[i][j] = puzzle.blankSymbol;
                            i++;
                        }
                    }
                }
                j++;
            }
        }
        return grid;
    }
	

    public static Grid commonClues(Puzzle puzzle, Grid grid) {
        grid = commonCluesSides(puzzle, grid, puzzle.right, "R");
        grid = commonCluesSides(puzzle, grid, puzzle.left, "L");
        grid = commonCluesSides(puzzle, grid, puzzle.top, "T");
        grid = commonCluesSides(puzzle, grid, puzzle.bottom, "B");

        return grid; // only there so it returns some kind of result
	}

    private static Grid commonCluesSides(Puzzle puzzle, Grid grid, char[] clue, String side) {
        int maxBlank = grid.size() - puzzle.numLetters();
        String str = new String(clue);
        // common clues
        ArrayList<Character> common = new ArrayList<Character>();
        // Represents number of blankSymbols that must be filled due to common clues
        ArrayList<Integer> numOfBlank = new ArrayList<Integer>();
        ArrayList<Character> singles = new ArrayList<Character>();
        for (char letter : clue) {
            int puzzleLetter = (int)str.chars().filter(ch -> ch == letter).count();
            if (puzzleLetter > 1 && !common.contains(letter) && letter != puzzle.unfilledChar) {
                common.add(letter);
                // It is how many letters matched(common clue) minus 1 -> it equals to number of blankSymbols that must be filled in due to the common clue
                numOfBlank.add(puzzleLetter - 1);
            } else if(puzzleLetter == 1 && letter!=puzzle.unfilledChar){
                singles.add(letter);
            }
        }
        // This part sums over arraylist that consits of numbers of blankSymbols that must be filled in.
        // That gives it sumOfBlank which is overall value of blankSymbols that must be filled in
        int sumOfBlank = 0;
        for (int num : numOfBlank) {
            sumOfBlank += num;
        }
        // If the overall number of blankSymbols is equal to maximumBlank symbols that can be used in one row or column,
        // it then means it can filled in the letters that have no common clue
        if (sumOfBlank == maxBlank) {
            if (side == "R") {
                for(char single : singles) {
                    int i = str.indexOf(single);
                    int j = str.length() - 1;
                    grid.chars[i][j] = single;
                }
            } else if (side == "L"){
                for(char single : singles) {
                    int i = str.indexOf(single);
                    int j = 0;
                    grid.chars[i][j] = single;
                }
            } else if (side == "T") {
                for(char single : singles) {
                    int j = str.indexOf(single);
                    int i = 0;
                    grid.chars[i][j] = single;
                }
            } else if (side == "B") {
                for(char single : singles) {
                    int j = str.indexOf(single);
                    int i = str.length() - 1;
                    grid.chars[i][j] = single;
                }
            }
        }
        return grid;
    }

    // This function fills in the corner with the common clue
    public static Grid cornerLetters(Puzzle puzzle, Grid grid) {
        int begIdx = 0;
        int lastIdx = puzzle.size() - 1;
        // This part takes corner by corner and checks whether the letters are the same
        // Top left corner
        if (puzzle.left[begIdx] == puzzle.top[begIdx] && puzzle.validLetter(puzzle.left[begIdx])) {
            grid.chars[begIdx][begIdx] = puzzle.left[begIdx];
        }
        // Left bottom
        if (puzzle.left[lastIdx] == puzzle.bottom[begIdx] && puzzle.validLetter(puzzle.left[lastIdx])) {
            grid.chars[lastIdx][begIdx] = puzzle.left[lastIdx];
        }
        // Top right
        if (puzzle.right[begIdx] == puzzle.top[lastIdx] && puzzle.validLetter(puzzle.right[begIdx])) {
            grid.chars[begIdx][lastIdx] = puzzle.right[begIdx];
        }
        // Bottom right
        if (puzzle.right[lastIdx] == puzzle.bottom[lastIdx] && puzzle.validLetter(puzzle.right[lastIdx])) {
            grid.chars[lastIdx][lastIdx] = puzzle.right[lastIdx];
        }
        return grid;
    }
}
