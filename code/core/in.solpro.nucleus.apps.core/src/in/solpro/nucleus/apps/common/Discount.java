package in.solpro.nucleus.apps.common;

public class Discount {
	protected DiscountType type;
	protected double value;

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}

