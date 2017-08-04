package encoder;
/******************************************
 * File Encoder Form
 * 
 * author: Amy Burns
 * date:   3/2012
 * 
 * Purpose:  This program serves as the interface for the File Handler class
 * and allows a file to be opened, viewed, edited, and saved.  The data will
 * be rendered
 *
 * 
 ******************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class FileEncoderFormv1 extends JFrame
{
	private static final int WIDTH = 820;
	private static final int HEIGHT = 310;

	private ButtonGroup rbGroup = new ButtonGroup();

	private JTextField description = new JTextField(100);	
	private JTextField username = new JTextField(100);
	private JTextField password = new JTextField(100);
	private JTextField notes = new JTextField(200);

	private JRadioButton encode1;
	private JRadioButton encode2;
	private JRadioButton encode3;

	private JButton addRecBtn = new JButton(" Add Record ");
	private JButton findLineBtn = new JButton("  Edit / View  ");
	private JButton deleteRecBtn = new JButton("Delete Record");

	private JButton saveRecBtn = new JButton(" Save Record ");
	private JButton clearBtn = new JButton("Clear Values");

	private JButton openFileBtn = new JButton("Open File");
	private JButton saveFileBtn = new JButton("Save File");

	private JComboBox descriptionLine; 
	private String[] descriptionList  = {"Open password file to populate this list."};
	
	private char addOrEdit='A';
	private char ch;

	FileHandler selectedFile = new FileHandler();

	//**************************************
	// constructor
	public FileEncoderFormv1()
	{
		setSize(WIDTH, HEIGHT);
		setTitle("File Encoder");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
		
		disableTextBoxAccess();
	} // end  constructor

	//**************************************

	 
	//Create components and add to window.
	private void createContents()
	{
		// Need windowPanel for south-panel separation and outer margin.
		JPanel windowPanelEncoding = new JPanel(new BorderLayout(0, 10));
		windowPanelEncoding.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowPanelEncoding.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel northPanel = new JPanel(new FlowLayout());	// Description selection and add, edit, delete buttons
		JPanel eastPanel = new JPanel(new GridLayout(6, 1, 5, 5));	// Encoding level options
		JPanel centerPanel = new JPanel(new GridLayout(6, 2, 5, 5));	// Edit values
		JPanel westPanel = new JPanel(new GridLayout(6, 1, 5, 5));  // not used
		JPanel southPanel = new JPanel(new GridLayout(1, 3, 5, 5));	// File handling buttons
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		centerPanel.setAlignmentY(RIGHT_ALIGNMENT);
   
		/********************* West Panel - empty ********************************/
		// West Panel is not in use.


		/********************* North Panel - Select description to view/edit or delete ************************/
		northPanel.add(addRecBtn);
		northPanel.add(new JLabel("Select an entry to view/edit:  ")  );
		descriptionLine = new JComboBox(descriptionList);
		northPanel.add(descriptionLine);		 
		northPanel.add(findLineBtn);
		northPanel.add(deleteRecBtn);
		
		// add listeners to Edit/view and Delete Record buttons
		addRecBtn.addActionListener(new ButtonListener());
		findLineBtn.addActionListener(new ButtonListener());
		deleteRecBtn.addActionListener(new ButtonListener());


		/********************* East Panel - Encoding Options********************************/
		encode1 = new JRadioButton("Level 1 Encoding    ", true);
		encode2 = new JRadioButton("Level 2 Encoding    ");
		encode3 = new JRadioButton("Level 3 Encoding    ");

		rbGroup.add(encode1);
		rbGroup.add(encode2);
		rbGroup.add(encode3);

		eastPanel.add(new JLabel("  Encoding Options:  "));
		eastPanel.add(encode1);
		eastPanel.add(encode2);
		eastPanel.add(encode3);
		eastPanel.add(new Label());   // filler   


		/********************* Center Panel - Add/Edit Data Line********************************/
		centerPanel.add(new JLabel("  Add/Edit Data:  "));
		centerPanel.add(new Label());   // filler
		centerPanel.add(new Label("     Description:  "));   
		centerPanel.add(description);
		centerPanel.add(new Label("     Username:  "));
		centerPanel.add(username);    
		centerPanel.add(new Label("     Password:  "));
		centerPanel.add(password);
		centerPanel.add(new Label("     Notes:  "));
		centerPanel.add(notes);
		centerPanel.add(saveRecBtn);     
		centerPanel.add(clearBtn);

		// add key listeners to textfields
		description.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				ch=e.getKeyChar();
			    if ( ch==KeyEvent.VK_SEMICOLON)   // If a semicolon was typed, remove it and display a message
			    {
			    	JOptionPane.showMessageDialog(null, "Semicolons can not be used.");
			    	e.setKeyChar(KeyEvent.CHAR_UNDEFINED);
			    }
			}
		});

		username.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				ch=e.getKeyChar();
			    if ( ch==KeyEvent.VK_SEMICOLON)    // If a semicolon was typed, remove it and display a message
			    {
			    	JOptionPane.showMessageDialog(null, "Semicolons can not be used.");
			    	e.setKeyChar(KeyEvent.CHAR_UNDEFINED);
			    }
			}
		});
		
		password.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				ch=e.getKeyChar();
			    if ( ch==KeyEvent.VK_SEMICOLON)    // If a semicolon was typed, remove it and display a message
			    {
			    	JOptionPane.showMessageDialog(null, "Semicolons can not be used.");
			    	e.setKeyChar(KeyEvent.CHAR_UNDEFINED);
			    }
			}
		});
		
		notes.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				ch=e.getKeyChar();
			    if ( ch==KeyEvent.VK_SEMICOLON)    // If a semicolon was typed, remove it and display a message
			    {
			    	JOptionPane.showMessageDialog(null, "Semicolons can not be used.");
			    	e.setKeyChar(KeyEvent.CHAR_UNDEFINED);
			    }
			}
		});
		
		// add action listeners to buttons - Save Record
		saveRecBtn.addActionListener(new ButtonListener());
		clearBtn.addActionListener(new ButtonListener());


		/********************* South Panel - File Handling Buttons ********************************/
		southPanel.add(openFileBtn);
		southPanel.add(new JLabel( ) );  // Filler
		southPanel.add(saveFileBtn);    

		// add action listeners to buttons - Open File, Save File
		openFileBtn.addActionListener(new ButtonListener());
		saveFileBtn.addActionListener(new ButtonListener());

		// add all panels to the window
		windowPanelEncoding.add(northPanel, BorderLayout.NORTH);
		windowPanelEncoding.add(westPanel, BorderLayout.WEST);
		windowPanelEncoding.add(centerPanel, BorderLayout.CENTER);
		windowPanelEncoding.add(eastPanel, BorderLayout.EAST);
		windowPanelEncoding.add(southPanel, BorderLayout.SOUTH);
		add(windowPanelEncoding);

	} // end createContents


	private void loadDescriptionList()
	{	
		// Load all descriptions from file into combobox
		clearDescriptionList();
		
		for (int i=0; i<= selectedFile.descList.length-1; i++)
		{
			descriptionLine.addItem(selectedFile.descList[i]);
		}
	
		setEncodingValue(); 
		validate();
	}

	
	private void setEncodingValue()
	{
		// handle radio button value changes
		if ( selectedFile.encodingType.equals("E1") )
		{
			this.encode1.setSelected(true);
		}
		else if ( selectedFile.encodingType.equals("E2") )
		{
			this.encode2.setSelected(true);			
		}
		else if ( selectedFile.encodingType.equals("E3") )
		{
			this.encode3.setSelected(true);			
		}
	}
	
	
	private void clearDescriptionList()
	{
		// clear descriptions from combobox
		descriptionLine.setSelectedIndex(-1);

		for (int i=0; i<= descriptionLine.getItemCount()-1; i++)
		{  
			descriptionLine.removeItemAt(i);
		}
		//descriptionLine.addItem("Open password file to populate this list.");
		descriptionLine.setSelectedIndex(-1);
	}

	private void clearTextBoxes()
	{
		description.setText("");
		username.setText("");
		password.setText("");
		notes.setText("");
	}
	
	private void disableTextBoxAccess()
	{
		//description.setEnabled(false);
		description.setEditable(false);
		username.setEditable(false);
		password.setEditable(false);
		notes.setEditable(false);		
	}
	
	private void enableTextBoxAccess()
	{
		// description field cannot be edited.
		//description.setEditable(true);
		username.setEditable(true);
		password.setEditable(true);
		notes.setEditable(true);		
	}



	//***************************************  Button Listener class ************************************************//
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 	
			// Handle Add record button
			if (e.getSource() == addRecBtn)
			{ 
				{
					addOrEdit = 'A';
					clearTextBoxes(); 
					description.setEditable(true);
					enableTextBoxAccess();
					validate();
				}
			}  // end if - addRecBtn (add new record)
			
			// Handle edit/view button
			if (e.getSource() == findLineBtn)
			{ 
				// if value selected, call search code to retrieve selected record 
				// from array and load into text boxes
				int i = selectedFile.findDescriptionLineNum(descriptionLine.getSelectedItem().toString());
				if (i >= 0)
				{
					addOrEdit = 'E';
					enableTextBoxAccess();
					description.setText( selectedFile.fileData[i][0] );
					username.setText ( selectedFile.fileData[i][1] );
					password.setText ( selectedFile.fileData[i][2] );
					notes.setText( selectedFile.fileData[i][3] ); 		
					validate();
				}
			}  // end if - find line (edit/view)
			
			// Handle save record button
			else if (e.getSource() == saveRecBtn)
			{
				selectedFile.saveRecord(description.getText(), username.getText(), password.getText(), notes.getText(), addOrEdit);
				disableTextBoxAccess();
					
				clearTextBoxes();
				validate();

			}  // end else if save record
			
			// Handle delete record button
			else if (e.getSource() == deleteRecBtn)
			{
				disableTextBoxAccess();
				// if record is found, delete from array
				int currentLine = selectedFile.findDescriptionLineNum(descriptionLine.getSelectedItem().toString() );
				
				// decrement the record count
				selectedFile.numLines --;
				if  ( currentLine >= 0 )
				{
					descriptionLine.removeItemAt(descriptionLine.getSelectedIndex() );
					descriptionLine.setSelectedIndex(-1);

					selectedFile.fileData[currentLine][0] = "DELETED";
					selectedFile.fileData[currentLine][1] = "";
					selectedFile.fileData[currentLine][2] = "";
					selectedFile.fileData[currentLine][3] = "";			
					validate();
				}
			}  // end else if - delete record

			// handle open file button
			else if (e.getSource() == openFileBtn)
			{	disableTextBoxAccess();

				// load file selection dialog, select file, returns
				// array of descriptions, sorted in alpha order and file array

				if (! selectedFile.fileStatus.equals("O")  )
				{
					if ( selectedFile.selectAndOpenFile() )
					{
					loadDescriptionList();
					addOrEdit = 'E';
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please close the file you currently have open.");
			}  // end else if - open file
			
			// handle save file button
			else if (e.getSource() == saveFileBtn)
			{
				disableTextBoxAccess();
				String fileEncType = "E1";

				if (selectedFile.numLines > 0 || selectedFile.newRecCount > 0)
				{
					if (encode1.isSelected())
						fileEncType = "E1";
					else if (encode2.isSelected())
						fileEncType = "E2";
					else if (encode3.isSelected())
						fileEncType = "E3";

					// write array contents to selected file and save			
					if ( selectedFile.saveFile(selectedFile.fileData, fileEncType) )
					{
						JOptionPane.showMessageDialog(null, "File has been saved.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "File save was not successful.");
					}
					selectedFile.closeFile();
				}
				else
					JOptionPane.showMessageDialog(null, "There are no records to save.");
					
			} // end else if - save file
			
			// handle clear button
			else if (e.getSource() == clearBtn)
			{	disableTextBoxAccess();
				clearTextBoxes();	 
				validate();
			}  // end else if - clear text boxes

		} // end actionPerformed
	} // end class ButtonListener

	

	//**************************************

	public static void main(String[] args)
	{
		new FileEncoderFormv1();
	}
	
} // end class FileEncoderForm
