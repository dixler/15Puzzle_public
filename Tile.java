public class Tile{
   private int index; // value held in the tile
   //private Object data; // PLACEHOLDER

   public Tile(int _index){
      this.index = _index;
      return;
   }
   public int index(){
      return this.index;
   }
   public Tile clone(){
      Tile clone = new Tile(this.index);
      return clone;
   }
}
