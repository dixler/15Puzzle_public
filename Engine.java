public class Engine{
   public static class Game{
      private Board board;
      public Game(int board_size){
         this.board = new Board(board_size, board_size);
         this.board.initialize();
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
