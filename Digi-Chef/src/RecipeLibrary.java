
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import sun.font.EAttribute;

import java.io.*;
import java.nio.charset.Charset;

public class RecipeLibrary
{
	
	private final String FILENAME = "bin/recipes.txt";
	
	private static ArrayList<Recipe> rLibrary;
	private static ArrayList<JButton> dRecipe;
	private static JPanel recipeLibraryJPanel;
	private JPanel recipeDetail;
	private static JPanel searchBar;
	private static JPanel addNewRecipePage;
	private static String recipeNameToShow;
	private static TextField nameLabel;
	private static JTextField search;
	private static JButton searchButton;
	
	
	//constructor
	public RecipeLibrary()
	{
		search = new JTextField();
		search.setColumns(25);
		searchButton = new JButton();
		rLibrary = new ArrayList<Recipe>();
		//recipeList = new JPanel();
		recipeLibraryJPanel = new JPanel();
		recipeDetail = new JPanel();
		recipeNameToShow = new String();
		nameLabel = new TextField("Name");
		nameLabel.setEditable(false);
		try {
			loadArray();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		sortArray(rLibrary);
	}

	public ArrayList<Recipe> getrLibrary() 
	{
		return rLibrary;
	}
	
	public ArrayList<Recipe> getFavorites()
	{
		ArrayList<Recipe> favoritesList = new ArrayList<Recipe>();
		for (Recipe r : rLibrary) 
		{
			if(r.getIsFavorite())
			{
				favoritesList.add(r);
			}
		}
		//sortArray(favoritesList); //not necessary will find them in order
		return favoritesList;
	}

	public boolean addRecipe(JFrame mainframe)
	{
		//TODO add recipe
		JPanel addRecipeJPanel = new JPanel();
		
		// ---------- Name ----------
		JLabel recipeNameLabel = new JLabel("Name: ", JLabel.LEFT);
		addRecipeJPanel.add(recipeNameLabel);
		
		JTextField recipeNameBox = new JTextField();
		recipeNameBox.setEditable(true);
		recipeNameBox.setColumns(10);
		addRecipeJPanel.add(recipeNameBox);
		
		// ---------- Recipe Description ----------
		JLabel recipeDescriptionLabel = new JLabel("Description: ", JLabel.LEFT);
		addRecipeJPanel.add(recipeDescriptionLabel);
		
		JTextField recipeDescriptionBox = new JTextField();
		recipeDescriptionBox.setEditable(true);
		recipeDescriptionBox.setColumns(10);
		addRecipeJPanel.add(recipeDescriptionBox);
		
		// ---------- Select Favorite Recipe ----------
		JLabel selectFavoriteLabel = new JLabel("Favorite: ", JLabel.LEFT);
		addRecipeJPanel.add(selectFavoriteLabel);
		
		JCheckBox favoriteCheckbox = new JCheckBox();
		addRecipeJPanel.add(favoriteCheckbox);
		
		// ---------- Recipe Category ----------
		JLabel recipeCategoryLabel = new JLabel("Cateogry: ", JLabel.LEFT);
		addRecipeJPanel.add(recipeCategoryLabel);
		
		String[] categories = Category.BEEF.getAllCategories();
		JComboBox recipeCategoryMenu = new JComboBox(categories);
		addRecipeJPanel.add(recipeCategoryMenu);
		
		// --------- Recipe Ingredients ----------
		JLabel ingredients = new JLabel("Ingredients: ", JLabel.LEFT);
		
		// ---------- Quantity ArrayLists ----------
		ArrayList<JLabel> ingredientQuantityLabels = new ArrayList<JLabel>();
		ArrayList<JTextField> ingredientQuantityBoxes = new ArrayList<JTextField>();
		
		// ---------- UnitType ArrayLists ----------
		ArrayList<JLabel> ingredientUnitTypeLabels = new ArrayList<JLabel>();
		ArrayList<JComboBox<UnitType>> ingredientUnitTypeComboBoxes = new ArrayList<JComboBox<UnitType>>();
		
		// ---------- Ingredient Name ArrayLists ----------
		ArrayList<JLabel> ingredientNameLabels = new ArrayList<JLabel>();
		ArrayList<JTextField> ingredientNameTextBox = new ArrayList<JTextField>();
		
		for (int i=0; i<5; i++)
		{
			// --------- Quantity ----------
			JLabel ingredientQuantity = new JLabel("Quantity");
			ingredientQuantityLabels.add(ingredientQuantity);
			
			JTextField ingredientQuantityBox = new JTextField();
			ingredientQuantityBox.setColumns(5);
			ingredientQuantityBoxes.add(ingredientQuantityBox);
			
			// ---------- Unit Type ----------
			JLabel ingredientUnitType = new JLabel("Units");
			ingredientNameLabels.add(ingredientUnitType);
			
			
			// ---------- Ingredient Name ----------
			JLabel ingredientNameLabel = new JLabel("Ingredient Name: ", JLabel.LEFT);
			
			
			JTextField ingredientNameBox = new JTextField();
			ingredientNameBox.setEditable(true);
			ingredientNameBox.setColumns(10);
			
		}
		
		
		
		
		
		
		// --------- Instructions ----------
		JLabel instructionLabel = new JLabel("Instructions: ", JLabel.LEFT);
		
		JTextField instructionsBox = new JTextField();
		instructionsBox.setEditable(true);
		instructionsBox.setColumns(10);
		addRecipeJPanel.add(instructionsBox);
		
		// --------- Servings ----------
		JLabel servesLabel = new JLabel("Serves: ", JLabel.LEFT);
		addRecipeJPanel.add(servesLabel);		
		
		JTextField servesBox = new JTextField();
		servesBox.setEditable(true);
		servesBox.setColumns(10);
		addRecipeJPanel.add(servesBox);
		
		// --------- Times ----------
		// --------- preptime ----------
		JLabel recipePreptimeLabel = new JLabel("Preptime: ", JLabel.LEFT);
		addRecipeJPanel.add(recipePreptimeLabel);
		
		JTextField recipePreptimeBox = new JTextField();
		recipePreptimeBox.setEditable(true);
		recipePreptimeBox.setColumns(10);
		addRecipeJPanel.add(recipePreptimeBox);
		
		// --------- cooktime ----------
		JLabel recipeCooktimeLabel = new JLabel("Cooktime: ", JLabel.LEFT);
		addRecipeJPanel.add(recipeCooktimeLabel);

		JTextField recipeCooktimeBox = new JTextField();
		recipeCooktimeBox.setEditable(true);
		recipeCooktimeBox.setColumns(10);
		addRecipeJPanel.add(recipeCooktimeBox);
	
		return true;
	}
	
	public static void deleteRecipe(Recipe recipe, JFrame mainFrame) // to remove a recipe from the library
	{
		rLibrary.remove(recipe);
		//TODO draw recipelibrary
		rewriteRecipesFile();
		mainFrame.remove(mainFrame.getContentPane().getComponent(1));
		drawRecipeLibrary(mainFrame, rLibrary);
	}

	private static void rewriteRecipesFile() 
	{
		// TODO Auto-generated method stub
	}

	public static void drawRecipeLibrary(JFrame mainframe, ArrayList<Recipe> recipeListToShow)
	{	//draw the Recipe library will call GUI class and function
		recipeLibraryJPanel = new JPanel();
		searchBar = new JPanel();	
		dRecipe = new ArrayList<JButton>();
		recipeLibraryJPanel.setLayout(new GridLayout(10,10));
		recipeLibraryJPanel.setName("recipeLibrary");
		searchBar.setLayout(new FlowLayout());
		searchButton.setText("Search");
		searchBar.add(search);
		searchBar.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				System.out.println(search.getText());
				System.out.println(searchArray(search.getText()).size());
				if(searchArray(search.getText()).size() != 0){
					mainframe.remove(mainframe.getContentPane().getComponent(1));
					drawRecipeLibrary(mainframe, searchArray(search.getText()));
				}else
				{
					drawRecipeLibrary(mainframe, rLibrary);
					JOptionPane.showMessageDialog(null, "Nothing Found!");
				}
			}
		});
		recipeLibraryJPanel.add(searchBar);
		for (int i = 0; i < recipeListToShow.size(); i++){
			dRecipe.add(new JButton(recipeListToShow.get(i).getName()));
			recipeLibraryJPanel.add(dRecipe.get(i));
			dRecipe.get(i).addActionListener(new ActionListener(){ // add ActionListener
	            public void actionPerformed(ActionEvent ae){
	            	recipeNameToShow = ae.getActionCommand();
	            	drawRecipe(mainframe,recipeListToShow);
	            }
			});
		}
		
		recipeLibraryJPanel.setBorder(BorderFactory.createTitledBorder(""));
		recipeLibraryJPanel.setPreferredSize(new Dimension(1028,668));
		mainframe.add(BorderLayout.CENTER,recipeLibraryJPanel);
		mainframe.repaint();
		mainframe.setVisible(true);
	}
	public static void drawRecipe(JFrame mainFrame, ArrayList<Recipe> recipeList){
		for (int i = 0; i < recipeList.size(); i++){
			if (recipeNameToShow == recipeList.get(i).getName()){
				recipeList.get(i).drawRecipe(mainFrame);
			}
		}
		
	}
	public void drawErrorPage()
	{
		//TODO draw error page
		
	}

	private Boolean loadArray() throws IOException
	{
		InputStream    input;
		BufferedReader buffer;
		String         line;
		int control;
		Ingredient tempIngredient;
		Instruction tempInstruction;
		Recipe tempRecipe;
		
		input = new FileInputStream(FILENAME);
		buffer = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
		//read in recipe
		while ((line = buffer.readLine()) != null) 
		{
			System.out.println(line); //for testing
			
			//variable used to build recipe to add to arryalist
			tempRecipe = new Recipe();
			
			//name
			tempRecipe.setName(line);
			
			//description
			line = buffer.readLine();
			tempRecipe.setDescription(line);
			
			//numIngredients
			line = buffer.readLine();
			control = Integer.parseInt(line);
			
			//ingredients
			for(int i=0; i<control; i++)
			{
				String name = buffer.readLine();
				double quantity = Double.parseDouble(buffer.readLine());
				UnitType type;
				line = buffer.readLine();
				type = stringToUnitType(line); //UnitType
				tempIngredient = new Ingredient(name, quantity, type);
				tempRecipe.getIngredientList().add(tempIngredient);
			}//end ingredients for loop
			
			//numInstruction
			line = buffer.readLine();
			control = Integer.parseInt(line);
			
			//description loop
			for(int i=0; i<control; i++)
			{
				line = buffer.readLine();
				tempInstruction = new Instruction(line);
				tempRecipe.getInstructionList().add(tempInstruction);
			}//end Instruction loop
			
			//isFavorite
			line = buffer.readLine();
			tempRecipe.setFavorite(stringToBoolean(line)); 
			
			//numServings
			line = buffer.readLine();
			tempRecipe.setNumServings(Integer.parseInt(line));
			
			//prepTime
			line = buffer.readLine();
			tempRecipe.setPrepTime(Double.parseDouble(line));
			
			//cookTime
			line = buffer.readLine();
			tempRecipe.setCookTime(Double.parseDouble(line));
			
			//Category
			line = buffer.readLine();
			tempRecipe.setCategory(stringToCategory(line));
			
			//add to library
			rLibrary.add(tempRecipe);
			
			//print recipe in console
			tempRecipe.consolePrint(); //for testing
			
			//reset variables
			line = null;
			tempRecipe = null;
			tempIngredient = null;
			tempInstruction = null;
			
			
		}//end of while

		// Done with the file
		buffer.close();
		buffer = null;
		input = null;
		
		return true;
	}

