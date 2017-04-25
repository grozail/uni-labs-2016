package grozail.lab12.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by grozail
 * on 10.12.16.
 */
@XmlRootElement(name = "visual")
@XmlType(propOrder = {"color", "body", "indications"})
public class Visual {
	private String color;
	private String body;
	private String indications;

	public Visual() {

	}

	public Visual(String color, String body, String indications) {
		this.color = color;
		this.body = body;
		this.indications = indications;
	}

	public String getColor() {
		return color;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	public String getBody() {
		return body;
	}

	@XmlElement
	public void setBody(String body) {
		this.body = body;
	}

	public String getIndications() {
		return indications;
	}

	@XmlElement
	public void setIndications(String indications) {
		this.indications = indications;
	}
}
