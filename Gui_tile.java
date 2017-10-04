import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

/*
 * PURPOSE: Data member. It contains various pieces of information
 *          on the size and position of a tile.
 */

public class Gui_tile{
   private Tile tile;
   private Rectangle icon;
   private String label;
   private int buffer_width;
   private Dimension size;
   private Point position;

/*           _     _ _      
 _ __  _   _| |__ | (_) ___ 
| '_ \| | | | '_ \| | |/ __|
| |_) | |_| | |_) | | | (__ 
| .__/ \__,_|_.__/|_|_|\___|
|_|                         */

   public Gui_tile(Tile tile, Dimension size, int buffer_width){
      this.tile = tile.clone();
      this.icon = new Rectangle();
      this.label = new String(Integer.toString(tile.index()+1));

      this.size = size;
      this.icon.setSize(this.size);
      this.buffer_width = buffer_width;

      this.position = new Point( this.tile.x()*((int)this.icon.getWidth() + this.buffer_width),
                                 this.tile.y()*((int)this.icon.getHeight() + this.buffer_width));

      this.icon.setLocation(this.position);  // move the rectangle to its position
   }
   // gets the image for drawing
   public Rectangle get_icon(){
      this.icon.setLocation(this.position);
      return this.icon;
   }
   // return the label of the square
   public String get_label(){
      return this.label;
   }
   // move the Gui_tile physically used for animations
   public void move(Direction.dir dir){
      switch(dir){
         case UP:
            this.position.setLocation(this.position.x, this.position.y-1);
            break;
         case DOWN:
            this.position.setLocation(this.position.x, this.position.y+1);
            break;
         case RIGHT:
            this.position.setLocation(this.position.x+1, this.position.y);
            break;
         case LEFT:
            this.position.setLocation(this.position.x-1, this.position.y);
            break;
      }
   }
   // get the position data of the middle of the board
   public int get_x(){
      return position.x+size.width/2;
   }
   public int get_y(){
      return position.y+size.height/2;
   }
   public int get_width(){
      return (int)this.size.getWidth();
   }
   public int get_height(){
      return (int)this.size.getHeight();
   }
}
