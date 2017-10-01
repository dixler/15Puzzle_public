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
   private Dimension size = new Dimension(100, 100);
	
	public Renderer(Game game){
      this.tile_arr = new Gui_tile[game.get_board().num_tiles()];
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], size, 10);
      }
	}
	public Renderer(int num_tiles){
      this.tile_arr = new Gui_tile[num_tiles];
	}
   public void update_game_state(Game game){
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], size, 10);
      }
   }

   // requires 60 calls to fully animate
   // heavily coupled with the window
   public int get_empty_index(){
      // the swapped will be somewhere relative to the empty
      return this.tile_arr.length-1;
   }
   public int get_adjacent_index(Direction dir){
      // the swapped will be somewhere relative to the empty
      System.out.printf("adjacent %d", this.tile_arr[this.get_empty_index()].get_index_adjacent(dir, 4));
      return this.tile_arr[this.get_empty_index()].get_index_adjacent(dir, 4);
   }

   public void move_tile(int index, Direction dir){
      System.out.printf("\npre %d %d\n", this.tile_arr[index].get_x(), this.tile_arr[index].get_y());
      this.tile_arr[index].move(dir);
      System.out.printf("post %d %d\n", this.tile_arr[index].get_x(), this.tile_arr[index].get_y());
      return;
   }

   public void set_tile_size(Dimension size){
      this.size = size;
      /* in case this fails
      for(int i = 0; i < tile_arr.length; i++){
         tile_arr[i].set_size(size);
      }
      */
   }
   public Renderer clone(){
      Renderer clone = new Renderer(this.tile_arr.length);
      for(int i = 0; i < clone.tile_arr.length; i++){
         clone.tile_arr[i] = this.tile_arr[i];
      }
      return clone;
   }
	
	@Override
   public void paintComponent(Graphics g) {  
      System.out.printf("repainting\n");
      Random rand = new Random();
      Graphics2D drawing_context = (Graphics2D) g;
      drawing_context.setFont(new Font("TimesRoman", Font.PLAIN, 36)); 
      // handle all tiles
      for(int i = 0; i < this.tile_arr.length-1; i++){
         drawing_context.setColor(new Color(0,255,0));
         drawing_context.fill(this.tile_arr[i].get_icon());
         drawing_context.setColor(new Color(255,255,255));
         drawing_context.drawString(this.tile_arr[i].get_label(), this.tile_arr[i].get_x(), this.tile_arr[i].get_y());
      }
      // handle empty
      drawing_context.setColor(new Color(255,255,255));
      drawing_context.fill(this.tile_arr[this.tile_arr.length-1].get_icon());
      return;
   }
}

