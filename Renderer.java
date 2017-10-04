import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JPanel;

// HistogramComponent extends the functionality of a JComponent
// in order to draw a histogram.
@SuppressWarnings("serial")
public class Renderer extends JPanel {
	
   private Gui_tile[] tile_arr;
   private Dimension size;
   // empty_index is the linear index of the empty tile
   private int empty_index;
   private int width;
   private int height;
	
	public Renderer(Game game, Dimension size){
      this.size = size;
      this.tile_arr = new Gui_tile[game.get_board().num_tiles()];
      this.width = game.get_width();
      this.height = game.get_height();
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], this.size, 10);
         if(tiles[i].index() == 15){
            // empty tile
            this.empty_index = i;
         }
      }
	}
   public void update_game_state(Game game){
      this.tile_arr = new Gui_tile[game.get_board().num_tiles()];
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], this.size, 10);
         if(tiles[i].index() == 15){
            // empty tile
            this.empty_index = i;
         }
      }
   }

   public int get_empty_index(){
      // the swapped will be somewhere relative to the empty
      return this.empty_index;
   }
   public void move_tile(Direction.dir dir){
      this.get_adjacent_tile(Direction.invert(dir)).move(dir);
      return;
   }
   // doesn't handle edges at all, don't mess up
   public Gui_tile get_adjacent_tile(Direction.dir dir){
      // the swapped will be somewhere relative to the empty
      int index = this.empty_index;
      if(dir == Direction.dir.RIGHT && (index % width == width - 1)) return null;

      // get the linear tile index of the tile to move
      switch(dir){
         case UP:
            index += -this.width;
            break;
         case DOWN:
            index += this.width;
            break;
         case RIGHT:
            index += 1;
            break;
         case LEFT:
            index += -1;
            break;
      }
      return this.tile_arr[index];
   }

	@Override
   public void paintComponent(Graphics g) {  
      Random rand = new Random();
      Graphics2D drawing_context = (Graphics2D) g;
      drawing_context.setFont(new Font("TimesRoman", Font.PLAIN, 36)); 
      // handle all tiles
      for(int i = 0; i < this.tile_arr.length; i++){
         if(i == this.empty_index){
            drawing_context.setColor(new Color(0,0,0));
         }
         else{
            drawing_context.setColor(new Color(0,155,0));
            drawing_context.fill(this.tile_arr[i].get_icon());
            drawing_context.setColor(new Color(255,255,255));
            drawing_context.drawString(this.tile_arr[i].get_label(), this.tile_arr[i].get_x(), this.tile_arr[i].get_y());
         }
      }
      return;
   }
}

