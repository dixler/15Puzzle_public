import java.util.*;
public class MoveList{
   private LinkedList<Move> move_list;
   protected Board board;

   public MoveList(){
      move_list = new LinkedList<Move>();
   }
   // allows anonymous inner class to create a base state
   public void set_board(Board board){}

   public Move pop(){
      return move_list.pop();
   }

   public boolean is_empty(){
      return move_list.isEmpty();
   }

   public boolean add_move(Move.Direction dir){
      // TRUE on success FALSE on failure
      Move cur_move = new Move(dir);
      this.move_list.add(cur_move);
      return this.board.swap(cur_move);
   }
   public MoveList make_move(Move.Direction dir){
      // copy this branch
      MoveList new_branch = this.duplicate();
      // add the desired move
      if(new_branch.add_move(dir)){
         // succeded
         // return the new branch
         return new_branch;
      }
      else{
         // failed
         return null;
      }
   }
   public MoveList duplicate(){
      MoveList clone = new MoveList();
      clone.board = this.board.clone();
      for(Move move : this.move_list){
         clone.move_list.add(new Move(move.get_direction()));
      }
      return clone;
   }
   public Board get_board(){
      return this.board.clone();
   }

}
