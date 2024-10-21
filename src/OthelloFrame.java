import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Insets;

public class OthelloFrame extends JFrame{
    public static final int BOARD_SIZE = 8;
    public static final int SCALE_FACTOR = 60;

    public OthelloFrame() {

        OthelloPanel gameBoard = new OthelloPanel(BOARD_SIZE, SCALE_FACTOR);

        // set up the frame
        setTitle("Othello");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        ImageIcon icon = new ImageIcon("./src/icon.png");
        setIconImage(icon.getImage());

        //  for some reason the insets chop off the bottom of the thing
        Insets i = this.getInsets();
        Dimension d = gameBoard.getMinimumSize();
        d.height += (i.bottom + i.top);
        d.width += (i.left + i.right);
        this.setMinimumSize(d);

        add(gameBoard);
    }
}
