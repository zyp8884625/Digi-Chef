import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;

import javafx.print.PageLayout;

import javax.swing.*;

import com.sun.beans.infos.ComponentBeanInfo;
import com.sun.glass.ui.Screen;

public class Chef 
{
	private final String LOGOFILE = "dclogo.jpg";
	
	private RecipeLibrary library; //RecipeLibrary
	private Dictionary dictionary; //Dictionary
	
	static JFrame frame ;	//Frame
	static JPanel toolBar;  //ToolBar
	static JButton[] topButton; //Button in ToolBar
	static java.lang.String[] ButtonText;  // Button Text
	static JLabel midPicLabel;
	static ImageIcon midPicIcon;
	static JPanel midPicPanel;
	static Dimension screenSize;
	static Dimension defaultFramsize;
	
	public Chef()
	{
		library = new RecipeLibrary();
		dictionary = new Dictionary(); //Dictionary
		toolBar = new JPanel(); //ToolBar
		midPicPanel = new JPanel();
		topButton = new JButton[5]; //Button in ToolBar
		ButtonText = new java.lang.String[5]; // Button Text
		midPicIcon = new ImageIcon(LOGOFILE);
		ButtonText[0] = "Home";				//set button text
		ButtonText[1] = "Recipe Library";
		ButtonText[2] = "Dictionary";
		ButtonText[3] = "Converter";
		ButtonText[4] = "Favorite";
		for (int i = 0; i < 5; i++){			//add button to top toolbar
			topButton[i] = new JButton();
			topButton[i].setText(ButtonText[i]);
			topButton[i].setSize(1028, 768);
			topButton[i].setFont(new Font("Times New Roma",Font.BOLD,20)); //set the text size
		}										//---------------
		topButton[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            	reDraw();
            }
		});
		topButton[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            	drawRecipeLibrary();
            }
		});
		topButton[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            	drawDictionary();
            }
		});
		topButton[3].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            	drawUnitConverter();
            }
		});
		topButton[4].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            	drawFavorite();
            }
		});
	}
	public void drawMainPage(){
		//TODO: Adjust window size by screen size!
		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get Screen Size
		defaultFramsize = new Dimension(1366, 768);
		frame = new JFrame("Digi-Chef");
		midPicIcon.getImage();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(defaultFramsize.height > screenSize.height){		//check default size is fit for screen
			defaultFramsize.height = screenSize.height;
		}
		if(defaultFramsize.width > screenSize.width){
			defaultFramsize.width = screenSize.width;
		}
		frame.setSize(defaultFramsize);		 		//set frame size
		toolBar.setLayout(new GridLayout(1,5)); //set ToolBar page lay out
		toolBar.setVisible(true);
		toolBar.setPreferredSize(new Dimension (defaultFramsize.width, 80)); //set the toolbar size
		toolBar.setBorder(BorderFactory.createTitledBorder(""));
		toolBar.setName("toolBar");
		frame.add(BorderLayout.NORTH,toolBar);  //add ToolBar to main frame, and set location to North
		
		midPicLabel = new JLabel(midPicIcon);
		midPicLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		midPicLabel.setPreferredSize(new Dimension(defaultFramsize.width,defaultFramsize.height-80));
		midPicPanel.add(midPicLabel);
		midPicPanel.setPreferredSize(new Dimension(defaultFramsize.width,defaultFramsize.height-80));
		frame.add(BorderLayout.CENTER,midPicPanel);
						//---------------
		for (int i = 0; i < 5; i++){			//add button to top toolbar
			toolBar.add(topButton[i]);
		}									//---------------
			frame.setVisible(true);
	}
	public void drawRecipeLibrary()
	{

			frame.remove(frame.getContentPane().getComponent(1));
			frame.remove(midPicLabel);
			library.drawRecipeLibrary(frame, library.getrLibrary());
			frame.setVisible(true);
	}
	public void drawDictionary(){

			frame.remove(frame.getContentPane().getComponent(1));
			frame.remove(midPicLabel);
			dictionary.drawDictionary(frame);
			frame.setVisible(true);
	}
	public void drawUnitConverter(){
		//TODO: Need UnitConverter!
		frame.remove(frame.getContentPane().getComponent(1));
		frame.remove(midPicLabel);
		UnitType.drawUnitConverter(frame);
		frame.setVisible(true);
	}
	public void drawFavorite(){
			frame.remove(frame.getContentPane().getComponent(1));
			frame.remove(midPicLabel);
			library.drawRecipeLibrary(frame,library.getFavorites());
			frame.setVisible(true);
	}
	public static void main(String[] args){
		Chef c = new Chef();
		c.drawMainPage();
	}
	public void reDraw(){
		//TODO:make home button didn't open a new window
		if(frame.getContentPane().getComponent(1).getName() == "toolBar"){
			return;
		}else{
			frame.remove(frame.getContentPane().getComponent(1));
			midPicLabel = new JLabel(midPicIcon);
			midPicLabel.setBorder(BorderFactory.createLineBorder(Color.red));
			midPicLabel.setPreferredSize(new Dimension(defaultFramsize.width,defaultFramsize.height-80));
			midPicPanel.add(midPicLabel);
			midPicPanel.setPreferredSize(new Dimension(defaultFramsize.width,defaultFramsize.height-80));
			frame.add(BorderLayout.CENTER,midPicPanel);
			frame.repaint();
			frame.setVisible(true);
		}	
	}
	public Component getCompentByName(String name){
		Component[] listofComponents = frame.getComponents();
		for (int i = 0; i < listofComponents.length; i++){
			if (listofComponents[i].getName() == name){
				return listofComponents[i];
			}
		}
		return null;
	}
}
