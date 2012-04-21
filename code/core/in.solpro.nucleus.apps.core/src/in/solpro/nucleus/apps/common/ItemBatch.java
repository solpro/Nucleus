package in.solpro.nucleus.apps.common;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemBatch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Item objItem = null;
	@Column(nullable=false)
	private String name;
	private Date expirydate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}*/

	public Item getObjItem() {
		return objItem;
	}

	public void setObjItem(Item item) {
		this.objItem = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
}

