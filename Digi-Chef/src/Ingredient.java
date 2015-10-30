
public class Ingredient 
{
	private String name;
	private double quantity;
	private UnitType units;
	
	public Ingredient(String name, double quantity, UnitType units) {
		this.name = name;
		this.quantity = quantity;
		this.units = units;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}
	/**
	 * @return the units
	 */
	public UnitType getUnits() {
		return units;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(UnitType units) {
		this.units = units;
	}

	public String getQtyFrac() {
		Fraction frac = new Fraction(quantity);
		
		return frac.toString();
	}
	
}