package grozail.lab9.forms.main.panels;


import grozail.lab9.drawing.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by grozail
 * on 3.12.16.
 */
public class CanvasPanel extends JPanel {
	private ArrayList<Line> objects;
	private Line current;
	BufferedImage image;
	private final int DEFAULT_WIDTH = 800;
	private final int DEFAULT_HEIGHT = 600;
	private Color penColor = Color.BLACK;
	private int penWidth = 1;

	public CanvasPanel() {
		current = new Line(penColor);
		objects = new ArrayList<>();
		objects.add(current);
		image = null;
		addMouseMotionListener(new MouseMotionHandler());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2D = (Graphics2D) g;
		if (image != null) {
			graphics2D.drawImage(image, 0, 0, null);
		}
		for (Line object : objects) {
			object.draw(graphics2D);
		}
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
		if (current.size() != 0) {
			current = new Line(penColor);
			objects.add(current);
		}
		else {
			current.setColor(penColor);
		}
	}

	public int getPenWidth() {
		return penWidth;
	}

	public void setPenWidth(int penWidth) {
		this.penWidth = penWidth;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		objects.clear();
		current.clear();
		objects.add(current);
		repaint();
	}


	public Dimension getPreferredSize() {
		if (image == null) {
			return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		}
		return new Dimension(image.getWidth(), image.getHeight());
	}

	private class MouseMotionHandler implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Ellipse2D.Float ellipse2D = new Ellipse2D.Float(x - penWidth / 2, y - penWidth / 2, penWidth + 1, penWidth + 1);
			current.add(ellipse2D);
			repaint();
		}

		public void mouseMoved(MouseEvent e) {

		}
	}

}
