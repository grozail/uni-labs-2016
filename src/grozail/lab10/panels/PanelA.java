package grozail.lab10.panels;

import grozail.lab10.util.Message;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class PanelA extends JPanel {
	private JLabel statusLabel;
	private JPanel centralPanel;
	private Message message;
	private Rectangle2D textBounds;
	private String messageText;
	private boolean pressOut = false;
	private JPanel statusPanel;

	public PanelA() {
		message = new Message();
		messageText = message.toString();
		setLayout(new BorderLayout());
		statusLabel = new JLabel("Ready");
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.add(statusLabel);
		add(statusPanel, BorderLayout.SOUTH);

		addMouseMotionListener(new MouseHandler());
		addMouseListener(new MouseHandler());
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				message.getMessage().append(e.getKeyChar());
				repaint();
			}

			public void keyReleased(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//					message.getMessage().delete(message.getMessage().length() - 1, message.getMessage().length());
//				}
//				else {
//					message.getMessage().append(((char) e.getKeyCode()));
//				}
//				repaint();
			}
		});
		setFocusTraversalKeysEnabled(false);
		System.out.println(isFocusOwner());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(message.getMessage().toString(), message.getX(), message.getY());
		textBounds = g.getFontMetrics().getStringBounds(messageText, g);
		textBounds.setFrame(message.getX(), message.getY() - textBounds.getHeight(), textBounds.getWidth(), textBounds.getHeight());
		((Graphics2D) g).draw(textBounds);
	}

	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.isControlDown()) {
				if (textBounds.contains(e.getX(), e.getY())) {
					updateLocation(e);
					pressOut = false;
				}
			}
			else {
				pressOut = true;
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (textBounds.contains(e.getX(), e.getY())) {
				if (!pressOut) {
					updateLocation(e);
				}
			}
		}

		public void mouseClicked(MouseEvent e) {
			requestFocusInWindow();
			if (e.getClickCount() == 2) {
				updateLocation(e);
			}
		}

		public void mouseDragged(MouseEvent e) {
			statusLabel.setText(e.getX() + " : " + e.getY());
			if (e.getX() <= PanelA.this.getWidth() - textBounds.getWidth() &&
					e.getX() > 0 &&
					e.getY() > textBounds.getHeight() &&
					e.getY() <= PanelA.this.getHeight() - PanelA.this.statusPanel.getHeight()) {
				if (!pressOut) {
					updateLocation(e);
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(e.getX() + " : " + e.getY());
			if (textBounds.contains(e.getX(), e.getY())) {
				statusLabel.setText("contains");
			}
		}

		private void updateLocation(MouseEvent e) {
			message.setX(e.getX());
			message.setY(e.getY());
			repaint();
		}
	}


}
