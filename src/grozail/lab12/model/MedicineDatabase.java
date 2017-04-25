package grozail.lab12.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grozail
 * on 10.12.16.
 */
@XmlRootElement(name = "medicine_list")
public class MedicineDatabase {
	private List<Medicine> medicines;

	public MedicineDatabase() {
	}

	public MedicineDatabase(List<Medicine> medicines){
		this.medicines = medicines;
	}

	public void add(Medicine medicine) {
		medicines.add(medicine);
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	@XmlElement(name = "medicine")
	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
}
