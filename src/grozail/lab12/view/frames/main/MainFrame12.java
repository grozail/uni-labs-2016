package grozail.lab12.view.frames.main;

import grozail.lab12.controller.Controller;
import grozail.lab12.controller.ParseController;
import grozail.lab12.model.Medicine;
import grozail.lab12.model.MedicineDatabase;
import grozail.lab12.model.MedicineFactory;
import grozail.lab12.util.JAXBParser;
import grozail.lab12.view.frames.main.panels.TablePanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class MainFrame12 extends JFrame {
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 800;
	private static final Dimension defaultAppDimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private TablePanel tablePanel;
	private Controller controller;
	private static final Comparator<Medicine> DEFAULT_COMPARATOR = new Comparator<Medicine>() {
		public int compare(Medicine o1, Medicine o2) {
			return o1.compareTo(o2);
		}
	};
	public MainFrame12() {
		super("Lab 12");
		controller = Controller.getInstance();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setPreferredSize(defaultAppDimension);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());
		setJMenuBar(createMenuBar());
		tablePanel = new TablePanel();
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);
		setVisible(true);
	}
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		//FILE MENU
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenuItem itemAddMedicine = new JMenuItem("Add medicine");
		JMenuItem itemSerialize = new JMenuItem("Serialize...");
		JMenuItem itemDeserialize = new JMenuItem("Deserialize...");
		JMenuItem itemCheckXSD = new JMenuItem("Check XSD");
		JMenuItem itemCheckDTD = new JMenuItem("Check DTD");
		JMenuItem itemTransformToHTML = new JMenuItem("Transform to HTML");
		JMenuItem itemAddMedicineToXML = new JMenuItem("Add Medicine to XML");
		JMenuItem itemParseDataFromXML = new JMenuItem("Parse Data From XML");
		menuFile.add(itemAddMedicine);
		menuFile.addSeparator();
		menuFile.add(itemSerialize);
		menuFile.add(itemDeserialize);
		menuFile.addSeparator();
		menuFile.add(itemCheckXSD);
		menuFile.add(itemCheckDTD);
		menuFile.addSeparator();
		menuFile.add(itemTransformToHTML);
		menuFile.addSeparator();
		menuFile.add(itemAddMedicineToXML);
		menuFile.add(itemParseDataFromXML);

		{
			//file->add medicine
			itemAddMedicine.setMnemonic(KeyEvent.VK_A);
			itemAddMedicine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
			itemAddMedicine.addActionListener(e -> {
				controller.addMedicine(MedicineFactory.create());
			});
			//file->serialize
			itemSerialize.setMnemonic(KeyEvent.VK_S);
			itemSerialize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			itemSerialize.addActionListener(e -> {
				Collections.sort(controller.getMedicineDatabase().getMedicines(), DEFAULT_COMPARATOR);
				try {
					JAXBParser.saveObject(new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine.xml"), controller.getMedicineDatabase());
					JOptionPane.showMessageDialog(MainFrame12.this,"Sussessfully saved!","Congrats!", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (JAXBException e1) {
					e1.printStackTrace();
				}
			});
			//file->deserialize
			itemDeserialize.setMnemonic(KeyEvent.VK_O);
			itemDeserialize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
			itemDeserialize.addActionListener(e ->  {
				try {
					MedicineDatabase medicineDatabase = (MedicineDatabase) JAXBParser.getObject(new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine.xml"), MedicineDatabase.class);
					controller.setMedicineDatabase(medicineDatabase);
					JOptionPane.showMessageDialog(MainFrame12.this,"Sussessfully loaded!","Congrats!", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (JAXBException e1) {
					e1.printStackTrace();
				}
			});
			//file->checkXSD
			itemCheckXSD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.SHIFT_MASK));
			itemCheckXSD.addActionListener(e -> {
				String answer = ParseController.validateXMLSchema("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine.xsd", "/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_xsd.xml");
				JOptionPane.showMessageDialog(MainFrame12.this, answer, "Validation cheched!", JOptionPane.INFORMATION_MESSAGE);
			});
			//file->checkDTD
			itemCheckDTD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK));
			itemCheckDTD.addActionListener(e -> {
				String answer = ParseController.validateXMLDTD("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine.dtd", "/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_dtd.xml");
				JOptionPane.showMessageDialog(MainFrame12.this, answer, "Validation cheched!", JOptionPane.INFORMATION_MESSAGE);
			});
			//file->transformToHTML
			itemTransformToHTML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.SHIFT_MASK));
			itemTransformToHTML.addActionListener(e -> {
				String answer = ParseController.transformToHTML("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_transform.xsl", "/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_xsd.xml");
				JOptionPane.showMessageDialog(MainFrame12.this, answer, "Transformation info", JOptionPane.INFORMATION_MESSAGE);
			});
			//file->addMedicineToXML
			itemAddMedicineToXML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
			itemAddMedicineToXML.addActionListener(e -> {
				controller.createDocument("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_xsd.xml");
				controller.addNewMedicineToDocument(MedicineFactory.create());
				JOptionPane.showMessageDialog(MainFrame12.this, "See results in: /opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/add_medicine.xml", "Add to XML info", JOptionPane.INFORMATION_MESSAGE);

			});
			//file->parceDataFromXML
			itemParseDataFromXML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
			itemParseDataFromXML.addActionListener(e -> {
				controller.getDataFromXMLSource("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/add_medicine.xml");
				JOptionPane.showMessageDialog(MainFrame12.this, "Tried to load", "Load info", JOptionPane.INFORMATION_MESSAGE);
			});
		}
		menuBar.add(menuFile);
		return menuBar;
	}
}
