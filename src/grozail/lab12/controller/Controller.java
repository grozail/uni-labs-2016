package grozail.lab12.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import grozail.lab12.model.Medicine;
import grozail.lab12.model.MedicineDatabase;
import grozail.lab12.model.MedicineTableModel;
import grozail.lab12.model.Visual;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grozail
 * on 10.12.16.
 */
public class Controller {
	private MedicineDatabase medicineDatabase;
	private MedicineTableModel medicineTableModel;
	private ParseController parseController;
	private boolean documentCreated;
	private static Controller instance = new Controller();

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	private Controller() {
		medicineDatabase = new MedicineDatabase(new ArrayList<>());
		medicineTableModel = new MedicineTableModel();
		medicineTableModel.setData(medicineDatabase.getMedicines());
		parseController = new ParseController();
	}

	public void addMedicine(Medicine medicine) {
		medicineDatabase.add(medicine);
		medicineTableModel.fireTableDataChanged();
	}

	public MedicineTableModel getMedicineTableModel() {
		return medicineTableModel;
	}


	public MedicineDatabase getMedicineDatabase() {
		return medicineDatabase;
	}

	public void setMedicineDatabase(MedicineDatabase medicineDatabase) {
		this.medicineDatabase = medicineDatabase;
		medicineTableModel.setData(this.medicineDatabase.getMedicines());
		medicineTableModel.fireTableDataChanged();
	}

	public boolean createDocument(String xmlPath) {
		if (parseController.createDocument(xmlPath) == null) {
			documentCreated = false;
			return false;
		}
		documentCreated = true;
		return true;
	}

	public void addNewMedicineToDocument(Medicine toAdd) {
		if (!documentCreated) {
			System.out.println("DOCUMENT CREATION FAILURE!!!");
			return;
		}
		Document document = parseController.getDocument();
		Node root = document.getDocumentElement();
		Element medicine = document.createElement("medicine");
		Element name = document.createElement("name");
		name.setTextContent(toAdd.getName());
		Element price = document.createElement("price");
		price.setTextContent(String.valueOf(toAdd.getPrice()));
		Element dosage = document.createElement("dosage");
		dosage.setTextContent(String.valueOf(toAdd.getDosage()));
		Element visual = document.createElement("visual");
		Element color = document.createElement("color");
		color.setTextContent(toAdd.getVisual().getColor());
		Element body = document.createElement("body");
		body.setTextContent(toAdd.getVisual().getBody());
		Element indications = document.createElement("indications");
		indications.setTextContent(toAdd.getVisual().getIndications());
		visual.appendChild(color);
		visual.appendChild(body);
		visual.appendChild(indications);
		medicine.appendChild(name);
		medicine.appendChild(price);
		medicine.appendChild(dosage);
		medicine.appendChild(visual);
		root.appendChild(medicine);
		writeDocument();
	}

	private void writeDocument() {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource domSource = new DOMSource(parseController.getDocument());
			StreamResult result = new StreamResult(new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/add_medicine.xml"));
			transformer.transform(domSource, result);
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromXMLSource(String sourceXML) {
		if (!createDocument(sourceXML)) {
			return;
		}
		ArrayList<Medicine> newData = new ArrayList<>();
		Node root = parseController.getDocument().getDocumentElement();
		NodeList listMedicine = root.getChildNodes();
		for (int i = 0; i < listMedicine.getLength(); i++) {
			Node medicine = listMedicine.item(i);
			if (medicine.getNodeType() != Node.TEXT_NODE) {
				Medicine toAdd = new Medicine();
				NodeList properties = medicine.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {
					Node property = properties.item(j);
					if (property.getNodeType() != Node.TEXT_NODE) {
						switch (property.getNodeName()) {
							case "name":
								toAdd.setName(property.getTextContent());
								break;
							case "price":
								toAdd.setPrice(Double.valueOf(property.getTextContent()));
								break;
							case "dosage":
								toAdd.setDosage(Double.valueOf(property.getTextContent()));
								break;
							case "visual":
								Visual visual = new Visual();
								NodeList propertiesVisual = property.getChildNodes();
								for (int k = 0; k < propertiesVisual.getLength(); k++) {
									Node propertyVisual = propertiesVisual.item(k);
									if (propertyVisual.getNodeType() != Node.TEXT_NODE) {
										switch (propertyVisual.getNodeName()) {
											case "color":
												visual.setColor(propertyVisual.getTextContent());
												break;
											case "body":
												visual.setBody(propertyVisual.getTextContent());
												break;
											case "indications":
												visual.setIndications(propertyVisual.getTextContent()) ;
												break;
										}
									}
								}
								toAdd.setVisual(visual);
								break;
						}
					}
				}
				newData.add(toAdd);
			}
		}
		medicineDatabase.setMedicines(newData);
		medicineTableModel.setData(medicineDatabase.getMedicines());
		medicineTableModel.fireTableDataChanged();
	}
}
