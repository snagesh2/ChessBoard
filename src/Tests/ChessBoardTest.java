package Tests;

import Game.ChessBoard;
import Game.Pieces.Bishop;
import Game.Pieces.King;
import Game.Pieces.Queen;
import Game.Pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

public class ChessBoardTest {

    /**
     * Sets up to achieve the board before a game with all pieces in position.
     * Assert if true.
     */
    @Test
    public void setUpStartBoard() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setUpStartBoard(chessBoard);
        int [][] possible_board = chessBoard.getBoardPrint();
        int [][] expected = new int[][]{
                {2, 3, 4, 5, 6, 4, 3, 2},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {-2, -3, -4, -5, -6, -4, -3, -2}
        };
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============================POSSIBLE========================================");

        for (int i = 0; i < possible_board.length; i++) {
            for (int j = 0; j < possible_board[i].length; j++) {
                System.out.print(possible_board[i][j] + " ");
            }
            System.out.println();
        }
        assertEquals(true, Arrays.deepEquals(possible_board,expected));


    }

    /**
     * Prints the board with the pieces you specify on it.
     * Assert if true.
     */
    @Test
    public void getBoardPrint() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,0),king);
        chessBoard.getBoardPrint()[4][0] = 6;
        Bishop bishop_w = new Bishop(5,1,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,1),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        chessBoard.getBoardPrint()[5][1] = 4;
        chessBoard.getBoardPrint()[3][1] = -4;
        int [][] possible_board = chessBoard.getBoardPrint();

        int[][] expected = new int[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                expected[i][j] = 0;
            }
        }
        expected[4][0] = 6;
        expected[5][1] = 4;
        expected[3][1] = -4;
        System.out.println("===============================EXPECTED========================================");

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                System.out.print(expected[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============================POSSIBLE========================================");

        for (int i = 0; i < possible_board.length; i++) {
            for (int j = 0; j < possible_board[i].length; j++) {
                System.out.print(possible_board[i][j] + " ");
            }
            System.out.println();
        }
        assertEquals(true, Arrays.deepEquals(possible_board,expected));

    }

    /**
     * Check if the player on the board has been check mated.
     * This test should return true.
     * Assert if true.
     */
    @Test
    public void checkmatedTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king_w = new King(5,5,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(5,5),king_w);
        chessBoard.getBoardPrint()[5][5] = 6;
        chessBoard.getPlayerWhite().add_to_piecelist(king_w);

        Queen queen = new Queen(6,6,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(6,6),queen);
        chessBoard.getBoardPrint()[6][6] = 5;
        chessBoard.getPlayerWhite().add_to_piecelist(queen);

        King king_b = new King(6,7,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(6,7),king_b);
        chessBoard.getBoardPrint()[6][7] = -6;
        chessBoard.getPlayerBlack().add_to_piecelist(king_b);

        assertEquals(true, chessBoard.checkmated(chessBoard.getPlayerBlack(),chessBoard));


    }

    /**
     * Check if the player on the board has been check mated.
     * This test should return false.
     * Assert if true.
     */

    @Test
    public void checkmatedIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king_w = new King(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(3,0),king_w);
        chessBoard.getBoardPrint()[3][0] = 6;
        chessBoard.getPlayerWhite().add_to_piecelist(king_w);

        Queen queen = new Queen(0,5,1, chessBoard.getPlayerWhite());
        Rook rook = new Rook(1,6,-1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(0,5),queen);
        chessBoard.getBoardPrint()[0][5] = 5;
        chessBoard.getPlayerWhite().add_to_piecelist(queen);

        chessBoard.getBoard().put(chessBoard.find_location(1,6),rook);
        chessBoard.getBoardPrint()[1][6] = 2;
        chessBoard.getPlayerWhite().add_to_piecelist(rook);

        King king_b = new King(5,7,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,7),king_b);
        chessBoard.getBoardPrint()[5][7] = -6;

        chessBoard.getPlayerBlack().add_to_piecelist(king_b);

        assertEquals(false, chessBoard.checkmated(chessBoard.getPlayerBlack(),chessBoard));


    }

    /**
     * Check if the player on the board has been checked.
     * This test should return true.
     * Assert if true.
     */

    @Test
    public void inCheckCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king_w = new King(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),king_w);
        chessBoard.getPlayerWhite().add_to_piecelist(king_w);

        Queen queen = new Queen(0,7,1, chessBoard.getPlayerWhite());
        Rook rook = new Rook(1,6,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(0,7),queen);
        chessBoard.getPlayerWhite().add_to_piecelist(queen);

        chessBoard.getBoard().put(chessBoard.find_location(1,6),rook);
        chessBoard.getPlayerWhite().add_to_piecelist(rook);

        King king_b = new King(5,7,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,7),king_b);
        chessBoard.getPlayerBlack().add_to_piecelist(king_b);
        assertEquals(true, chessBoard.inCheck(chessBoard.getPlayerBlack(),chessBoard));
    }

    /**
     * Check if the player on the board has been checked.
     * This test should return false.
     * Assert if true.
     */
    @Test
    public void inCheckIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king_w = new King(7,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(7,0),king_w);
        chessBoard.getPlayerWhite().add_to_piecelist(king_w);

        Queen queen = new Queen(0,7,1, chessBoard.getPlayerWhite());
        Rook rook = new Rook(1,6,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(0,7),queen);
        chessBoard.getPlayerWhite().add_to_piecelist(queen);

        chessBoard.getBoard().put(chessBoard.find_location(1,6),rook);
        chessBoard.getPlayerWhite().add_to_piecelist(rook);

        King king_b = new King(5,7,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,7),king_b);
        chessBoard.getPlayerBlack().add_to_piecelist(king_b);
        assertEquals(true, chessBoard.inCheck(chessBoard.getPlayerBlack(),chessBoard));
    }

}