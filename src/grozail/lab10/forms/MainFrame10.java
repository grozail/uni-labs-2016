package grozail.lab10.forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class MainFrame10 extends JFrame {
	private final int DEFAULT_WIDTH = 300;
	private final int DEFAULT_HEIGHT = 200;

	private JButton invokeDialogA;
	private JButton invokeDialogB;
	private DialogA dialogA;
	private DialogB dialogB;

	public MainFrame10() {
		super("Lab 10");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Dimension appDim = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setPreferredSize(appDim);
		setMinimumSize(appDim);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		//BUTTONS
		invokeDialogA = new JButton("A");
		invokeDialogA.addActionListener(e -> {
			if (dialogA == null) {
				dialogA = new DialogA(MainFrame10.this);
			}
			dialogA.setVisible(true);
		});
		invokeDialogB = new JButton("B");
		invokeDialogB.addActionListener(e -> {
			if (dialogB == null) {
				dialogB = new DialogB(MainFrame10.this);
			}
			dialogB.setVisible(true);
		});
		JPanel root = new JPanel(new BorderLayout());
		JPanel forButtons = new JPanel();
		forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.Y_AXIS));
		forButtons.add(Box.createRigidArea(new Dimension((getWidth() / 2 - 85), 25)));
		root.add(forButtons, BorderLayout.CENTER);
		add(root);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		forButtons.add(invokeDialogA);
		forButtons.add(invokeDialogB);
		//-------
		setVisible(true);

	}
}
