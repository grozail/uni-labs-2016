package grozail.lab10.forms;

import grozail.lab10.panels.PanelB;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class DialogB extends JDialog {
	private final int DEFAULT_WIDTH = 400;
	private final int DEFAULT_HEIGHT = 300;
	PanelB root;
	public DialogB(Frame owner) {
		super(owner, "Task B", true);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());

		root = new PanelB();
		add(root, BorderLayout.CENTER);
	}
}
