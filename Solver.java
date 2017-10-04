import java.util.*;
/*
 * PURPOSE: Holds an instance of the solved board and can
 *          tell the client if the board is solved. Also 
 *          uses bfs to solve the board when needed. Extremely
 *          slow.
 */
public class Solver{
   // holds all of our previously traversed nodes
   Hashtable<Board, Board> graph;
   ArrayList<Node> list_queue;
   Board target;
   // holds our nodes for recycling
   Node_pool pool;

/*              _     _ _      
    _ __  _   _| |__ | (_) ___ 
   | '_ \| | | | '_ \| | |/ __|
   | |_) | |_| | |_) | | | (__ 
   | .__/ \__,_|_.__/|_|_|\___|
   |_|                         */
   
   public Solver(Board target){
      this.target = target.clone();
      this.pool = new Node_pool();
      return;
   }
   public boolean is_solved(Board current){
      return this.target.equals(current);
   }
   public Node find_solution(Board origin){
      System.out.printf("Solving\n");
      this.list_queue = new ArrayList<Node>();
      this.graph = new Hashtable<Board, Board>();
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
      // CALL BFS
      return bfs();
   }

/*             _            _       
    _ __  _ __(_)_   ____ _| |_ ___ 
   | '_ \| '__| \ \ / / _` | __/ _ \
   | |_) | |  | |\ V / (_| | ||  __/
   | .__/|_|  |_| \_/ \__,_|\__\___|
   |_|                              */
   private Node bfs(){
      // we're at the current position
      // inspect first position in queue
      while(list_queue.size() > 0){
         // get the next node to inspect
         Node cur_node = this.list_queue.remove(0);

         // check if the node is solved
         if(this.target.equals(cur_node.get_board())){
            return cur_node;
         }
         for(Direction.dir dir : Direction.dir.values()){
            Node next_node = cur_node.make_move(dir);
            if(next_node != null){
                  if(!graph.contains(next_node.get_board())){
                     // add the next node to the back of the list
                     this.graph.put(next_node.get_board(), next_node.get_board());
                     this.list_queue.add(next_node);
                  }
                  else{
                     this.pool.return_node(next_node);
                  }
            }
         }
         this.pool.return_node(cur_node);
      }
      
      return null;
   }
}
