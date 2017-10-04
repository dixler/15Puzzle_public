import java.util.LinkedList;
import java.util.Random;
/*
 * PURPOSE: Create a decoupled game representation for possible
 *          reimplementation in other client functions.
 */
public class Game{
   private Board board;
   private Solver my_solver;
   private LinkedList<Direction.dir> undo_list;

   /*           _     _ _      
    _ __  _   _| |__ | (_) ___ 
   | '_ \| | | | '_ \| | |/ __|
   | |_) | |_| | |_) | | | (__ 
   | .__/ \__,_|_.__/|_|_|\___|
   |_|                         */

   // constructor creates a game of board size
   public Game(int board_size){
      this.board = new Board((char)board_size, (char)board_size);
      this.undo_list = new LinkedList<Direction.dir>();

      // give the solver the original board state
      // to keep track of
      this.my_solver = new Solver(this.board);

      // now shuffle the board
      //this.board.shuffle();
      System.out.printf("Complexity: %d\n", this.board.get_complexity());
   }

   // client function calls this to make a move
   // moves relative to the tile being moved
   public boolean user_move(Direction.dir dir){
      if(this.board.swap(Direction.invert(dir))){
         this.undo_list.addFirst(Direction.invert(dir));
         return true;
      }
      // handle invalid move
      else return false;
   }

   public void user_shuffle(){
      this.board.shuffle();
      this.undo_list.clear();
   }

   // pop the last element of the undo list for the client to handle
   public Direction.dir user_undo(){
      // swap by the reverse of the undo list
      if(!undo_list.isEmpty()){
         return undo_list.remove(0);
      }
      return null;
   }

   // returns a solution to the current board
   // the client can decide what to do with the solution
   public LinkedList<Direction.dir> user_solve(){
      LinkedList<Direction.dir> solution_set = this.my_solver.find_solution(this.board).get_move_list();
      for(int i = 0; i < solution_set.size(); i++){
         solution_set.add(Direction.invert(solution_set.pop()));
      }
      return solution_set;
   }

   /*
    * various board state/status functions
    */
   public boolean is_original_puzzle(){
      return this.undo_list.isEmpty();
   }
   public boolean is_solved(){
      return this.my_solver.is_solved(this.board);
   }
   public int get_width(){
      return this.board.get_width();
   }
   public int get_height(){
      return this.board.get_height();
   }
   public Board get_board(){
      return this.board;
   }
   public char[] get_empty_pos(){
      return this.board.get_empty_pos();
   }
   public Tile[] get_tiles(){
      return this.board.get_tiles();
   }
}
