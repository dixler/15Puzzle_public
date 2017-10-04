// Makes it so that we can issue moves literally
// for saner code
public class Direction{
   public enum dir{UP, LEFT, DOWN, RIGHT}
   static public dir invert(dir my_dir){
      switch(my_dir){
         case UP:
            return dir.DOWN;
         case DOWN:
            return dir.UP;
         case LEFT:
            return dir.RIGHT;
         case RIGHT:
            return dir.LEFT;
      }
      return null;
   }
}
