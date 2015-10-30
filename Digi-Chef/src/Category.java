
public enum Category {
	BEEF("beef"),
	PORK("pork"),
	POULTRY("poultry"),
	FISH("fish"),
	VEGETARIAN("vegetarian"),
	DAIRY("dairy");
	
	private String stringRep;
	
	private Category(String s)
	{
		stringRep = s;
	}
	
	public String[] getAllCategories()
	{
		String[] list = {BEEF.toString(), PORK.toString(), POULTRY.toString(), FISH.toString(), VEGETARIAN.toString(), DAIRY.toString()};
		return list;
	}
	
	public String toString()
	{
		return stringRep;
	}
}