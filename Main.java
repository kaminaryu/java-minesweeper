package minesweeper;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        int boardSize = BoardHandler.getBoardSize();

        int[][] board = new int[boardSize][boardSize];
        int[][] boardMask = new int[boardSize][boardSize];

        Scanner input = new Scanner(System.in);

        int totalMines = BoardHandler.getTotalMines(boardSize);
        BoardHandler.placeMines(board, totalMines);
 
        while (true) {
            BoardHandler.displayBoard(board, boardMask);

            int[] coords = GameMaster.getCoords(boardSize);

            // DEBUG mode
            if (coords[0] == -7) {
                System.out.println("-- DEBUG --");
                DebugTools.revealAllTiles(board); 
                System.out.println("-- DEBUG --\n");
                continue;
            }

            GameMaster.unvailTile(boardMask, board, coords);
        }
    }
}
