import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements ActionListener{
   private JFrame app_frame;
   private Renderer renderer;

   private Gui_button button_up;
   private Gui_button button_down;
   private Gui_button button_left;
   private Gui_button button_right;
   private Gui_button button_undo;
   private Gui_button button_undo_all;
   private Gui_button button_solve;

   private Game game;

   private Point empty_pos;
   private Dimension size;

   private Point button_dir_pos(Direction dir, int offset){
      Point origin = new Point(this.game.get_empty_pos()[0]*(this.size.width+10), this.game.get_empty_pos()[1]*(this.size.height+10));
      Point target = null;
      switch(dir){
         case UP:
            target = new Point(origin.x, origin.y - offset*(this.size.height+10));
            break;
         case DOWN:
            target = new Point(origin.x, origin.y + offset*(this.size.height+10));
            break;
         case LEFT:
            target = new Point(origin.x - offset*(this.size.width+10), origin.y);
            break;
         case RIGHT:
            target = new Point(origin.x + offset*(this.size.width+10), origin.y);
            break;
      }
      return target;
   }

   private Point button_pos(int offset_x, int offset_y){
      return new Point(offset_x*(this.size.width+10), offset_y*(this.size.height+10));
   }

   @SuppressWarnings("serial")
   private void initialize_buttons(){
      // button handling buttons are all relative to the empty space
      this.button_up = new Gui_button(size)
         //*
      {
         @Override
         public void paint(Graphics g){
         }
      };
      //*/
      this.button_up.setActionCommand("UP");
      this.button_up.addActionListener(this);
      this.button_up.setOpaque(false);
      this.button_up.setContentAreaFilled(false);
      this.button_up.setBorderPainted(false);
      this.button_up.setRolloverEnabled(false);


      this.button_down = new Gui_button(size)
         //*
      {
         @Override
         public void paint(Graphics g){
         }
      };
      //*/
      this.button_down.setActionCommand("DOWN");
      this.button_down.addActionListener(this);
      this.button_down.setOpaque(false);
      this.button_down.setContentAreaFilled(false);
      this.button_down.setBorderPainted(false);
      this.button_down.setRolloverEnabled(false);

      this.button_left = new Gui_button(size)
         //*
      {
         @Override
         public void paint(Graphics g){
         }
      };
      //*/
      this.button_left.setActionCommand("LEFT");
      this.button_left.addActionListener(this);
      this.button_left.setOpaque(false);
      this.button_left.setContentAreaFilled(false);
      this.button_left.setBorderPainted(false);
      this.button_left.setRolloverEnabled(false);

      this.button_right = new Gui_button(size)
      //*
      {
         @Override
         public void paint(Graphics g){
         }
      };
         //*/
      this.button_right.setActionCommand("RIGHT");
      this.button_right.addActionListener(this);
      this.button_right.setOpaque(false);
      this.button_right.setContentAreaFilled(false);
      this.button_right.setBorderPainted(false);
      this.button_right.setRolloverEnabled(false);

      this.button_up.setLocation(this.button_dir_pos(Direction.UP, 1));
      this.button_down.setLocation(this.button_dir_pos(Direction.DOWN, 1));
      this.button_left.setLocation(this.button_dir_pos(Direction.LEFT, 1));
      this.button_right.setLocation(this.button_dir_pos(Direction.RIGHT, 1));

      // handle menu buttons
      this.button_undo = new Gui_button(size.width, size.height/2);
      this.button_undo.setActionCommand("undo");
      this.button_undo.setText("undo");
      this.button_undo.addActionListener(this);
      this.button_undo.setLocation(this.button_pos(0, 5));

      this.button_undo_all = new Gui_button(size.width, size.height/2);
      this.button_undo_all.setActionCommand("undo all");
      this.button_undo_all.setText("undo all");
      this.button_undo_all.addActionListener(this);
      this.button_undo_all.setLocation(this.button_pos(1, 5));

      this.button_solve = new Gui_button(size.width, size.height/2);
      this.button_solve.setActionCommand("solve");
      this.button_solve.setText("solve");
      this.button_solve.addActionListener(this);
      this.button_solve.setLocation(this.button_pos(2, 5));
   }

   private void draw_frame(){
      //System.out.printf("draw_frame called\n");
      //this.renderer.update_game_state(this.game);
      this.app_frame.setForeground(new Color(238,238,238));
      this.app_frame.setBackground(new Color(238,238,238));
      this.app_frame.add(this.button_up);
      this.app_frame.add(this.button_down);
      this.app_frame.add(this.button_left);
      this.app_frame.add(this.button_right);

      this.app_frame.add(this.button_undo);
      this.app_frame.add(this.button_undo_all);
      this.app_frame.add(this.button_solve);
      this.app_frame.add(this.renderer);
      this.app_frame.revalidate();
      //this.app_frame.repaint((Graphics2D)this.app_frame.getGraphics());
      this.app_frame.paint((Graphics2D)this.app_frame.getGraphics());
      this.app_frame.repaint();
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

      // we use 10 padding
      this.renderer = new Renderer(my_game);
      this.renderer.set_tile_size(size);

      this.initialize_buttons();


      this.draw_frame();
   }

   public void play(){
      this.draw_frame();
   }

   private void animate(Direction dir, int delay){
      /*
         int index_empty = renderer.get_empty_index();
         int index_adjacent = renderer.get_adjacent_index(dir);
         for(int i = 0; i < 110; i++){
         this.renderer.move_tile(index_adjacent, dir);
         this.draw_frame();
      //*/
      //*
      long secs = (System.currentTimeMillis());

      /*
         while(System.currentTimeMillis() - secs <= delay){
         this.draw_frame();
         }

      //*/
      //System.gc();

      /*
         try{
         Thread.sleep(delay);
         }catch(InterruptedException ex){
         Thread.currentThread().interrupt();
         }
      /*
      System.out.printf("TICK\n");
         }
      //*/
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
      else if("undo".equals(event.getActionCommand())){
         dir = this.game.user_undo();
         this.execute_move(dir);
         this.game.user_undo(); // removes the last entry
         return;
      }
      else if("undo all".equals(event.getActionCommand())){
         while(!this.game.is_original_puzzle()){
            this.actionPerformed(new ActionEvent(this, 1001, "undo"));
            this.animate(dir, 100);
         }
         return;
      }
      else if("solve".equals(event.getActionCommand())){
         LinkedList<Direction> solution = this.game.user_solve();
         System.out.printf("Solved\n");
         while(solution.size() > 0){
            this.execute_move(solution.pop());
            this.animate(dir, 500);
         }
         return;
      }
      if(valid){
         this.animate(dir, 100);
         this.button_up.setLocation(this.button_dir_pos(Direction.UP, 1));
         this.button_down.setLocation(this.button_dir_pos(Direction.DOWN, 1));
         this.button_left.setLocation(this.button_dir_pos(Direction.LEFT, 1));
         this.button_right.setLocation(this.button_dir_pos(Direction.RIGHT, 1));
         this.renderer.update_game_state(this.game);
         this.draw_frame();
      }
      return;
   }
   private void handle_undo_all(){
      /*
         while(this.game.user_undo()){
         this.renderer.update_game_state(this.game);
         this.draw_frame();
         this.animate(null, 500);
         }
         return;
         */
   }
   private void execute_move(Direction dir){
         if(dir == null){
            return;
         }
         switch(dir){
            case UP:
               //this.actionPerformed(new ActionEvent(this, 1001, "UP"));
               this.button_up.doClick(100);
               break;
            case DOWN:
               //this.actionPerformed(new ActionEvent(this, 1001, "DOWN"));
               this.button_down.doClick(100);
               break;
            case LEFT:
               //this.actionPerformed(new ActionEvent(this, 1001, "LEFT"));
               this.button_left.doClick(100);
               break;
            case RIGHT:
               //this.actionPerformed(new ActionEvent(this, 1001, "RIGHT"));
               this.button_right.doClick(100);
               break;
         }
         return;
   }
}

