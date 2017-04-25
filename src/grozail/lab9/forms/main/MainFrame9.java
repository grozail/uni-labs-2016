package grozail.lab9.forms.main;

import grozail.lab9.drawing.Line;
import grozail.lab9.forms.main.panels.CanvasPanel;
import grozail.lab9.util.ImageFileFilter;
import sun.awt.image.PNGImageDecoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by grozail
 * on 3.12.16.
 */
public class MainFrame9 extends JFrame {
	private CanvasPanel canvasPanel;
	private JFileChooser fileChooser;
	private static File workingDir = new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab9/input");

	public MainFrame9() {
		super("Lab 9");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeigth = screenSize.height;
		setSize(screenWidth / 2, screenHeigth / 2);
		setMinimumSize(new Dimension(500, 400));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame9.this, "Do you really want to exit?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
				setVisible(true);
			}
		});
		//ENABLE FILE CHOOSER
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", ImageIO.getReaderFileSuffixes()));
		fileChooser.setCurrentDirectory(workingDir);
		//SET MENU
		setJMenuBar(createMenuBar());
		//  CANVAS PANEL
		canvasPanel = new CanvasPanel();
		add(new JScrollPane(canvasPanel), BorderLayout.CENTER);

		setVisible(true);
	}
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		//FILE MENU
		JMenu menuFile = new JMenu("File");
		JMenuItem itemOpenImage = new JMenuItem("Open Image...");
		JMenuItem itemSaveImage = new JMenuItem("Save Image...");
		JMenuItem itemExit = new JMenuItem("Exit");
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.add(itemOpenImage);
		menuFile.add(itemSaveImage);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		//file open
		itemOpenImage.setMnemonic(KeyEvent.VK_O);
		itemOpenImage.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		itemOpenImage.addActionListener(e -> {
			if (fileChooser.showOpenDialog(MainFrame9.this) == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedImage image = ImageIO.read(fileChooser.getSelectedFile());
					canvasPanel.setImage(image);
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		//file save
		itemSaveImage.setMnemonic(KeyEvent.VK_S);
		itemSaveImage.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		itemSaveImage.addActionListener(e -> {
			if (fileChooser.showSaveDialog(MainFrame9.this) == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedImage image = new BufferedImage(canvasPanel.getWidth(), canvasPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
					canvasPanel.paint(image.getGraphics());
					ImageIO.write(image, "jpg", fileChooser.getSelectedFile());
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		//file exit
		itemExit.setMnemonic(KeyEvent.VK_X);
		itemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		itemExit.addActionListener(e -> {
			int action = JOptionPane.showConfirmDialog(MainFrame9.this, "Do you really want to exit the application?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
			if (action == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		});

		//WINDOW MENU
		JMenu menuWindow = new JMenu("Window");
		JMenuItem itemChooseColor = new JMenuItem("Choose Color");
		JMenuItem itemChooseWidth = new JMenuItem("Choose Width");
		menuWindow.add(itemChooseColor);
		menuWindow.add(itemChooseWidth);
		//window choose_color
		itemChooseColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		itemChooseColor.addActionListener(e -> {
			Color newColor = JColorChooser.showDialog(null, "Choose Pen Color", canvasPanel.getPenColor());
			if (newColor != null) {
				canvasPanel.setPenColor(newColor);
			}

		});
		//window choose_width
		itemChooseWidth.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		itemChooseWidth.addActionListener(e -> {
			String newWidth = JOptionPane.showInputDialog(MainFrame9.this, "Enter new width:", "Choose width", JOptionPane.QUESTION_MESSAGE);
			if (newWidth != null) {
				int penWidth = Math.abs(Integer.valueOf(newWidth));
				if (penWidth == 0) {
					penWidth = 1;
				}
				canvasPanel.setPenWidth(penWidth);
			}
		});

		//ADDING TO BAR
		menuBar.add(menuFile);
		menuBar.add(menuWindow);
		return menuBar;
	}
}
