package minesweeper;
import java.util.Scanner;

public class GameMaster {
    public static int[] convertCoordsToInt(char[] coords) {
        int[] newCoords = new int[2];

        for (int i = 0; i < 2; i++) {
            int ascii = (int)(coords[i]);

            // if digits
            if (48 <= ascii && ascii < 58)
                newCoords[i] = ascii - 48;
            // if caps letters
            else if (65 <= ascii && ascii < 91)
                newCoords[i] = ascii - 55;
            // if lowercase letters
            else if (97 <= ascii && ascii < 123)
                newCoords[i] = ascii - 87;
            else 
                newCoords[i] = -1;
        }

        return newCoords;
    }

    public static boolean isValidTile(int[] coords, int boardSize) {
        for (int i = 0; i < 2; i++) {
            if (coords[i] == -1) 
                return false;
            else if (coords[i] > boardSize - 1)
                return false;
        }
        return true;
    }

    public static int[] getCoords(int boardSize) {
        Scanner input = new Scanner(System.in);
        char[] coords = new char[2];
        int[] actualCoords = new int[2];

        do {
            System.out.print("\nChoose a tile to unvail [row (space) column]: ");
            coords[0] = input.next().charAt(0);
            coords[1] = input.next().charAt(0);

            // DEBUG mode
            if (coords[0] == '<' && coords[1] == '>') {
                actualCoords = new int[]{-7, -7};
                break;
            }

            actualCoords = convertCoordsToInt(coords);

            if ( !isValidTile(actualCoords, boardSize) ) {
                System.out.println("\nInvalid coordinates! Try again.\n");
            }
        } while ( !isValidTile(actualCoords, boardSize) );

        return actualCoords;
    }

    public static int unvailTile(int[][] mask, int[][] board, int[] coords) {
        mask[coords[0]][coords[1]] = 1;
        return board[coords[0]][coords[1]];
    }
}
