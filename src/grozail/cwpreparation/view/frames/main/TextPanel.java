package grozail.cwpreparation.view.frames.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 25.12.16.
 */
public class TextPanel extends JPanel {
	private JTextArea textArea;


	public TextPanel() {
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(500,400));
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}
