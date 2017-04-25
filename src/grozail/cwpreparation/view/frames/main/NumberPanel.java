package grozail.cwpreparation.view.frames.main;

import grozail.cwpreparation.controller.Controller;
import grozail.cwpreparation.model.longnumber.LongNumber;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by grozail
 * on 27.12.16.
 */
public class NumberPanel extends JPanel {
	private static Random random = new Random();
	private Controller controller;
	private Color[] colors;
	private JLabel labelNumber;
	public NumberPanel() {
		controller = Controller.getInstance();
		colors = formRandomColors();
		setLayout(new BorderLayout());
		labelNumber = new JLabel();
		add(labelNumber, BorderLayout.CENTER);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		LongNumber longNumber = controller.getLongNumber();
		if (longNumber != null) {
			labelNumber.setText(longNumber.coloredNumber());
		}
	}

	private Color[] formRandomColors() {
		Random random = new Random();
		Color[] colors = new Color[10];
		for (int i = 0; i < colors.length; i++) {
			Color color = colors[i];
			color = new Color(Math.abs(random.nextInt(255)), Math.abs(random.nextInt(255)), Math.abs(random.nextInt(255)));
		}
		return colors;
	}
}
