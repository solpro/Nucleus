package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemTax extends BaseObject{
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private String name;
	private String description;
	private double value;
	private Ledger ledger;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String toString() {
		return this.name;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

	public Ledger getLedger() {
		return ledger;
	}
}

