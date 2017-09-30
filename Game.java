import java.util.LinkedList;
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
      this.board.shuffle();
   }

   public boolean user_move(Direction dir){
      if(this.board.swap(dir)){
         this.undo_list.addFirst(dir);
         return true;
      }
      else
         return false;
         // handle invalid move
   }
   public void user_undo(){
      // swap by the reverse of the undo list
      switch(undo_list.remove(0)){
         case UP:
      this.board.swap(Direction.DOWN);
            break;
         case LEFT:
      this.board.swap(Direction.RIGHT);
            break;
         case DOWN:
      this.board.swap(Direction.UP);
            break;
         case RIGHT:
      this.board.swap(Direction.LEFT);
            break;
      }
      return;
   }

   // returns a solution to the current board
   // the caller can decide what to do with the solution
   public LinkedList<Direction> user_solve(){
      return this.my_solver.find_solution(this.board).move_list;
   }

   // CLI test cases
   public void play(){

      this.board.print_board();
      // TODO test solver
      //System.out.printf("begin solve\n");
      LinkedList<Direction> solution = this.my_solver.find_solution(this.board).move_list;
      //System.out.printf("Moves: %d\n", solution.size());
         this.board.print_board();
         System.out.printf("\n");
      while(solution.size() > 0){
         this.board.swap(solution.pop());
         // add binding for display
         this.board.print_board();
         System.out.printf("\n");
      }
      System.out.printf("Solved\n");
      this.board.print_board();
      


      return;
   }
   public Board game_state(){
      return null;
   }
}
