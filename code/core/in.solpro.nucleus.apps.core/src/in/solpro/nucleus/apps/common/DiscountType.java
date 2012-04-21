package in.solpro.nucleus.apps.common;

public enum DiscountType {
	FIXED, PERCENT;
	private static final String TX_FIXED_NAME = "Fixed";
	private static final int TX_FIXED_CODE = 0;
	private static final String TX_PERCENT_NAME = "Percent";
	private static final int TX_PERCENT_CODE = 1;

	public String toString() {
		switch (this) {
		case FIXED:
			return TX_FIXED_NAME;
		case PERCENT:
			return TX_PERCENT_NAME;
		}
		return TX_FIXED_NAME;
	}

	public int toInt() {
		switch (this) {
		case FIXED:
			return TX_FIXED_CODE;
		case PERCENT:
			return TX_PERCENT_CODE;
		}
		return TX_FIXED_CODE;
	}
	
	public static DiscountType getDiscountType(int code){
		switch(code){
		case TX_FIXED_CODE:
			return FIXED;
		case TX_PERCENT_CODE:
			return PERCENT;
		}
		return FIXED;
	}
	
	public static DiscountType getDiscountTypefromString(String str){
		if(str.equals(TX_FIXED_NAME))
			return FIXED;
		if(str.equals(TX_PERCENT_NAME))
			return PERCENT;
		return FIXED;
	}
}
