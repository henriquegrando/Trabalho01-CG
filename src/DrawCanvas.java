import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputListener;
import javax.swing.SwingUtilities;

public class DrawCanvas extends JPanel implements MouseInputListener {
	ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	DefaultListModel<String> polygonNames = new DefaultListModel<>();
	JList<String> polygonList = new JList<>(polygonNames);
	public boolean drawLabels = true;

	int gridSize = 20;
	Insets insets;
	Color color = Color.gray;
	int polygonNumber = 1;

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
		// Paint background if we're opaque
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		g.setColor(Color.GRAY);
		drawGrid(g, gridSize);

		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		for (Polygon p : polygons) {
			p.paintPolygon(g, insets.left, insets.top, drawLabels);
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
		repaint(); // calls paintComponent()
	}

	// Changes color of the selected polygon
	public void setColor(Color newColor) {
		color = newColor;
		int index = polygonList.getSelectedIndex();

		if (index != -1) {
			polygons.get(index).setColor(color);
		}

		repaint();
	}

	// Adds a polygon to be printed
	public void addPolygon(Polygon p) {
		if (p != null) {
			polygons.add(p);
			int index = polygonList.getLastVisibleIndex() + 1;
			polygonNames.addElement("Polygon " + polygonNumber++);
			polygonList.setSelectedIndex(index);
		}
		else
			return;
	}

	// Deletes selected polygon
	public void deletePolygon(int k) {
		if (k != -1 && k < polygons.size()) {
			//System.out.println(k);
			polygons.remove(k);
			polygonNames.remove(k);
			repaint();
		}
	}

	// Calls fillPolygon function for selected polygon
	public void drawPolygon(int k) {
		//System.out.println(k);
		if (k >= polygons.size() || k == -1)
			return;

		Polygon p = polygons.get(k);

		if (p.drawn)
			return;
		
		if (p.sizeOfVertices() < 3)
			JOptionPane.showMessageDialog(null, "Polygon must have at least three distinct vertices.", "Warning", JOptionPane.WARNING_MESSAGE);

		else if (p.checkDegenerate())
			JOptionPane.showMessageDialog(null, "Degenerate polygon.", "Warning", JOptionPane.WARNING_MESSAGE);
		
		else p.fillPolygon();

		// Clear the vertices list so the vertices markers don't get painted
		p.clearMarkers();

		repaint();
	}

	public JList<String> getPolygonList() {
		return polygonList;
	}

	// Handles right and left click events
	@Override
	public void mouseClicked(MouseEvent e) {

		// Right click -> draws polygon
		if (SwingUtilities.isRightMouseButton(e)) {
			int index = polygonList.getSelectedIndex();
			if (index != -1) {
				drawPolygon(index);
			}
		}

		// Left click -> if a polygon is being drawn adds a new vertex to that polygon.
		// 								Otherwise, creates the first vertex of a new Polygon
		if (SwingUtilities.isLeftMouseButton(e)) {
			int x = (e.getX() - insets.left) / this.gridSize;
			int y = (e.getY() - insets.top) / this.gridSize;
			x = x * this.gridSize;
			y = y * this.gridSize;

			Point newPoint = new Point(x, y);

			int index = polygonList.getSelectedIndex();
			if (index != -1) {
				Polygon p = polygons.get(index);
				if (!p.hasVertice(newPoint))
					p.addVertice(newPoint);
			}
			else {
				if (polygons.isEmpty()) {
					addPolygon(new Polygon(this.gridSize, color));
					polygonList.setSelectedIndex(0);
				}
				else {
					int last = polygonList.getLastVisibleIndex();
					polygonList.setSelectedIndex(last);
				}

				// polygons.get(0).setColor(color);
				Polygon p = polygons.get(0);
				if (!p.hasVertice(newPoint))
					p.addVertice(newPoint);
			}
			// vertices.add(newPoint);

			repaint(); // calls paintComponent
		}

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
