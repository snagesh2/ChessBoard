package Tests;

//import static org.junit.Assert.*;

import Game.ChessBoard;
import Game.Location;
import Game.Pieces.Bishop;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;


public class BishopTest {

    /**
     * Check if Bishop can move to adjacent piece.
     * Should return false.
     */
    @Test
    public void canMoveToAdjacentCellTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        assertEquals(false,bishop.canMoveTo(chessBoard.find_location(2,0),chessBoard.find_location(3,0), chessBoard));

    }
    /**
     * Check if Bishop can move to diagonal piece.
     * Should return true.
     */
    @Test
    public void canMoveToDiagonalCellTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        assertEquals(true,bishop.canMoveTo(chessBoard.find_location(2,0),chessBoard.find_location(3,1), chessBoard));
    }

    /**
     * Return all the places that a Bishop can go from a position with no other pieces on board.
     */

    @Test
    public void explore_possible_positionsAllTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(2,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        ArrayList<Location> possible_places =bishop.explore_possible_positions(chessBoard.find_location(2,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));
        expected_places.add(chessBoard.find_location(4,2));
        expected_places.add(chessBoard.find_location(5,3));
        expected_places.add(chessBoard.find_location(6,4));
        expected_places.add(chessBoard.find_location(7,5));
        expected_places.add(chessBoard.find_location(1,1));
        expected_places.add(chessBoard.find_location(0,2));
        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(expected_places,possible_places);
    }

    /**
     * Return all the places that a Bishop can go from a position with some pieces on board.
     */
    @Test
    public void explore_possible_positionsBlockCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(2,0),bishop);
        Bishop bishop_w = new Bishop(1,1,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(1,1),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        ArrayList<Location> possible_places =bishop.explore_possible_positions(chessBoard.find_location(2,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));// <----------This can be captured, add.
        expected_places.add(chessBoard.find_location(4,2));
        expected_places.add(chessBoard.find_location(5,3));
        expected_places.add(chessBoard.find_location(6,4));
        expected_places.add(chessBoard.find_location(7,5));
        //expected_places.add(chessBoard.find_location(1,1));<-------------This is blocked
        //expected_places.add(chessBoard.find_location(0,2));
        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(expected_places,possible_places);
    }

}