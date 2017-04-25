package grozail.lab11.main;

import grozail.lab11.frames.MainFrame11;

import javax.swing.*;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class Lab11 {
	public static void run() {
		SwingUtilities.invokeLater(MainFrame11::new);
	}
}