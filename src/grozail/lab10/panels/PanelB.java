package grozail.lab10.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class PanelB extends JPanel implements MouseMotionListener, MouseListener{
	private JButton[] buttons;
	private Color newBackground = new Color(111,117,117);
	private Color background;
	private String buttonText;

	public PanelB() {
		setLayout(new GridLayout(3, 3));
		buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++) {
			JButton button = buttons[i];
			button = new JButton(String.valueOf(i));
			button.addMouseListener(this);
			button.addMouseMotionListener(this);
			add(button);
		}

	}


	public void mouseClicked(MouseEvent e) {

	}


	public void mousePressed(MouseEvent e) {
		JButton button = ((JButton) e.getSource());
		buttonText = button.getText();
		button.setText("Clicked");
	}


	public void mouseReleased(MouseEvent e) {
		JButton button = ((JButton) e.getSource());
		button.setText(buttonText);
	}


	public void mouseEntered(MouseEvent e) {
		((JButton) e.getSource()).setBackground(newBackground);
	}


	public void mouseExited(MouseEvent e) {
		((JButton) e.getSource()).setBackground(background);
	}


	public void mouseDragged(MouseEvent e) {

	}


	public void mouseMoved(MouseEvent e) {

	}
}
