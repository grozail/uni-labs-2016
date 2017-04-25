package grozail.lab12.controller;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by grozail
 * on 12.12.16.
 */
public class ParseController {
	private Document document;

	public ParseController() {

	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Document createDocument(String xmlPath) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}

				public void error(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}

				public void fatalError(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}
			});
			document = documentBuilder.parse(xmlPath);
			return document;
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String validateXMLSchema(String xsdPath, String xmlPath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		}
		catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return "File not valid at " + xmlPath + "\nSchema: " + xsdPath;
		}
		return "File valid at " + xmlPath + "\nSchema: " + xsdPath;

	}

	public static String validateXMLDTD(String dtdPath, String xmlPath) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}

				public void error(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}

				public void fatalError(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}
			});
			Document document = documentBuilder.parse(xmlPath);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return "File not valid at " + xmlPath + "\nSchema: " + dtdPath;
		}
		return "File valid at " + xmlPath + "\nSchema: " + dtdPath;

	}

	public static String transformToHTML(String xslPath, String xmlPath) {
		try {
			InputStream xsl = new FileInputStream(xslPath);
			InputStream xml = new FileInputStream(xmlPath);
			StreamSource sourceXSL = new StreamSource(xsl);
			StreamSource sourceXML = new StreamSource(xml);
			StreamResult outputHTML = new StreamResult(new File("/opt/!ProjectsJava/UniLabs/src/grozail/lab12/source/medicine_html.html"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer(sourceXSL);
			transformer.transform(sourceXML, outputHTML);
		}
		catch (TransformerException | FileNotFoundException e) {
			e.printStackTrace();
			return "Unable to transform " + xmlPath + "\nwith\n" + xslPath;
		}
		return "Successfully transformed " + xmlPath + "\nwith\n" + xslPath;
	}
}
