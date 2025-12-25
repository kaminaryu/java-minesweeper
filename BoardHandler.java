package minesweeper;
import java.util.Scanner;

public class BoardHandler {
    public static int getBoardSize() {
        int size = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Pick the board size.");
            System.out.print("Enter an int between 4 and 36: ");
            size = input.nextInt();

            if (size < 4 || size > 36)
                System.out.println("Range Error. Try Again.");

        } while (size < 4 || size > 36);

        return size;
    }

    public static int getTotalMines(int boardSize) {
        int totalMines = 0, difficulty = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print(
                """
                1) Easy
                2) Medium
                3) Hard
                Choose a difficulty [1 or 2 or 3]:\s"""
            );
            difficulty = input.nextInt();

            if (difficulty < 1 || difficulty > 3)
                System.out.println("\nInvalid choice!\n");

        } while (difficulty < 1 || difficulty > 3);

        // switch (difficulty) {
        //     case 1:
        //         totalMines = 0.2 * (boardSize * boardSizeoo);
        //         break;
        //     case 2 :
        //         totalMines = 0.4 * (boardSize * boardSizeoo);
        //         break;
        //     case 3 :
        //         totalMines = 0.6 * (boardSize * boardSizeoo);
        //         break;
        // }

        totalMines = (int)(Math.round(( difficulty * 0.2 ) * ( boardSize * boardSize )));

        return totalMines;
    }

    public static void increaseSurroundingTilesValue(int[][] board, int row, int col) {
        for (int r = -1; r < 2; r++) {
            // if out of bounds
            if (row + r < 0 || row + r > board.length - 1) continue;

            for (int c = -1; c < 2; c++) {
                // if out of bounds
                if (col + c < 0 || col + c > board[row + r].length - 1) continue;

                if (row + r == 0 && col + c == 0) continue;

                if (board[row + r][col + c] == 9) continue;

                board[row + r][col + c]++;
            }
        }
    }

    public static void placeMines(int[][] board, int totalMines) {
        int placedMines = 0;

        while (placedMines < totalMines) {
            int randomRow = (int)(Math.random() * board.length);
            int randomCol = (int)(Math.random() * board[0].length);

            if (board[randomRow][randomCol] != 9) {
                board[randomRow][randomCol] = 9; // 9 = mine
                placedMines++;
                increaseSurroundingTilesValue(board, randomRow, randomCol);
            }
        }
    }

    public static void displayBoard(int[][] board, int[][] mask) {
        // 💣
        // Print Column indicators
        System.out.print("    ");
        for (int i = 0; i < board[0].length; i++) {
            if (i < 10)
                System.out.print(i + " ");
            else 
                System.out.print( (char)(55 + i) + " ");
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            // print row indicators
            if (i < 10)
                System.out.print("\n" + i + "   ");
            else 
                System.out.print("\n" + (char)(55 + i) + "   ");


            for (int j = 0; j < board[i].length; j++) {
                if (mask[i][j] == 0)
                    System.out.print("# ");
                else 
                    System.out.print(board[i][j] == 9 ? "☢ " : board[i][j]  + " ");
            }
        }
        System.out.println();
    }
}
