package grozail.lab12.run;

import grozail.lab12.view.frames.main.MainFrame12;

import javax.swing.*;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class Lab12 {
	public static void run() {
		SwingUtilities.invokeLater(MainFrame12::new);
	}
}
