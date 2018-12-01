package Tests;

//import static org.junit.Assert.*;

import Game.ChessBoard;
import Game.Location;
import Game.Pieces.Bishop;
import Game.Pieces.Knight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;


public class KnightTest {
    /**
     * Check if King can move to incorrect piece.
     * Should return false.
     */
    @Test
    public void canMoveToCellIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(1,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(1,0),knight);
        assertEquals(false,knight.canMoveTo(chessBoard.find_location(1,0),chessBoard.find_location(2,0), chessBoard));

    }

    /**
     * Check if King can move to correct piece.
     * Should return true.
     */
    @Test
    public void canMoveToCellCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(1,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(1,0),knight);
        assertEquals(true,knight.canMoveTo(chessBoard.find_location(1,0),chessBoard.find_location(3,1), chessBoard));
    }

    /**
     * Return all the places that a Knight can go from a position with no other pieces on board.
     */
    @Test
    public void explore_possible_positionsAllTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(1,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(1,0),knight);
        ArrayList<Location> possible_places =knight.explore_possible_positions(chessBoard.find_location(1,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(2,2));
        expected_places.add(chessBoard.find_location(0,2));
        expected_places.add(chessBoard.find_location(3,1));
        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

    /**
     * Return all the places that a Knight can go from a position with some other pieces on board.
     */

    @Test
    public void explore_possible_positionsBlockCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(1,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(1,0),knight);
        Bishop bishop_w = new Bishop(2,2,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(2,2),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        ArrayList<Location> possible_places =knight.explore_possible_positions(chessBoard.find_location(1,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        //expected_places.add(chessBoard.find_location(2,2));<-----------This is now blocked.
        expected_places.add(chessBoard.find_location(0,2));
        expected_places.add(chessBoard.find_location(3,1));// <--------------This can be captured, must add.


        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

}