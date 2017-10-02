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
      System.out.printf("state updated\n");
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
   public int[] get_empty_index2d(){
      // the swapped will be somewhere relative to the empty
      int[] coord = new int[2];
      coord[0] = get_empty_index()%this.width;
      coord[1] = get_empty_index()/this.height;
      return coord;
   }
   public int get_adjacent_index(Direction dir){
      // the swapped will be somewhere relative to the empty
      System.out.printf("adjacent %d", this.tile_arr[this.get_empty_index()].get_index_adjacent(dir, 4));
      return this.tile_arr[this.get_empty_index()].get_index_adjacent(dir, 4);
   }

	@Override
   public void paintComponent(Graphics g) {  
      System.out.printf("repainting\n");
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
   public void print_board(){
      if(this.tile_arr == null)
         return;
      System.out.printf("Tile\tx\ty\n");
      for(int i = 0; i < this.tile_arr.length; i++){
         System.out.printf("%s\t%d\t%d\n", this.tile_arr[i].get_label(), this.tile_arr[i].get_x(), this.tile_arr[i].get_y());
      }
   }
}

