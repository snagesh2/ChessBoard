package Game.Pieces;

import Game.ChessBoard;
import Game.Location;
import Game.Player;

import java.util.ArrayList;

public class Bishop extends Game.Piece {
    private String kind;
    /**
     * Constructor.
     * @param c color of piece
     * @param X X coordinate
     * @param Y y coordinate
     * @param owner is the owner of the piece
     *
     */
    public Bishop(int X, int Y, int c, Player owner) {
        this.location = new Location(X,Y);
        this.color = c;
        this.kind = "BISHOP";
        if(c == 1){
            this.value = 4;
        }
        else {
            this.value = -4;
        }
        this.owner = owner;
    }

    /**
     * Explores all options for movement from location.
     * @param location location from where we want to explore.
     * @param chessBoard is the chess board we are working on.
     */
    public ArrayList<Location> explore_possible_positions(Location location,ChessBoard chessBoard) {
        possible_positions = new ArrayList<>();
        if (chessBoard == null) {
            return possible_positions;
        }
        // add moves in diagonal lines to the list
        addMovesInLine(location,chessBoard, possible_positions, 1, 1);
        addMovesInLine(location,chessBoard, possible_positions, -1, 1);
        addMovesInLine(location,chessBoard, possible_positions, 1, -1);
        addMovesInLine(location,chessBoard, possible_positions, -1, -1);
        return possible_positions;
    }

}