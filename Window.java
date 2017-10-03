import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{
   private JFrame app_frame;
   private Renderer renderer;
   private Game game;

   private Gui_button   button_up, button_down, button_left, button_right; 
   private JButton   button_undo, button_undo_all, button_solve, button_quit,
                     button_about, button_help;

   // holds the size of the tiles
   private Dimension size;

   // tracks the empty position location for rendering the buttons relative to it
   private Point empty_pos;



   @SuppressWarnings("serial")
   private void initialize_buttons(){

      // button handling buttons are all relative to the empty space
      this.button_up = new Gui_button(size);
         this.button_up.setActionCommand("UP");
         this.button_up.addActionListener(this);
         this.button_up.setLocation(this.button_dir_pos(Direction.UP, 1));


      this.button_down = new Gui_button(size);
         this.button_down.setActionCommand("DOWN");
         this.button_down.addActionListener(this);
         this.button_down.setLocation(this.button_dir_pos(Direction.DOWN, 1));

      this.button_left = new Gui_button(size);
         this.button_left.setActionCommand("LEFT");
         this.button_left.addActionListener(this);
         this.button_left.setLocation(this.button_dir_pos(Direction.LEFT, 1));

      this.button_right = new Gui_button(size);
         this.button_right.setActionCommand("RIGHT");
         this.button_right.addActionListener(this);
         this.button_right.setLocation(this.button_dir_pos(Direction.RIGHT, 1));

      // handle menu buttons
      this.button_undo = new JButton();
         this.button_undo.setActionCommand("undo");
         this.button_undo.setText("undo");
         this.button_undo.setSize(size.width, size.height/2);
         this.button_undo.addActionListener(this);
         this.button_undo.setLocation(this.button_pos(0, 5));

      this.button_undo_all = new JButton();
         this.button_undo_all.setActionCommand("undo all");
         this.button_undo_all.setText("undo all");
         this.button_undo_all.setSize(size.width, size.height/2);
         this.button_undo_all.addActionListener(this);
         this.button_undo_all.setLocation(this.button_pos(1, 5));

      this.button_solve = new JButton();
         this.button_solve.setActionCommand("solve");
         this.button_solve.setText("solve");
         this.button_solve.setSize(size.width, size.height/2);
         this.button_solve.addActionListener(this);
         this.button_solve.setLocation(this.button_pos(2, 5));

      this.button_quit = new JButton();
         this.button_quit.setActionCommand("quit");
         this.button_quit.setText("quit");
         this.button_quit.setSize(size.width, size.height/2);
         this.button_quit.addActionListener(this);
         this.button_quit.setLocation(this.button_pos(3, 5));

      this.button_about = new JButton();
         this.button_about.setActionCommand("about");
         this.button_about.setText("about");
         this.button_about.setSize(size.width, size.height/2);
         this.button_about.addActionListener(this);
         this.button_about.setLocation(this.button_pos(0, 6));

      this.button_help = new JButton();
         this.button_help.setActionCommand("help");
         this.button_help.setText("help");
         this.button_help.setSize(size.width, size.height/2);
         this.button_help.addActionListener(this);
         this.button_help.setLocation(this.button_pos(1, 6));
   }

   private void draw_frame(){
      // update the game state so that everything is up to date
      // when we start drawing
      this.renderer.update_game_state(this.game);

      this.app_frame.add(this.button_up);
         this.button_up.setLocation(this.button_dir_pos(Direction.UP, 1));

      this.app_frame.add(this.button_down);
         this.button_down.setLocation(this.button_dir_pos(Direction.DOWN, 1));

      this.app_frame.add(this.button_left);
         this.button_left.setLocation(this.button_dir_pos(Direction.LEFT, 1));

      this.app_frame.add(this.button_right);
         this.button_right.setLocation(this.button_dir_pos(Direction.RIGHT, 1));


      this.app_frame.add(this.button_undo);
      this.app_frame.add(this.button_undo_all);
      this.app_frame.add(this.button_solve);
      this.app_frame.add(this.button_quit);
      this.app_frame.add(this.button_about);
      this.app_frame.add(this.button_help);
      this.app_frame.add(this.renderer);
      this.app_frame.revalidate();
      this.app_frame.paint((Graphics2D)this.app_frame.getGraphics());
      this.app_frame.repaint();

      // handle popups
      if(this.game.is_solved()){
         JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!");
      }
   }

   public Window(Game my_game){
      this.game = my_game;

      // graphics actions
      this.size = new Dimension(100, 100);

      // frame configuration
      this.app_frame = new JFrame();
      this.app_frame.setTitle("15 puzzle");
      this.app_frame.setSize(1000, 1000);
      this.app_frame.setForeground(new Color(238,238,238));
      this.app_frame.setBackground(new Color(238,238,238));
      this.app_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.app_frame.setVisible(true);

      // create the renderer
      this.renderer = new Renderer(my_game, this.size);

      this.initialize_buttons();
   }

   public void play(){
      this.draw_frame();
   }

   public void actionPerformed(ActionEvent event) {
      int button_offset = this.size.width; // height and width are the same
      boolean valid = false;
      Direction dir = null;
      System.out.printf("actionPerformed %s\n", event.getSource());
      if("UP".equals(event.getActionCommand())){
         // animate moves the tile in the opposite direction
         dir = Direction.DOWN;
         this.game.user_move(Direction.UP);
         valid = true;
      }
      else if("DOWN".equals(event.getActionCommand())){
         // animate moves the tile in the opposite direction
         dir = Direction.UP;
         this.game.user_move(Direction.DOWN);
         valid = true;
      }
      else if("RIGHT".equals(event.getActionCommand())){
         // animate moves the tile in the opposite direction
         dir = Direction.LEFT;
         this.game.user_move(Direction.RIGHT);
         valid = true;
      }
      else if("LEFT".equals(event.getActionCommand())){
         // animate moves the tile in the opposite direction
         dir = Direction.RIGHT;
         this.game.user_move(Direction.LEFT);
         valid = true;
      }
      else if("about".equals(event.getActionCommand())){
         JOptionPane.showMessageDialog(this, "Author: Kyle Dixler\nDate Written: 10/3/2017\nThe 2nd programming assignment for CS 342\n");
         return;
      }
      else if("help".equals(event.getActionCommand())){
         JOptionPane.showMessageDialog(this, "This is the 15 puzzle, you need to organize the pattern into an ascending order from 1-15");
         return;
      }
      else if("quit".equals(event.getActionCommand())){
         this.app_frame.setVisible(false);
         this.app_frame.dispose();
         this.dispose();
         return;
      }
      else if("undo".equals(event.getActionCommand())){
         this.execute_move(this.game.user_undo(), 250);
         this.game.user_undo(); // removes the last entry
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
         LinkedList<Direction> solution = this.game.user_solve();
         System.out.printf("Solved\n");
         while(solution.size() > 0){
            this.renderer.print_board();
            this.execute_move(solution.remove(0),1000);
            this.renderer.print_board();
         }
         return;
      }
      this.draw_frame();
      return;
   }
   private void execute_move(Direction dir, int anim_time){
         if(dir == null){
            return;
         }
         // doClick's argument acts as a delay
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
   private Point button_dir_pos(Direction dir, int offset){
      Point origin = new Point(  this.game.get_empty_pos()[0]*(this.size.width+10), 
                                 this.game.get_empty_pos()[1]*(this.size.height+10));
      switch(dir){
         case UP:
            return new Point( origin.x, 
                              origin.y - offset*(this.size.height+10));
         case DOWN:
            return new Point( origin.x, 
                              origin.y + offset*(this.size.height+10));
         case LEFT:
            return new Point( origin.x - offset*(this.size.width+10), 
                              origin.y);
         case RIGHT:
            return new Point( origin.x + offset*(this.size.width+10), 
                              origin.y);
      }
      return null;
   }

   private Point button_pos(int offset_x, int offset_y){
      return new Point(offset_x*(this.size.width+10), offset_y*(this.size.height+10));
   }
}

