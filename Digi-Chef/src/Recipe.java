import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;

import javax.swing.*;
import javax.swing.table.*;

public class Recipe implements Comparable
{
	
	private String name;
	private ArrayList<Ingredient> ingredientList;
	private ArrayList<Instruction> instructionList;
	private int numServings;
	private double prepTime;
	private double cookTime;
	private String description;
	private boolean isFavorite;
	private int numInstructions, numIngredient;
	private Category category;
	Recipe recipeToDelete = this;
	
	//---------For recipe GUI--------
	private JPanel recipeDetail;							//Panel for recipe
	private JTextPane descriptionText;						//TextPane of intorduction
	private JTable ingredientJTable;						//Table of ingredient
	private JButton printButton;							//button for print
	private JButton editButton;								//button for edit
	private JButton shareButton;							//button for share
	private JTextArea instructionJtext;
	private JTextField recipeNameField;

	//---------For Edit recipe-------
	//TODO Assign in constructor
	private JButton saveButton = new JButton();				//button for save
	private JButton cancelButton = new JButton();				//button for back
	//------------------------
	
	
	//CONSTRUCTOR
	public Recipe(String name, ArrayList<Ingredient> ingredientList, ArrayList<Instruction> instructionList, int numServings, double prepTime, double cookTime, String description, Category cat) 
	{
		this.name = name;
		this.ingredientList = new ArrayList<Ingredient>();
		this.instructionList = new ArrayList<Instruction>();
		this.numServings = numServings;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.description = description;
		this.isFavorite = false;
		this.category = cat;
		//JTable instrucionJTable = new JTable();
	}
	
	//Constructor generic
	public Recipe() 
	{
		this.name = "temp";
		this.ingredientList = new ArrayList<Ingredient>();
		this.instructionList = new ArrayList<Instruction>();
		this.numServings = 0;
		this.prepTime = 0;
		this.cookTime = 0;
		this.description = "temp";
		this.isFavorite = false;
		this.category = null;
	}

	//GETTERS
	public String getName() 
	{
		return name;
	}
	public ArrayList<Ingredient> getIngredientList() 
	{
		return ingredientList;
	}
	public ArrayList<Instruction> getInstructionList() 
	{
		return instructionList;
	}
	public int getNumServings() 
	{
		return numServings;
	}
	public double getPrepTime() 
	{
		return prepTime;
	}
	public double getCookTime() 
	{
		return cookTime;
	}
	public String getDescription() 
	{
		return description;
	}
	public boolean getIsFavorite() 
	{
		return isFavorite;
	}
	public int getNumInstruction()
	{
		return numInstructions;
	}
	public int getNumIngredient()
	{
		return numIngredient;
	}
	public Category getCategory()
	{
		return this.category;
	}
	
