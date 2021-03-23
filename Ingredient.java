/**
 * Class for storing an ingredient
 * @author PatrickLenis
 *
 */
public class Ingredient {
	
	private String name, measure;
	private int quantity;
	
	/**
	 * Class constructor
	 * @param name name of the ingredient
	 * @param quantity quantity of the ingredient
	 * @param measure measure unit for quantity
	 */
	public Ingredient(String name, int quantity, String measure)
	{
		this.name = name;
		this.quantity = quantity;
		this.measure = measure;
	}
	
	/**
	 * Returns the ingredient's name
	 * @return name
	 */
	public String GetName()
	{
		return name;
	}
	
	/**
	 * Returns the ingredient's quantity
	 * @return quantity
	 */
	public int GetQuantity()
	{
		return quantity;
	}
	
	/**
	 * Returns the ingredient's quantity measure unit
	 * @return measure
	 */
	public String GetMeasure()
	{
		return measure;
	}
	
	/**
	 * Overwrite toString
	 */
	public String toString()
	{
		return name + " " + quantity + measure;
	}
}
