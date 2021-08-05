
import java.io.*;
import java.util.*;
public class Dijkstra {

  private class Vertex {
    private EdgeNode edges1;
    private EdgeNode edges2;
     private boolean known;
     private int distance;
     private int previous;
     
     private Vertex() {
     edges1 = null;
     edges2 = null;
     known = false;
     distance = Integer.MAX_VALUE;
     previous = -1;
       }
     }
  
  private class EdgeNode {
    private int vertex1; //array index in g
    private int vertex2; //array index in g
    private EdgeNode next1; //next node in edge list 1
    private EdgeNode next2; //next node in edge list 2
    private int weight;
    private EdgeNode(int v1, int v2, EdgeNode e1, EdgeNode e2, int w) {
    //PRE: v1 < v2
    vertex1 = v1;
    vertex2 = v2;
    next1 = e1;
    next2 = e2;
    weight = w;
      }
    }
  
  private Vertex[] g; //array of vertices
  
  public Dijkstra(int size) {
   g = new Vertex[size];
   for (int i = 0; i < size; i++) {
   g[i] = new Vertex();
     }
   }
  
  public void addEdge(int v1, int v2, int w) {
   //PRE: v1 and v2 are legitimate vertices
   //(i.e. 0 <= v1 < g.length and 0 <= v2 < g.length
    if(v1 < v2) {
      EdgeNode ed = new EdgeNode(v1, v2,  g[v1].edges1,  g[v2].edges2, w);
      g[v1].edges1 = ed;
      g[v2].edges2 = ed;
    }
    else {
      EdgeNode ed = new EdgeNode(v1, v2, g[v2].edges1, g[v1].edges2, w);
      g[v1].edges2 = ed;
      g[v2].edges1 = ed;
    }
    
   }
 
   public void printRoutes(int j) {
   //find and print the best routes from j to all other nodes in the graph
   DijkstraHeap p = new DijkstraHeap(g.length);
   g[j].distance = 0;
   p.insert(j, 0);
   //I know something else is supposed to be here but I am not sure how to build it correctly 
   //buildShortestPath(p);
   print();
   }
   
   private void print() {
     int counter;
     String output = "";
     for (int i = 0; i < g.length; i++) {
       counter = i;
     while(counter != -1) {
       output = counter + " " + output;
       counter = g[counter].previous;
       }
     System.out.println(i + ": " + output);
     output = " ";
     }
   }
   
   
   
   public static void main(String args[]) throws IOException {
     BufferedReader b = new BufferedReader(new FileReader(args[0]));
     String line = b.readLine();
     int numNodes = Integer.parseInt(line);
     line = b.readLine();
     int source = Integer.parseInt(line);
     Dijkstra g = new Dijkstra(numNodes);
     line = b.readLine();
     while (line != null) {
     Scanner scan = new Scanner(line);
     g.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
     line = b.readLine();
     }
     g.printRoutes(source);
     } 
}
