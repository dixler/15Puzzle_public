import java.util.ArrayList;
import java.util.Collections;
/*
 * PURPOSE: manages the tiles
 * NOTES:   uses chars in order to save size due to space complexity issues
 */

public class Board{
   private char width;   // x dimension of the board
   private char height;   // y dimension of the board
   private Tile[][] board;
   private int num_tiles;
   private char[] empty_coord;

   /*              _     _ _      
       _ __  _   _| |__ | (_) ___ 
      | '_ \| | | | '_ \| | |/ __|
      | |_) | |_| | |_) | | | (__ 
      | .__/ \__,_|_.__/|_|_|\___|
      |_|                         
   */

   // Constructor:   fill the board with tiles(in order) this serves as a base state 
   //                and gives us something to signal the solver as to our target
   //                board state.
   public Board(char x, char y){
      this.width = x;
      this.height = y;
      this.num_tiles = x*y;

      // create the 2-d board
      board = new Tile[this.width][this.height];

      // fill the 2-d board
      for(char cur_y = 0; cur_y < this.height; cur_y++){
         for(char cur_x = 0; cur_x < this.width; cur_x++){
            board[cur_x][cur_y] = new Tile(cur_y*height + cur_x, cur_x, cur_y);
         }
      }

      // keep track of the empty position
      this.empty_coord = new char[2];
      empty_coord[0] = (char)(this.width-1);
      empty_coord[1] = (char)(this.height-1);

      return;
   }
   public int num_tiles(){
      return this.num_tiles;
   }
   // we stuff the tiles into an array in linear order
   // by position
   public Tile[] get_tiles(){
      Tile[] tile_arr = new Tile[this.num_tiles];
      for(int cur_y = 0; cur_y < this.width; cur_y++){
         for(int cur_x = 0; cur_x < this.width; cur_x++){
            tile_arr[cur_x+cur_y*this.width] = this.board[cur_x][cur_y];
         }
      }

      return tile_arr;
   }
   // returns the board's empty position in a 2d array
   // [0] x [1] y;
   public char[] get_empty_pos(){
      return this.empty_coord;
   }
   public int get_width(){
      return this.width;
   }
   public int get_height(){
      return this.height;
   }

   // returns the number of inversions in the board
   public int get_complexity(){
      int inversions = 0;
      for(int i = 0; i < this.width*this.height; i++){
         for(int j = i+1; j < this.width*this.height; j++){
            if(board[i%this.width][i/this.height].index() > board[j%this.width][j/this.height].index()){
               inversions += 1;
            }
         }
      }
      return inversions;
   }

   // this is an alternative way to shuffle them
   // it uses randomness to shuffle the puzzle, 
   // it has to wait on a legitimate tile layout
   public void shuffle(){
      // make tiles and shuffle them
      do{
         ArrayList<Integer> tiles = new ArrayList<Integer>();
         for(char tile_id = 0; tile_id < (this.width * this.height - 1); tile_id++){
            tiles.add(new Integer(tile_id));
         }

         // shuffle until legal hand
            Collections.shuffle(tiles);

         // fill the 2-d board
         for(char cur_y = 0; cur_y < this.height; cur_y++){
            for(char cur_x = 0; cur_x < this.width; cur_x++){
               // is it not the last tile?
               if(cur_y != (this.height-1) || cur_x != (this.width-1)){
                  Tile cur_tile = new Tile(tiles.remove(0), cur_x, cur_y);
                  this.board[cur_x][cur_y] = cur_tile;                // get the empty tile's position
               }
               else{
                  this.board[cur_x][cur_y] = new Tile(15, cur_x, cur_y);
                  this.empty_coord[0] = cur_x;
                  this.empty_coord[1] = cur_y;
               }
            }
         }
      }while(this.get_complexity() % 2 != 0);
      return;
   }

   //swap the empty space with the slot to the [UP/DOWN/LEFT/RIGHT] returns TRUE if successful
   public boolean swap(Direction.dir move){
      Direction.dir inverse = Direction.invert(move);
      // check if move is out of bounds
      switch(move){
         case UP:
            if(empty_coord[1] == 0) return false;
            break;
         case DOWN:
            if(empty_coord[1] == (height-1)) return false;
            break;
         case LEFT:
            if(empty_coord[0] == 0) return false;
            break;
         case RIGHT:
            if(empty_coord[0] == (width-1)) return false;
            break;
      }
      char[] adj_indx = this.get_adj_tile_index(move);
      Tile adj_tile = this.board[adj_indx[0]][adj_indx[1]];
      Tile empty_tile = this.board[this.empty_coord[0]][this.empty_coord[1]];

      // sets the swapped tiles' local positions to their new positions
      adj_tile.move(inverse);
      empty_tile.move(move);

      // move the empty tile into the adjacent position
      this.board[adj_indx[0]][adj_indx[1]] = this.board[this.empty_coord[0]][this.empty_coord[1]];
      // move the adjacent tile into the empty position
      this.board[this.empty_coord[0]][this.empty_coord[1]] = adj_tile;

      // keep track of empty_coord
      this.empty_coord = adj_indx;
      // mark adj_indx to null for garbage collection
      adj_indx = null;
      empty_tile = null;
      return true;
   }

