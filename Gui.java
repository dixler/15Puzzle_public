import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * PURPOSE: Graphical implementation of Game
 *          works to display the board in tiles
 *          in conjuction with the Renderer class
 */

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener{
   private Renderer renderer;
   private Game game;

   private Gui_button   button_up, button_down, button_left, button_right; 
   private JButton   button_undo, button_undo_all, button_solve, button_shuffle,
                     button_quit, button_about, button_help;

   // holds the size of the tiles
   private Dimension size;
   private int buffer_size;

   // tracks the empty position location for rendering the buttons relative to it
   private Point empty_pos;


/*           _     _ _      
 _ __  _   _| |__ | (_) ___ 
| '_ \| | | | '_ \| | |/ __|
| |_) | |_| | |_) | | | (__ 
| .__/ \__,_|_.__/|_|_|\___|
|_|                         */

   public Gui(Game my_game){
      this.game = my_game;
      this.buffer_size = 10;

      // graphics actions
      this.size = new Dimension(100, 100);

      // frame configuration
      this.setTitle("15 puzzle");
      this.setSize(  this.game.get_height()*((int)this.size.getHeight()+this.buffer_size+2), // there are 2 extra slots for buttons
                     this.game.get_width()*((int)this.size.getWidth()+this.buffer_size));
      this.setForeground(new Color(238,238,238));
      this.setBackground(new Color(238,238,238));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);

      // create the renderer
      this.renderer = new Renderer(this.game, this.size, this.buffer_size);
      this.renderer.setDoubleBuffered(true);

      this.initialize_buttons();
      this.draw_frame();
   }

   // action handler. Does all of the main gamework.
   public void actionPerformed(ActionEvent event) {
      int button_offset = this.size.width; // height and width are the same
      if("UP".equals(event.getActionCommand())){
         // we're moving the empty tile up meaning that 
         // we're moving the filled tile down
         if(this.animate_move(Direction.dir.UP, 10) == false) return;
      }
      else if("DOWN".equals(event.getActionCommand())){
         if(this.animate_move(Direction.dir.DOWN, 10) == false) return;
      }
      else if("RIGHT".equals(event.getActionCommand())){
         if(this.animate_move(Direction.dir.RIGHT, 10) == false) return;
      }
      else if("LEFT".equals(event.getActionCommand())){
         if(this.animate_move(Direction.dir.LEFT, 10) == false) return;
      }
      else if("shuffle".equals(event.getActionCommand())){
         this.game.user_shuffle();
      }
      else if("about".equals(event.getActionCommand())){
         JOptionPane.showMessageDialog(this, "Author: Kyle Dixler\nDate Written: 10/3/2017\nThe 2nd programming assignment for CS 342\n");
         return;
      }
      else if("help".equals(event.getActionCommand())){
         JOptionPane.showMessageDialog(this, " This is the 15 puzzle, you need to organize the pattern into \n an ascending order from 1-15.  To do this, you can only click \n the tiles next to the empty space to move them into the empty \n space. \n The top row should read [1] [2] [3] [4].\n The bottom row should read [13] [14] [15] [empty space]\n ");
         /*
This is the 15 puzzle, you need to organize the pattern into \n an ascending order from 1-15.  To do this, you can only click \n the tiles next to the empty space to move them into the empty \n space. \n The top row should read [1] [2] [3] [4].\n The bottom row should read [13] [14] [15] [empty space]\n
*/
         return;
      }
      else if("quit".equals(event.getActionCommand())){
         this.setVisible(false);
         this.dispose();
         return;
      }
      else if("undo".equals(event.getActionCommand())){
         this.execute_move(this.game.user_undo(), 10);   // removes the last entry in the undo list
                                                         // and then calls execute_move()
         this.game.user_undo();                       // removes the last entry in the undo list 
                                                      // and does nothing
         return;
      }
      else if("undo all".equals(event.getActionCommand())){
         // while we haven't reverted the puzzle to its original one
         // keep undoing
         while(!this.game.is_original_puzzle()){
            this.actionPerformed(new ActionEvent(this, 1001, "undo"));
         }
         return;
      }
      else if("solve".equals(event.getActionCommand())){
         LinkedList<Direction.dir> solution = this.game.user_solve();
         System.out.printf("Solved\n");
         while(solution.size() > 0){
            this.execute_move(solution.remove(0),10);
         }
         return;
      }
      // handle popups
      if(this.game.is_solved()){
         JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!");
      }
      this.renderer.update_game_state(this.game);
      this.draw_frame();
      return;
   }
