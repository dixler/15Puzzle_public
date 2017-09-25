public class Engine{
   public static class Game{
      private Board board;
      private Solver my_solver;
      public Game(int board_size){
         this.board = new Board(board_size, board_size);
         this.my_solver = new Solver(this.board);
         this.board.print_board();
         //this.board.initialize();
         this.play();
      }

      public void play(){

         this.board.print_board();

         Move my_move = new Move(Move.Direction.RIGHT);
         this.board.swap(my_move);
         this.board.print_board();

         my_move.set_direction(Move.Direction.UP);
         this.board.swap(my_move);
         this.board.print_board();

         my_move.set_direction(Move.Direction.UP);
         this.board.swap(my_move);
         this.board.print_board();

         my_move.set_direction(Move.Direction.UP);
         this.board.swap(my_move);
         this.board.print_board();

         my_move.set_direction(Move.Direction.UP);
         this.board.swap(my_move);
         this.board.print_board();
         // TODO test solver
         System.out.printf("begin solve\n");
         MoveList solution = this.my_solver.find_solution(this.board);
         while(!solution.is_empty()){
            this.board.swap(solution.pop());
            this.board.print_board();
         }
         System.out.printf("Solved\n");
         this.board.print_board();
         


         return;
      }
      public Board game_state(){
         return null;
      }
   }

   public static void main(String[] args){
      Game my_game = new Game(4);

      return;
   }
}
