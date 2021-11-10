package mains;

import java.util.ArrayList;

public class gridToString {

    // Converts and returns the grid chars in the ArrayList of Strings for better manipulation 
    public static ArrayList<String> gToString(Grid grid, Boolean rows){
        ArrayList<String> inString = new ArrayList<String>();
        if (rows) {
            for (int i = 0; i < grid.size(); i++) {
                String row = "";
                for (int j = 0; j < grid.size(); j++) {
                    row += grid.chars[i][j];
                }
                inString.add(row);
            }
        }
        else {
            for (int i = 0; i < grid.size(); i++) {
                String column = "";
                for (int j = 0; j < grid.size(); j++) {
                    column += grid.chars[j][i];
                }
                inString.add(column);
            }
        }
        return inString;
    }
    
}
