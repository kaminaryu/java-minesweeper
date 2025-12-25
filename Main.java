package minesweeper;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        int boardSize = BoardHandler.getBoardSize();

        int[][] board = new int[boardSize][boardSize];
        int[][] boardMask = new int[boardSize][boardSize];

        Scanner input = new Scanner(System.in);
        int totalMines = BoardHandler.getTotalMines(boardSize);
        int tileValue = 0;

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

            boolean gameover = BoardHandler.unvailTile(board, boardMask, coords);

            if (gameover)
                break;

            // System.out.println("Total Revealed: " + BoardHandler.totalRevealedTiles);
            // System.out.println("Total Flagged: " + BoardHandler.totalFlagged);
            System.out.println();

            // win con
            if (totalMines == boardSize * boardSize - BoardHandler.totalRevealedTiles) {
                GameMaster.gameWin(board, boardMask);
                break;
            }
            else if (BoardHandler.totalMineFlagged == totalMines && BoardHandler.totalFalseFlagged == 0) {
                GameMaster.gameWin(board, boardMask);
                break;
            }
        }
    }
}
