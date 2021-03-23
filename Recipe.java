import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing a recipe
 * @author PatrickLenis
 *
 */
public class Recipe implements Comparable<Recipe> {
	

	
	private String author, name, type, description;
	private int difficulty;
	/**
	 * List of ingredients stored as Ingredient class
	 */
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	/**
	 * 
	 * @param author name of the author
	 * @param name name of the recipe
	 * @param type type of the recipe
	 * @param difficulty difficulty of the recipe
	 * @param description recipe description
	 */
	public Recipe(String author, String name, String type, int difficulty, String description)
	{
		this.author = author;
		this.name = name;
		this.type = type;
		this.difficulty = difficulty;
		this.description = description;
	}
	
	/**
	 * Used to add a new ingredient
	 * @param ingredient the ingredient <Ingredient> to add
	 */
	public void AddIngredient(Ingredient ingredient)
	{
		ingredients.add(ingredient);
	}
	
	/**
	 * Returns the recipe name
	 * @return name
	 */
	public String GetName()
	{
		return name;
	}
	
	/**
	 * Returns the recipe type
	 * @return type
	 */
	public String GetType()
	{
		return type;
	}
	
	/**
	 * Returns the recipe author
	 * @return author
	 */
	public String GetAuthor()
	{
		return author;
	}
	
	/**
	 * Returns the recipe description
	 * @return description
	 */
	public String GetDescription()
	{
		return description;
	}
	
	/**
	 * Returns the recipe difficulty
	 * @return difficulty
	 */
	public int GetDifficulty()
	{
		return difficulty;
	}
	
	/**
	 * Used to compare recipes by thre criteria (type, difficulty, name)
	 */
	public int compareTo(Recipe arg0)
	{
		//compare type
		if(this.type.compareTo(arg0.type) > 0)
			return 1;
		else if(this.type.compareTo(arg0.type) < 0)
			return -1;
		
		//compare difficulty if type is the same
		else if(this.difficulty > arg0.difficulty)
			return 1;
		else if(this.difficulty < arg0.difficulty)
			return -1;
		
		//if both are the same compares by name
		return (this.name.compareTo(arg0.type));
	}
	
	/**
	 * Overwrite toString
	 */
	public String toString()
	{
		return name + "\nAuthor : " + author + "\nType : " + type +"\nDifficulty : " + difficulty + "\nIngredients : "+ingredients.toString() + "\nRecipe : " + description;
	}
}
