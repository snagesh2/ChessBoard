package Tests;

import Game.ChessBoard;
import Game.Pieces.Bishop;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

public class PieceTest {
    /**
     * Check if Piece can move correctly.
     * Should return true;
     */

    @Test
    public void moveCorrectlyTest() throws Exception {
        //fail("Not yet implemented");
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[3][1] = 4;
        int [][]possible_board = bishop.move(chessBoard.find_location(2,0),chessBoard.find_location(3,1), chessBoard);
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(true, Arrays.deepEquals(possible_board,expected));

    }
    /**
     * Check if Piece can move incorrectly.
     * Should return false;
     */

    @Test
    public void moveIncorrectlyTest() throws Exception {
        //fail("Not yet implemented");
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[1][3] = 4;
        int [][]possible_board = bishop.move(chessBoard.find_location(2,0), chessBoard.find_location(3,1), chessBoard);
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(false,Arrays.deepEquals(possible_board,expected));

    }

    /**
     * Check if Piece can move correctly and capture.
     * Should return true;
     */
    @Test
    public void moveCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop_w = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[3][1] = 4;
        int [][]possible_board = bishop_w.move(chessBoard.find_location(2,0),chessBoard.find_location(3,1), chessBoard);
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(true,Arrays.deepEquals(possible_board,expected));
    }

    /**
     * Check if Piece can move correctly and capture if different colors.
     * Should return true;
     */
    @Test
    public void moveCaptureCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop_w = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[3][1] = 4;
        int [][]possible_board = bishop_w.move(chessBoard.find_location(2,0),chessBoard.find_location(3,1), chessBoard);
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(true,Arrays.deepEquals(possible_board,expected));
    }

    /**
     * Check if Piece can move correctly and capture if all same color on board.
     * Should return false;
     */
    @Test
    public void moveCaptureIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop_w1 = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        Bishop bishop_w2 = new Bishop(3,1,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop_w1);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_w2);
        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[3][1] = 4;
        int [][]possible_board = bishop_w1.move(chessBoard.find_location(2,0),chessBoard.find_location(3,1), chessBoard);
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(false,Arrays.deepEquals(possible_board,expected));
    }

}