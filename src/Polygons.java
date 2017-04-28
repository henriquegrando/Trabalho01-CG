import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.colorchooser.*;
import javax.swing.border.*;
import java.util.*;


public class Polygons {

	// Sets up Graphical User Interface
	private static void setGUI() {
		JFrame frame = new JFrame("Polygons");
		BorderLayout layout = new BorderLayout();
		//layout.setHgap(2);
		//layout.setVgap(2);
		frame.setLayout(layout);

		DrawCanvas canvas = new DrawCanvas();
		frame.add(canvas, BorderLayout.CENTER);

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new GridBagLayout());
		sidePanel.setBorder(BorderFactory.createLoweredBevelBorder());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;

		// Clear button
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.clearCanvas();
			}
		});
		c.weighty = 1;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		sidePanel.add(clearBtn, c);

		// Slider panel
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new TitledBorder("Grid"));

	    JSlider slider = new JSlider(5, 55, 10);
	    slider.setMinorTickSpacing(1);
	    slider.setMajorTickSpacing(10);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    sliderPanel.add(slider);

	    JLabel gridValLb = new JLabel(String.valueOf(slider.getValue()));
	    slider.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent e) {
	    		gridValLb.setText(String.valueOf(slider.getValue()));
	    	}
	    });
	    sliderPanel.add(gridValLb);

	    JButton setGridBtn = new JButton ("Set");
	    setGridBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		canvas.setGridSize(slider.getValue());
	    	}
	    });
	    sliderPanel.add(setGridBtn);

	    c.gridy = 1;
	    c.weighty = 1;
	    sidePanel.add(sliderPanel, c);

	    // Color chooser
	    JColorChooser colorChooser = new JColorChooser(Color.gray);
	    AbstractColorChooserPanel swatchesPanel = colorChooser.getChooserPanels()[0];
	    JPanel holder = (JPanel) swatchesPanel.getComponent(0);
	    holder.remove(2);
	    holder.remove(1);

	    ColorSelectionModel model = colorChooser.getSelectionModel();
	    ChangeListener changeListener = new ChangeListener() {
	    	public void stateChanged(ChangeEvent changeEvent) {
	    		Color newColor = colorChooser.getColor();
	    		canvas.setColor(newColor);

	    	}
	    };
	    model.addChangeListener(changeListener);
	    c.gridy = 2;
	    c.weighty = 1;
	    sidePanel.add(swatchesPanel, c);

	    // Polygon list
	    JPanel polygonPanel = new JPanel();
	    polygonPanel.setBorder(new TitledBorder("Polygons"));

	    JList<String> polygonList = canvas.getPolygonList();

	    JScrollPane listScroller = new JScrollPane(polygonList);
	    listScroller.setPreferredSize(new Dimension(250, 120));
	    polygonPanel.add(listScroller);

	    JPanel buttons = new JPanel();
	    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

	    JButton newPolygonBtn = new JButton ("New Polygon");
	    newPolygonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addPolygon(new Polygon(canvas.gridSize, colorChooser.getColor()));
			}
		});
	    //polygonPanel.add(newPolygonBtn);
	    newPolygonBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    buttons.add(newPolygonBtn);
	    buttons.add(Box.createRigidArea(new Dimension(0, 10)));

	    JButton deletePolygonBtn = new JButton ("Delete Polygon");
	    deletePolygonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.deletePolygon(polygonList.getSelectedIndex());
			}
		});
	    //polygonPanel.add(deletePolygonBtn);
	    deletePolygonBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    buttons.add(deletePolygonBtn);
	    buttons.add(Box.createRigidArea(new Dimension(0, 10)));

	    JCheckBox labelsChb = new JCheckBox("Labels");
	    labelsChb.setAlignmentX(Component.LEFT_ALIGNMENT);
	    labelsChb.setSelected(canvas.drawLabels);
	    labelsChb.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent e) {
	    		canvas.drawLabels = labelsChb.isSelected();
	    		canvas.repaint();
	    	}
	    });
	    buttons.add(labelsChb);

	    polygonPanel.add(buttons);

	    c.gridy = 3;
	    c.weighty = 1;
		//c.weightx = 2;
	    sidePanel.add(polygonPanel, c);

	    // Draw button
	    JButton drawPolygonBtn = new JButton("Draw Polygon");
	    drawPolygonBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		canvas.drawPolygon(polygonList.getSelectedIndex());
	    	}
	    });
	    c.gridy = 4;
	    c.weighty = 20;
		//c.weightx = 2;
	    sidePanel.add(drawPolygonBtn, c);

	  	frame.add(sidePanel, BorderLayout.LINE_START);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	frame.setSize(700, 500);
	  	//frame.pack();
	  	frame.setLocationRelativeTo(null);
			frame.setVisible(true);
	}

	public static void main(String[] args) {
		setGUI();
	}
}
