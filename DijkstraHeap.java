
public class DijkstraHeap {
  /*
  implements a binary heap where the heap rule is the value in the
  parent node is less than or equal to the values in the child nodes.
  In addition to the standard binary heap implementation the class
  keeps an array of locations of items in the heap so an item can be
  quickly found for the decreaseKey operation.
  */
  private class Item {
  private int node;
  private int distance; //the priority

  private Item(int n, int d) {
  node = n;
  distance = d;
    }
  }
  
  private Item items[];
  private int locations[]; //if i is in the heap the location of node i
  //is in location[i] otherwise location[i] is -1
  private int size; //current number of elements in the heap
 public DijkstraHeap(int s) {
  items = new Item[s+1];
  locations = new int[s];
  //initially no nodes have been inserted
  for (int i = 0; i < locations.length; i++)
  locations[i] = -1;
  size = 0;
  }
 
 public void removeMin() {
 //PRE: getSize() != 0
  //removes the highest priority item in the queue
   locations[items[1].node] = -1;
   items[1] = items[size];
   locations[items[1].node] = 1;
   items[size] = null;
   size--;
   
   int root = 1;
   
   while(root * 2 < size && (items[root].distance > items[root * 2].distance || items[root].distance > items[root*2 + 1].distance)){
     if(items[root * 2].distance < items[root * 2 + 1].distance) {
       switcher(root, root * 2);
       root = root * 2;
     }
     else {
       switcher(root, root * 2 + 1);
       root = root * 2 + 1;
     }
   }
  }
 
 private void switcher (int i, int j) {
   Item temp = items[i];
   int templocation = locations[items[i].node];
   
   items[i] = items[j];
   locations[items[j].node] = locations[temp.node];
   items[j] = temp;
   locations[temp.node] = templocation;
 }
 public int getMinNode() {
   //PRE: getSize() != 0
   //returns the highest priority node
   return items[1].node;
   }

   public int getMinDistance() {
   //PRE: getSize() != 0
   //returns the distance of highest priority node
     return items[1].distance;
   }
   
  public boolean full() {
   //return true if the heap is full otherwise return false
    if(size == items.length) {
      return true;
    }
    return false;
   }
  
  public void insert(int n, int d) {
  //PRE !full() and !inserted(n))
    size++;
    items[size] = new Item(n,d);
    locations[n] = size;
    int leaf = size;
    while (leaf / 2 != 0 && items[leaf].distance < items[leaf/2].distance) {
      switcher(leaf, leaf / 2);
      leaf = leaf/2;
    }
   }
  
  public void decreaseKey(int n, int d) {
    //PRE: inserted(n) and d < the distance associated with n
    //replace the current distance associated with n with d
    //and adjust the heap
    items[locations[n]].distance = d;
    
    int leaf = locations[n];
    
    while(leaf / 2 != 0 && items[leaf].distance < items[leaf/2].distance) {
      switcher(leaf, leaf/2);
      leaf = leaf / 2;
      }
    }
  
   public int getSize() {
    //return the number of items in the heap
     return size;
    }

    public boolean inserted(int n) {
    //return true if n has been inserted otherwise return false
      if(locations[n] != -1) {
        return true;
      }
      return false;
    }
}

