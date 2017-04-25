package grozail.lab8.main;

import grozail.lab8.forms.main.MainFrame8;

import javax.swing.*;

/**
 * Created by grozail on 18.11.16.
 * lab8
 */
public class Lab8 {
	public static void run() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame8();
			}
		});
	}
}
