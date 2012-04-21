package in.solpro.nucleus.apps.common;

public enum ItemType{
	TAXABLE, EXEMPTED, TAX_FREE;
	private static final String TYPE_TAXABLE = "Taxable";
	private static final int TYPE_TAXABLE_CODE = 0;
	private static final String TYPE_EXEMPTED = "Exempted";
	private static final int TYPE_EXEMPTED_CODE = 1;
	private static final String TYPE_TAX_FREE = "Tax Free";
	private static final int TYPE_TAX_FREE_CODE = 2;
	public String toString() {
		switch (this) {
		case TAXABLE:
			return TYPE_TAXABLE;
		case EXEMPTED:
			return TYPE_EXEMPTED;
		case  TAX_FREE:
			return TYPE_TAX_FREE;
		}
		return TYPE_TAXABLE;
	}
	
	public int toInt() {
		switch (this) {
		case TAXABLE:
			return TYPE_TAXABLE_CODE;
		case EXEMPTED:
			return TYPE_EXEMPTED_CODE;
		case  TAX_FREE:
			return TYPE_TAX_FREE_CODE;
		}
		return TYPE_TAXABLE_CODE;
	}
	public static ItemType getItemType(int code){
		switch(code){
		case TYPE_TAXABLE_CODE:
			return TAXABLE;
		case TYPE_EXEMPTED_CODE:
			return EXEMPTED;
		case TYPE_TAX_FREE_CODE:
			return TAX_FREE;
		}
		return TAXABLE;
	}
}
