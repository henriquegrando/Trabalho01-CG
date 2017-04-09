import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import Edge.java

public class Polygon {
	ArrayList<Point> vertices = new ArrayList<Point>();
	ArrayList<Point> pixels = new ArrayList<Point>();
	ArrayList<Edge> edgeTable = new ArrayList<Edge>();
	ArrayList<Edge> activeEdgeTable = new ArrayList<Edge>();
	Color color;
	
	public Polygon() {
		color = Color.GRAY;
	}
	
	public Polygon(Color color) {
		this.color = color;
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
	
	public void addPixel(Point p) {
		pixels.add(p);
	}
	
	public void paintPolygon(Graphics g, int gridSize, int left, int top) {
		g.setColor(color);
		for (Point v : vertices) {
			g.fillRect(v.x + left, v.y + top, gridSize , gridSize);
		}
		
		for (Point p : pixels) {
			g.fillRect(p.x + left, p.y + top, gridSize , gridSize);
		}
	}
	
	public void fillPolygon(int gridSize) {
		// TODO: algoritmo que a moca pediu vai aqui
		// DEBUG
		for (int i = vertices.get(0).x + gridSize; i <= vertices.get(1).x; i++) {
			addPixel(new Point(i, vertices.get(0).y));
		}
	}

	public void leftEdgeScan(int xmin, int xmax, int ymin, int ymax, int valor){
		int x, y;
		Point p;

		x = xmin;
		y = ymin;
		numerador = xmax - xmin;
		denominador = ymax - ymin;
		incremento = denominador;

		for(y = ymin; y <= ymax; y++){
			//PintaPixel(x, y, valor)
			p = (x, y);
			addPixel(p);
			incremento += numerador;

			if (incremento > denominador){
				x++;
				incremento -= denominador;
			}
		}
	}
}
