package Edges;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class EdgeTable {
	Hashtable<Integer, LinkedList<Edge>> et;

	public EdgeTable() {
		et = new Hashtable<Integer, LinkedList<Edge>>();
	}

	// Adds an edge to an existing Linked List in the ET
	// or creates a new one to hold it
	public void addEdge(int y, Edge e) {
		LinkedList<Edge> edgeList = et.get(y);

		if (edgeList == null) {
			edgeList = new LinkedList<Edge>();
			et.put(y, edgeList);
		}
		edgeList.add(e);
	}

	// Removes the Linked List from the ET and returns it
	public LinkedList<Edge> getLine(int y) {
		return et.remove(y);
	}

	public boolean isEmpty() {
		return et.isEmpty();
	}

	// Sorts all Linked Lists in the ET by its Edges' xmin
	public void sort() {
		for (Map.Entry<Integer, LinkedList<Edge>> line : et.entrySet()) {
			Collections.sort(line.getValue());
		}
	}
}
