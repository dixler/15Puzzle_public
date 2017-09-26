import java.util.*;
public class Solver{
   Hashtable<Integer, Board> graph;
   Node solution_set;
   LinkedList<Node> list_queue;
   Board target;

   public Solver(Board target){
      this.target = target.clone();
      solution_set = new Node();
      list_queue = new LinkedList<Node>();
      graph = new Hashtable<Integer, Board>();
      return;
   }
   public Node find_solution(Board origin){
      // add the first node to the Hashtable
      Node first_node = new Node(){
         @Override
         public void set_board(Board board){
            this.board = board.clone();
            return;
         }
      };
      // set the board
      first_node.set_board(origin);
      // place the current position into the hash table
      this.graph.put(first_node.get_board().hashCode(), first_node.get_board());
      this.list_queue.addLast(first_node);
      return bfs();
   }
   private Node bfs(){
      // we're at the current position
      // inspect first position in queue
      while(list_queue.size() > 0){
         // get the next node to inspect
         Node cur_node = this.list_queue.pop();

         // check if the node is solved
         if(this.target.hashCode() == cur_node.get_board().hashCode() && this.target.equals(cur_node.get_board())){
            return cur_node;
         }
         for(Move.Direction dir : Move.Direction.values()){
            Node next_node = cur_node.make_move(dir);
            if(next_node != null && !graph.contains(next_node.get_board())){
               // add the next node to the back of the list
               this.graph.put(next_node.get_board().hashCode(), next_node.get_board());
               this.list_queue.addLast(next_node);
            }
         }
      }
      
      return null;
   }


}