   public Board clone(){
      Board clone = new Board(this.width, this.height);

      // duplicate empty_coord
      clone.empty_coord = new char[2];
         clone.empty_coord[0] = this.empty_coord[0];
         clone.empty_coord[1] = this.empty_coord[1];

      for(char cur_y = 0; cur_y < this.height; cur_y++){
         for(char cur_x = 0; cur_x < this.width; cur_x++){
            // duplicate tiles
            clone.board[cur_x][cur_y] = this.board[cur_x][cur_y].clone();
         }
      }

      return clone;
   }

   // displays the board to stdout DEBUGGING feature
   public void print_board(){
      for(char cur_y = 0; cur_y < this.height; cur_y++){
         for(char cur_x = 0; cur_x < this.width; cur_x++){
            System.out.printf("%d\t", this.board[cur_x][cur_y].index());
         }
         System.out.printf("\n");
      }
      System.out.printf("\n");
   }


   /*   ___                      _     _      
       / _ \__   _____ _ __ _ __(_) __| | ___ 
      | | | \ \ / / _ \ '__| '__| |/ _` |/ _ \
      | |_| |\ V /  __/ |  | |  | | (_| |  __/
       \___/  \_/ \___|_|  |_|  |_|\__,_|\___|
    */

   @Override
   public int hashCode(){
      int hash = 0;
      for(char cur_y = 0; cur_y < height; cur_y++){
         for(char cur_x = 0; cur_x < width; cur_x++){
            hash += board[cur_x][cur_y].index()*(cur_x+cur_y*height);
         }
      }
      return hash;
   }

   @Override
   public boolean equals(Object obj){
      // check that the dimensions are the same
      if(this.width != ((Board)obj).width)   // width
            return false;
      if(this.height != ((Board)obj).height)   // height
            return false;

      for(char cur_y = 0; cur_y < height; cur_y++){
         for(char cur_x = 0; cur_x < width; cur_x++){
            // check that the contents of the tiles in both boards
            // are the same
            if(      this.board[cur_x][cur_y].index() 
                  != ((Board)obj).board[cur_x][cur_y].index())
               return false;
         }
      }
      return true;
   }

   /*             _            _       
       _ __  _ __(_)_   ____ _| |_ ___ 
      | '_ \| '__| \ \ / / _` | __/ _ \
      | |_) | |  | |\ V / (_| | ||  __/
      | .__/|_|  |_| \_/ \__,_|\__\___|
      |_|                              
   */
   
   private Tile get_adj_tile(Direction.dir direction){
      switch(direction){
         case UP:
            return this.board[this.empty_coord[0]][this.empty_coord[1]-1];
         case DOWN:
            return this.board[this.empty_coord[0]][this.empty_coord[1]+1];
         case LEFT:
            return this.board[this.empty_coord[0]-1][this.empty_coord[1]];
         case RIGHT:
            return this.board[this.empty_coord[0]+1][this.empty_coord[1]];
      }
      return null;
   }

   private char[] get_adj_tile_index(Direction.dir dir){
      char[] adj_indx = new char[2];
      switch(dir){
         case UP:
            adj_indx[0] = this.empty_coord[0];           // x index
            adj_indx[1] = (char)(this.empty_coord[1]-1); // y index
            return adj_indx;
         case DOWN:
            adj_indx[0] = this.empty_coord[0];           // x index
            adj_indx[1] = (char)(this.empty_coord[1]+1); // y index
            return adj_indx;
         case LEFT:
            adj_indx[0] = (char)(this.empty_coord[0]-1); // x index
            adj_indx[1] = this.empty_coord[1];           // y index
            return adj_indx;
         case RIGHT:
            adj_indx[0] = (char)(this.empty_coord[0]+1); // x index
            adj_indx[1] = this.empty_coord[1];           // y index
            return adj_indx;
      }
      // catch exceptions
      adj_indx = null;
      return null;
   }


}
