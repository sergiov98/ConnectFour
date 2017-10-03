/*
 * The abstract model for the game
 * Holds the state of the game and the rules by which it may be modified
 */
public class Model {
  
  final static int WIN = 4;
  final static int ROWS = 6;
  final static int COLS = 7;
  private int[][] grid;
  private int playerTurn;
  private int recentDropR;
  private int recentDropC;
  private int tempR;
  private int tempC;
  private int inARow;

  /**
   * Constructor
   */
  public Model() {
    playerTurn = 1;
    recentDropR = 0;
    recentDropC = 0;
    grid = new int[ROWS][COLS];

    // row numbers from bottom to top, columns from left to right
    for(int r = 0; r < ROWS; r++) {
      for(int c = 0; c < COLS; c++) {
        grid[r][c] = 0;
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
      if(grid[r][col] == 0) {
        grid[r][col] = playerTurn;
        recentDropR = r;
        recentDropC = col;
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
    // check for 4 in a row

    // horizontally
    inARow = 0;
    for(int c = 0; c < COLS; c++) {
      if(grid[recentDropR][c] == 1) {
        if(inARow <= 0) {
          inARow = 1;
        } else {
          inARow++;
        }
      } else if(grid[recentDropR][c] == 2) {
        if(inARow >= 0) {
          inARow = -1;
        } else {
          inARow--;
        }
      } else {
        inARow = 0;
      }
      if(inARow == WIN) {
        return 1;
      } else if(inARow == -WIN) {
        return 2;
      }
    }

    // vertically 
    inARow = 0;
    for(int r = 0; r < ROWS; r++) {
      if(grid[r][recentDropC] == 1) {
        if(inARow <= 0) {
          inARow = 1;
        } else {
          inARow++;
        }
      } else if(grid[r][recentDropC] == 2) {
        if(inARow >= 0) {
          inARow = -1;
        } else {
          inARow--;
        }
      } else {
        inARow = 0;
      }
      if(inARow == WIN) {
        return 1;
      } else if(inARow == -WIN) {
        return 2;
      }
    }

    // diagonally (bottom-left to top-right)
    inARow = 0; 
    tempR = recentDropR;
    tempC = recentDropC;
    while(tempR > 0 && tempC > 0) {
      tempR--;
      tempC--;
    }
    while(tempR< ROWS && tempC < COLS) {
      if(grid[tempR][tempC] == 1) {
        if(inARow <= 0) {
          inARow = 1;
        } else {
          inARow++;
        }
      } else if(grid[tempR][tempC] == 2) {
        if(inARow >= 0) {
          inARow = -1;
        } else {
          inARow--;
        }
      } else {
        inARow = 0;
      }
      if(inARow == WIN) {
        return 1;
      } else if(inARow == -WIN) {
        return 2;
      }
      tempR++;
      tempC++;
    }

    // diagonally (top-left to bottom-right)
    inARow = 0; 
    tempR = recentDropR;
    tempC = recentDropC;
    while(tempR < ROWS - 1 && tempC > 0) {
      tempR++;
      tempC--;
    }
    while(tempR >= 0 && tempC < COLS) {
      if(grid[tempR][tempC] == 1) {
        if(inARow <= 0) {
          inARow = 1;
        } else {
          inARow++;
        }
      } else if(grid[tempR][tempC] == 2) {
        if(inARow >= 0) {
          inARow = -1;
        } else {
          inARow--;
        }
      } else {
        inARow = 0;
      }
      if(inARow == WIN) {
        return 1;
      } else if(inARow == -WIN) {
        return 2;
      }
      tempR--;
      tempC++;
    }

    // if any space is unfilled, game is not over yet
    for(int r = 0; r < ROWS; r++) {
      for(int c = 0; c < COLS; c++) {
        if(grid[r][c] == 0) {
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

  /**
   * Prints the board in text form
   */
  public void print() {
    System.out.println("Board:");
    for(int r = ROWS - 1; r >= 0; r--) {
      for(int c = 0; c < COLS; c++) {
        System.out.print(grid[r][c] + " ");
      }
      System.out.println();
      System.out.println();
    }
  }
    
} // End Model Class