//sort the array list
	private boolean sortArray(ArrayList<Recipe> result)
	{
		Collections.sort(result);
		return true;
	}
	static ArrayList<Recipe> searchArray(String searched) {
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		for (Recipe r : rLibrary) {
			//System.out.println(rLibrary.get(index).getName());
			if (searched.contains(r.getName())) {
				list.add(r);
				/*if (index-1 > 0){
					list.add(rLibrary.get(index-1));
				}
				if (index +1 < getrLibrary().size()) {
					list.add(rLibrary.get(index));
				}*/
			}
		}
		return list;
	}

	private UnitType stringToUnitType(String inputString)
	{
		switch(inputString)//UnitType
		{
		case "cups":
			return UnitType.CUPS;
		case "ounces":
			return UnitType.OUNCES;
		case "tablespoons":
			return UnitType.TABLESPOONS;
		case "teaspoons":
			return UnitType.TEASPOONS;
		case "gallons":
			return UnitType.GALLONS;
		case "liters":
			return UnitType.LITERS;
		case "quarts":
			return UnitType.QUART;
		case "pints":
			return UnitType.PINT;
		case "milliliters":
			return UnitType.MILLILITERS;
		case "pounds":
			return UnitType.POUNDS;
		case "NONE":
			return null;
		default:
			throw new IllegalArgumentException("Invalid UnitType: " + inputString);
		}//end UnitType switch
	}

	public Category stringToCategory(String inputString) 
	{
		switch(inputString)//category
		{
		case "beef":
			return Category.BEEF;
		case "fish":
			return Category.FISH;
		case "pork":
			return Category.PORK;
		case "poultry":
			return Category.POULTRY;
		case "vegetarian":
			return Category.VEGETARIAN;
		case "dairy":
			return Category.DAIRY;
		default:
			throw new IllegalArgumentException("Invalid category: " + inputString);
		}//end Category switch
	}
	
	private boolean stringToBoolean(String inputString) 
	{
		switch (inputString)
		{
		case "true":
			return true;
		case "false":
			return false;
		default:
			throw new IllegalArgumentException("invalid Boolean: " + inputString);
		}
	}

	public static void main(String[] args)
	{
		RecipeLibrary library = new RecipeLibrary();
		
		Recipe tester = library.getrLibrary().get(3);
		tester.setName("test");
		
		for(Recipe r: library.getrLibrary())
		{
			System.out.println(r.getName());
		}
		
		library.getrLibrary().remove(tester);
		System.out.println();
		
		for(Recipe r: library.getrLibrary())
		{
			System.out.println(r.getName());
		}
	}
}
