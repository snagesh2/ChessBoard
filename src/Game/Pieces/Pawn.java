package Game.Pieces;

import Game.ChessBoard;
import Game.Location;
import Game.Player;

import java.util.ArrayList;

public class Pawn extends Game.Piece {
    private boolean first_turn;
    /**
     * Constructor.
     * @param c color of piece.
     * @param X X coordinate.
     * @param Y y coordinate.
     * @param owner is the owner of the piece.
     *
     */
    public Pawn(int X, int Y, int c, Player owner) {
        this.location = new Location(X,Y);
        this.color = c;
        this.kind = "PAWN";
        if(c == 1){
            this.value = 1;
        }
        else {
            this.value = -1;
        }
        this.owner = owner;
        this.first_turn = true;
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
        addIfPawnValid(location, chessBoard, possible_positions, 0, 1);
        addIfCanCapture(location,chessBoard, possible_positions, 1, 1);
        addIfCanCapture(location,chessBoard, possible_positions, -1, 1);
        addIfPawnValid(location,chessBoard, possible_positions, -1, -2);
        return possible_positions;
    }

    /**
     * Adds valid moves
     * Valid here means, on board and capturable or unoccupied.
     * @param location current location.
     * @param moves list to add to.
     * @param xi increment.
     * @param yi increment.
     */
    private void addIfPawnValid(Location location, ChessBoard chessBoard, ArrayList<Location> moves, int xi, int yi) {
        int x = location.getFile();
        int y = location.getRank();
        if((location.getFile()>7 || location.getFile() <0 || location.getRank() <0 || location.getRank() >7)){
            return;
        }
        Location pt = chessBoard.find_location(x + xi, y + yi);
        // if the location is valid
        if(!(pt.getFile()>7 || pt.getFile() <0 || pt.getRank() <0 || pt.getRank() >7)){
            if(!positionOccupied(pt,chessBoard.getBoard())){
                moves.add(pt);
            }
        }
        if(first_turn){
            Location pt2 = chessBoard.find_location(x + xi + xi, y + yi + yi);
            if(!(pt2.getFile()>7 || pt2.getFile() <0 || pt2.getRank() <0 || pt2.getRank() >7)){
                if(!positionOccupied(pt2,chessBoard.getBoard())){
                    moves.add(pt2);
                }
            }
        }
    }

    /**
     * Adds valid moves
     * Valid here means, on board and capturable.
     * @param location current location.
     * @param moves list to add to.
     * @param xi increment.
     * @param yi increment.
     */
    private void addIfCanCapture(Location location,ChessBoard chessBoard, ArrayList<Location> moves, int xi, int yi) {
        int x = location.getFile();
        int y = location.getRank();
        Location pt = chessBoard.find_location(x + xi, y + yi);
        if(!(pt.getFile()>7 || pt.getFile() <0 || pt.getRank() <0 || pt.getRank() >7)){
            if(positionOccupied(pt,chessBoard.getBoard())) {
                if(canCapture(location,pt,chessBoard.getBoard())){
                    moves.add(pt);
                }
            }
        }
    }

    /**
     * Move to destination and then set first turn to false.
     * @param location location from where we want to leave.
     * @param destination location we want to reach.
     * @param chessBoard is the chess board we are working on.
     */
    @Override
    public int[][] move(Location location, Location destination, ChessBoard chessBoard) {
        int[][] result = move(location, destination, chessBoard);
        first_turn = false;
        return result;
    }
}