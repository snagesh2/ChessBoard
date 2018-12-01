package Tests;

import Game.ChessBoard;
import Game.Location;
import Game.Pieces.Bishop;
import Game.Pieces.Pawn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class PawnTest {
    /**
     * Check if King can move to incorrect piece.
     * Should return false.
     */
    @Test
    public void canMoveToCellIncorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(4,1,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,1),pawn);
        assertEquals(false,pawn.canMoveTo(chessBoard.find_location(4,1),chessBoard.find_location(2,0), chessBoard));

    }

    /**
     * Check if King can move to incorrect piece.
     * Should return false.
     */
    @Test
    public void canMoveToCellCorrectTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(4,1,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,1),pawn);
        assertEquals(true,pawn.canMoveTo(chessBoard.find_location(4,1),chessBoard.find_location(4,2), chessBoard));
    }

    /**
     * Return all the places that a Pawn can go from a position with no other pieces on board.
     */
    @Test
    public void explore_possible_positionsAllTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(4,1,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,1),pawn);
        ArrayList<Location> possible_places =pawn.explore_possible_positions(chessBoard.find_location(4,1),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(4,2));
        expected_places.add(chessBoard.find_location(4,3));
        System.out.println(expected_places);
        //System.out.println(possible_places);
        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

    /**
     * Return all the places that a Pawn can go from a position with some other pieces on board.
     */
    @Test
    public void explore_possible_positionsBlockCaptureTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(4,1,1, chessBoard.getPlayerWhite());
        chessBoard.getBoard().put(chessBoard.find_location(4,1),pawn);
        Bishop bishop_w = new Bishop(5,2,1, chessBoard.getPlayerWhite());
        Bishop bishop_b = new Bishop(3,2,-1, chessBoard.getPlayerBlack());
        chessBoard.getBoard().put(chessBoard.find_location(5,2),bishop_w);
        chessBoard.getBoard().put(chessBoard.find_location(3,2),bishop_b);

        ArrayList<Location> possible_places =pawn.explore_possible_positions(chessBoard.find_location(4,1),chessBoard);
        ArrayList<Location> expected_places = new ArrayList<>();
        expected_places.add(chessBoard.find_location(4,2));
        expected_places.add(chessBoard.find_location(4,3));// <--------------First move, so add.
        //expected_places.add(chessBoard.find_location(5,2));// <--------------This will be blocked.
        expected_places.add(chessBoard.find_location(3,2));// <--------------This can be captured, must add.


        for(int i = 0;i<possible_places.size();i++){
            possible_places.get(i).printLoc();
        }
        assertEquals(true,        expected_places.containsAll(possible_places) && possible_places.containsAll(expected_places));
    }

}