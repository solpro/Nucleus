package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


    @Entity
	public class StockLocation extends BaseObject{
		@Id
    	@GeneratedValue(strategy=GenerationType.IDENTITY)
    	private int id;
		@Column(nullable=false)
		private String name;
		private String description;
		private StockLocationType objType = null;

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

		
		public StockLocationType getObjType() {
			return objType;
		}

		public void setObjType(StockLocationType stocklocationtype) {
			this.objType = stocklocationtype;
		}

		public String toString() {
			return this.name;
		}
	}


