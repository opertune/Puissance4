import javax.swing.*;
import java.util.Scanner;

public class Puisance4Main {
    public static void main(String[] args) {
        String[][] grid = {
                {"\n|-1-|-2-|-3-|-4-|-5-|-6-|-7-|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|"},
                {"\n| " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | " , " " , " | "},
                {"\n|---|---|---|---|---|---|---|\n"}
        };
        int p1NbX = 0, p2NbO = 0;
        boolean player1 = true;

        // Diplay grid
        displayGrid(grid);

        do {
            int column, row;
            if (player1) {
                System.out.println("Player 1 (X)");
            } else {
                System.out.println("Player 2 (O)");
            }

            Scanner columnInput = new Scanner(System.in);
            do {
                // Token position input
                System.out.println("Column: ");
                column = (columnInput.nextInt()*2)-1;
            }while (column > 13 | column < 1);

            row = rowNumber(grid, column);
            // checks if column is full
            if (row < 1){
                int oldColumn = column;
                do {
                    System.out.println("Column full, choose an another column");
                    do {
                        // Token position input
                        System.out.print("\nColumn: ");
                        column = (columnInput.nextInt()*2)-1;
                    }while (column > 13 | column < 1);
                }while (column == oldColumn);
                row = rowNumber(grid, column);
            }

            // assign player token in array
            if (player1){
                grid[row][column] = "X";
                player1 = false;

                p1NbX = lineTest(p1NbX, grid, "X", row, column);
            }else{
                grid[row][column] = "O";
                player1 = true;
                p2NbO = lineTest(p2NbO, grid, "O", row, column);
            }

            // Diplay grid with player token
            displayGrid(grid);

        }while(p1NbX != 4 & p2NbO != 4);

        displayGrid(grid);
        if(p1NbX == 4){
            System.out.println("Player 1 WIN !");
        }else if (p2NbO == 4){
            System.out.println("Player 2 Win !");
        }else {
            System.out.println("Equality !");
        }


    }


    public static int lineTest(int pNbToken, String[][] grid, String token, int row, int column){
        // Horizontal checks
        for (int i = 1; i <= 13; i += 2){
            if(grid[row][i] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }
        }

        // Vertical checks
        for (int i = 1; i <= 11; i += 2){
            if (grid[i][column] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }
        }

        // Diag checks
        int col = 11, i = 1, j = 1;
        do{
            if (grid[i][j] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }
            i += 2;
            j += 2;

            if(j > col){
                col -= 2;
                j = 1;
                if (col == 9){
                    i = 3;
                }else if (col == 7){
                    i = 5;
                }
            }
        }while (col >= 7);

        int r = 11; i = 1; j = 3;
        do{
            if (grid[i][j] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }

            i += 2;
            j += 2;

            if(i > r){
                r -= 2;
                i = 1;
                if (r == 9){
                    j = 5;
                }else if (r == 7){
                    j = 7;
                }
            }
        }while (r >= 7);

        col = 11; i = 1; j = 11;
        do{
            if (grid[i][j] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }
            i += 2;
            j -= 2;

            if(j < 1){
                col -= 2;
                i = 1;
                if (col == 9){
                    j = 9;
                }else if (col == 7){
                    j = 7;
                }
            }
        }while (col >= 7);

        col = 3; i = 1; j = 13;
        do{
            if (grid[i][j] == token){
                pNbToken++;
                if (pNbToken == 4){
                    return pNbToken;
                }
            }else {
                pNbToken = 0;
            }

            i += 2;
            j -= 2;

            if(j < col){
                col += 2;
                j = 13;
                if (col == 5){
                    i = 3;
                }else if (col == 7){
                    i = 5;
                }
            }
        }while (col <= 7);


        return pNbToken;
    }


    /*
    @Param String array, int column
    checks every box, if she's empty increase row number
    @Return row number
     */
    public static int rowNumber(String[][] grid, int column){
        int row = -1;
        do {
            if (grid[row+2][column] == "X" | grid[row+2][column] == "O"){
                break;
            }
            row += 2;
        }while (row < 11);
        return row;
    }

    /*
    Display 2D array
     */
    public static void displayGrid(String[][] grid){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j<grid[i].length; j++){
                System.out.print(grid[i][j]);
            }
        }
    }
}
