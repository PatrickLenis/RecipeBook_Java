import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the RecipeBook program
 * @author PatrickLenis
 *
 */
public class Main {
	
	/**
	 * Uses scanner to read from console
	 */
	public static Scanner console = new Scanner(System.in);
	
	/**
	 * List of recipes
	 */
	public static List<Recipe> recipes = new ArrayList<Recipe>();
	
	/**
	 * Used to print the help menu
	 */
	public static void PrintHelp()
	{
		System.out.print("\n\n------MENU------");
		System.out.print("\n1. Add new recipe");
		System.out.print("\n2. Remove recipe");
		System.out.print("\n3. Update recipe");
		System.out.print("\n4. Search recipe");
		System.out.print("\n5. Recipe Book table content");
		System.out.print("\n0. Save and exit");
	}
	
	/**
	 * Uses Recipe sort method
	 * @param recipesToSort recipe list parameter
	 */
	public static void Sort(List<Recipe> recipesToSort)
	{
		Collections.sort(recipesToSort);
	}
	
	/**
	 * reads saved information from RecipeBook.txt and loads it in to the recipe list
	 */
	public static void Load()
	{
		try {
			File file = new File("RecipeBook.txt");
	        //create the file if it doesn't exist
	        if (!file.exists()) {
	             file.createNewFile();
	        }
	        
	        Scanner reader = new Scanner(file);
	        
	        int numberOfElements, numberOfIngredients, countE, countI;
	        
	        numberOfElements = reader.nextInt();
	        reader.nextLine();
	        
	        for(countE=0;countE<numberOfElements;countE++)
	        {
		        String author, name, type, description;
		    	int difficulty;
		    	
		    	name = reader.nextLine();
		    	author = reader.nextLine();
		    	type = reader.nextLine();
		    	difficulty = reader.nextInt();
		    	reader.nextLine();
		    	description = reader.nextLine();
		    	
		    	Recipe r = new Recipe(author, name, type, difficulty, description);
		    	
		    	numberOfIngredients = reader.nextInt();
		    	reader.nextLine();
		    	
		    	for(countI=0;countI<numberOfIngredients;countI++)
		    	{
		    		String nameIng, measure;
		    		int quantity;
		    		
		    		nameIng = reader.nextLine();
		    		quantity = reader.nextInt();
		    		reader.nextLine();
			    	measure = reader.nextLine();
			    	
			    	Ingredient i = new Ingredient(nameIng, quantity, measure);
			    	r.ingredients.add(i);
		    	}
		    	
		    	recipes.add(r);
	        }
	        
	        reader.close();
	        

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
        
	}
	
	/**
	 * Saves the recipe list in RecipeBook.txt
	 * @param saveList recipe list to save
	 */
	public static void Save(List<Recipe> saveList)
	{
		 try {
				File file = new File("RecipeBook.txt");
		        //create the file if it doesn't exist
		        if (!file.exists()) {
		             file.createNewFile();
		        }
		        
		        FileWriter fstream = new FileWriter(file);
		        PrintWriter writer = new PrintWriter(fstream);
		        
		        writer.println(saveList.size());
		        for(Recipe r : saveList)
		        {
		        	writer.println(r.GetName());
		        	writer.println(r.GetAuthor());
		        	writer.println(r.GetType());
		        	writer.println(r.GetDifficulty());
		        	writer.println(r.GetDescription());
		        	
		        	writer.println(r.ingredients.size());
		        	for(Ingredient i : r.ingredients)
		        	{
		        		writer.println(i.GetName());
			        	writer.println(i.GetQuantity());
			        	writer.println(i.GetMeasure());
		        	}
		        	
		        }
		        
		        writer.close();

	        } catch (FileNotFoundException e) {
	            System.out.println("File not found");
	        } catch (IOException e) {
	            System.out.println("Error initializing stream");
	        } 
	}
	
	/**
	 * Used to add a new recipe to the list
	 */
	public static void AddRecipe()
	{
		String author, name, type, description;
		int difficulty;
		
		System.out.print("\nRecipe author : ");
		author = console.nextLine();
		
		System.out.print("Recipe name : ");
		name = console.nextLine();
		
		System.out.print("Recipe type : ");
		type = console.nextLine();
		
		System.out.print("Recipe difficulty : ");
		difficulty = console.nextInt();
		console.nextLine();
		
		System.out.print("Recipe description : ");
		description = console.nextLine();
		
		Recipe r = new Recipe(author, name, type, difficulty, description);
		
		//Ingredients
		int nr;
		System.out.print("Number of ingredients : ");
		nr = console.nextInt();
		console.nextLine();
		
		for(int i=0;i<nr;i++) {
			String ingredientName, measure;
			int quantity;
			
			System.out.print("Ingredient " + (i+1) + " name : ");
			ingredientName = console.nextLine();
			
			System.out.print("Ingredient " + (i+1) + " quantity : ");
			quantity = console.nextInt();
			console.nextLine();
			
			System.out.print("Quantity measure unit : ");
			measure = console.nextLine();
			
			Ingredient ingredient = new Ingredient(ingredientName, quantity, measure);
			r.AddIngredient(ingredient);
		}
		
		//Validate
		/**
		 * Validate if the recipe is correct (if not it can't be added to the recipe list)
		 */
		boolean isValid = true;
		
		for(Ingredient i : r.ingredients) {
			if(description.indexOf(i.GetName()) == -1)
			{
				isValid = false;
				break;
			}
		}
		
		if(isValid == true)
		{
			recipes.add(r);
		}
		else
		{
			System.out.print("\nRecipe is invalid (ingredients don't apear in description), try again\n");
		}
	}
	
	
	/**
	 * Used to update information from a recipe
	 * @param name recipe to update
	 */
	public static void AddRecipeUpdated(String name)
	{
		String author, type, description;
		int difficulty;
		
		System.out.print("\nRecipe author : ");
		author = console.nextLine();
		
		System.out.print("Recipe type : ");
		type = console.nextLine();
		
		System.out.print("Recipe difficulty : ");
		difficulty = console.nextInt();
		console.nextLine();
		
		System.out.print("Recipe description : ");
		description = console.nextLine();
		
		Recipe r = new Recipe(author, name, type, difficulty, description);
		
		//Ingredients
		int nr;
		System.out.print("Number of ingredients : ");
		nr = console.nextInt();
		console.nextLine();
		
		for(int i=0;i<nr;i++) {
			String ingredientName, measure;
			int quantity;
			
			System.out.print("Ingredient " + (i+1) + " name : ");
			ingredientName = console.nextLine();
			
			System.out.print("Ingredient " + (i+1) + " quantity : ");
			quantity = console.nextInt();
			console.nextLine();
			
			System.out.print("Quantity measure unit : ");
			measure = console.nextLine();
			
			Ingredient ingredient = new Ingredient(ingredientName, quantity, measure);
			r.AddIngredient(ingredient);
		}
		
		//Validate
		/**
		 * Validate if the recipe is correct (if not it can't be added to the recipe list)
		 */
		boolean isValid = true;
		
		for(Ingredient i : r.ingredients) {
			if(description.indexOf(i.GetName()) == -1)
			{
				isValid = false;
				break;
			}
		}
		
		if(isValid == true)
		{
			recipes.add(r);
		}
		else
		{
			System.out.print("\nRecipe is invalid (ingredients don't apear in description), try again\n");
		}
	}
	
	
	
	/**
	 * Used to remove a recipe from the list
	 */
	public static void RemoveRecipe()
	{
		boolean found = false;
		String name;
		System.out.print("\nRecipe to remove (name) : ");

		name = console.nextLine();
		
		for(Recipe r : recipes)
		{
			if(r.GetName().equals(name))
			{
				System.out.print("\nRecipe removed\n");
				recipes.remove(r);
				found = true;
				break;
			}
		}
		
		if(found == false)
		{
			System.out.print("\nRecipe not found\n");
		}
	}
	
	/**
	 * Used to update a recipe (searches and removes the recipe that needs to be updated and calls the AddRecipeUpdated method to update the recipe information)
	 */
	public static void UpdateRecipe()
	{
		boolean found = false;
		String name;

		System.out.print("\nRecipe to update (name) : ");
		
		name = console.nextLine();
		
		for(Recipe r : recipes)
		{
			if(r.GetName().equals(name))
			{
				System.out.print("\nRecipe ready to update\n");
				recipes.remove(r);
				found = true;
				break;
			}
		}
		
		if(found == false)
		{
			System.out.print("\nRecipe not found\n");
		}
		else {
			AddRecipeUpdated(name);
		}
	}
	
	/**
	 * Used to search for a recipe
	 */
	public static void SearchRecipe()
	{
		boolean found = false;
		String name;
		System.out.print("\nRecipe to search for (name) : ");

		name = console.nextLine();
		
		for(Recipe r : recipes)
		{
			if(r.GetName().equals(name))
			{
				System.out.print("\nRecipe found\n");
				System.out.print(r);
				
				found = true;
			}
		}
		
		if(found == false)
		{
			System.out.print("\nRecipe not found\n");
		}
	}
	
	/**
	 * Prints the recipe list grouped by type, difficulty and name
	 */
	public static void TableContent()
	{
		Sort(recipes);
		System.out.print("\nRecipe Book Content : \n");
		String type = "";
		for(Recipe r : recipes)
		{
			if(!type.equals(r.GetType()))
			{
				type = r.GetType();
				System.out.print("\nGrouped by type : " + type + "\n");
			}
			System.out.print("\n" + r + "\n");
				
		}
	}
	
	/**
	 * Used to exit the program and save the recipe list to the RecipeBook.txt file (calls Save method)
	 */
	public static void Exit()
	{
		Sort(recipes);
		Save(recipes);
		console.close();
	}
	
	/**
	 * Main function
	 * @param arg default arg
	 */
	public static void main(String[] arg)
	{
		
		Load();
		
		/**
		 * Used to select an option in switch case structure
		 */
		int menuIndex;
		
		/**
		 * Uses switch case structure to display a menu until exit option is chosen 
		 */
		do
		{
			PrintHelp();
			System.out.print("\n\nYour choice :  ");
		    menuIndex = console.nextInt();
		    console.nextLine();
		    
			switch(menuIndex)
			{
				case 1:
					AddRecipe();
					break;
				case 2:
					RemoveRecipe();
					break;
				case 3:
					UpdateRecipe();
					break;
				case 4:
					SearchRecipe();
					break;
				case 5:
					TableContent();
					break;
				case 0:
					Exit();
					break;
				default:
					System.out.print("Wrong input, try again ...");
				
			}
		}while(menuIndex != 0);
		
	}

}
