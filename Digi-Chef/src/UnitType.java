import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

public enum UnitType 
{
	CUPS(8.0, "cup"),
	OUNCES(1.0, "oz"),
	TABLESPOONS(0.5, "tbsp"),
	TEASPOONS(0.166667, "tsp"),
	GALLONS(128.0, "gal"),
	LITERS(33.814, "l"),
	QUART(32.0, "qt"),
	PINT(16.0, "pt"),
	MILLILITERS(0.033814, "ml"),
	POUNDS(16.0, "lbs");
		
	private final double inOunces;//in ounces
	private final String stringRep;
	//private final UnitType[] typeList = {UnitType.CUPS, UnitType.OUNCES, UnitType.TABLESPOONS, UnitType.TEASPOONS, UnitType.GALLONS, UnitType.LITERS, UnitType.QUART, UnitType.PINT, UnitType.MILLILITERS, UnitType.POUNDS};
	private static JPanel unitConverterJPanel;
	
	//Constructor
    private UnitType(double inOz, String value)
    {
        inOunces = inOz;
        stringRep = value;
    }
    
    public double convertUnits(UnitType to, double currentValue)
    {
    	return ((currentValue*inOunces)/to.inOunces);
    }
    
    public String toString()
	{
		return stringRep;
	}
    
    public static String[] getAllUnits()
    {
    	String[] allUnits = {"cups", "ounces", "tablespoons", "teaspoons", "gallons", "liters", "quarts", "pints", "milliliters", "pounds"};
    	return allUnits;
    }
   	
	private static UnitType stringToUnitType(String inputString)
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

	public static void drawUnitConverter(JFrame mainFrame) 
	{
		// TODO Auto-generated method stub
		unitConverterJPanel = new JPanel();
		GridBagConstraints unitPanellayout = new GridBagConstraints();
		GridBagLayout panelLayoutmanager = new GridBagLayout();
		unitConverterJPanel.setLayout(panelLayoutmanager);
		unitConverterJPanel.setName("unitConverter");
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 0;
		unitPanellayout.gridy = 0;
		JLabel fromLabel = new JLabel("From:");
		panelLayoutmanager.setConstraints(fromLabel, unitPanellayout);
		unitConverterJPanel.add(fromLabel);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 0;
		unitPanellayout.gridy = 1;
		JTextField fromBox = new JTextField("0");
		fromBox.setColumns(4);
		panelLayoutmanager.setConstraints(fromBox, unitPanellayout);
		unitConverterJPanel.add(fromBox);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 1;
		unitPanellayout.gridy = 0;
		String[] list = getAllUnits();
		JLabel fromUnitsLabel = new JLabel("From Units:");
		panelLayoutmanager.setConstraints(fromUnitsLabel, unitPanellayout);
		unitConverterJPanel.add(fromUnitsLabel);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 1;
		unitPanellayout.gridy = 1;
		JComboBox fromUnitsList = new JComboBox(list);
		panelLayoutmanager.setConstraints(fromUnitsList, unitPanellayout);
		unitConverterJPanel.add(fromUnitsList);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 2;
		unitPanellayout.gridy = 0;
		JLabel toUnitsLabel = new JLabel("To Units:");
		panelLayoutmanager.setConstraints(toUnitsLabel, unitPanellayout);
		unitConverterJPanel.add(toUnitsLabel);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 2;
		unitPanellayout.gridy = 1;
		JComboBox toUnitsList = new JComboBox(list);
		panelLayoutmanager.setConstraints(toUnitsList, unitPanellayout);
		unitConverterJPanel.add(toUnitsList);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 3;
		unitPanellayout.gridy = 0;
		JLabel resultLabel = new JLabel("Result:");
		panelLayoutmanager.setConstraints(resultLabel, unitPanellayout);
		unitConverterJPanel.add(resultLabel);
		
		unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 3;
		unitPanellayout.gridy = 1;
		JLabel result = new JLabel("0", JLabel.CENTER);
		Dimension d = result.getPreferredSize();
	    result.setPreferredSize(new Dimension(d.width+60,d.height));
	    panelLayoutmanager.setConstraints(result, unitPanellayout);
		unitConverterJPanel.add(result);
		
    	unitPanellayout = new GridBagConstraints();
		unitPanellayout.gridx = 4;
		unitPanellayout.gridy = 1;
    	JButton convert = new JButton("Convert");
		convert.addActionListener(new ActionListener() // add ActionListener for imageSearch
		{ 
            public void actionPerformed(ActionEvent ae)
            {
            	//success.setText("Success"); //for testing
            	//result.setText(stringToUnitType(fromUnitsList.getSelectedItem().toString()).convertUnits(stringToUnitType(toUnitsList.getSelectedItem().toString()), Double.parseDouble(fromBox.getText())).toString());
            	            	
            	double num = stringToUnitType(fromUnitsList.getSelectedItem().toString()).convertUnits(stringToUnitType(toUnitsList.getSelectedItem().toString()), Double.parseDouble(fromBox.getText()));
            	DecimalFormat df = new DecimalFormat("#.##");
            	//Double.toString(stringToUnitType(fromUnitsList.getSelectedItem().toString()).convertUnits(stringToUnitType(toUnitsList.getSelectedItem().toString()), Double.parseDouble(fromBox.getText())));
            	result.setText(df.format(num));
            }
		});
		panelLayoutmanager.setConstraints(convert, unitPanellayout);
		unitConverterJPanel.add(convert);
		
		mainFrame.add(unitConverterJPanel);
		mainFrame.repaint();
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		UnitType testA = UnitType.POUNDS;
		UnitType testB = UnitType.LITERS;
		UnitType testC = UnitType.GALLONS;
		UnitType testD = UnitType.OUNCES;
		
		System.out.println(testA.convertUnits(testB, 2.5));
		System.out.println(testC.convertUnits(testD, 1));
	}
}