package part3;

public class Constraints {

    // This constraint is: Every square contains at least one symbol
    public static String squareLeastOne(char[] atomicSymbols, char[] atomicLetters, int maxBlank, int gridLenght) {
        String str = "";
        char[] symbols;
        if (maxBlank == 0) {
            symbols = atomicLetters;
        }
        else {
            symbols = atomicSymbols;
        }
        int lenght = symbols.length;

        for (int i = 0; i < gridLenght; i++) {

            for (int j = 0; j < gridLenght; j++) {
                str += "(";
                for (int a = 0; a < lenght; a++) {
                    str += symbols[a];
                    str += i;
                    str += j;
                    if ( a != lenght - 1) {
                        str += " | ";
                    }
                }
                if (i == gridLenght-1 && j == gridLenght-1) {
                    str += ")";
    
                } else {
                    str += ") & ";
                }
            }

        } 
        return str;
    }

    // This constraint is: Every square contains at most one symbol
    public static String squareMostOne(char[] atomicSymbols, char[] atomicLetters, int maxBlank, int gridLenght) {
        String str = "";
        char[] symbols;
        if (maxBlank == 0) {
            symbols = atomicLetters;
        }
        else {
            symbols = atomicSymbols;
        }
        int lenght = symbols.length;

        for (int i = 0; i < gridLenght; i++) {

            for (int j = 0; j < gridLenght; j++) {
                int count = 1;
                for (int b = 0; b < lenght; b++) {    
                    for (int a = count; a < lenght; a++) {
                        str += "(";
                        str += "~";
                        str += symbols[b];
                        str += i;
                        str += j;
                        str +=" | ";
                        str += "~";
                        str += symbols[a];
                        str += i;
                        str += j;
                        if (i == gridLenght-1 && j == gridLenght-1 && a == lenght-1 && b == lenght-2) {
                            str += ")";
                        } else {
                            str += ") & ";
                        }

                    }
                    count ++;
                }

            }
        } 
        return str;
    }