/*          _            _       
 _ __  _ __(_)_   ____ _| |_ ___ 
| '_ \| '__| \ \ / / _` | __/ _ \
| |_) | |  | |\ V / (_| | ||  __/
| .__/|_|  |_| \_/ \__,_|\__\___|
|_|                              */
   // handle all of the rendering
   private void draw_frame(){
      this.add(this.button_up);
         this.button_up.setLocation(this.button_dir_pos(Direction.dir.DOWN, 1));

      this.add(this.button_down);
         this.button_down.setLocation(this.button_dir_pos(Direction.dir.UP, 1));

      this.add(this.button_left);
         this.button_left.setLocation(this.button_dir_pos(Direction.dir.RIGHT, 1));

      this.add(this.button_right);
         this.button_right.setLocation(this.button_dir_pos(Direction.dir.LEFT, 1));
      this.add(this.button_undo);
      this.add(this.button_undo_all);
      this.add(this.button_solve);
      this.add(this.button_shuffle);

      this.add(this.button_quit);
      this.add(this.button_about);
      this.add(this.button_help);
      this.add(this.renderer);
      this.paint(this.getGraphics());
      this.revalidate();
      this.repaint();
   }
   // serves to call the animation subroutine and 
   private boolean animate_move(Direction.dir dir, int anim_time){
         if(dir == null || this.game.user_move(dir) == false) return false;
         long time = System.currentTimeMillis();
         for(int i = 0; i < this.size.getHeight()+this.buffer_size; i++){
            while( anim_time != 0 && System.currentTimeMillis() - time < anim_time/5);
            time = System.currentTimeMillis();
            this.renderer.move_tile(dir);
            this.draw_frame();
         }
         // we now update the game state so the next move is up to date
         return true;
   }
   // Called by undo and solver to perform moves
   private void execute_move(Direction.dir dir, int anim_time){
         if(dir == null) return;
         // doClick's argument acts as a delay to avoid extra
         // unnecessary classes
         switch(dir){
            case UP:
               this.button_up.doClick(anim_time);
               break;
            case DOWN:
               this.button_down.doClick(anim_time);
               break;
            case LEFT:
               this.button_left.doClick(anim_time);
               break;
            case RIGHT:
               this.button_right.doClick(anim_time);
               break;
         }
         return;
   }
   // determines the position of the move issuing hidden buttons
   private Point button_dir_pos(Direction.dir dir, int offset){
      Point origin = new Point(  this.game.get_empty_pos()[0]*(this.size.width+this.buffer_size), 
                                 this.game.get_empty_pos()[1]*(this.size.height+this.buffer_size));
      switch(dir){
         case UP:
            return new Point( origin.x, 
                              origin.y - offset*(this.size.height+this.buffer_size));
         case DOWN:
            return new Point( origin.x, 
                              origin.y + offset*(this.size.height+this.buffer_size));
         case LEFT:
            return new Point( origin.x - offset*(this.size.width+this.buffer_size), 
                              origin.y);
         case RIGHT:
            return new Point( origin.x + offset*(this.size.width+this.buffer_size), 
                              origin.y);
      }
      return null;
   }
   // determine the position of a button in a makeshift grid
   private Point button_pos(int offset_x, int offset_y){
      return new Point(offset_x*(this.size.width+this.buffer_size), offset_y*(this.size.height+this.buffer_size));
   }
   // makes creating buttons less hectic
   private JButton create_menu_button(String label, int x_pos, int y_pos){
      JButton button = new JButton();
      button.setActionCommand(label);
      button.setText(label);
      button.setSize(size.width, size.height/2);
      button.addActionListener(this);
      button.setLocation(this.button_pos(x_pos, y_pos));
      button.setDoubleBuffered(true);
      return button;
   }
   // configures the buttons
   private void initialize_buttons(){
      // button handling buttons are all relative to the empty space
      this.button_down = new Gui_button(size);
         this.button_down.setActionCommand("DOWN");
         this.button_down.addActionListener(this);
         this.button_down.setLocation(this.button_dir_pos(Direction.dir.UP, 1));
         this.button_down.setDoubleBuffered(true);

      this.button_up = new Gui_button(size);
         this.button_up.setActionCommand("UP");
         this.button_up.addActionListener(this);
         this.button_up.setLocation(this.button_dir_pos(Direction.dir.DOWN, 1));
         this.button_up.setDoubleBuffered(true);

      this.button_right = new Gui_button(size);
         this.button_right.setActionCommand("RIGHT");
         this.button_right.addActionListener(this);
         this.button_right.setLocation(this.button_dir_pos(Direction.dir.LEFT, 1));
         this.button_right.setDoubleBuffered(true);

      this.button_left = new Gui_button(size);
         this.button_left.setActionCommand("LEFT");
         this.button_left.addActionListener(this);
         this.button_left.setLocation(this.button_dir_pos(Direction.dir.RIGHT, 1));
         this.button_left.setDoubleBuffered(true);

      // handle menu buttons
      this.button_undo = this.create_menu_button("undo", 0, 5);
      this.button_undo_all = this.create_menu_button("undo all", 1, 5);
      this.button_solve = this.create_menu_button("solve", 2, 5);
      this.button_shuffle = this.create_menu_button("shuffle", 3, 5);

      this.button_about = this.create_menu_button("about", 0, 6);
      this.button_help = this.create_menu_button("help", 1, 6);
      this.button_quit = this.create_menu_button("quit", 2, 6);
   }
}
