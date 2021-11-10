package tests;

import mains.Puzzle;
import mains.gridToString;
import mains.Grid;
import tests.SamplePuzzles;
import tests.SampleGrids;
import part1.CheckerABC;
import part2.ProceduralABC;
import part3.DeclarativeABC;

import tests.TestsBase;


public class TestsStudent {


    // Use this file to add your own tests
    // If you wish to do additional tests like e.g. Junit tests, please feel free
    TestsStudent() {}

    public static void test() {
        TestsBase.testIsConsistent("Student Part 1",true,"MY1","MY1Sol");
        TestsBase.testIsFullGrid("Student Part 1",true,"MY1","MY1Sol");
        TestsBase.testIsSolution("Student Part 1",true,"MY1","MY1Sol");
        TestsBase.testIsConsistent("Student Part 1",false,"MY2","MY1Sol");
        TestsBase.testIsFullGrid("Student Part 1",false,"MY2","MY1Sol");
        TestsBase.testIsSolution("Student Part 1",false,"MY2","MY1Sol");
        TestsBase.testIsConsistent("Student Part 1",false,"AUGUST","MY1Sol");
        TestsBase.testIsFullGrid("Student Part 1",false,"AUGUST","MY1Sol");
        TestsBase.testIsSolution("Student Part 1",false,"AUGUST","MY1Sol");

        TestsBase.testCommonClues("Student Part 2","ABC1EmptyCom","MY1","ABC1Empty");
        TestsBase.testCommonClues("Student Part 2","MYAUGUSTSOL","AUGUST","MYAUGUST");
        TestsBase.testCornerLetters("Student Part 2","ABC1EmptyCornersSol","ABC1","ABC1Empty");
    }
    public static void test3() {
        DeclarativeABC d = new DeclarativeABC(SamplePuzzles.valueOf("ABC1").puzzle, SampleGrids.valueOf("ABC1Empty").grid);
        d.createClauses();
        System.out.println(d.solvePuzzle());
        System.out.println(gridToString.gToString(d.modelToGrid(), true));
    }

}


