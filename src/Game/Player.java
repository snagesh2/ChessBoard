package Game;

import java.util.ArrayList;

public class Player {

    private int color; //Here, color is set as either -1 or +1 for black or white respectively.
    private boolean turn;
    private ArrayList<Piece> piecesList;
    private Location kingLoc;
    private ArrayList<Location> playerMoves;

    /**
     * This is the player class. There are two players in our chess game, who get their attributes here.
     */


    public Player(boolean turn,  int color){
        color = this.color;
        turn = this.turn;
        piecesList = new ArrayList<>();
    }

    public void add_to_piecelist(Piece piece){
        piecesList.add(piece);
    }

    public ArrayList<Piece> get_list(){
        return this.piecesList;
    }


    public boolean get_turn(){
        return this.turn;
    }
    public int getColor(){
        return this.color;
    }
    public void set_turn() {
        turn = !turn;
    }
    public void remove_from_list(Piece piece){
        piecesList.remove(piece);
    }
}


