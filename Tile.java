/*
 * this class contains the raw data of the tile
 * it's primarily used in the Game class
 */
public class Tile{
   private int index; // value held in the tile
   private int x, y;

/*              _     _ _      
    _ __  _   _| |__ | (_) ___ 
   | '_ \| | | | '_ \| | |/ __|
   | |_) | |_| | |_) | | | (__ 
   | .__/ \__,_|_.__/|_|_|\___|
   |_|                         */

   public Tile(int index, int x, int y){
      this.index = index;
      this.x = x;
      this.y = y;
      return;
   }
   public Tile clone(){
      Tile clone = new Tile(this.index, this.x, this.y);
      return clone;
   }
   public int x(){
      return this.x;
   }
   public int y(){
      return this.y;
   }
   public int index(){
      return this.index;
   }
   public void move(Direction.dir dir){
      switch(dir){
         case UP:
            this.y--;
            break;
         case LEFT:
            this.x--;
            break;
         case DOWN:
            this.y++;
            break;
         case RIGHT:
            this.x++;
            break;
      }
   }
}
