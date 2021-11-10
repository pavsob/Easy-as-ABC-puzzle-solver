package part3;


import java.util.ArrayList;

import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;
import org.logicng.datastructures.Tristate;
import org.logicng.datastructures.Assignment;

import mains.Grid;
import mains.Puzzle;
import mains.gridToString;
import tests.SampleGrids;
import tests.TestsStaff;

public class DeclarativeABC {

	private Puzzle puzzle;
	private Grid grid;

	FormulaFactory f;
	PropositionalParser p;
	MiniSat solver;
    Tristate result;

    String squareLeastOne; 
    String squareMostOne;
    String rowColLeastOnce;
    String rowColMostOnce;
    String clueConstraint;

    String gridLogic;

    
	public DeclarativeABC()  {  
    }

	public DeclarativeABC(Puzzle puzzle)  { 
	    this.setup(puzzle);
    }

	public DeclarativeABC(Puzzle puzzle, Grid grid)  { 
	    this.setup(puzzle,grid);
    }

    public boolean isUnknown() { 
        return result == Tristate.UNDEF;
    }
    public boolean isTrue() { 
        return result == Tristate.TRUE;
    }
    public boolean isFalse() { 
        return result == Tristate.FALSE;
    }

	public void setup(Puzzle puzzle)  { 
        this.setup(puzzle,new Grid(puzzle.size())); 
    }

	public void setup(Puzzle puzzle, Grid grid)  { 
        this.puzzle=puzzle;
        this.grid=grid;
        // Complete this code

		this.f = new FormulaFactory();
		this.p = new PropositionalParser(f);
		this.solver = MiniSat.miniSat(f);
        this.result = Tristate.UNDEF;

	}

    // Solves and checks whether the given puzzle is solvable
    public boolean solvePuzzle() {
        try {
            Formula KBA;
            
            KBA=p.parse(squareLeastOne+" & "+ squareMostOne);
            if (rowColLeastOnce.length() != 0) {
                Formula KBA1 =p.parse(rowColLeastOnce);
                solver.add(KBA1);
            }
            if (rowColMostOnce.length() != 0) {
                Formula KBA2 =p.parse(rowColMostOnce);
                solver.add(KBA2);
            }
            if (gridLogic.length() != 0) {
                Formula KBA3 = p.parse(gridLogic);
                solver.add(KBA3);
            }
            if (clueConstraint.length() != 0) {
                Formula KBB=p.parse(clueConstraint);
                Formula cnfKBB = KBB.cnf();
                solver.add(cnfKBB);

            }
            solver.add(KBA);
            result = solver.sat();
        }
        catch (ParserException e) { e.printStackTrace(); }
        if (isTrue()) {
            return true;
        }
        return false;
    }

    public void createClauses() {
        int gridLenght = puzzle.size();
        char[] atomicLetters = puzzle.letters;
        char[] atomicSymbols = puzzle.symbols;
        char blankSymbol = puzzle.blankSymbol;
        char unfilledSymbol = puzzle.unfilledChar;
        // Maximum number of blanks in row or column
        int maxBlank = puzzle.size() - puzzle.numLetters();

        // Clues
        char[] left = puzzle.left;
        char[] right = puzzle.right;
        char[] top = puzzle.top;
        char[] bottom = puzzle.bottom;

        // Puzzle constraints in propositional logic
        squareLeastOne = Constraints.squareLeastOne(atomicSymbols, atomicLetters, maxBlank, gridLenght);
        squareMostOne = Constraints.squareMostOne(atomicSymbols, atomicLetters, maxBlank, gridLenght);
        rowColLeastOnce = Constraints.rowColLeastOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        rowColMostOnce = Constraints.rowColMostOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        clueConstraint = Constraints.clueConstraint(unfilledSymbol, blankSymbol, maxBlank, gridLenght, left, right, top, bottom);

        //In case there is something already filled in in the grid
        gridLogic = gridToLogic.gToLogic(grid.chars, unfilledSymbol);
    }



    public Grid modelToGrid() {
        // Creates the model from the solver and converts it into the grid
        //if (isTrue())
        Assignment model = solver.model();
        int dimension = (int)Math.sqrt(model.positiveVariables().size());
        char[][] solutionGrid = new char[dimension][dimension];
        for(int iter = 0; iter < model.positiveVariables().size(); iter++) {
            String s = model.positiveVariables().get(iter).toString();
            char ch = s.charAt(0);
            int i = Character.getNumericValue(s.charAt(1));
            int j = Character.getNumericValue(s.charAt(2));
            solutionGrid[i][j] = ch;
        }
        Grid solvedGrid = new Grid(solutionGrid);

        return solvedGrid;
    }


}
