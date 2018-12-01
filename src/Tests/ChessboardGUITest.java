package Tests;

import GUI.ChessboardGUI;
import Game.ChessBoard;
import Game.Pieces.AKnightBishop;
import Game.Pieces.CKnightRook;
import Game.Pieces.King;
import Game.Pieces.Queen;
import org.junit.After;
import org.junit.jupiter.api.Test;

public class ChessboardGUITest {

    @Test
    public void FullChessboardGUITest() throws Exception {
            ChessBoard chessData = new ChessBoard();
            chessData.setUpStartBoard(chessData);

            new ChessboardGUI(chessData);
    }

    @Test
    public void CustomPieceChessboardGUITest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(4,0,1, chessBoard.getPlayerWhite());
        AKnightBishop knightbishop_w = new AKnightBishop(5,1,1, chessBoard.getPlayerWhite());
        AKnightBishop knightbishop_b = new AKnightBishop(3,1,-1, chessBoard.getPlayerBlack());
        CKnightRook knightrook_w = new CKnightRook(2,1,1, chessBoard.getPlayerWhite());
        CKnightRook knightrook_b = new CKnightRook(1,1,-1, chessBoard.getPlayerBlack());

        chessBoard.placePiece(king);
        chessBoard.placePiece(knightbishop_w);
        chessBoard.placePiece(knightbishop_b);
        chessBoard.placePiece(knightrook_w);
        chessBoard.placePiece(knightrook_b);

        new ChessboardGUI(chessBoard);
    }

    @Test
    public void CheckmateSituationChessboardGUITest() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setBoardAfresh();
        King king_w = new King(5, 5, 1, chessBoard.getPlayerWhite());
        Queen queen = new Queen(6, 6, 1, chessBoard.getPlayerWhite());
        King king_b = new King(6, 7, -1, chessBoard.getPlayerBlack());
        chessBoard.placePiece(king_w);
        chessBoard.placePiece(queen);
        chessBoard.placePiece(king_b);

        new ChessboardGUI(chessBoard);
    }

    @Test
    public void PieceOutsideChessboardGUITest() throws Exception {

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setBoardAfresh();        Queen queen_w = new Queen(6,6,1, chessBoard.getPlayerWhite());
        Queen queen_b = new Queen(9,6,-1, chessBoard.getPlayerBlack());// This piece is out of board
        chessBoard.placePiece(queen_b);
        chessBoard.placePiece(queen_w);

        new ChessboardGUI(chessBoard);
    }

    @After
    public void tearDown() throws Exception {
        while (true) { Thread.sleep(2000); }
    }
}