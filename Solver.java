import java.util.*;
public class Solver{
   Hashtable<Board, Board> graph;
   MoveList solution_set;
   LinkedList<MoveList> list_queue;
   Board target;

   public Solver(Board target){
      this.target = target;
      solution_set = new MoveList();
      list_queue = new LinkedList<MoveList>();
      graph = new Hashtable<Board, Board>();
      return;
   }
   public MoveList find_solution(Board origin){
         System.out.printf("WORKING\n");
      // add the first node to the Hashtable
      MoveList first_node = new MoveList(){
         @Override
         public void set_board(Board board){
            this.board = board.clone();
            return;
         }
      };
      // set the board
      first_node.set_board(origin);
      // place the current position into the hash table
      this.graph.put(first_node.get_board(), first_node.get_board());
      this.list_queue.addLast(first_node);
      return bfs();
   }
   private MoveList bfs(){
      // we're at the current position
      // inspect first position in queue
      while(list_queue.size() > 0){
         // get the next node to inspect
         MoveList cur_node = this.list_queue.pop();
         // check if the node is solved
         if(this.target.equals(cur_node.get_board())){
            return cur_node;
         }
         for(Move.Direction dir : Move.Direction.values()){
            MoveList next_node = cur_node.make_move(dir);
            if(next_node != null && graph.contains(next_node)){
               // add the next node to the back of the list
               this.graph.put(next_node.get_board(), next_node.get_board());
               this.list_queue.addLast(next_node);
            }
         }
      }
      
      return null;
   }


}
