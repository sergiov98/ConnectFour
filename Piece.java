/*
 * View of the model
 * Displays the game state to the user
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Piece extends JPanel{
  //private final JPanel gui = new JPanel(new BorderLayout(4,4));

  int xPosition;
  int yPosition;
  Color pieceColor;
  public Piece(int x, int y, Color color){
    xPosition = x;
    yPosition = y;
    pieceColor = color;
  }
  public void paint(Graphics g){
    super.paint(g);

    setSize(600,600);
    g.setColor(pieceColor);
    g.fillOval(xPosition,yPosition,200,200);
  }


}
