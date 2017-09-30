/*
 * This seemed to improve runtime by reducing the number of newly allocated nodes
 * when we're done with a node, we place it in this object. When we want a new node
 * we take it from the pool if the pool is empty, allocate a new node.
 */
import java.util.*;
public class Node_pool{
   // the pool of nodes itself
   ArrayList<Node> pool;

   // constructor
   public Node_pool(){
      this.pool = new ArrayList<Node>();
   }

   // obtain a new node
   public Node request_node(){
      if(pool.size() == 0){
         // create new node
         return new Node(this);
      }
      else{
         // recycle old node
         Node popped = pool.remove(0);
         popped.move_list = new LinkedList<Move>();
         return popped;
      }
   }
   // return the node to the pool
   public void return_node(Node node){
      pool.add(node);
      return;
   }
}

