import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

public class Gui_tile{
   private Tile tile;
   private Rectangle icon;
   private int buffer_width;
   private Dimension size;
   private Point position;

   public Gui_tile(Tile tile, Dimension size, int buffer_width){
      this.tile = tile;
      this.icon = new Rectangle();
      this.size = size;
      this.icon.setSize(this.size);
      this.buffer_width = buffer_width;

      this.position = new Point( this.tile.x()*((int)this.icon.getWidth() + this.buffer_width),
                                 this.tile.y()*((int)this.icon.getHeight() + this.buffer_width));

      this.icon.setLocation(this.position);

   }
   public Rectangle get_icon(){
         // the rectangle has been modified
         this.icon.setLocation(  this.tile.x()*((int)this.icon.getWidth() + this.buffer_width),
                                 this.tile.y()*((int)this.icon.getHeight() + this.buffer_width));
         this.icon.setSize(this.size);
      return this.icon;
   }
   public void set_size(Dimension size){
         // the rectangle has been modified
         this.size = size;
         return;
   }

}
