package Game.Pieces;

import Game.ChessBoard;
import Game.Location;
import Game.Player;

import java.util.ArrayList;

public class King extends Game.Piece {
    /**
     * Constructor.
     * @param c color of piece.
     * @param X X coordinate.
     * @param Y y coordinate.
     * @param owner is the owner of the piece.
     *
     */
    public King(int X, int Y, int c, Player owner) {
        this.location = new Location(X,Y);
        this.color = c;
        this.kind = "KING";
        if(c == 1){
            this.value = 6;
        }
        else {
            this.value = -6;
        }
        this.owner = owner;
    }

    /**
     * Explores all options for movement from location.
     * @param location location from where we want to explore.
     * @param chessBoard is the chess board we are working on.
     */
    public ArrayList<Location> explore_possible_positions(Location location, ChessBoard chessBoard) {
        possible_positions = new ArrayList<>();
        if (chessBoard == null) {
            return possible_positions;
        }
        // add moves in diagonal lines to the list

        addIfValid(location,chessBoard, possible_positions,-1, -1);
        addIfValid(location,chessBoard, possible_positions,0,-1);
        addIfValid(location,chessBoard, possible_positions,1,-1);
        addIfValid(location,chessBoard, possible_positions,1,0);
        addIfValid(location,chessBoard, possible_positions,1,1);
        addIfValid(location,chessBoard, possible_positions,0,1);
        addIfValid(location,chessBoard, possible_positions,-1,1);
        addIfValid(location,chessBoard, possible_positions,-1,0);
        return possible_positions;
    }






}