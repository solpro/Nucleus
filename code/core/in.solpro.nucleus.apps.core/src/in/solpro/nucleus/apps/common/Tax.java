package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tax extends BaseObject{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    @Column(nullable=false)
	private String name;
	private String description;
	private double value;
	private TaxType type;
	private int ledger;
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

	public void setType(TaxType type) {
		this.type = type;
	}

	public TaxType getType() {
		return type;
	}
	
	public void setType(int type){
		TaxType typ = TaxType.getTaxType(type);
		if(typ!=null){
			this.type=typ;
		}else{
			this.type=TaxType.PERCENT;
		}
	}

	public void setLedger(int ledger) {
		this.ledger = ledger;
	}

	public int getLedger() {
		return ledger;
	}
}
