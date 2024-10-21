import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.util.List;
import java.util.LinkedList;

public class OthelloBoard extends JPanel implements MouseListener { 
    
    private int board_size;
    OthelloManager om;
    private List<BoardListener> listeners;

    public OthelloBoard(int board_size) {
        // 1. Set fields
        this.board_size = board_size;
        this.om = new OthelloManager(board_size);

        // 2. Set up panel interactivity
        setLayout(null);
        setFocusable(true);
        this.addMouseListener(this);

        // 3. Create the border
        Border boardBorder = BorderFactory.createStrokeBorder(new BasicStroke(5.0f), Color.BLACK);
        this.setBorder(boardBorder);

        // 4. set up the observer shenanigans
        listeners = new LinkedList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int space_height = this.getHeight() / board_size;
        int space_width = this.getWidth() / board_size;

        // 1. paint background
        setBackground(new Color(0xA1662F));

        // 2. paint the grid
        g.setColor(Color.BLACK);
        for(int i = 1; i < board_size; i++)  {
            g.drawLine(0, space_height *  i, board_size * space_width, space_height * i);
            g.drawLine(space_width *  i,  0, space_width * i, board_size * space_height);
        }

        // 3. paint the pieces
        for(int i = 0; i < board_size; i++) {
            for(int j =  0; j  < board_size; j++) {
                if(om.boardAt(i,j) ==  0) continue;
                if(om.boardAt(i,j) ==  -1) g.setColor(Color.WHITE);
                else g.setColor(Color.BLACK);
                g.fillOval(space_width * i + (space_width >> 3),
                    space_height * j + (space_height >> 3),
                    (space_width >> 2)*3,
                    (space_width >> 2)*3);
            }
        }
    }

    public int[] getScores() {
        return om.getScores();
    }

    public void newGame() {
        om.newGame();
        repaint();
    }

    public int getTurn() {
        return om.getTurn();
    }

    public void addListener(BoardListener l) {
        listeners.add(l);
    } 

    public void notifyListeners(String event) {
        for(BoardListener l : listeners) {
            l.boardNotified(event);
        }
    }


    /** ------------ MOUSELISTENER METHODS ------------------------- pressed, entered, released, clicked, exited
    */

    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        //  calculate the (board) i, j coordinates  of the event
        int i = e.getY() / (getHeight() / board_size);
        int j = e.getX() / (getWidth() / board_size);

        if(om.makeMove(j, i) == 0) {
            notifyListeners("ValidMove");
        }
        repaint();
    }
    public void mouseExited(MouseEvent e) {}
}
