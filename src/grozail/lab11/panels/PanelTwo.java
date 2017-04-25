package grozail.lab11.panels;

import javax.swing.*;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class PanelTwo extends JPanel {
	private JRadioButton radioButton1, radioButton2;
	private ButtonGroup buttonGroup;
	private Dimension buttonSize = new Dimension(100, 50);
	private JPanel radioPanel;
	public PanelTwo() {
		setLayout(new BorderLayout());
		buttonGroup = new ButtonGroup();
		{
			radioButton1 = new JRadioButton("Button One");
			radioButton1.setPreferredSize(buttonSize);
			radioButton1.setMnemonic(KeyEvent.VK_Q);
			radioButton1.setSelected(true);
			radioButton1.setIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/not_selected.gif"));
			radioButton1.setSelectedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/selected.gif"));
			radioButton1.setPressedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered_and_pressed.gif"));
			radioButton1.setRolloverSelectedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered.gif"));
			radioButton1.setRolloverIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered.gif"));
			buttonGroup.add(radioButton1);

			radioButton2 = new JRadioButton("Button Two");
			radioButton2.setPreferredSize(buttonSize);
			radioButton2.setMnemonic(KeyEvent.VK_W);
			radioButton2.setIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/not_selected.gif"));
			radioButton2.setSelectedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/selected.gif"));
			radioButton2.setPressedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered_and_pressed.gif"));
			radioButton2.setRolloverSelectedIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered.gif"));
			radioButton2.setRolloverIcon(new ImageIcon("/opt/!ProjectsJava/UniLabs/src/grozail/lab11/images/entered.gif"));
			buttonGroup.add(radioButton2);
		}

		radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(radioButton1);
		radioPanel.add(radioButton2);
		add(radioPanel, BorderLayout.CENTER);
	}

}
