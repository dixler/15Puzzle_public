/*
 * Move list pool so we don't have to delete objects every time
 */
import java.util.*;
public class Node_pool{
   // linked list of objects
   ArrayList<Node> pool;
   public Node_pool(){
      this.pool = new ArrayList<Node>();
   }
   public Node request_node(){
      if(pool.size() == 0){
         // return new node
         return new Node(this);
      }
      else{
         Node popped = pool.remove(0);
         popped.move_list = new LinkedList<Move>();
         return popped;
      }
   }
   public void return_node(Node node){
      pool.add(node);
      return;
   }
}

