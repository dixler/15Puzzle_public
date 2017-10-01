import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JPanel;

// HistogramComponent extends the functionality of a JComponent
// in order to draw a histogram.
public class Renderer extends JPanel {
	
	private Game game;
   private Gui_tile[] tile_arr;
   private Dimension size = new Dimension(100, 100);
	
	public Renderer(Game game){
		this.game = game;
      this.tile_arr = new Gui_tile[this.game.get_board().num_tiles()];
      Tile[] tiles = this.game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], size, 10);
      }
	}

   public void set_tile_size(Dimension size){
      this.size = size;
      /* in case this fails
      for(int i = 0; i < tile_arr.length; i++){
         tile_arr[i].set_size(size);
      }
      */

   }
	
	@Override
   public void paintComponent(Graphics g) {  
      Random rand = new Random();
      Graphics2D drawing_context = (Graphics2D) g;
      for(int i = 0; i < this.tile_arr.length; i++){
         drawing_context.fill(this.tile_arr[i].get_icon());
         drawing_context.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
      }
      return;
   }
}

