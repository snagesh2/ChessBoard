package Game;

import Game.Pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChessBoard {

    private int ranks;//This is the number of rows
    private int files;//This is the number of columns
    private int[][] boardPrint;//This is the data structure for printing and checking.
    private HashMap<Location, Piece> board; //This is the structure to hold pieces of the board.
    private ArrayList<Location> playerMoves; //This contains the places each player can reach.
    private Player playerWhite;
    private Player playerBlack;

    /**
     * Constructor for ChessBoard. Intended for the board data structures and checking if won or not.
     */

    public ChessBoard() {
        ranks = 8;
        files = 8;
        boardPrint = new int[ranks][files];
        board = new HashMap<>();
        playerWhite = new Player(true,1);
        playerBlack = new Player(false,-1);
        setBoardAfresh();
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }

    /**
     * Set the board up, Empty.
     */
    public void setBoardAfresh() {
        System.out.println("Building a new basic chessboard with two players");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                board.put(loc, new Piece());
                boardPrint[i][j] = 0;
            }
        }
    }

    /**
     * Prints the board with data structure HashMap<Location, Piece> board.
     * @param board the data structure which contains pieces at certain locations.
     */
    public void printBoard(HashMap<Location, Piece> board) {
        for (Map.Entry<Location, Piece> entry : board.entrySet()) {
            Location key = entry.getKey();
            Piece value = entry.getValue();
            key.printLoc();
            System.out.println("      " + value);
        }
    }

    /**
     * Prints the board with data structure int[][] board.
     */
    public void printBoardPrint(){
        for(int i = 0;i<boardPrint.length;i++){
            for(int j=0;j<boardPrint[i].length;j++){
                System.out.print(boardPrint[i][j]+" , ");
            }
            System.out.println();
        }
    }

    /**
     * Finds the location given x and y coordinates.
     * @param x is the x coordinate
     * @param x is the x coordinate
     */
    public Location find_location(int x, int y) {
        for (Map.Entry<Location, Piece> entry : board.entrySet()) {
            Location key = entry.getKey();
            if (key.getFile() == x && key.getRank() == y) {
                return key;
            }
        }
        return new Location(x, y);
    }

    /**
     * Set the board up to the initial board set up, with all data structures full of .
     */
    public void setUpStartBoard(ChessBoard chessBoard) {
        for (int i = 0; i < 8; i++) {
            chessBoard.boardPrint[1][i] = 1;
            chessBoard.board.put(new Location(1, i), new Pawn(1, i, 1, playerWhite));
            chessBoard.playerWhite.add_to_piecelist(board.get(find_location(1,i)));
        }
        chessBoard.boardPrint[0][0] = 2;
        chessBoard.board.put(new Location(0, 0), new Rook(0, 0, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,0)));
        chessBoard.boardPrint[0][1] = 3;
        chessBoard.board.put(new Location(0, 1), new Knight(0, 1, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,1)));
        chessBoard.boardPrint[0][2] = 4;
        chessBoard.board.put(new Location(0, 2), new Bishop(0, 2, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,2)));
        chessBoard.boardPrint[0][3] = 5;
        chessBoard.board.put(new Location(0, 3), new Queen(0, 3, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,3)));
        chessBoard.boardPrint[0][4] = 6;
        chessBoard.board.put(new Location(0, 4), new King(0, 4, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,4)));
        chessBoard.boardPrint[0][5] = 4;
        chessBoard.board.put(new Location(0, 5), new Bishop(0, 5, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,5)));
        chessBoard.boardPrint[0][6] = 3;
        chessBoard.board.put(new Location(0, 6), new Knight(0, 6, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,6)));
        chessBoard.boardPrint[0][7] = 2;
        chessBoard.board.put(new Location(0, 7), new Rook(0, 7, 1, playerWhite));
        chessBoard.playerWhite.add_to_piecelist(board.get(find_location(0,7)));

        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 7; j++) {
                chessBoard.boardPrint[j][i] = 0;
            }
        }
        for (int i = 0; i < 8; i++) {
            chessBoard.boardPrint[6][i] = -1;
            chessBoard.board.put(new Location(6, i), new Pawn(6, i, -1, playerBlack));
            chessBoard.playerBlack.add_to_piecelist(board.get(find_location(6,i)));
        }
        chessBoard.boardPrint[7][0] = -2;
        chessBoard.board.put(new Location(7, 0), new Rook(7, 0, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,0)));
        chessBoard.boardPrint[7][1] = -3;
        chessBoard.board.put(new Location(7, 1), new Knight(7, 1, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,1)));
        chessBoard.boardPrint[7][2] = -4;
        chessBoard.board.put(new Location(7, 2), new Bishop(7, 2, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,2)));
        chessBoard.boardPrint[7][3] = -5;
        chessBoard.board.put(new Location(7, 3), new Queen(7, 3, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,3)));
        chessBoard.boardPrint[7][4] = -6;
        chessBoard.board.put(new Location(7, 4), new King(7, 4, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,4)));
        chessBoard.boardPrint[7][5] = -4;
        chessBoard.board.put(new Location(7, 5), new Bishop(7, 5, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,5)));
        chessBoard.boardPrint[7][6] = -3;
        chessBoard.board.put(new Location(7, 6), new Knight(7, 6, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,6)));
        chessBoard.boardPrint[7][7] = -2;
        chessBoard.board.put(new Location(7, 7), new Rook(7, 7, -1, playerBlack));
        chessBoard.playerBlack.add_to_piecelist(board.get(find_location(7,7)));

    }

    public int[][] getBoardPrint() {
        return this.boardPrint;
    }

    public HashMap<Location, Piece> getBoard() {
        return this.board;
    }

    /**
     * Checks whether player is check mated.
     * @param chessBoard board to check players status on
     * @param player player to check the status of
     * @return Whether the player has been checkmated.
     */
    public boolean checkmated(Player player, ChessBoard chessBoard) {
        if (!(inCheck(player, chessBoard)) || inStalemate(chessBoard)){
            return false;
        }
        ArrayList<Piece> myPieces = player.get_list();

        for (Piece each : myPieces) {
            playerMoves = each.explore_possible_positions(find_location(each.get_location().getFile(),each.get_location().getRank()),chessBoard);

            for(int k=0; k<playerMoves.size(); k++){
                Location eachMove = playerMoves.get(k);
                Piece capturedPiece = chessBoard.getBoard().get(eachMove);
                each.move(chessBoard.find_location(each.get_location().getFile(),each.get_location().getRank()),eachMove, chessBoard);
                if (!inCheck(player, chessBoard)) { //now we can check the modified board
                    each.undoMove(eachMove, chessBoard.find_location(each.get_location().getFile(),each.get_location().getRank()),capturedPiece, chessBoard);
                    return false;
                }
                each.undoMove(eachMove, chessBoard.find_location(each.get_location().getFile(),each.get_location().getRank()),capturedPiece, chessBoard);
            }
            return true;
        }
        return true;
    }

    /**
     * If the player who's turn it currently is is not in check and cannot move, return true
     * @param chessBoard board to check players status on
     * @return is the game in stalemate
     */
    public boolean inStalemate(ChessBoard chessBoard) {
        if (inCheck(chessBoard.getCurrentPlayer(), chessBoard)) {
            return false;
        }
        Player player = (chessBoard.getCurrentPlayer());
        ArrayList<Piece> myPieces = player.get_list();

        for (Piece each : myPieces) {
            ArrayList<Location> ret = each.explore_possible_positions(find_location(each.get_location().getFile(),each.get_location().getRank()),chessBoard);
            for (Location destination : ret) {
                if (each.canMoveTo(each.get_location(), destination, chessBoard)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks whether player is in check.
     * @param chessBoard board to check players status on
     * @param player player to check the status of
     * @return Whether the player is in check.
     */
    public boolean inCheck( Player player, ChessBoard chessBoard) {
        Piece king = getKing(player);
        if(king.getValue() == 0){
            return false;
        }
        Location kingLoc = chessBoard.find_location(getKing(player).get_location().getFile(),getKing(player).get_location().getRank());
        Player other = getOtherPlayer(player);
        for (Piece enemyPiece : other.get_list()) {
            Location enemy_loc = chessBoard.find_location(enemyPiece.get_location().getFile(),enemyPiece.get_location().getRank());
            if (enemyPiece.canMoveTo(enemy_loc,kingLoc, chessBoard)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets king piece
     * @param player player to check the status of
     * @return King piece of player
     */
    public Piece getKing(Player player){
        for (Piece myPieces : player.get_list()) {
            if(myPieces.getValue() == 6 || myPieces.getValue() == -6){
                return myPieces;
            }
        }
        int X = 0;
        int Y = 0;
        for(int i = 0;i<boardPrint.length;i++){
            for(int j=0;j<boardPrint[i].length;j++){
                if(boardPrint[i][j] == 6 ||boardPrint[i][j] == -6 ){
                    X = i;
                    Y = j;
                }
            }
        }
        return board.get(find_location(X,Y));
    }

    /**
     * Returns current player
     */
    public Player getCurrentPlayer(){
        if(playerWhite.get_turn()){
            return playerWhite;
        }
        else{
            return playerBlack;
        }
    }
    /**
     * Returns the other player
     */
    public Player getOtherPlayer(Player player){
        if(player == playerWhite){
            return playerBlack;
        }
        else{
            return playerWhite;
        }
    }

    public void placePiece(Piece piece){
        int x = piece.get_location().getFile();
        int y = piece.get_location().getRank();
        if(isOnBoard(x,y)){
            getBoard().put(find_location(piece.get_location().getFile(),piece.get_location().getRank()), piece);
            getBoardPrint()[x][y] = piece.getValue();
            if(piece.getColor() == 1){
                getPlayerWhite().add_to_piecelist(piece);
            }
            else if((piece.getColor() == -1)){
                getPlayerBlack().add_to_piecelist(piece);
            }
            else{
                return;
            }
        }
        else{
            return;
        }
    }

    public boolean isOnBoard(int x,int y){
        if(x < 0 ||  x > 7 || y < 0 || y > 8){
            return false;
        }
        return true;
    }

    public int getRanks(){
        return ranks;
    }

    public int getFiles() {
        return files;
    }
}
