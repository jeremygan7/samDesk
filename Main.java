import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));
        int nRows = lines.size();
        int nCols = lines.get(0).length(); //Note we assume that input matrix provided is uniform
        char[][] input = new char [nRows][nCols];

        //We setup a char matrix
        for (int r = 0; r < nRows; r++) {
            input[r] = lines.get(r).toCharArray();
        }


        //Treat each char in the grid as origin and loop through
        int count = 0;
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                if(input[r][c] == 'X') { //We want >X<MAS
                    //Travel in each direction
                    //Boundary check done in helper fn
                    //Horizontals
                    if (walk(input, nRows, nCols, r, c, 0,1)) count++; //>>
                    if (walk(input, nRows, nCols, r, c, 0, -1)) count++; //<<

                    //Verticals
                    if (walk(input, nRows, nCols, r, c, 1, 0)) count++; // ^^
                    if (walk(input, nRows, nCols, r, c, -1, 0)) count++; // vv

                    //Diagonals
                    if (walk(input, nRows, nCols, r, c, 1, 1)) count++; // \\
                    if (walk(input, nRows, nCols, r, c, 1, -1)) count++; // //
                    if (walk(input, nRows, nCols, r, c, -1, 1)) count++; // \\
                    if (walk(input, nRows, nCols, r, c, -1, -1)) count++; // //
                }
            }
        }

        System.out.println(count);
    }

    static boolean walk(char[][] input, int nRow, int nCol, int r, int c, int dr, int dc) {

        //Setup Target
        String searchWord = "XMAS"; //Could be different here
        int searchLen = searchWord.length();

        //End Points
        int endR = r + dr * (searchLen-1);
        int endC = c + dc * (searchLen-1);

        // bounds check first
        if (endR < 0 || endR >= nRow || endC < 0 || endC >= nCol) return false;

        for (int i = 0; i < searchLen; i++) {
            int nr = r + (dr * i);
            int nc = c + (dc * i);
            if (input[nr][nc] != searchWord.charAt(i)) {
                return false;
            }
        }

        return true;
    }

}