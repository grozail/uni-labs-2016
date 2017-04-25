package grozail.lab12.model;

import javax.xml.bind.annotation.*;
import java.util.Comparator;

/**
 * Created by grozail
 * on 10.12.16.
 */
@XmlRootElement(name = "medicine")
@XmlType(propOrder = {"name","price","dosage","visual"})
public class Medicine implements Comparable {
	private String name;
	private double price;
	private double dosage;
	private Visual visual;

	public Medicine() {

	}

	public Medicine(String name, double price, double dosage, Visual visual) {
		this.name = name;
		this.price = price;
		this.dosage = dosage;
		this.visual = visual;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public double getDosage() {
		return dosage;
	}

	@XmlElement
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}

	public Visual getVisual() {
		return visual;
	}

	@XmlElement
	public void setVisual(Visual visual) {
		this.visual = visual;
	}

	public int compareTo(Object o) {
		Medicine obj = ((Medicine) o);
		return price < obj.price ? -1 : price > obj.price ? 1 : 0;
	}

	public String toString() {
		return "Name: " + name + "\nPrice: " + price + "\nDosage (mg/day): " + dosage + "\nColor and Body: " + visual.getColor() + " | " + visual.getBody() + "\nIndications: " + visual.getIndications() + "\n";
	}

	public boolean equals(Object o) {
		if (o instanceof Medicine) {
			Medicine obj = ((Medicine) o);
			return name.equals(obj.name) && price == obj.price && dosage == obj.dosage;
		}
		return false;
	}
}