	//SETTERS
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setIngredientList(ArrayList<Ingredient> ingredientList) 
	{
		this.ingredientList = ingredientList;
	}
	public void setInstructionList(ArrayList<Instruction> instructionList) 
	{
		this.instructionList = instructionList;
	}
	public void setNumServings(int numServings) 
	{
		this.numServings = numServings;
	}
	public void setPrepTime(double prepTime) 
	{
		this.prepTime = prepTime;
	}
	public void setCookTime(double cookTime) 
	{
		this.cookTime = cookTime;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public void setFavorite(boolean isFavorite) 
	{
		this.isFavorite = isFavorite;
	}
	public void setInstruction(int index, String instruction)
	{
		instructionList.get(index-1).setInstruction(instruction);
	}
	public void setIngredient(int index, String name, double qty, UnitType uType, Category category)
	{
		ingredientList.get(index-1).setName(name);
		ingredientList.get(index-1).setQuantity(qty);
		ingredientList.get(index-1).setUnits(uType);
	}
	
	public void setCategory(Category cat) 
	{
		this.category = cat;		
	}

	//FUNCTIONALITY
	public void drawRecipe(JFrame MainFrame) 
	{
		//recipe knows how to draw it self on a page
		//TODO adjust size of table
		//TODO add name of recipe
		recipeNameField = new JTextField(this.name);		//textfield for name
		recipeDetail = new JPanel();			//Panel for recipe
		recipeDetail.setPreferredSize(new Dimension(MainFrame.getWidth(), 85));
		recipeNameField.setEditable(false);
		recipeDetail.setName("recipeDetail");
		recipeDetail.add(recipeNameField);
		recipeNameField.setPreferredSize(new Dimension(MainFrame.getWidth(),30));
		recipeNameField.setFont(new Font("Times New Roma",Font.BOLD,20));
		recipeNameField.setBackground(MainFrame.getBackground());
		recipeNameField.setHorizontalAlignment(JTextField.CENTER);
		
		//recipeNameField.setHorizontalAlignment(JTextField.CENTER);
		descriptionText = new JTextPane();		//TextPane of introductionß
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				RecipeLibrary.deleteRecipe(recipeToDelete,MainFrame);
			}
		});
		
		printButton = new JButton();			//button for print
		printButton.setText("Print");
		printButton.addActionListener(new ActionListener() // add ActionListener for print
		{ 
            public void actionPerformed(ActionEvent ae)
            {
            	printRecipeFile();
            }
		});
		editButton = new JButton();				//button for edit
		editButton.setText("Edit");
		editButton.addActionListener(new ActionListener()// add ActionListener for edit
		{ 
            public void actionPerformed(ActionEvent ae)
            {
            	editRecipe(MainFrame);
            }
		});
		shareButton = new JButton();			//button for share
		shareButton.setText("Share");
		shareButton.addActionListener(new ActionListener()// add ActionListener for share
		{ 
            public void actionPerformed(ActionEvent ae)
            {
            	shareRecipe();
            }
		});
		
		instructionJtext = new JTextArea();
		descriptionText = new JTextPane();
		recipeDetail.setLayout(new FlowLayout());
		descriptionText.setText(description);
		recipeDetail.add(descriptionText);
		descriptionText.setEditable(false);
		recipeDetail.setBorder(BorderFactory.createTitledBorder(""));
		//-----------instruction text
		for (int i = 0; i < instructionList.size();i++){
			instructionJtext.append(instructionList.get(i).getInstruction());
			instructionJtext.append("\r\n");
		}
		instructionJtext.append("\r\n");
		instructionJtext.append("Prep Time: "+ Double.toString(prepTime)+"mins");
		instructionJtext.append("\r\n");
		instructionJtext.append("Cook Time: "+ Double.toString(cookTime)+"mins");
		instructionJtext.append("\r\n");
		instructionJtext.append("Total Time: "+ Double.toString(prepTime+cookTime)+"mins");
		instructionJtext.append("\r\n");
		instructionJtext.append("\r\n");
		instructionJtext.append("Serves: "+Integer.toString(numServings));
		instructionJtext.setLineWrap(true);// set text wrap
		instructionJtext.setWrapStyleWord(true); // text wrap by words
		instructionJtext.setEditable(false);
		JScrollPane instructionScrooll = new JScrollPane(instructionJtext);
		instructionJtext.setWrapStyleWord(true);
		instructionScrooll.setPreferredSize(new Dimension(MainFrame.getWidth(),MainFrame.getHeight()-500));
		instructionJtext.setFont(new Font("Times New Roma", Font.BOLD, 14));
		
		//----------IngredientTable----------------
		ingredientJTable = new JTable();
		ingredientJTable.setRowMargin(ingredientList.size());
		DefaultTableModel ingrTable = new DefaultTableModel();
		Vector<String> TableTittlea = new Vector<String>(1);
		Vector<String> TableTittleb = new Vector<String>(1);
		Vector<String> TableTittlec = new Vector<String>(1);
		TableTittlea.add("Quantity");
		TableTittleb.add("Unit");
		TableTittlec.add("Name");
		ingrTable.addColumn(TableTittlea);
		ingrTable.addColumn(TableTittleb);
		ingrTable.addColumn(TableTittlec);
		ArrayList<Vector<String>> ingreTableRow = new ArrayList<Vector<String>>();
		for (int i = 0;i<ingredientList.size(); i++){
			Vector<String> ingredDetail = new Vector<String>(ingredientList.size());
			ingredDetail.add(Double.toString(ingredientList.get(i).getQuantity()));
//			ingredDetail.add(ingredientList.get(i).getQtyFrac());
			
			if(ingredientList.get(i).getUnits() == null){
				ingredDetail.add(" ");
			}else{
				ingredDetail.add(ingredientList.get(i).getUnits().toString());
			}
			ingredDetail.add(ingredientList.get(i).getName());
			ingreTableRow.add(ingredDetail);
		}
		for (int i = 0;i<ingredientList.size(); i++){
			ingrTable.addRow(ingreTableRow.get(i));
		}
		ingredientJTable.setModel(ingrTable);
		ingredientJTable.setRowHeight(30);
		ingredientJTable.setFont(new Font("Times New Roma",Font.BOLD,14));
		ingredientJTable.setPreferredSize(new Dimension(MainFrame.getWidth(),ingredientList.size()*30));
		JScrollPane ingredScroll = new JScrollPane(ingredientJTable);
		ingredScroll.setPreferredSize(new Dimension(MainFrame.getWidth(),MainFrame.getHeight()-500));
		MainFrame.remove(MainFrame.getContentPane().getComponent(1));
		ingredientJTable.setEnabled(false);
		recipeDetail.add(ingredScroll);
		recipeDetail.add(instructionScrooll);
		recipeDetail.add(printButton);
		recipeDetail.add(editButton);
		recipeDetail.add(shareButton);
		recipeDetail.add(deleteButton);
		//recipeDetail.add(scaleInput);
		//recipeDetail.add(scaleButton);
		MainFrame.add(BorderLayout.CENTER,recipeDetail);
		MainFrame.repaint();
		MainFrame.setVisible(true);
		
	}

	public void shareRecipe() 
	{
	//TODO Research & Write
		
//		String mailTo = "test@domain.com";
//		String cc = "test2@domain.com";
//		String subject = this.name;
		String body = "this is a test /n this is only a test";
		final String mailURIStr = String.format("mailto:?subject=" + buildName() + "&cc=&body=" + buildBody());
//		new URI("mailto","myemail@email.com&SUBJECT=Test Subject&BODY=<html><body><i>Test body line #1</i></body></html>",null)
		
		try {
			final URI mailURI = new URI(mailURIStr);
			Desktop desktop;
			desktop = Desktop.getDesktop();
			desktop.mail(mailURI);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String buildName()
	{
		String subject = "";
		//TODO figure out formating
		String[] split = this.name.split(" ");
		for(String i: split)
		{
			subject += ("_" + i);
		}
		//subject = name.replace(" ", "%20");
		return subject;
	}
	private String buildBody()
	{
		String body = "";
		//TODO figure out formating
		//name
		String[] split = this.name.split(" ");
		for(String i: split)
		{
			body += ("_" + i);
		}
		//description
		split = this.description.split(" ");
		for(String i: split)
		{
			body += ("_" + i);
		}
		//ingredients
		for(Ingredient i: ingredientList)
		{
			body += (i.getQuantity() + "_");
			body += (i.getUnits() + "_");
			
			split = i.getName().split(" ");
			for(String j: split)
			{
				body += ("_" + j);
			}
		}
		for(Instruction i: this.instructionList)
		{
			split = i.getInstruction().split(" ");
			for(String j: split)
			{
				body += ("_" + j);
			}
		}
		return body;
	}

	private String urlEncode(String str) 
	{
	    try 
	    {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) 
	    {
	        throw new RuntimeException(e);
	    }
	}

	public void makeRecipeFile() throws IOException
	{
		//create text file
		PrintWriter file = new PrintWriter("tmp/temp.rtf");
		
		file.println(name);
		file.println();//space
		file.println(description);
		file.println();//space
		
		//ingredients
		for (Ingredient ingredient : ingredientList)
		{
			if (ingredient.getUnits() == null)
			{
				file.println(Double.toString(ingredient.getQuantity()) + "		" + ingredient.getName());
			}
			else
			{
				file.println(Double.toString(ingredient.getQuantity()) + ingredient.getUnits() + "		" + ingredient.getName());
			}
		}
		file.println();//space
		
		//directions
		int instructionCount = 0;
		for (Instruction instruct : instructionList)
		{
			instructionCount++;
			file.println(instructionCount + ". " + instruct.getInstruction());
		}
		file.println();//space
		file.println("Prep Time: " + prepTime + "mins");
		file.println("Cook Time: " + cookTime + "mins");
		file.println("Total time: " + (prepTime+cookTime + "mins"));
		file.println();//space
		file.println("Serves: " + numServings);
		file.println();//space
			
		file.close();
	}
	
	public void printRecipeFile()
	{
		try 
		{
			makeRecipeFile();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //makes text file of recipe
		
		try 
		{
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) 
			{
				desktop = Desktop.getDesktop();
			}

		   	desktop.print(new File("tmp/temp.rtf"));
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}		
	}

	public boolean editRecipe(JFrame mainFrame) 
	{
		drawAddRecipeForm(mainFrame);
		return true;
	}
	public void drawAddRecipeForm(JFrame mainFrame) 
	{//edit Recipe
		mainFrame.remove(mainFrame.getContentPane().getComponent(1));
		JPanel editFormJPanel = new JPanel();
		JTextField recipeNameField = new JTextField();
		JTextArea instructionTextArea = new JTextArea();
		JCheckBox favoirateCheckBox = new JCheckBox("Favorite");
		JTextField descriptionTextField = new JTextField();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		GridBagLayout formLayout = new GridBagLayout();
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		JPanel ingredPanel = new JPanel();
		//-------Set location----------
		editFormJPanel.setLayout(formLayout);
		editFormJPanel.setName("editForm");
		mainFrame.add(BorderLayout.CENTER,editFormJPanel);
		//-------Name Label------------
		labels.add(new JLabel());
		GridBagConstraints layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 0;
		layoutCont.gridy = 0;
		layoutCont.gridwidth = 1;
		layoutCont.gridheight = 1;
		layoutCont.ipady = 1;
		layoutCont.ipadx = 1;
		labels.get(0).setText("Name: ");
		labels.get(0).setFont(new Font("Times New Roman",Font.BOLD,12));
		formLayout.setConstraints(labels.get(0), layoutCont);
		editFormJPanel.add(labels.get(0));
		//-------Name text filed
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 1;
		layoutCont.gridy = 0;
		formLayout.setConstraints(recipeNameField, layoutCont);
		recipeNameField.setText(this.name);
		editFormJPanel.add(recipeNameField);
		//-------Favorite check box
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 2;
		layoutCont.gridy = 0;
		favoirateCheckBox.setSelected(this.getIsFavorite());
		formLayout.setConstraints(favoirateCheckBox, layoutCont);
		editFormJPanel.add(favoirateCheckBox);
		//---------description label
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 0;
		layoutCont.gridy = 1;
		labels.add(new JLabel("Description: "));
		formLayout.setConstraints(labels.get(1), layoutCont);
		editFormJPanel.add(labels.get(1));
		//---------description Text field
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 1;
		layoutCont.gridy = 1;
		descriptionTextField.setText(this.description);
		formLayout.setConstraints(descriptionTextField, layoutCont);
		editFormJPanel.add(descriptionTextField);
		//-------Ingredients Panel
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 0;
		layoutCont.gridy = 2;
		layoutCont.gridheight = 2;
		layoutCont.gridwidth = 5;
		JScrollPane ingredScrollPane = new JScrollPane(ingredPanel);
		formLayout.setConstraints(ingredScrollPane, layoutCont);
		GridBagConstraints ingredLayout = new GridBagConstraints();
		GridBagLayout ingredlayoutManager = new GridBagLayout();
		ingredScrollPane.setPreferredSize(new Dimension(mainFrame.getWidth(),200));
		ingredPanel.setLayout(ingredlayoutManager);
			//------ingredients list
			JLabel ingreNameLabels = new JLabel("Name");
			JLabel ingreQtyLabels = new JLabel("QTY");
			JLabel ingreUnitLabels = new JLabel("Unit");
			ArrayList<JTextField> ingreName = new ArrayList<JTextField>();
			ArrayList<JTextField> ingreQty = new ArrayList<JTextField>();
			ArrayList<JComboBox<String>> ingreDropDown = new ArrayList<JComboBox<String>>();
			String[] Units = UnitType.getAllUnits();
			ingredLayout = new GridBagConstraints();
			ingredLayout.gridx = 0;
			ingredLayout.gridy = 0;
			ingredlayoutManager.setConstraints(ingreNameLabels, ingredLayout);
			ingredPanel.add(ingreNameLabels);
			ingredLayout = new GridBagConstraints();
			ingredLayout.gridx = 1;
			ingredLayout.gridy = 0;
			ingredlayoutManager.setConstraints(ingreQtyLabels, ingredLayout);
			ingredPanel.add(ingreQtyLabels);
			ingredLayout = new GridBagConstraints();
			ingredLayout.gridx = 2;
			ingredLayout.gridy = 0;
			ingredlayoutManager.setConstraints(ingreUnitLabels, ingredLayout);
			ingredPanel.add(ingreUnitLabels);
			//------read from recipe
			for (int i = 0; i < ingredientList.size();i++){
				ingreName.add(new JTextField(ingredientList.get(i).getName()));
				ingreName.get(i).setPreferredSize(new Dimension(200,30));
				ingreQty.add(new JTextField(String.valueOf(ingredientList.get(i).getQuantity())));
				ingreDropDown.add(new JComboBox<String>(Units));
				if (ingredientList.get(i).getUnits() == null){
					ingreDropDown.get(i).setSelectedIndex(9);
				}else{
					switch (ingredientList.get(i).getUnits()) {
					case CUPS:
						ingreDropDown.get(i).setSelectedIndex(0);
						break;
					case OUNCES:
						ingreDropDown.get(i).setSelectedIndex(1);
						break;
					case TABLESPOONS:
						ingreDropDown.get(i).setSelectedIndex(2);
						break;
					case TEASPOONS:
						ingreDropDown.get(i).setSelectedIndex(3);
						break;
					case GALLONS:
						ingreDropDown.get(i).setSelectedIndex(4);
						break;
					case LITERS:
						ingreDropDown.get(i).setSelectedIndex(5);
						break;
					case QUART:
						ingreDropDown.get(i).setSelectedIndex(6);
						break;
					case MILLILITERS:
						ingreDropDown.get(i).setSelectedIndex(7);
						break;
					case POUNDS:
						ingreDropDown.get(i).setSelectedIndex(8);
						break;
					default:
						ingreDropDown.get(i).setSelectedIndex(9);
						break;
					}
				}
				ingredLayout = new GridBagConstraints();
				ingredLayout.gridx = 0;
				ingredLayout.gridy = i+1;
				ingredlayoutManager.setConstraints(ingreName.get(i), ingredLayout);
				ingredPanel.add(ingreName.get(i));
				ingredLayout = new GridBagConstraints();
				ingredLayout.gridx = 1;
				ingredLayout.gridy = i+1;
				ingredlayoutManager.setConstraints(ingreQty.get(i), ingredLayout);
				ingredPanel.add(ingreQty.get(i));
				ingredLayout = new GridBagConstraints();
				ingredLayout.gridx = 2;
				ingredLayout.gridy = i+1;
				ingredlayoutManager.setConstraints(ingreDropDown.get(i), ingredLayout);
				ingredPanel.add(ingreDropDown.get(i));
			}
			//ingredScrollPane.add(ingredPanel);
			editFormJPanel.add(ingredScrollPane);
			//------add line button
				buttons.add(new JButton("Add"));		
		//-------instructionTextArea
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 0;
		layoutCont.gridy = 5;
		layoutCont.gridwidth = 5;
		layoutCont.gridheight = 3;
		JScrollPane instructionScrooll = new JScrollPane(instructionJtext);
		instructionScrooll.setPreferredSize(new Dimension(mainFrame.getWidth(),mainFrame.getHeight()-500));
		instructionScrooll.add(instructionTextArea);
		formLayout.setConstraints(instructionScrooll, layoutCont);		
		for (int i = 0; i < instructionList.size();i++){
			instructionTextArea.append(instructionList.get(i).getInstruction());
			instructionTextArea.append("\r\n");
		}
		instructionTextArea.setLineWrap(true);// set text wrap
		instructionTextArea.setEditable(true);
		instructionTextArea.setWrapStyleWord(true); // text wrap by words
		instructionTextArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		editFormJPanel.add(instructionScrooll);
		
		// save Button and Cancel button
		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 3;
		layoutCont.gridy = 13;
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		formLayout.setConstraints(saveButton,layoutCont);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				//TODO: Update recipe	
				Recipe temp = new Recipe();
				temp.setName(recipeNameField.getText());
				temp.setFavorite(favoirateCheckBox.isSelected());
				String[] lineString = instructionTextArea.getText().split("\r\n");
				for (int i = 0; i <lineString.length;i++){
					temp.setInstruction(i+1, lineString[i]);
				}
				updateRecipe(temp);
			}
		});
		editFormJPanel.add(saveButton);

		layoutCont = new GridBagConstraints();
		layoutCont.anchor = GridBagConstraints.NORTH;
		layoutCont.gridx = 4;
		layoutCont.gridy = 13;
		formLayout.setConstraints(cancelButton,layoutCont);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				drawRecipe(mainFrame);
			}
		});
		editFormJPanel.add(cancelButton);
		mainFrame.repaint();
		mainFrame.setVisible(true);
	}
	
	public void updateRecipe(Recipe updatedRecipe){
		
	}
	
	public void editMsgConfirm(boolean isTrue) 
	{
		//TODO Write method
	}

	public void consolePrint() 
	{
		int control;
		System.out.println(name);
		System.out.println();//space
		System.out.println(description);
		System.out.println();//space
		//ingredients
		for (Ingredient ingredient : ingredientList)
		{
			System.out.println(ingredient.getQuantity() + "	" + ingredient.getUnits() + "	" + ingredient.getName());
		}
		System.out.println();//space
		//directions
		for (Instruction instruct : instructionList)
		{
			System.out.println(instruct.getInstruction());
		}
		System.out.println();//space
		System.out.println("Prep Time: " + prepTime + "mins");
		System.out.println("Cook Time: " + cookTime + "mins");
		System.out.println("Total time: " + (prepTime+cookTime + "mins"));
		System.out.println();//space
		System.out.println("Serves: " + numServings);
		System.out.println();//space
		
	}
	
	@Override
	public int compareTo(Object compared) 
	{
		String nameOne = ((Recipe)compared).getName();
		return getName().compareTo(nameOne);
	}
	
	public String toString() 
	{
		return name;
	}
	

	public static void main(String [] args) throws IOException
	{
		
		RecipeLibrary RL = new RecipeLibrary();
		Recipe recipeFile = RL.getrLibrary().get(0);
		//recipeFile.makeRecipeFile();
		//recipeFile.printRecipeFile();
		recipeFile.shareRecipe();
	}
}
