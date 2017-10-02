/*
 * View of the model
 * Displays the game state to the user
 */
import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
  private final JPanel gui = new JPanel(new BorderLayout(4,4));

  public Board() {
    JFrame frame = new JFrame();
    frame.setSize(600,600);
    frame.getContentPane().setBackground(Color.BLUE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void display(Model m) {

  }

}
