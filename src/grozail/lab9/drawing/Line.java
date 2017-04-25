package grozail.lab9.drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class Line {
	private List<Ellipse2D.Float> line;
	private Color color;
	public Line(Color color) {
		line = new ArrayList<>();
		this.color = color;
	}

	public void add(Ellipse2D.Float ellipse) {
		line.add(ellipse);
	}

	public void draw(Graphics2D graphics2D) {
		graphics2D.setPaint(color);
		line.forEach(graphics2D::fill);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int size() {
		return line.size();
	}

	public void clear() {
		line.clear();
	}
}
