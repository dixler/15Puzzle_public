import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {
   private Game game;
   private JFrame app_frame;
   private Renderer renderer;
   private Gui_button button_up;
   private Gui_button button_down;
   private Gui_button button_left;
   private Gui_button button_right;

   private Dimension size;

   public Window(Game my_game){
      this.game = my_game;

      // graphics actions
      this.size = new Dimension(100, 100);
      this.app_frame = new JFrame();
      this.renderer = new Renderer(my_game);
      this.renderer.set_tile_size(size);

      // button handling
      button_up = new Gui_button(size);
      button_down = new Gui_button(size);
      button_left = new Gui_button(size);
      button_right = new Gui_button(size);

      this.app_frame.setTitle("15 puzzle");
      this.app_frame.setSize(1000, 1000);
      this.app_frame.setBackground(Color.BLACK);
      this.app_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.app_frame.setVisible(true);

      this.app_frame.add(renderer);
      this.app_frame.repaint();
   }

   public void play(){
      this.game.user_move();


   }
   class MoveListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         //      this.app_frame.repaint();
      }
   }  // close inner class
}

