import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

public class InfoPane extends JPanel {
    private JLabel turnInfo;
    private JLabel whitePoints;
    private JLabel blackPoints;
    private ImageIcon white_chip;
    private ImageIcon black_chip;
    private Font othelloFont;
    

    public InfoPane(int width, int height) {

        // import the images
        white_chip = new ImageIcon("./src/white_token.png");
        black_chip = new ImageIcon("./src/black_token.png");

        // create the font
        this.othelloFont = new Font("Poor Richard", Font.BOLD, 48);

        // set up this panel
        this.setLayout(null);
        Border panelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(0x217a34), new Color(0x164f22));
        this.setBorder(panelBorder);

        // turn info panel
        turnInfo = new JLabel();
        turnInfo.setBounds(10, 0, width, height / 3);
        turnInfo.setText("Current turn: ");
        turnInfo.setFont(othelloFont);
        turnInfo.setHorizontalTextPosition(JLabel.LEFT);
        turnInfo.setVerticalAlignment(JLabel.CENTER);
        turnInfo.setIcon(white_chip);
        turnInfo.setIconTextGap(48);
        
        // white score panel
        whitePoints = new JLabel();
        whitePoints.setBounds(10, height / 3, width, height / 6);
        whitePoints.setFont(othelloFont);
        whitePoints.setHorizontalTextPosition(JLabel.RIGHT);
        whitePoints.setVerticalAlignment(JLabel.CENTER);
        whitePoints.setIcon(white_chip);
        whitePoints.setIconTextGap(30);

        // black score panel
        blackPoints = new JLabel();
        blackPoints.setBounds(10, height / 2, width, height / 6);
        blackPoints.setFont(othelloFont);
        blackPoints.setHorizontalTextPosition(JLabel.RIGHT);
        blackPoints.setVerticalAlignment(JLabel.CENTER);
        blackPoints.setIcon(black_chip);
        blackPoints.setIconTextGap(30);

        this.add(turnInfo);
        this.add(blackPoints);
        this.add(whitePoints);
    
    }

    public void setScores(int[] whiteBlack) {
        whitePoints.setText("Score: " + whiteBlack[0]);
        blackPoints.setText("Score: " + whiteBlack[1]);
    }

    public void setTurn(int turn) {
        if(turn == -1) {
            turnInfo.setIcon(white_chip);
        } else {
            turnInfo.setIcon(black_chip);
        }
    }

    public void setColor(Color c) {
        setBackground(c);
        turnInfo.setBackground(c);
        whitePoints.setBackground(c);
        blackPoints.setBackground(c);
    }
}
