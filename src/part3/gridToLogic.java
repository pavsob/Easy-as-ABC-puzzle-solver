package part3;

public class gridToLogic {

    // Converts grid to propositional logic
    public static String gToLogic(char[][] grid, char unfilledSymbol) {
        String str = "";

        for (int i=0; i < grid.length; i++ ) {
            for (int j=0; j < grid.length; j++ ) {
                if (grid[i][j]!=unfilledSymbol){
                    str += grid[i][j];
                    str += i;
                    str += j;
                    if (!(i == grid.length-1 && j== grid.length-1)) {
                        str += " & ";
                    }
                }
            
            }
        }
        return str;
    }
}
