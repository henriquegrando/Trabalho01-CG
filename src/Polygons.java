import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.colorchooser.*;
import javax.swing.border.*;
import java.util.*;

public class Polygons {	
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
		c.weightx = 2;
		c.gridx = 0;
		c.gridy = 0;
		sidePanel.add(clearBtn, c);
		
		// Slider panel
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new TitledBorder("Grid"));
        
        JSlider slider = new JSlider(10, 60, 20);
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
        c.weightx = 2;
        sidePanel.add(sliderPanel, c);
        
        // Color chooser
        JColorChooser colorChooser = new JColorChooser();
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
        c.weightx = 1;
        sidePanel.add(swatchesPanel, c);
        
        // Polygon list
        JPanel polygonPanel = new JPanel();
        polygonPanel.setBorder(new TitledBorder("Polygons"));
        
        JList<String> polygonList = canvas.getPolygonList();
        
        JScrollPane listScroller = new JScrollPane(polygonList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        polygonPanel.add(listScroller);
        
        JButton newPolygonBtn = new JButton ("New Polygon");
        newPolygonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addPolygon(new Polygon(colorChooser.getColor()));
			}
		});
        polygonPanel.add(newPolygonBtn);
        
        c.gridy = 3;
        c.weighty = 1;
		c.weightx = 2;
        sidePanel.add(polygonPanel, c);
        
	    frame.add(sidePanel, BorderLayout.LINE_START);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(360, 300);
	    //frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		setGUI();
	}		
}