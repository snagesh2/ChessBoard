/*
source: https://github.com/Creyslz/ChessLibrary
 */

package GUI;
import Game.ChessBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class is used to generate the GUI for a chess game.
 * Currently, it is a Static GUI. NO moves have been implemented.
 */
public class ChessboardGUI implements ActionListener {

    private final JPanel appGUI = new JPanel(new BorderLayout(3, 3));
    private JPanel chessboardGUI;
    private JButton[][] chessBoardSquares;
    private Image[][] chessPieceImages = new Image[2][9];//row 0: white. row 1: black.
    private ChessBoard boardData; //data according to which the GUI is built
    private static final String COLS = "ABCDEFGH";
    private final JLabel message = new JLabel("Chess board is ready and waiting!");

    /**
     * This is the constructor for the GUI for the chessboard.
     * @param chessData
     */
    public ChessboardGUI(ChessBoard chessData) {
        boardData = chessData;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            //nothing
        }
        JFrame window = new JFrame("Chess");
        window.setSize(500, 500);
        chessBoardSquares = new JButton[boardData.getFiles()][boardData.getRanks()];
        createImages();
        initializeAppGUI();
        System.out.println("Printing the chess pieces");
        drawPieces();
        window.add(appGUI);
        setUpMenu(window);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *This sets up the window where the chess game will be played.
     */
    private void initializeAppGUI() {
        // set up the main GUI
        appGUI.setBorder(new EmptyBorder(5, 5, 5, 5));
        setupToolbar();
        appGUI.add(new JLabel("?"), BorderLayout.LINE_START);
        chessboardGUI = new JPanel(new GridLayout(0, 9));
        chessboardGUI.setBorder(new LineBorder(Color.BLACK));
        appGUI.add(chessboardGUI);
        setupBoard();
        //fill the chess board
        chessboardGUI.add(new JLabel(""));
        // fill the top row
        for (int index = 0; index < 8; index++) {
            chessboardGUI.add(new JLabel(COLS.substring(index, index + 1), SwingConstants.CENTER));
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                switch (col) {
                    case 0:
                        chessboardGUI.add(new JLabel("" + (8-row),
                                SwingConstants.CENTER));
                    default:
                        chessboardGUI.add(chessBoardSquares[boardData.getFiles()-row-1][boardData.getRanks() - col-1]);
                }
            }
        }
    }

    /**
     * This function sets up the toolbar in the window where the chess game will be played.
     */
    private void setupToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        appGUI.add(toolbar, BorderLayout.PAGE_START);
        toolbar.add(new JButton("Buttons!"));
        toolbar.addSeparator();
        toolbar.add(message);
    }

    /**
     * This function sets up the board in the window where the chess game will be played.
     * The board will be made up of alternating black and white tiles.
     */
    private void setupBoard() {
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int col = 0; col < chessBoardSquares[row].length; col++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                if ((col % 2 == 1 && row % 2 == 1) || (col % 2 == 0 && row % 2 == 0)) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLACK);
                }
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                chessBoardSquares[row][col] = button;
            }
        }
    }
    /**
     * This function sets up the menu bar in the window where the chess game will be played.
     */
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }

    /**
     * This function sets up the array of chess piece images from the images folder.
     * Images from link mentioned above.
     */
    private final void createImages() {
        try {
            chessPieceImages[0][0] = ImageIO.read(new File("images/e.png"));
            chessPieceImages[0][1] = ImageIO.read(new File("images/wp.png"));
            chessPieceImages[0][2] = ImageIO.read(new File("images/wr.png"));
            chessPieceImages[0][3] = ImageIO.read(new File("images/wn.png"));
            chessPieceImages[0][4] = ImageIO.read(new File("images/wb.png"));
            chessPieceImages[0][5] = ImageIO.read(new File("images/wq.png"));
            chessPieceImages[0][6] = ImageIO.read(new File("images/wk.png"));
            chessPieceImages[0][7] = ImageIO.read(new File("images/wa.png"));
            chessPieceImages[0][8] = ImageIO.read(new File("images/wc.png"));

            chessPieceImages[1][0] = ImageIO.read(new File("images/e.png"));
            chessPieceImages[1][1] = ImageIO.read(new File("images/bp.png"));
            chessPieceImages[1][2] = ImageIO.read(new File("images/br.png"));
            chessPieceImages[1][3] = ImageIO.read(new File("images/bn.png"));
            chessPieceImages[1][4] = ImageIO.read(new File("images/bb.png"));
            chessPieceImages[1][5] = ImageIO.read(new File("images/bq.png"));
            chessPieceImages[1][6] = ImageIO.read(new File("images/bk.png"));
            chessPieceImages[1][7] = ImageIO.read(new File("images/ba.png"));
            chessPieceImages[1][8] = ImageIO.read(new File("images/bc.png"));

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This function draws the pieces according to the ChessBoard initialized.
     */
    private void drawPieces() {
        for(int row = 0; row < boardData.getFiles(); row++) {
            for(int col = 0; col < boardData.getRanks(); col++) {
                int pieceId = boardData.getBoardPrint()[row][col];
                if(pieceId > 0) {
                    chessBoardSquares[row][col].setIcon(new ImageIcon(chessPieceImages[0][pieceId]));
                } else if(pieceId < 0) {
                    chessBoardSquares[row][col].setIcon(new ImageIcon(chessPieceImages[1][-pieceId]));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "I was clicked by "+e.getActionCommand(),
                "Title here", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This is the main function where GUI is tested.
     * For each test case, refer to ChessboardGUI test. Copy the function contents and run.
     * @param args
     */
    public static void main(String args[]){
        ChessBoard chessData = new ChessBoard();
        chessData.setUpStartBoard(chessData);

        new ChessboardGUI(chessData);
    }
}
