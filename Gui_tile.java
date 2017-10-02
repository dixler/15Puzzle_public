import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

public class Gui_tile{
   private Tile tile;
   private Rectangle icon;
   private String label;
   private int buffer_width;
   private Dimension size;
   private Point position;

   public Gui_tile(Tile tile, Dimension size, int buffer_width){
      this.tile = tile.clone();
      this.icon = new Rectangle();
      this.label = new String(Integer.toString(tile.index()));

      this.size = size;
      this.icon.setSize(this.size);
      this.buffer_width = buffer_width;

      this.position = new Point( this.tile.x()*((int)this.icon.getWidth() + this.buffer_width),
                                 this.tile.y()*((int)this.icon.getHeight() + this.buffer_width));

      this.icon.setLocation(this.position);
   }
   public Rectangle get_icon(){
         // the rectangle has been modified
         /*
         this.icon.setLocation(  this.tile.x()*((int)this.icon.getWidth() + this.buffer_width),
                                 this.tile.y()*((int)this.icon.getHeight() + this.buffer_width));
         */
         this.icon.setLocation(this.position);
         //this.icon.setSize(this.size);
      return this.icon;
   }
   public String get_label(){
      return this.label;
   }
   // NOTE the number of tiles isn't known by the tile
   // so we need to provide it
   public int get_index_adjacent(Direction dir, int board_width){
      int index = 0;
      switch(dir){
         case UP:
            index = (this.tile.x()) + (this.tile.y()-1)*board_width;
            break;
         case DOWN:
            index = (this.tile.x()) + (this.tile.y()+1)*board_width;
            break;
         case RIGHT:
            index = (this.tile.x()+1) + (this.tile.y())*board_width;
            break;
         case LEFT:
            index = (this.tile.x()-1) + (this.tile.y())*board_width;
            break;
      }
      return index;
   }
   public void move(Direction dir){
      switch(dir){
         case UP:
            this.position.setLocation(this.position.x, this.position.y-1);
            break;
         case DOWN:
            this.position.setLocation(this.position.x, this.position.y+1);
            break;
         case RIGHT:
            this.position.setLocation(this.position.x-1, this.position.y);
            break;
         case LEFT:
            this.position.setLocation(this.position.x+1, this.position.y);
            break;
      }
   }
   public int get_x(){
      return position.x+size.width/2;
   }
   public int get_y(){
      return position.y+size.height/2;
   }
   public void set_size(Dimension size){
         // the rectangle has been modified
         this.size = size;
         return;
   }

}
