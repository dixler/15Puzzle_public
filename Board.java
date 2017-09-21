public class Board{
   private int dimension_x;   // x dimension of the board
   private int dimension_y;   // y dimension of the board
   private Tile[][] board;

   private int solution_hash; // every time that a piece is moved into 
                              // its original square, we add 2^(square_id)
                              // to solution hash. If it's removed, we 
                              // subtract it. This makes validation snappy
                              // NOTE: only works for less than 32 squares

   /*
    * FUNCTION:   public Board(int x, int y)
    * description: constructor: create a board with x and y dimensions
    */
   public Board(int x, int y){
      dimension_x = x;
      dimension_y = y;
      // initialize board
      return;
   }
   /*
    * FUNCTION:   public Board(int x, int y)
    * description: constructor: create a board with x and y dimensions
    */





}
