
//package my_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import Edges.Edge;
import Edges.EdgeTable;

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
		//x *= this.gridSize;
		//y *= this.gridSize;

		pixels.add(new Point(x, y));
	}

	// Paints a polygon using the stored vertices and pixels
	public void paintPolygon(Graphics g, int left, int top, boolean drawLabels) {
		int i = 0;
		g.setColor(color);
		for (Point p : pixels) {
			g.fillRect(p.x + left, p.y + top, this.gridSize, this.gridSize);
		}
		
		for (Point v : vertices) {
			g.setColor(color);
			g.fillRect(v.x + left, v.y + top, this.gridSize, this.gridSize);
			if (drawLabels) {
				g.setColor(Color.BLACK);
				g.drawString("V" + i, v.x + left + this.gridSize + 2, v.y + top - 2);
				i++;
			}
		}
	}

	// Fills the polygon using edge coherence algorithm
	public void fillPolygon() {
		LinkedList<Edge> line;

		buildEdgeTable();
		
		int y = 0;
		// Get the first non-empty bucket in the ET
		while ((line = edgeTable.getLine(y)) == null){
			y += this.gridSize;
		}

		while (!edgeTable.isEmpty() || !activeEdgeTable.isEmpty()) {
			// Add in the AET all edges starting at the currently scanned line
			if (line != null) {
				for (Edge e : line){
					activeEdgeTable.add(e);
				}
			}

			System.out.println("y: " + y);
			
			Collections.sort(activeEdgeTable);

			// Remove from the AET all edges ending at the currently scanned line
			for (int i = 0; i < activeEdgeTable.size(); i++) {
				if (activeEdgeTable.get(i).getYmax() == y) {
					System.out.println("Removed: ");
					System.out.println(activeEdgeTable.get(i).toString());
					activeEdgeTable.remove(i);
					i--;
				}
			}	
			
			// Add pixels to be painted
			for (int i = 0; i < activeEdgeTable.size() - 1; i += 2) {
				int x1 = activeEdgeTable.get(i).getXmin();
				int x2 = activeEdgeTable.get(i + 1).getXmin();
				for (int x = x1; x < x2; x += this.gridSize) {
					addPixel(x, y);
				}
			}
			
			// Print active edge table
			System.out.println(activeEdgeTable);

			y += this.gridSize;
			line = edgeTable.getLine(y);
			
			// Scan Edges updating their x values
			for (Edge e : activeEdgeTable)
				e.scan(this.gridSize);
		}
	}

	// Builds the Edges of the Polygon based on the list of vertices
	// and adds each of them to the Polygon's Edge Table
	private void buildEdgeTable() {
		//Point v1, v2;
		Point v2;
		Edge edge;
		int xmin, xmax, ymin, ymax, num, den, index;

		for (Point v1 : vertices) {
			index = vertices.indexOf(v1);
			if (index == vertices.size() - 1)
				v2 = vertices.get(0);
			else
				v2 = vertices.get(index + 1);
			
			// Calculate Edge attributes
			if (v2.y > v1.y) {
				ymax = v2.y;
				ymin = v1.y;
				xmin = v1.x;
				num = v2.x - v1.x;
			}
			
			else {
				ymax = v1.y;
				ymin = v2.y;
				xmin = v2.x;
				num = v1.x - v2.x;
			}
			
			den = ymax - ymin;

			edgeTable.addEdge(ymin, new Edge(ymax, xmin, num, den));
		}

		edgeTable.sort();
	}
}
