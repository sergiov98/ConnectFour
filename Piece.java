import java.awt.*;

/*
 * Class for pieces that go on the board
 */
public class Piece { 

  private int color;

  /**
   * Constructor
   */
  public Piece() {
    color = 0; // None
  }

  /**
   * Mutator for color
   * 0 for None, 1 for Red, 2 for Yellow
   * @param the color to set this piece to
   */
  public void setColor(int c) {
    color = c;
  }

  /**
   * Accessor for color
   * @return the color of the piece
   */
  public int getColor() {
    return color;
  }
}
