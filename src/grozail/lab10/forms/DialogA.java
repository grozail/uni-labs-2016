package grozail.lab10.forms;

import grozail.lab10.panels.PanelA;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class DialogA extends JDialog {
	private final int DEFAULT_WIDTH = 400;
	private final int DEFAULT_HEIGHT = 300;

	private PanelA root;

	public DialogA(Frame owner) {
		super(owner, "Task A", true);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());

		root = new PanelA();
		add(root, BorderLayout.CENTER);
	}


}

