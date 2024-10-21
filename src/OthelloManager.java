import java.util.*;

public class OthelloManager {
    private int BOARD_SIZE;
    private int[][] board;
    private int colorTurn;
    private int[] scores;

    /** ---------------------------- CONSTRUCTOR ---------------------------------
    */

    public OthelloManager(int board_size) {
        this.BOARD_SIZE = board_size;
        newGame();
    }

    /** ----------------------- FUNCTIONALITY METHODS  ---------------------------
    */

    // flips the piece at (x, y), if there is one.
    public void flip(int x, int  y) {
        board[y][x] *= -1;
        scores[turnIndex()]++;
        scores[turnIndex()^1]--;
    }

    public int makeMove(int y, int x) {
        if(board[y][x] != 0) { // spot is already occupied
            return 1;
        }
        List<Coordinate> toFlip = new LinkedList<Coordinate>();
        // make move
        board[y][x] = colorTurn;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j  <= 1; j++) {
                traceLine(x + j, y + i, j, i, toFlip);
            }
        }
        if(toFlip.size() == 0) { // play did not flip any opponent's tiles
            board[y][x] = 0;
            return 1;
        }
        // adjust score
        scores[turnIndex()]++;
        for(Coordinate c : toFlip) {
            flip(c.x, c.y);
        }
        colorTurn *= -1;
        return 0;
    }

    public boolean traceLine(int x, int y, int dx, int dy, List<Coordinate> fliplist) {
        if(x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE) { // check for out of bounds
            return false;
        } else if(board[y][x] == 0) {
            return false;
        } else if(board[y][x] == colorTurn) {
            return true;
        }
        boolean next = traceLine(x + dx, y+ dy, dx, dy, fliplist);
        if(next) {
            fliplist.add(new Coordinate(x, y));
        }
        return next;
    }

    public void newGame() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        board[BOARD_SIZE / 2][BOARD_SIZE / 2] = -1;
        board[(BOARD_SIZE / 2) - 1][(BOARD_SIZE / 2) - 1] = -1;
        board[BOARD_SIZE / 2][(BOARD_SIZE/2) - 1] = 1;
        board[(BOARD_SIZE / 2) - 1][BOARD_SIZE / 2] = 1;
        scores = new int[]{2, 2};
        // white goes first
        colorTurn = -1;
    }

    // turns -1 (white turn) to 0, 1 (black turn) to 1
    private int turnIndex() {
        return (colorTurn + 1) >> 1;
    }


    /** -------------------------- ACCESS METHODS --------------------------------
    */

    public int boardAt(int i, int j) {
        return board[i][j];
    }

    public int[] getScores() {
        return scores;
    }

    public int getTurn() {
        return colorTurn;
    }

}


