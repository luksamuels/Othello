
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;


public class OthelloPanel extends JPanel implements ActionListener, BoardListener {
    private final int X_UNITS = 24;
    private final int Y_UNITS = 16;
    private int scale_factor;

    private OthelloBoard board;
    private JButton newGameButton;
    private InfoPane gameInfo;

    public OthelloPanel(int board_size, int scale_factor) {
        // set up the thing
        this.scale_factor = scale_factor;
        this.setMinimumSize(new Dimension(X_UNITS * scale_factor, Y_UNITS * scale_factor));
        this.setSize(X_UNITS * scale_factor, Y_UNITS * scale_factor);
        this.setFocusable(true);
        this.setLayout(null);

        // set up the board
        board = new OthelloBoard(board_size);
        board.setBounds(2*scale_factor, 2*scale_factor, 12 * scale_factor, 12 * scale_factor);
        board.addListener(this);

        // set up the information pane
        gameInfo = new InfoPane(7 * scale_factor, 9*scale_factor);
        gameInfo.setBounds(16 * scale_factor, 1 * scale_factor, 7 * scale_factor, 9 * scale_factor);
        gameInfo.setScores(new int[]{2, 2});
        gameInfo.setColor(Color.LIGHT_GRAY);

        // set up the new game button
        newGameButton = new JButton("New Game");
        newGameButton.setBounds(16 * scale_factor, 11 * scale_factor, 7 * scale_factor, 4 * scale_factor);
        newGameButton.setActionCommand("new_game");
        newGameButton.addActionListener(this);
        newGameButton.setFont(new Font("Poor Richard", Font.BOLD, 48));
        
        this.add(board);
        this.add(gameInfo);
        this.add(newGameButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(0x154711));

        // 1. Place the background for the board
        g.setColor(new Color(0x825226));
        g.fillRect(scale_factor, scale_factor, (X_UNITS * scale_factor * 7) / 12, (X_UNITS * scale_factor * 7) / 12);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(16 * scale_factor, 1 * scale_factor, 7 * scale_factor, 9 * scale_factor);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("new_game")) {
            board.newGame();
        }
    }

    public void boardNotified(String event) {
        gameInfo.setScores(board.getScores());
        gameInfo.setTurn(board.getTurn());
    }
}