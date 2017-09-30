import java.util.Random;
import java.util.LinkedList;
public class Game{
   private Board board;
   private Solver my_solver;

   public Game(int board_size){
      Random rand = new Random();
      this.board = new Board((char)board_size, (char)board_size);
      // set solver original board state
      this.my_solver = new Solver(this.board);
      // make move
      this.board.print_board();
      int successful_swaps = 0;
      //*
      for(int i = 0; i < 55; i++){
         switch(rand.nextInt() % 4){
            case 0:
               if(this.board.swap(new Move(Move.Direction.UP))){
                  successful_swaps++;
                  break;
               }
            case 1:
               if(this.board.swap(new Move(Move.Direction.DOWN))){
                  successful_swaps++;
                  break;
               }
               break;
            case 2:
               if(this.board.swap(new Move(Move.Direction.LEFT))){
                  successful_swaps++;
                  break;
               }
            case 3:
               if(this.board.swap(new Move(Move.Direction.RIGHT))){
                  successful_swaps++;
                  break;
               }
               break;
         }
      }
      System.out.printf("Swap count: %d\n", successful_swaps);
      //*/
   }

   public void play(){

      this.board.print_board();
      // TODO test solver
      System.out.printf("begin solve\n");
      LinkedList<Move> solution = this.my_solver.find_solution(this.board).move_list;
      System.out.printf("Moves: %d\n", solution.size());
         this.board.print_board();
         System.out.printf("\n");
      while(solution.size() > 0){
         this.board.swap(solution.pop());
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
