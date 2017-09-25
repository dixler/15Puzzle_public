public class Move{
   public enum Direction {
      UP, DOWN, LEFT, RIGHT
   }
   private Direction swap_direction;

   /*
    * FUNCTION:   public Move(Direction my_dir)
    * description: make a move
    */
   public Move(Direction my_dir){
      this.swap_direction = my_dir;
   }
   public Direction get_direction(){
      return this.swap_direction;
   }
   public void set_direction(Direction my_dir){
      this.swap_direction = my_dir;
      return;
   }

}
