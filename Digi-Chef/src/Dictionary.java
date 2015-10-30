import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import javax.swing.*;

public class Dictionary 
{
	private final String FILENAME = "bin/dictionary.txt";
	
	private ArrayList<Definition> definitions;
	private JPanel definitionList;
	private ArrayList<JButton> defList;
	//private JPanel searchBar;
	private String definitionName;
	//private JTextField search;
	//private JButton searchButton;
	
	//constructor
	public Dictionary()
	{ 
		definitions = new ArrayList<Definition>();
		defList = new ArrayList<JButton>();
		definitionList = new JPanel();
		//searchBar = new JPanel();
		try {
			loadArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sortArray(definitions);

	}
	public void drawDictionary(JFrame mainFrame){//draw dictionary interface
		int NumList = definitions.size();
		definitionList.removeAll();
		/*searchBar.removeAll();
		search = new JTextField("Search...");
		searchButton = new JButton("Search!");
		searchBar.add(search);
		searchBar.add(searchButton);
		definitionList.add(searchBar);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(searchArray(search.getText()).size() != 0){
					mainFrame.remove(mainFrame.getContentPane().getComponent(1));
					//drawDictionary(mainFrame, searchArray(search.getText()));
				}else
				{
					//drawDictionary(mainFrame);
					JOptionPane.showMessageDialog(null, "Nothing Found!");
				}
			}
		});*/
		for (int i = 0; i < NumList;i++){
			defList.add(new JButton(definitions.get(i).getName()));
			definitionList.add(defList.get(i));
			
			defList.get(i).addActionListener(new ActionListener(){ // add ActionListener
	            public void actionPerformed(ActionEvent ae){
	            	definitionName = ae.getActionCommand();
	            	drawDefinition(mainFrame);
	            }
			});
		}
		definitionList.setName("Dictionary");
		definitionList.setLayout(new GridLayout(10,10)); // change here to modify row and column of the words list.
		//First number is Row, second number is column, it should be depend on how many words we have
		definitionList.setBorder(BorderFactory.createTitledBorder(""));
		definitionList.setPreferredSize(new Dimension(1024,668));
		mainFrame.add(BorderLayout.CENTER,definitionList);
		mainFrame.repaint();
		
		
	}
	
	
	public void drawDefinition(JFrame mainFrame)//call draw in definition class
	{
		for (int i = 0; i < definitions.size(); i++)
		{
			if (definitionName == definitions.get(i).getName())
			{
				definitions.get(i).drawDefinition(mainFrame);
			}
		}
	}
	private boolean sortArray(ArrayList<Definition> result){//sort the array
		Collections.sort(result);
		return true;
	}
	/*ArrayList<Definition> searchArray(String searched) {
		ArrayList<Definition> list = new ArrayList<Definition>();
		for (Definition d : definitions) {
			//System.out.println(rLibrary.get(index).getName());
			if (searched.contains(d.getName())) {
				list.add(d);
			}
		}
		return list;
	}*/
	private Boolean loadArray() throws IOException
	{
		InputStream    input;
		BufferedReader buffer;
		String         line;
		Definition tempDef;
		
		input = new FileInputStream(FILENAME);
		buffer = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
		//read in recipe
		while ((line = buffer.readLine()) != null) 
		{
			//System.out.println(line); //for testing
			
			tempDef = new Definition();
			
			//name
			tempDef.setName(line);
			
			//definition
			line = buffer.readLine();
			tempDef.setDefinition(line);
			
			//varieties
			line = buffer.readLine();
			tempDef.setVarieties(line);
			
			//equivalence
			line = buffer.readLine();
			tempDef.setEquivalence(line);
			
			//category
			line = buffer.readLine();
			tempDef.setCategory(stringToCategory(line));
			
			//add to ArrayList
			definitions.add(tempDef);
			
			//print definition
			tempDef.consolePrint();
			
			//reset variables
			tempDef = null;
			
		}//end of while

		// Done with the file
		buffer.close();
		buffer = null;
		input = null;
		
		return true;
	}
	
	public Category stringToCategory(String inputString) {
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

	
	public static void main(String[] args)
	{
		Dictionary dictionary = new Dictionary();
	}
	/**
	 * @return the definitions
	 */
	public ArrayList<Definition> getDefinitions() {
		return definitions;
	}
}