    // This constraint is: Every row/column has every letter at least once
    public static String rowColLeastOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        String str = "";
        String rowS = rowLeastOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        String colS = colLeastOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        str += rowS;
        if (rowS.length() != 0 && colS.length() != 0) {
            str += " & ";
        }
        str += colS;
        return str;
    }
    private static String rowLeastOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        char[] symbols;

        // It assumes that all the X spaces are fixed by the first two constraints
        symbols = atomicLetters;
        String str = "";
		int lenght = symbols.length;
        int column = 0;

        for (int i = 0; i < gridLenght; i++) {

            for (int l = 0; l < lenght; l++) {
                str += "(";
                for (int j = 0; j < gridLenght; j++) {
                    str += symbols[l];
                    str += i;
                    str += j;
                    if ( j != gridLenght - 1) {
                        str += " | ";
                    }
                    column = j;
                }
                if (i == gridLenght-1 && column == gridLenght-1 && l == lenght -1) {
                    str += ")";
        
                } else {
                    str += ") & ";
                }
            }
        }
        return str;
    }
    private static String colLeastOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        char[] symbols;

        // It assumes that all the X spaces are fixed by the first two constraints
        symbols = atomicLetters;
        String str = "";
		int lenght = symbols.length;
        int column = 0;

        for (int i = 0; i < gridLenght; i++) {

            for (int l = 0; l < lenght; l++) {
                str += "(";
                for (int j = 0; j < gridLenght; j++) {
                    str += symbols[l];
                    str += j;
                    str += i;
                    if ( j != gridLenght - 1) {
                        str += " | ";
                    }
                    column = j;
                }
                if (i == gridLenght-1 && column == gridLenght-1 && l == lenght -1) {
                    str += ")";
        
                } else {
                    str += ") & ";
                }
            }
        }
        return str;
    }


    public static String rowColMostOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        String str = "";
        String rowS = rowMostOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        String colS = colMostOnce(atomicSymbols, gridLenght, atomicLetters, blankSymbol, maxBlank);
        str +=  rowS;
        if (rowS.length() != 0 && colS.length() != 0) {
            str += " & ";
        }
        str += colS;
        return str;
    }
    private static String rowMostOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        char[] symbols;

        // It assumes that all the X spaces are fixed by the first two constraints
        symbols = atomicLetters;
        String str = "";
		int lenght = symbols.length;

        for (int i = 0; i < gridLenght; i++) {

            for (int l = 0; l < lenght; l++) {
                int count = 1;
                for (int j = 0; j < gridLenght; j++) {
                    for (int a = count; a < gridLenght; a++) {
                        str += "(";
                        str += "~";
                        str += symbols[l];
                        str += i;
                        str += j;
                        str +=" | ";
                        str += "~";
                        str += symbols[l];
                        str += i;
                        str += a;
                        if (i == gridLenght-1 && j == gridLenght-2 && a == gridLenght-1 && l == lenght-1) {
                            str += ")";
                        } else {
                            str += ") & ";
                        }
                    }
                    count ++;
                }
            }
        }
        return str;
        
    }
    private static String colMostOnce(char[] atomicSymbols, int gridLenght, char[] atomicLetters, char blankSymbol, int maxBlank) {
        char[] symbols;

        // It assumes that all the X spaces are fixed by the first two constraints
        symbols = atomicLetters;
        String str = "";
		int lenght = symbols.length;

        for (int i = 0; i < gridLenght; i++) {

            for (int l = 0; l < lenght; l++) {
                int count = 1;
                for (int j = 0; j < gridLenght; j++) {
                    for (int a = count; a < gridLenght; a++) {
                        str += "(";
                        str += "~";
                        str += symbols[l];
                        str += j;
                        str += i;
                        str +=" | ";
                        str += "~";
                        str += symbols[l];
                        str += a;
                        str += i;
                        if (i == gridLenght-1 && j == gridLenght-2 && a == gridLenght-1 && l == lenght-1) {
                            str += ")";
                        } else {
                            str += ") & ";
                        }
                    }
                    count ++;
                }
            }
        }
        return str;
    }



    public static String clueConstraint(char unfilledSymbol, char blankSymbol, int maxBlank, int gridLenght, char[] left, char[] right, char[] top, char[] bottom) {
        String str = "";
        String leftS = clueConstraintLeft(unfilledSymbol, blankSymbol, maxBlank, gridLenght, left);
        String rightS = clueConstraintRight(unfilledSymbol, blankSymbol, maxBlank, gridLenght, right);
        String topS = clueConstraintTop(unfilledSymbol, blankSymbol, maxBlank, gridLenght, top);
        String bottomS = clueConstraintBottom(unfilledSymbol, blankSymbol, maxBlank, gridLenght, bottom);

        if (leftS.length() != 0) {
            str += leftS;
            if (rightS.length() != 0 | topS.length() != 0 | bottomS.length() != 0) {
                str += " & ";
            }
        }

        if (rightS.length() != 0) {
            str += rightS;
            if (topS.length() != 0 | bottomS.length() != 0) {
                str += " & ";
            }
        }

        if (topS.length() != 0) {
            str += topS;
            if (bottomS.length() != 0) {
                str += " & ";
            }
        }

        if (bottomS.length() != 0) {
            str += bottomS;
        }

        return str;
    }

    private static String clueConstraintLeft(char unfilledSymbol, char blankSymbol, int maxBlank, int gridLenght, char[] left) {
        String str = "";
        for (int i = 0; i < gridLenght; i++) {

            if (left[i] != unfilledSymbol) {
                str += "(";
                int j = 0;
                str += left[i];
                str += i;
                str += j;
                if (maxBlank != 0) {
                    str += " | ";
                    int count = 1;
                    for (j = 1; j < maxBlank+1; j++) {
                        str += "(";
                        for (int x = 0 ; x < count; x++) {
                            str += blankSymbol;
                            str += i;
                            str += x;
                            str += " & ";
                        }
                        str += left[i];
                        str += i;
                        str += j;
                        str += ")";
                        if (count < maxBlank) {
                            str += " | ";
                            count++;
                        }
                    }
                }
                str += ")";
                if (!(i == gridLenght-1)) {
                    str += " & ";
                }
            }
        }
        return str;
    }
    
    private static String clueConstraintRight(char unfilledSymbol, char blankSymbol, int maxBlank, int gridLenght, char[] right) {
        String str = "";
        for (int i = 0; i < gridLenght; i++) {

            if (right[i] != unfilledSymbol) {
                str += "(";
                int j = gridLenght-1;
                str += right[i];
                str += i;
                str += j;
                if (maxBlank != 0) {
                    str += " | ";
                    int count = 1;
                    for (j = gridLenght-2; j >= (gridLenght - maxBlank -1); j--) {
                        str += "(";
                        for (int x = gridLenght-1 ; x >= (gridLenght - count); x--) {
                            str += blankSymbol;
                            str += i;
                            str += x;
                            str += " & ";
                        }
                        str += right[i];
                        str += i;
                        str += j;
                        str += ")";
                        if (count < maxBlank) {
                            str += " | ";
                            count++;
                        }
                    }
                }
                str += ")";
                if (!(i == gridLenght-1)){
                    str += " & ";
                }
            }
        }
        return str;
    }

    private static String clueConstraintTop(char unfilledSymbol, char blankSymbol, int maxBlank, int gridLenght, char[] top) {
        String str = "";
        for (int i = 0; i < gridLenght; i++) {

            if (top[i] != unfilledSymbol) {
                str += "(";
                int j = 0;
                str += top[i];
                str += j;
                str += i;
                if (maxBlank != 0) {
                    str += " | ";
                    int count = 1;
                    for (j = 1; j < maxBlank+1; j++) {
                        str += "(";
                        for (int x = 0 ; x < count; x++) {
                            str += blankSymbol;
                            str += x;
                            str += i;
                            str += " & ";
                        }
                        str += top[i];
                        str += j;
                        str += i;
                        str += ")";
                        if (count < maxBlank) {
                            str += " | ";
                            count++;
                        }
                    }
                }
                str += ")";
                if (!(i == gridLenght-1)){
                    str += " & ";
                }
            }
        }
        return str;
    }

    private static String clueConstraintBottom(char unfilledSymbol, char blankSymbol, int maxBlank, int gridLenght, char[] bottom) {
        String str = "";
        for (int i = 0; i < gridLenght; i++) {

            if (bottom[i] != unfilledSymbol) {
                str += "(";
                int j = gridLenght-1;
                str += bottom[i];
                str += j;
                str += i;
                if (maxBlank != 0) {
                    str += " | ";
                    int count = 1;
                    for (j = gridLenght-2; j >= (gridLenght - maxBlank -1); j--) {
                        str += "(";
                        for (int x = gridLenght-1 ; x >= (gridLenght - count); x--) {
                            str += blankSymbol;
                            str += x;
                            str += i;
                            str += " & ";
                        }
                        str += bottom[i];
                        str += j;
                        str += i;
                        str += ")";
                        if (count < maxBlank) {
                            str += " | ";
                            count++;
                        }
                    }
                }
                str += ")";
                if (!(i == gridLenght-1)){
                    str += " & ";
                }
            }
        }
        return str;
    }
}
