import java.util.ArrayList;
import java.util.Collections;

public class Board{
   private enum coord{
      _x, _y
   }
   private int dimension_x;   // x dimension of the board
   private int dimension_y;   // y dimension of the board
   private Tile[][] board;
   private int[] empty_position;

   /*
    * FUNCTION:   public Board(int x, int y)
    * description: constructor: create a board with x and y dimensions
    */
   public Board(int x, int y){
      this.dimension_x = x;
      this.dimension_y = y;
      this.empty_position = new int[2];

      // create the 2-d board
      board = new Tile[this.dimension_x][this.dimension_y];

      for(int cur_y = 0; cur_y < this.dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < this.dimension_x; cur_x++){
            //pop the list
            board[cur_x][cur_y] = new Tile(cur_y*dimension_y + cur_x);
            // get the empty tile's position
            if(board[cur_x][cur_y].index() == 15){
               empty_position[0] = cur_x;
               empty_position[1] = cur_y;
            }

         }
      }
      return;
   }

   public void initialize(){
      // make tiles and shuffle them
      ArrayList<Tile> tiles = new ArrayList<Tile>();
      for(int tile_id = 0; tile_id < (this.dimension_x * this.dimension_y); tile_id++){
         Tile cur_tile = new Tile(tile_id);
         tiles.add(cur_tile);
      }

      // shuffle until legal hand
      do{
         Collections.shuffle(tiles);
      }while(!this.is_solveable(tiles));

      // fill the 2-d board
      for(int cur_y = 0; cur_y < this.dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < this.dimension_x; cur_x++){
            //pop the list
            board[cur_x][cur_y] = tiles.remove(0);
            // get the empty tile's position
            if(board[cur_x][cur_y].index() == 15){
               empty_position[0] = cur_x;
               empty_position[1] = cur_y;
            }

         }
      }
      return;
   }
   @Override
   public int hashCode(){
      int hash = 0;
      for(int cur_y = 0; cur_y < dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < dimension_x; cur_x++){
            hash += board[cur_x][cur_y].index()*(cur_x+cur_y*dimension_y);
         }
      }
      return hash;
   }
   @Override
   public boolean equals(Object obj){
      // check that the dimensions are the same
      if(this.dimension_x != ((Board)obj).dimension_x)   // dimension_x
            return false;
      if(this.dimension_y != ((Board)obj).dimension_y)   // dimension_y
            return false;

      for(int cur_y = 0; cur_y < dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < dimension_x; cur_x++){
            // check that the contents of the tiles are the same
            if(      this.board[cur_x][cur_y].index() 
                  != ((Board)obj).board[cur_x][cur_y].index())
               return false;
         }
      }

      return true;
   }

   // TODO
   private boolean is_solveable(ArrayList<Tile> tiles){

      return true;
   }
   /*
    * FUNCTION:   public void print_board()
    * description: print the contents of the board
    */
   public void print_board(){
      for(int cur_y = 0; cur_y < this.dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < this.dimension_x; cur_x++){
            System.out.printf("%d\t", this.board[cur_x][cur_y].index());
         }
         System.out.printf("\n");
      }
   }
   private Tile get_adj_tile(Move.Direction direction){
      switch(direction){
         case UP:
            return this.board[this.empty_position[0]][this.empty_position[1]-1];
         case DOWN:
            return this.board[this.empty_position[0]][this.empty_position[1]+1];
         case LEFT:
            return this.board[this.empty_position[0]-1][this.empty_position[1]];
         case RIGHT:
            return this.board[this.empty_position[0]+1][this.empty_position[1]];
      }
      return null;
   }
   private int[] get_adj_tile_index(Move.Direction direction){
      int[] adj_indx = new int[2];
      switch(direction){
         case UP:
            adj_indx[0] = this.empty_position[0];
            adj_indx[1] = this.empty_position[1]-1;
            return adj_indx;
         case DOWN:
            adj_indx[0] = this.empty_position[0];
            adj_indx[1] = this.empty_position[1]+1;
            return adj_indx;
         case LEFT:
            adj_indx[0] = this.empty_position[0]-1;
            adj_indx[1] = this.empty_position[1];
            return adj_indx;
         case RIGHT:
            adj_indx[0] = this.empty_position[0]+1;
            adj_indx[1] = this.empty_position[1];
            return adj_indx;
      }
      // catch exceptions
      adj_indx = null;
      return null;
   }

   /*
    * FUNCTION:   public void swap()
    * description: swap the empty space with the slot to the [UP/DOWN/LEFT/RIGHT] returns TRUE if successful
    */
   public boolean swap(Move move){
      switch(move.get_direction()){
         case UP:
            if(empty_position[1] == 0){
               System.out.printf("UP failed\n");
               return false;
            }
            break;
         case DOWN:
            if(empty_position[1] == (dimension_y-1)){
               System.out.printf("DOWN failed\n");
               return false;
            }
            break;
         case LEFT:
            if(empty_position[0] == 0){
               System.out.printf("LEFT failed\n");
               return false;
            }
            break;
         case RIGHT:
            if(empty_position[0] == (dimension_x-1)){
               System.out.printf("RIGHT failed\n");
               return false;
            }
            break;
      }
      // catch illegal moves
      int[] adj_indx = this.get_adj_tile_index(move.get_direction());
      Tile adj_tile = this.board[adj_indx[0]][adj_indx[1]];

      // move the empty tile into the adjacent position
      this.board[adj_indx[0]][adj_indx[1]] = this.board[this.empty_position[0]][this.empty_position[1]];
      // move the adjacent tile into the empty position
      this.board[this.empty_position[0]][this.empty_position[1]] = adj_tile;

      // keep track of empty_position
      this.empty_position = adj_indx;
      // mark adj_indx to null for garbage collection
      adj_indx = null;
      return true;
   }

   public int[][] get_state(){
      int[][] state = new int[this.dimension_x][this.dimension_y];
      for(int cur_y = 0; cur_y < this.dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < this.dimension_x; cur_x++){
            state[cur_x][cur_y] = this.board[cur_x][cur_y].index();
         }
      }
      return state;
   }
   public Board clone(){
      Board clone = new Board(this.dimension_x, this.dimension_y);

      // duplicate empty_position
      clone.empty_position = new int[2];
         clone.empty_position[0] = this.empty_position[0];
         clone.empty_position[1] = this.empty_position[1];

      for(int cur_y = 0; cur_y < this.dimension_y; cur_y++){
         for(int cur_x = 0; cur_x < this.dimension_x; cur_x++){
            // duplicate tiles
            clone.board[cur_x][cur_y] = this.board[cur_x][cur_y].clone();
         }
      }

      return clone;
   }

}
