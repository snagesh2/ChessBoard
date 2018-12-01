package Tests;

import Game.ChessBoard;
import Game.Location;
import Game.Pieces.Bishop;
import Game.Pieces.King;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class KingTest {

    /**
     * Check if King can move to wrong piece.
     * Should return false.
     */
    @Test
    public void canMoveToCellIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,0),king);
        assertEquals(false,king.canMoveTo(chessBoard.find_location(4,0),chessBoard.find_location(4,2), chessBoard));

    }

    /**
     * Check if King can move to correct piece.
     * Should return true.
     */
    @Test
    public void canMoveToCellCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,0),king);
        assertEquals(true,king.canMoveTo(chessBoard.find_location(4,0),chessBoard.find_location(4,1), chessBoard));
    }

    /**
     * Return all the places that a King can go from a position with no other pieces on board.
     */
    @Test
    public void explore_possible_positionsAllTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,0),king);
        ArrayList<Location> possible_places =king.explore_possible_positions(chessBoard.find_location(4,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));
        expected_places.add(chessBoard.find_location(4,1));
        expected_places.add(chessBoard.find_location(5,1));
        expected_places.add(chessBoard.find_location(3,0));
        expected_places.add(chessBoard.find_location(5,0));

        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

    /**
     * Return all the places that a King can go from a position with some other pieces on board.
     */
    @Test
    public void explore_possible_positionsBlockedCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,0),king);
        Bishop bishop_w = new Bishop(5,1,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,1),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        ArrayList<Location> possible_places =king.explore_possible_positions(chessBoard.find_location(4,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));// <--------------This can be captured, must add.
        expected_places.add(chessBoard.find_location(4,1));
        //expected_places.add(chessBoard.find_location(5,1)); <-----------This is now blocked.
        expected_places.add(chessBoard.find_location(3,0));
        expected_places.add(chessBoard.find_location(5,0));

        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

}