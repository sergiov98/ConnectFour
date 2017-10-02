/*
 * The abstract model for the game
 * Holds the state of the game and the rules by which it may be modified
 */
public class Model {
  
  final static int ROWS = 6;
  final static int COLS = 7;
  private Piece[][] grid;
  private int playerTurn;

  /**
   * Constructor
   */
  public Model() {
    playerTurn = 1;
    grid = new Piece[ROWS][COLS];

    // row numbers from bottom to top, columns from left to right
    for(int r = 0; r < ROWS; r++) {
      for(int c = 0; c < COLS; c++) {
        grid[r][c] = new Piece();
      }
    }
  }

  /**
   * Drops a piece into the specified column
   * @param col The column to drop the piece in
   * @return False if column is full, true if successful
   */
  public boolean dropPiece(int col) {
    // check column for empty spaces from the bottom up
    for(int r = 0; r < ROWS; r++) {
      if(grid[r][col].getColor() == 0) {
        grid[r][col].setColor(playerTurn);
        return true;
      }
    }
    return false;
  }

  /** 
   * Checks for game over conditions:
   * -If 4 in a row has been made
   * -If the board is completely filled
   * @return whether or not the game is over, if so which player won
   *         0 for not game over, 1 for p1, 2 for p2, 3 for draw
   */
  public int gameOver() {
    // TODO check 4 in a row

    // if any space is unfilled, game is not over yet
    for(int r = 0; r < ROWS; r++) {
      for(int c = 0; c < COLS; c++) {
        if(grid[r][c].getColor() == 0) {
          return 0;
        }
      }
    }
    return 3; // board is filled, draw game
  }

  /**
   * Switches which player's turn it is
   */
  public void switchTurn() {
    playerTurn = (playerTurn == 1) ? 2 : 1;
  }

  /**
   * Accessor for playerTurn
   * @return which player's turn it is
   */
  public int getTurn() {
    return playerTurn;
  }
}
