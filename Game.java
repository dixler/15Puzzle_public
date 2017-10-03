import java.util.LinkedList;
import java.util.Random;
public class Game{
   private Board board;
   private Solver my_solver;
   private LinkedList<Direction> undo_list;

   @SuppressWarnings("fallthrough")
   public Game(int board_size){
      this.board = new Board((char)board_size, (char)board_size);
      this.undo_list = new LinkedList<Direction>();

      // feed the solver the original board state
      this.my_solver = new Solver(this.board);
      // now shuffle the board
            int successful_swaps = 0;

      this.board.shuffle();
      this.board.print_board();
   }

   public boolean user_move(Direction dir){
      if(this.board.swap(dir)){
         this.undo_list.addFirst(dir);
         this.board.print_board();
         if(this.my_solver.is_solved(this.board)){
            System.out.printf("Congratulations! You solved the puzzle!\n");
         }
         System.out.printf("Complexity: %d\n", this.board.get_complexity());
         return true;
      }
      else
         return false;
         // handle invalid move
   }

   public boolean is_original_puzzle(){
      return this.undo_list.isEmpty();
   }

   // pop the last element of the undo list for the gui to handle
   public Direction user_undo(){
      // swap by the reverse of the undo list
      if(!undo_list.isEmpty()){
         switch(undo_list.remove(0)){
            case UP:
               return Direction.DOWN;
            case LEFT:
               return Direction.RIGHT;
            case DOWN:
               return Direction.UP;
            case RIGHT:
               return Direction.LEFT;
         }
      }
      return null;
   }

   // returns a solution to the current board
   // the caller can decide what to do with the solution
   public LinkedList<Direction> user_solve(){
      return this.my_solver.find_solution(this.board).move_list;
   }

   // CLI test cases
   public void play(){

      this.board.print_board();
      //this.board.DEBUG_TILE_print_board();
      System.out.printf("Complexity: %d\n", this.board.get_complexity());
      System.out.printf("begin solve\n");
      LinkedList<Direction> solution = this.my_solver.find_solution(this.board).move_list;
      System.out.printf("Moves: %d\n", solution.size());
         this.board.print_board();
         System.out.printf("\n");
      while(solution.size() > 0){
         this.board.swap(solution.pop());
         // add binding for display
         this.board.print_board();
         //this.board.DEBUG_TILE_print_board();
         System.out.printf("\n");
      }
      System.out.printf("Solved\n");
      this.board.print_board();
      return;
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
