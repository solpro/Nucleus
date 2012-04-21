package in.solpro.nucleus.apps.common;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemDiscount extends Discount{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int item;
	private Item objItem;
	private Date datefrom;
	private Date dateto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Date getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}

	public Date getDateto() {
		return dateto;
	}

	public void setDateto(Date dateto) {
		this.dateto = dateto;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public Item getObjItem() {
		return objItem;
	}

	public void setObjItem(Item item) {
		this.objItem = item;
	}

}

