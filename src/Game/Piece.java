package Game;

import java.util.ArrayList;
import java.util.HashMap;

public  class Piece {
    protected Player owner;
    protected int color;
    protected int value = 0;
    protected String kind;
    protected ArrayList<Location> possible_positions;
    protected boolean can_move;
    protected Location location;

    /**
     * Constructor for piece, returns an empty piece which can be replaced.
     */
    public Piece(){
        value= 0;
        color =0;
        kind = "EMPTY";
    }
    /**
     * Check if Piece is on board.
     */
    public boolean isOutBoard(Location location){
        if(location.getRank() < 1 || location.getRank() > 8 || location.getFile() < 1 || location.getFile() > 8){
            return true;
        }
        return false;
    }
    public  ArrayList<Location> explore_possible_positions(Location location,ChessBoard chessBoard) {
        return this.possible_positions;
    }

    public Location get_location(){
        return this.location;
    }

    public String get_name(){
        return this.kind;
    }

    public int getColor(){
        return this.color;
    }

    public int getValue() {
        return value;
    }

    public Player get_owner(){
        return this.owner;
    }



    /**
     * This is the move function. It places a piece from @param location to @param destination.
     * @param location Move from here.
     * @param destination Move to here.
     * @param chessBoard This the board we play on
     */
    public int[][] move(Location location, Location destination, ChessBoard chessBoard) {
        if(!canMoveTo(location, destination,chessBoard)){
            return chessBoard.getBoardPrint();
        }
        if(canMoveTo(location, destination,chessBoard)){
            if(positionOccupied(destination,chessBoard.getBoard())){
                if(canCapture(location,destination,chessBoard.getBoard())){
                    chessBoard.getBoard().put(chessBoard.find_location(destination.getFile(),destination.getRank()),chessBoard.getBoard().get(location));
                    chessBoard.getBoard().put(chessBoard.find_location(location.getFile(),location.getRank()),new Piece());
                    chessBoard.getBoardPrint()[destination.getFile()][destination.getRank()] = value;
                    chessBoard.getBoardPrint()[location.getFile()][location.getRank()] = 0;
                    owner.set_turn();
                    owner.remove_from_list(chessBoard.getBoard().get(destination));
                }
                else return chessBoard.getBoardPrint();
            }
            else{

                chessBoard.getBoard().put(chessBoard.find_location(destination.getFile(),destination.getRank()),chessBoard.getBoard().get(location));
                chessBoard.getBoard().put(chessBoard.find_location(location.getFile(),location.getRank()),new Piece());
                chessBoard.getBoardPrint()[destination.getFile()][destination.getRank()] = value;
                chessBoard.getBoardPrint()[location.getFile()][location.getRank()] = 0;
                owner.set_turn();
            }
        }

        return chessBoard.getBoardPrint();
    }

    public boolean canCapture(Location location, Location destination, HashMap<Location,Piece> board) {
        if(Math.signum(board.get(location).color) == Math.signum(board.get(destination).color)){
            return false;
        }
        return true;
    }
    public boolean positionOccupied(Location loc, HashMap<Location,Piece> board) {
        if(board.get(loc).getValue() != 0){
            return true;
        }
        return false;
    }

    /**
     * This is the undo move function.
     * @param location Move from here.
     * @param destination Move to here.
     * @param capturedPiece This was the piece previously captured.
     * @param chessBoard This the board we play on
     */
    public int[][] undoMove(Location location, Location destination,Piece capturedPiece, ChessBoard chessBoard) {
        chessBoard.getBoard().put(chessBoard.find_location(destination.getFile(),destination.getRank()),chessBoard.getBoard().get(location));
        chessBoard.getBoard().put(chessBoard.find_location(location.getFile(),location.getRank()),capturedPiece);
        chessBoard.getBoardPrint()[destination.getFile()][destination.getRank()] = value;
        chessBoard.getBoardPrint()[location.getFile()][location.getRank()] = capturedPiece.getValue();
        owner.set_turn();
        owner.add_to_piecelist(capturedPiece);
        return chessBoard.getBoardPrint();
    }

    /**
     * Checks if possible to move to destination.
     * @param location location from where we want to leave.
     * @param destination location we want to reach.
     * @param chessBoard is the chess board we are working on.
     */
    public boolean canMoveTo(Location location, Location destination, ChessBoard chessBoard) {
        if(explore_possible_positions(location, chessBoard).contains(destination)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds valid moves in a straight line.
     * Valid here means, on board and capturable or unoccupied.
     * @param location current location.
     * @param moves list to add to.
     * @param xi increment.
     * @param yi increment.
     */
    protected void addMovesInLine(Location location,ChessBoard chessBoard, ArrayList<Location> moves, int xi, int yi) {
        int x = location.getFile();
        int y = location.getRank();
        if((location.getFile()>7 || location.getFile() <0 || location.getRank() <0 || location.getRank() >7)){
            return;
        }
        Location pt = chessBoard.find_location(x + xi, y + yi);

        while(true) {
            if(pt.getFile()>7 || pt.getFile() <0 || pt.getRank() <0 || pt.getRank() >7){
                break;
            }
            if(positionOccupied(pt,chessBoard.getBoard())){
                if(canCapture(location, pt, chessBoard.getBoard())) {
                    moves.add(pt);
                } else break;
            }
            if(!moves.contains(pt)){
                moves.add(pt);

            }
            x = x+xi;
            y = y+yi;
            pt = chessBoard.find_location(x + xi, y + yi);
        }
    }
    /**
     * Adds valid moves
     * Valid here means, on board and capturable or unoccupied.
     * @param location current location.
     * @param moves list to add to.
     * @param xi increment.
     * @param yi increment.
     */
    protected void addIfValid(Location location, ChessBoard chessBoard, ArrayList<Location> moves, int xi, int yi) {
        int x = location.getFile();
        int y = location.getRank();
        Location pt = chessBoard.find_location(x + xi, y + yi);
        if(!(pt.getFile()>7 || pt.getFile() <0 || pt.getRank() <0 || pt.getRank() >7)){
            if(positionOccupied(pt,chessBoard.getBoard())) {
                if(canCapture(location,pt,chessBoard.getBoard())){
                    moves.add(pt);
                }
            }
            else{
                moves.add(pt);
            }
        }

    }

}
