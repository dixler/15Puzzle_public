import java.util.*;

/*
 * PURPOSE: this is a state of the board in breadth first 
 *          search. One of these states will have a solution
 *          so we keep track of it.
 */

public class Node{
   private LinkedList<Direction.dir> move_list;
   protected Board board;
   Node_pool pool;

/*              _     _ _      
    _ __  _   _| |__ | (_) ___ 
   | '_ \| | | | '_ \| | |/ __|
   | |_) | |_| | |_) | | | (__ 
   | .__/ \__,_|_.__/|_|_|\___|
   |_|                         */

   public Node(Node_pool pool){
      this.pool = pool;
      move_list = new LinkedList<Direction.dir>();
   }
   // allows anonymous inner class to create a base state for bfs
   public void set_board(Board board){}

   public Board get_board(){
      return this.board.clone();
   }

   public boolean is_empty(){
      return move_list.isEmpty();
   }
   // allows returning a solution
   public LinkedList<Direction.dir> get_move_list(){
      return this.move_list;
   }
   // clears the list for reuse in the Node_pool
   public void clear_move_list(){
      this.move_list.clear();
   }
   // copies this node and makes a move 
   // in the copy
   public Node make_move(Direction.dir dir){
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
         // possible optimizations
         return new_branch;
      }
   }

   /*          _            _       
    _ __  _ __(_)_   ____ _| |_ ___ 
   | '_ \| '__| \ \ / / _` | __/ _ \
   | |_) | |  | |\ V / (_| | ||  __/
   | .__/|_|  |_| \_/ \__,_|\__\___|
   |_|                              */
   // deep copies the node. I'm new to java so if
   // there's a better way, please tell me.
   private Node duplicate(){
      Node clone = pool.request_node();
      clone.board = this.board.clone();
      for(Direction.dir move : this.move_list){
         clone.move_list.add(move);
      }
      return clone;
   }
   // adds a move to the node's move_list
   private boolean add_move(Direction.dir dir){
      // TRUE on success FALSE on failure
      Direction.dir cur_move = dir;
      this.move_list.add(cur_move);
      return this.board.swap(cur_move);
   }

}
