package Tests;

import Game.ChessBoard;
import Game.Location;
import Game.Pieces.Bishop;
import Game.Pieces.Queen;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class QueenTest {
    /**
     * Check if King can move to incorrect piece.
     * Should return false.
     */
    @Test
    public void canMoveToCellIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(3,0),queen);
        assertEquals(false,queen.canMoveTo(chessBoard.find_location(3,0),chessBoard.find_location(4,2), chessBoard));

    }
    /**
     * Check if King can move to correct piece.
     * Should return true.
     */
    @Test
    public void canMoveToCellCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(3,0),queen);
        assertEquals(true,queen.canMoveTo(chessBoard.find_location(3,0),chessBoard.find_location(6,0), chessBoard));
    }

    /**
     * Return all the places that a Queen can go from a position with no other pieces on board.
     */

    @Test
    public void explore_possible_positionsAllTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(3,0),queen);
        ArrayList<Location> possible_places =queen.explore_possible_positions(chessBoard.find_location(3,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));
        expected_places.add(chessBoard.find_location(3,2));
        expected_places.add(chessBoard.find_location(3,3));
        expected_places.add(chessBoard.find_location(3,4));
        expected_places.add(chessBoard.find_location(3,5));
        expected_places.add(chessBoard.find_location(3,6));
        expected_places.add(chessBoard.find_location(3,7));

        expected_places.add(chessBoard.find_location(0,0));
        expected_places.add(chessBoard.find_location(1,0));
        expected_places.add(chessBoard.find_location(2,0));
        expected_places.add(chessBoard.find_location(4,0));
        expected_places.add(chessBoard.find_location(5,0));
        expected_places.add(chessBoard.find_location(6,0));
        expected_places.add(chessBoard.find_location(7,0));

        expected_places.add(chessBoard.find_location(4,1));
        expected_places.add(chessBoard.find_location(5,2));
        expected_places.add(chessBoard.find_location(6,3));
        expected_places.add(chessBoard.find_location(7,4));

        expected_places.add(chessBoard.find_location(2,1));
        expected_places.add(chessBoard.find_location(1,2));
        expected_places.add(chessBoard.find_location(0,3));

        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

    /**
     * Return all the places that a Queen can go from a position with some other pieces on board.
     */

    @Test
    public void explore_possible_positionsBlockCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(3,0,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(3,0),queen);
        Bishop bishop_w = new Bishop(2,1,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,1,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(2,1),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,1),bishop_b);
        ArrayList<Location> possible_places =queen.explore_possible_positions(chessBoard.find_location(3,0),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(3,1));//<-----------This can be captured,add.
        expected_places.add(chessBoard.find_location(3,2));
        expected_places.add(chessBoard.find_location(3,3));
        expected_places.add(chessBoard.find_location(3,4));
        expected_places.add(chessBoard.find_location(3,5));
        expected_places.add(chessBoard.find_location(3,6));
        expected_places.add(chessBoard.find_location(3,7));

        expected_places.add(chessBoard.find_location(0,0));
        expected_places.add(chessBoard.find_location(1,0));
        expected_places.add(chessBoard.find_location(2,0));
        expected_places.add(chessBoard.find_location(4,0));
        expected_places.add(chessBoard.find_location(5,0));
        expected_places.add(chessBoard.find_location(6,0));
        expected_places.add(chessBoard.find_location(7,0));

        expected_places.add(chessBoard.find_location(4,1));
        expected_places.add(chessBoard.find_location(5,2));
        expected_places.add(chessBoard.find_location(6,3));
        expected_places.add(chessBoard.find_location(7,4));

        //expected_places.add(chessBoard.find_location(2,1));//<--------This is now blocked
        //expected_places.add(chessBoard.find_location(1,2));
        //expected_places.add(chessBoard.find_location(0,3));


        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }
}