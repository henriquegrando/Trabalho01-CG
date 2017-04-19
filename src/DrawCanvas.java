import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class DrawCanvas extends JPanel implements MouseInputListener {
	ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	DefaultListModel<String> polygonNames = new DefaultListModel<>();
	JList<String> polygonList = new JList<>(polygonNames);

	int gridSize = 20;
	Insets insets;
	Color color = Color.GRAY;

	public DrawCanvas() {
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(Color.WHITE);
		setOpaque(true);
		addMouseListener(this);
		insets = getInsets();
		polygonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// Paints the background, grid, and polygons
	@Override
	protected void paintComponent(Graphics g) {
		// Paint background if we're opaque.
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		g.setColor(Color.GRAY);
		drawGrid(g, gridSize);

		for (Polygon p : polygons) {
			p.paintPolygon(g, insets.left, insets.top);
		}
	}

	// Draws grid lines
	protected void drawGrid(Graphics g, int gridSpace) {
		int firstX = insets.left;
		int firstY = insets.top;

		int lastX = getWidth() - insets.right;
		int lastY = getHeight() - insets.bottom;

		// Draw vertical lines.
		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		// Draw horizontal lines.
		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}
	}

	// Sets gridSize and clears all painted polygons
	public void setGridSize(int size) {
		gridSize = size;
		clearCanvas();
	}

	// Clears all polygons and repaint de grid
	public void clearCanvas() {
		polygons.clear();
		polygonNames.clear();
		repaint();	// calls paintComponent()
	}

	// Changes color of the selected polygon
	public void setColor(Color newColor) {
		color = newColor;
		int index = polygonList.getSelectedIndex();

		if (index != -1) {
			polygons.get(index).setColor(color);
		}
	}

	// Adds a polygon to be printed
	public void addPolygon(Polygon p) {
		if (p != null) {
			polygons.add(p);
			polygonNames.addElement("Polygon " + polygons.size());
		}
		else return;
	}

	public JList<String> getPolygonList() {
		return polygonList;
	}

	// Handles click events
	// If a polygon is being drawn adds a new vertex to that polygon.
	// Otherwise, creates the first vertex of a new Polygon
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = (e.getX() -  insets.left) / this.gridSize;
		int y = (e.getY() - insets.top) / this.gridSize;
		x = x * this.gridSize;
		y = y * this.gridSize;

		Point newPoint = new Point(x, y);

		int index = polygonList.getSelectedIndex();

		if (index != -1) {
			Polygon p = polygons.get(index);
			p.addVertice(newPoint);
			if (p.sizeOfVertices() >= 3)
				p.fillPolygon();
		}

		else {
			if (polygons.isEmpty())
				addPolygon(new Polygon(this.gridSize, color));

			//polygons.get(0).setColor(color);
			polygons.get(0).addVertice(newPoint);
		}
		//vertices.add(newPoint);

		repaint();	// calls paintComponent
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
