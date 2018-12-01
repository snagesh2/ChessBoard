package Game.Pieces;

import Game.ChessBoard;
import Game.Location;
import Game.Player;

import java.util.ArrayList;

public class Rook extends Game.Piece {
    /**
     * Adds all valid moves in a straight line to the list.
     * Valid here means, on board and capturable or unoccupied.
     * @param c color of piece.
     * @param X X coordinate.
     * @param Y y coordinate.
     * @param owner is the owner of the piece.
     *
     */
    public Rook(int X, int Y, int c, Player owner) {
        this.location = new Location(X,Y);
        this.color = c;
        this.kind = "ROOK";
        if(c == 1){
            this.value = 2;
        }
        else {
            this.value = -2;
        }        this.owner = owner;
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

        // up/down/left/right
        addMovesInLine(location,chessBoard, possible_positions, 1, 0);
        addMovesInLine(location,chessBoard, possible_positions, 0, 1);
        addMovesInLine(location,chessBoard, possible_positions, -1, 0);
        addMovesInLine(location,chessBoard, possible_positions, 0, -1);

        return possible_positions;
    }



}