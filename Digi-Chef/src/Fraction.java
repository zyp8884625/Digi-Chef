
public class Fraction 
{
	private int whole, numerator, denominator;
	
	public Fraction()
	{
		this.whole = 0;
		this.numerator = 0;
		this.denominator = 0;
	}
	
	public Fraction(double d)
	{
		Fraction frac = doubleToFraction(d);
//		this.whole = frac.getWhole();
//		this.numerator = frac.getNumerator();
//		this.denominator = frac.getDenominator();
	}
	
	public Fraction(int w, int n, int d)
	{
		this.whole = w;
		this.numerator = n;
		this.denominator = d;
	}
	
	/**
	 * @return the whole
	 */
	public int getWhole() {
		return whole;
	}

	/**
	 * @return the numerator
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * @return the denominator
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * @param whole the whole to set
	 */
	public void setWhole(int whole) {
		this.whole = whole;
	}

	/**
	 * @param numerator the numerator to set
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * @param denominator the denominator to set
	 */
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	public Fraction doubleToFraction(Double d)
	{
		Fraction tempFraction = new Fraction();
		int temp=0;
		
		this.whole =  d.intValue();
		
		d = d - d.intValue(); //remove whole
		d = (d/.25); // round to nearest quarter
		temp  = d.intValue();
		
		switch (temp)
		{
			case 1:
			{
				numerator = 1;
				denominator = 4;
				break;
			}
			case 2:
			{
				numerator = 1;
				denominator = 2;
				break;
			}
			case 3:
			{
				numerator = 3;
				denominator = 4;
				break;
			}
		}
		
		return tempFraction;
	}

	public int GCD(int a, int b) 
	{
		   if (b==0) return a;
		   return GCD(b,a%b);
	}
	
	public String toString()
	{
		if(numerator == 0 || whole == 0)
		{
			return Integer.toString(whole);
		}
		return whole + " " + numerator + "/" + denominator;
	}
	
	public static void main(String[] args)
	{
		Fraction frac = new Fraction(4.75);
		System.out.println(frac.toString());
	}
}