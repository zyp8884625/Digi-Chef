import java.awt.*;

import javax.swing.*;

import java.awt.Desktop;
import java.awt.event.*;
import java.net.*;
import java.io.*;

//Class for definitions in the dictionary
public class Definition implements Comparable
{
	//constants
	private final String GOOGLESEARCH = "http://images.google.com/images?q=";
	
	private String name, definition, equivalence, varieties;
	private Category category;
	
	//--------For GUI---------
	private JButton imageSearch;
	private JPanel definJPanel;
	private JTextArea definitionArea;
	private JScrollPane definJScrollPane;
	
	//constructor
	public Definition(String defName, String def, String equal) 
	{
		name = defName;
		definition = def;
		equivalence = equal;
	}//add by Yunpeng
	
	public Definition()
	{
		this.name = null;
		this.definition = null;
		this.equivalence = null;
		this.category = null;
	}
	
	public String getName() {
		return name;
	}
	public String getDefinition() {
		return definition;
	}

	public String getEquivalence() {
		return equivalence;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @return the varieties
	 */
	public String getVarieties() {
		return varieties;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public void setEquivalence(String equivalence) {
		this.equivalence = equivalence;
	}
	
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param varieties the varieties to set
	 */
	public void setVarieties(String varieties) 
	{
		this.varieties = varieties;
	}
	
	public void googleSearch()
	{
		String[] exploded=name.split(" ");
		String imageUrl = GOOGLESEARCH;
		for(String s: exploded)
		{
			imageUrl = imageUrl + s;
		}
		try 
		{
			URI imageURI = new URI((imageUrl));
			//TODO call desktop browser
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) 
			{
				desktop = Desktop.getDesktop();
			}

			desktop.browse(imageURI);
		} 
		catch (URISyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public int compareTo(Object definition) 
	{
		System.out.println("CHECK CHECK");
		String nameOne = ((Definition)definition).getName();
		return getName().compareTo(nameOne);
		}
	public String toString() 
	{
		return name;
	}

	public void consolePrint() 
	{
		System.out.println("Name:	" + name);
		System.out.println("definintion:	" + definition);
		System.out.println("Varieties:	" + varieties);
		System.out.println("Equivalencies:	" + equivalence);
		System.out.println("Category:	" + category);
		System.out.println();

	}

	public void drawDefinition(JFrame mainFrame) {
		// TODO Auto-generated method stub
		mainFrame.remove(mainFrame.getContentPane().getComponent(1));
		//buttons
		imageSearch = new JButton();
		imageSearch.setText("Image Search");
		imageSearch.addActionListener(new ActionListener() // add ActionListener for imageSearch
		{ 
            public void actionPerformed(ActionEvent ae)
            {
            	googleSearch();
            }
		});
		
		definJPanel = new JPanel();
		definJPanel.setName("DefinitionDetial");
		definitionArea = new JTextArea();
		definJScrollPane = new JScrollPane(definitionArea);
		definJScrollPane.setPreferredSize(new Dimension(1024,300));getClass();
		definitionArea.setEditable(false);
		definitionArea.setSize(new Dimension(1024,300));
		definitionArea.append(name);
		definitionArea.append("\r\n");
		definitionArea.append(definition);
		definitionArea.append("\r\n");
		definitionArea.append("Varieties: " + varieties);
		definitionArea.append("\r\n");
		definitionArea.append("Equivalencies: "+equivalence);
		definitionArea.append("\r\n");
		definitionArea.append("Category: " + category.toString());
		definitionArea.append("\r\n");
		definitionArea.setWrapStyleWord(true);
		definitionArea.setLineWrap(true);
		definitionArea.setFont(new Font("Times New Roma", Font.BOLD, 14));
		definJPanel.add(definitionArea);
		definJPanel.add(imageSearch);
		definJPanel.setLayout(new FlowLayout());
		mainFrame.add(definJPanel);
		mainFrame.repaint();
		mainFrame.setVisible(true);
		
	}
	public static void main(String[] args)
	{
		Dictionary dict = new Dictionary();
		
		dict.getDefinitions().get(0).googleSearch();
	}
}