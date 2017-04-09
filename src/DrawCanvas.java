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
	
	protected void paintComponent(Graphics g) { 
		// Paint background if we're opaque.
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		g.setColor(Color.GRAY);
		drawGrid(g, gridSize);
		
		for (Polygon p : polygons) {
			p.paintPolygon(g, gridSize, insets.left, insets.top);
		}
	}
	
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
	
	public void setGridSize(int size) {
		gridSize = size;
		clearCanvas();
	}
	
	public void clearCanvas() {
		polygons.clear();
		polygonNames.clear();
		repaint();
	}
	
	public void setColor(Color newColor) {
		color = newColor;
		int index = polygonList.getSelectedIndex();
		
		if (index != -1) {
			polygons.get(index).setColor(color);
		}
	}
	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = (e.getX() -  insets.left) / gridSize;
		int y = (e.getY() - insets.top) / gridSize;
		x = x * gridSize;
		y = y * gridSize;
		
		Point newPoint = new Point(x, y);
		
		int index = polygonList.getSelectedIndex();
		
		if (index != -1) {
			Polygon p = polygons.get(index);
			p.addVertice(newPoint);
			if (p.sizeOfVertices() >= 3)
				p.fillPolygon(gridSize);
		}
		
		else {
			if (polygons.isEmpty())
				addPolygon(new Polygon(color));
			
			//polygons.get(0).setColor(color);
			polygons.get(0).addVertice(newPoint);
		}
		//vertices.add(newPoint);
		
		repaint();
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
