package edges;

import java.util.Hashtable;
import java.util.LinkedList;

public class EdgeTable{
  Hashtable<Integer, LinkedList<Edge>> et;

  public EdgeTable(){
    et = new Hashtable<Integer, LinkedList<Edge>>();
  }

  public void addEdge(int y, Edge e){
    LinkedList<Edge> edgeList = et.get(y);

    if (edgeList == null){
      edgeList = new LinkedList<Edge>();
      et.put(y, edgeList);
    }
    edgeList.add(e);
  }

  public LinkedList<Edge> getLine(int y){
    return et.remove(y);
  }

  public boolean isEmpty(){
    return et.isEmpty();
  }
}
