//package my_package;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import edges.*;

public class Polygon {
	ArrayList<Point> vertices;
	ArrayList<Point> pixels;
	EdgeTable edgeTable;
	ArrayList<Edge> activeEdgeTable;
	Color color;
	int gridSize;

	public Polygon(int gridSize) {
		vertices = new ArrayList<Point>();
		pixels = new ArrayList<Point>();
		edgeTable = new EdgeTable();
		activeEdgeTable = new ArrayList<Edge>();
		color = Color.GRAY;
		this.gridSize = gridSize;
	}

	public Polygon(int gridSize, Color color) {
		vertices = new ArrayList<Point>();
		pixels = new ArrayList<Point>();
		edgeTable = new EdgeTable();
		activeEdgeTable = new ArrayList<Edge>();
		this.color = color;
		this.gridSize = gridSize;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void addVertice(Point v) {
		vertices.add(v);
	}

	public int sizeOfVertices() {
		return vertices.size();
	}

	public void addPixel(int x, int y) {
		x *= this.gridSize;
		y *= this.gridSize;

		pixels.add(new Point(x,y));
	}

	// Paints a polygon using the stored vertices and pixels
	public void paintPolygon(Graphics g, int left, int top) {
		g.setColor(color);
		for (Point v : vertices) {
			g.fillRect(v.x + left, v.y + top, this.gridSize , this.gridSize);
		}

		for (Point p : pixels) {
			g.fillRect(p.x + left, p.y + top, this.gridSize , this.gridSize);
		}
	}

	// Fills the polygon using edge coherence algorithm
	public void fillPolygon() {
		LinkedList<Edge> line;
		int y = 0;

		buildEdgeTable();

		// Get the first non-empty bucket in the ET
		while((line = edgeTable.getLine(y)) == null){
			y++;
		}

		while(!edgeTable.isEmpty() || !activeEdgeTable.isEmpty()){

			// Add in the AET all edges starting at the currently scanned line
			for (Edge e : line)
				activeEdgeTable.add(e);

			// Remove from the AET all edges ending at the currently scanned line
			for (int i = 0; i < activeEdgeTable.size(); i++)
				if (activeEdgeTable.get(i).getYmax() == y)
					activeEdgeTable.remove(i);

			Collections.sort(activeEdgeTable);

			// Add pixels to be painted
			for (int i = 0; i < activeEdgeTable.size(); i += 2)
				for (int x = activeEdgeTable.get(i).getXmin(); x < activeEdgeTable.get(i+1).getXmin(); x++)
					addPixel(x,y);

			// Scan Edges updating their x values
			for (Edge e : activeEdgeTable)
				e.scan();

			line = edgeTable.getLine(++y);
		}
	}

	// Builds the Edges of the Polygon based on the list of vertices
	// and adds each of them to the Polygon's Edge Table
	private void buildEdgeTable(){
		Point v1, v2;
		Edge edge;
		int xmin, xmax, ymin, ymax, num, den, index;

		for(Iterator<Point> i = vertices.iterator(); i.hasNext(); ) {
    		// Select two consecutive points to build an Edge
    		v1 = i.next();
    		index = vertices.indexOf(v1);
    		if (index == vertices.size())
    			v2 = vertices.get(0);
    		else
    			v2 = vertices.get(index+1);

    		// Calculate Edge attributes
    		xmin = Math.min(v1.x, v2.x);
    		ymax = Math.max(v1.y, v2.y);
    		ymin = Math.min(v1.y, v2.y);

    		// 1/m
    		num = (v2.x - v1.x);
    		den = (v2.y - v1.y);

    		edgeTable.addEdge(ymin, new Edge(ymax, xmin, num, den));
		}

		edgeTable.sort();
	}
}
