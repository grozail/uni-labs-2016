package grozail.lab8.forms.main;

import grozail.lab8.data.Database;
import grozail.lab8.data.Student;
import grozail.lab8.forms.main.panels.table.TablePanel;
import grozail.lab8.util.StudentFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by grozail on 19.11.16.
 * mainframe
 */
public class MainFrame8 extends JFrame {
	private Database database;
	private JFileChooser fileChooser;
	private TablePanel tablePanel;
	private static File workingDir = new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab8");

	public MainFrame8() {
		super("Lab 8");
		database = new Database();
		setSize(800, 600);
		setMinimumSize(new Dimension(500, 400));
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setJMenuBar(createMenuBar());

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new StudentFileFilter());

		//TABLE PANEL
		tablePanel = new TablePanel();
		tablePanel.setData(database.getData());
		add(tablePanel, BorderLayout.CENTER);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		//FILE MENU
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(importDataItem);
		fileMenu.add(exportDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		//window import
		importDataItem.setMnemonic(KeyEvent.VK_I);
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(workingDir);
				if (fileChooser.showOpenDialog(MainFrame8.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					File last = file.getParentFile();
					if (last != null) {
						fileChooser.setCurrentDirectory(last);
					}
					try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							List<Student> students = Student.tryParse(line);
							students.forEach(student -> {
								if (!database.getData().contains(student)) {
									database.add(student);
								}
							});
						}
						Collections.sort(database.getData(), Student.getNameComparator());
						tablePanel.refresh();
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		//window export
		exportDataItem.setMnemonic(KeyEvent.VK_E);
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame8.this) == JFileChooser.APPROVE_OPTION) {
					//TODO action on export

				}
			}
		});

		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame8.this, "Do you really want to exit the application?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		//WINDOW MENU
		JMenu windowMenu = new JMenu("Window");
		JMenuItem returnData = new JMenuItem("Return data");
		JMenu searchMenu = new JMenu("Search");
		JMenuItem searchId = new JMenuItem("Id");
		JMenuItem searchSurname = new JMenuItem("Surname");
		JMenuItem searchCourse = new JMenuItem("Cource");
		JMenuItem searchGroup = new JMenuItem("Group");
		JMenuItem searchCourceAndGroup = new JMenuItem("Cource and group");
		windowMenu.add(returnData);
		windowMenu.add(searchMenu);
		searchMenu.add(searchId);
		searchMenu.add(searchSurname);
		searchMenu.add(searchCourse);
		searchMenu.add(searchGroup);
		searchMenu.add(searchCourceAndGroup);
		searchId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(MainFrame8.this, "Enter id:", "Find ID", JOptionPane.INFORMATION_MESSAGE);
				if (id != null) {
					StringBuilder text = new StringBuilder();
					database.getData().stream().filter(s -> s.getId() == Integer.valueOf(id)).forEach(s -> {
						text.append(s.toString());
					});
					if (text.length() > 1) {
						JOptionPane.showMessageDialog(MainFrame8.this, text.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame8.this, "Not found", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		searchSurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String surname = JOptionPane.showInputDialog(MainFrame8.this, "Enter surname:", "Find surname", JOptionPane.INFORMATION_MESSAGE);
				if (surname != null) {
					StringBuilder text = new StringBuilder();
					database.getData().stream().filter(s -> s.getSurname().equals(surname)).forEach(s -> {
						text.append(s.toString());
					});
					if (text.length() > 1) {
						JOptionPane.showMessageDialog(MainFrame8.this, text.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame8.this, "Not found", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		searchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cource = JOptionPane.showInputDialog(MainFrame8.this, "Enter cource:", "Find cource", JOptionPane.INFORMATION_MESSAGE);
				if (cource != null) {
					StringBuilder text = new StringBuilder();
					database.getData().stream().filter(s -> s.getCource() == Byte.valueOf(cource)).forEach(s -> {
						text.append(s.toString());
					});
					if (text.length() > 1) {
						JOptionPane.showMessageDialog(MainFrame8.this, text.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame8.this, "Not found", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		searchGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String group = JOptionPane.showInputDialog(MainFrame8.this, "Enter group:", "Find group", JOptionPane.INFORMATION_MESSAGE);
				if (group != null) {
					StringBuilder text = new StringBuilder();
					database.getData().stream().filter(s -> s.getGroup() == Byte.valueOf(group)).forEach(s -> {
						text.append(s.toString());
					});
					if (text.length() > 1) {
						JOptionPane.showMessageDialog(MainFrame8.this, text.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame8.this, "Not found", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		searchCourceAndGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(MainFrame8.this, "Enter cource+group:", "Find cource and group", JOptionPane.INFORMATION_MESSAGE);
				if (input != null) {
					StringBuilder text = new StringBuilder(input.replaceAll("\\s", ""));
					String cource = text.substring(0, text.indexOf("+"));
					String group = text.substring(text.indexOf("+") + 1, text.length());
					if (cource != null && group != null) {
						database.getData().stream().filter(s -> s.getCource() == Byte.valueOf(cource) && s.getGroup() == Byte.valueOf(group)).forEach(s -> {
							text.append(s.toString());
						});
						if (text.length() > 1) {
							JOptionPane.showMessageDialog(MainFrame8.this, text.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(MainFrame8.this, "Not found", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(MainFrame8.this, "Wrong input", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		return menuBar;
	}
}
