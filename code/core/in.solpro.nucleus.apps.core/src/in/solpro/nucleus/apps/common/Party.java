package in.solpro.nucleus.apps.common;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Party extends Ledger{
		private static int CUSTOMER = 1;
		private static int SUPPLIER = 2;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private int type;
		private String pan;
		private String tan;
		private String vat;
		private String tot;
		private String servicetax;
	//	private Vector<PartyAddress> addressList = new Vector<PartyAddress>();
		private double drlimit;
		private double crlimit;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getPan() {
			return pan;
		}

		public void setPan(String pan) {
			this.pan = pan;
		}

		public String getTan() {
			return tan;
		}

		public void setTan(String tan) {
			this.tan = tan;
		}

		public String getVat() {
			return vat;
		}

		public void setVat(String vat) {
			this.vat = vat;
		}

		public String getTot() {
			return tot;
		}

		public void setTot(String tot) {
			this.tot = tot;
		}

		public String getServicetax() {
			return servicetax;
		}

		public void setServicetax(String servicetax) {
			this.servicetax = servicetax;
		}

		public String toString() {
			return this.name;
		}

/*		public void setAddressList(Vector<PartyAddress> addressList) {
			this.addressList = addressList;
		}

		public Vector<PartyAddress> getAddressList() {
			if (addressList == null) {
				try {
					addressList = PartyAddressHelper.getPartyaddressList(ledgerId);
				} catch (Exception e) {

				}
				if (addressList == null) {
					addressList = new Vector<PartyAddress>();
				}
			}
			return addressList;
		}
*/
		public boolean isCustomer() {
			if ((type & CUSTOMER) != 0) {
				return true;
			}
			return false;
		}

		public boolean isSupplier() {
			if ((type & SUPPLIER) != 0) {
				return true;
			}
			return false;
		}

		public void setCustomer(boolean value) {
			if(value){
				type = type | CUSTOMER;
			}else{
				type = type & (~CUSTOMER);
			}
		}

		public void setSupplier(boolean value) {
			if(value){
				type = type | SUPPLIER;
			}else{
				type = type & (~SUPPLIER);
			}
		}

		public void setDrlimit(double drlimit) {
			this.drlimit = drlimit;
		}

		public double getDrlimit() {
			return drlimit;
		}

		public void setCrlimit(double crlimit) {
			this.crlimit = crlimit;
		}

		public double getCrlimit() {
			return crlimit;
		}
		
		public String getTypeLabel()
		{
		    if (isCustomer() && !isSupplier())
		    {
		        return "Customer";
		    }
	        if (!isCustomer() && isSupplier())
	        {
	            return "Supplier";
	        }
	        if (isCustomer() && isSupplier())
	        {
	            return "Customer & Supplier";
	        }
	        return null;
		}
	}

	
