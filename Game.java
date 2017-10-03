import java.util.*;

/*
 * The controller
 * Translates user actions into operations on the model
 */
public class Game{
  
  public static void main(String[] args) {
    
    //TODO UNCOMMENT
    //Board connect_four_board = new Board();

    // TEMP while gui doesnt work
    Model m = new Model();
    Scanner reader = new Scanner(System.in);
    int n;

    while(m.gameOver() == 0) {
      m.print();
      System.out.println("Player " + m.getTurn() + "'s turn: ");
      System.out.println("Enter a column to place a piece in.");
      n = reader.nextInt() - 1;
      if(n < 0 || n > Model.ROWS) {
        System.out.println("Invalid column! Try again.");
        continue;
      }
      if(!m.dropPiece(n)) {
        System.out.println("Column is full! Try again.");
        continue;
      }
      m.switchTurn();
    }

    m.print();
    System.out.println("Game over!");
    if(m.gameOver() == 3) {
      System.out.println("It's a draw!");
    } else {
      System.out.println("Player " + m.gameOver() + " wins!");
    }
    // TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP
  }
}
