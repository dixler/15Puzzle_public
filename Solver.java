import java.util.*;
public class Solver{
   Hashtable<Board, Board> graph;
   Node solution_set;
   ArrayList<Node> list_queue;
   Board target;
   Node_pool pool;

   public Solver(Board target){
      this.target = target.clone();
      this.list_queue = new ArrayList<Node>();
      this.graph = new Hashtable<Board, Board>();
      this.pool = new Node_pool();
      return;
   }
   public boolean is_solved(Board current){
      return this.target.equals(current);
   }
   public Node find_solution(Board origin){
      System.out.printf("Solving\n");
      // add the first node to the Hashtable
      Node first_node = new Node(this.pool){
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
      this.list_queue.add(first_node);
      return bfs();
   }
   private Node bfs(){
      // we're at the current position
      // inspect first position in queue
      while(list_queue.size() > 0){
         // get the next node to inspect
         Node cur_node = this.list_queue.remove(0);


/*
System.out.printf("exploring\n");
cur_node.get_board().print_board();
//*/

         // check if the node is solved
         if(this.target.equals(cur_node.get_board())){
            return cur_node;
         }
         for(Direction dir : Direction.values()){
            Node next_node = cur_node.make_move(dir);
            if(next_node != null){
                  if(!graph.contains(next_node.get_board())){
                     // add the next node to the back of the list
                     this.graph.put(next_node.get_board(), next_node.get_board());
                     this.list_queue.add(next_node);
                  }
                  else{
                     pool.return_node(next_node);
                  }
            }
         }
         pool.return_node(cur_node);
      }
      
      return null;
   }


}
