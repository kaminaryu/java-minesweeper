package minesweeper;

public class DebugTools {
    public static void revealAllTiles(int[][] board) {
        // int[][] prevMask = new int[mask.length][mask.length];
        // System.arrcopy(mask, 0, prevMask, 0, mask.length);

        int[][] emptyMask = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                emptyMask[i][j] = 1;
            }
        }

        BoardHandler.displayBoard(board, emptyMask);
    }
}
