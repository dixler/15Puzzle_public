import java.util.*;
public class Node{
   public LinkedList<Direction> move_list;
   protected Board board;
   Node_pool pool;

   public Node(Node_pool pool){
      this.pool = pool;
      move_list = new LinkedList<Direction>();
   }
   // allows anonymous inner class to create a base state for bfs
   public void set_board(Board board){}

   public Direction pop(){
      return move_list.pop();
   }

   public boolean is_empty(){
      return move_list.isEmpty();
   }

   // adds a move to the move_list
   public boolean add_move(Direction dir){
      // TRUE on success FALSE on failure
      Direction cur_move = dir;
      this.move_list.add(cur_move);
      return this.board.swap(cur_move);
   }
   public Node make_move(Direction dir){
      // copy this branch
      Node new_branch = this.duplicate();
      // add the desired move
      if(new_branch.add_move(dir)){
         // succeded
         // return the new branch
         return new_branch;
      }
      else{
         // failed
         // TODO
         return new_branch;
      }
   }
   public Node duplicate(){
      Node clone = pool.request_node();
      clone.board = this.board.clone();
      for(Direction move : this.move_list){
         clone.move_list.add(move);
      }
      return clone;
   }
   public Board get_board(){
      return this.board.clone();
   }

}
