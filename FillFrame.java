package labs.lab9;

import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FillFrame extends JFrame 
{
		
	private JLabel nameLabel;
	
	private JLabel emailLabel;
	
	private JLabel petLabel;
	
	private JLabel spentLabel;
	
	private JLabel locationLabel;
	
	private JLabel notesLabel;
	
	// Checkbox Variables
	
	private JCheckBox dogCheckBox;
	private JCheckBox catCheckBox;
	private JCheckBox birdCheckBox;
	private JCheckBox fishCheckBox;
	private JCheckBox otherCheckBox;
	
	private ActionListener listener;
	
	private Set<String> setNames;
	
	//boolean editC = false;
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 100;
	
	
	private DefaultListModel<String> nameListModel;
	
	private JList<String> nameList;
	
	private List<Customer> custList;
	
	private JTextField nameField;

	private JTextField emailField;
	
	private JTextField spentField;
	
	private JComboBox<String> locationCombo;
	
	private JTextArea notesArea;
	
	
	public FillFrame() 
	{
		
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		listener = new ChoiceListener();
		
		custList = new ArrayList<>();
		
		setNames = new HashSet<>();
		
		nameListModel = new DefaultListModel<>();
		
		createComponents();
		
		
	}
	
	
	class ChoiceListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			;
		}
	}
	
	
	private void createComponents() 
	{
		
		
		final int FIELD_WIDTH = 10;

		//Panel to hold two panels side by side
		
		JPanel overallPanel = new JPanel();
		overallPanel.setLayout(new GridLayout(1, 2));
		
		
		
		//Left side panel of interface
	
		JPanel leftPanel = new JPanel();
		
		leftPanel.setLayout(new BorderLayout());
		
		
		
		//List of customers JList
			
		nameList = new JList<>(nameListModel);
		
		nameList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) 
            {
                if (!e.getValueIsAdjusting()) 
                {
                	
                    int selectedIndex = nameList.getSelectedIndex();
//                    
                    if (selectedIndex != -1) 
                    {
//
                    	Customer selectedCustomer = custList.get(selectedIndex);
                        String currentName = selectedCustomer.getName();
                        
                        nameField.setText(selectedCustomer.getName());
                        emailField.setText(selectedCustomer.getEmail());

                        dogCheckBox.setSelected(selectedCustomer.getDog());
                        catCheckBox.setSelected(selectedCustomer.getCat());
                        birdCheckBox.setSelected(selectedCustomer.getBird());
                        fishCheckBox.setSelected(selectedCustomer.getFish());
                        otherCheckBox.setSelected(selectedCustomer.getOther());

                        spentField.setText(String.valueOf(selectedCustomer.getTotal()));

                        locationCombo.setSelectedItem(selectedCustomer.getLocation());

                        notesArea.setText(selectedCustomer.getNotes());
                    }
                }
            }
        });
		
		leftPanel.add(nameList, BorderLayout.CENTER);
		
		
		//Delete Button
		
		JPanel leftButtonPanel = new JPanel();
		
		JButton deleteButton = new JButton("Delete");
		
		class DeleteButtonListener implements ActionListener {
		    public void actionPerformed(ActionEvent event) {
		    	
		        int selectedIndex = nameList.getSelectedIndex();

		        if (selectedIndex != -1) {
		        	
		            String selectedName = nameListModel.getElementAt(selectedIndex);
		            
		            //Find the chosen customer
		            Customer customerToDelete = null;
		            
		            for (Customer customer : custList) 
		            {
		                if (customer.getName().equals(selectedName)) 
		                {
		                    customerToDelete = customer;
		                    break;
		                }
		            }

		
		            if (customerToDelete != null) 
		            {
		            	
		                // Remove the customer from the list model and custList
		                nameListModel.removeElement(selectedName);
		                custList.remove(customerToDelete);
		                setNames.remove(selectedName);
		                
		            }
		        }
		    }
		}
		
		deleteButton.addActionListener(new DeleteButtonListener());
				
		leftButtonPanel.add(deleteButton);
		
		leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);
		
		//------------------------------------------------------------------------------------------------------------------------------------
			
		//Right side panel of interface
		JPanel rightPanel = new JPanel();
		
		rightPanel.setLayout(new GridLayout(7, 1));
		

		//Name panel with input
		
		JPanel namePanel = new JPanel();
				
		nameLabel = new JLabel("Name: ");
		
		nameField = new JTextField(FIELD_WIDTH);
		
		nameField.setText("");
			
		namePanel.add(nameLabel);
		
		namePanel.add(nameField);
		
		rightPanel.add(namePanel);
		
		
		//Email panel with input
		
		JPanel emailPanel = new JPanel();
		
		emailLabel = new JLabel("Email: ");
		
		emailField = new JTextField(FIELD_WIDTH);
		
		emailField.setText("");
			
		emailPanel.add(emailLabel);
		
		emailPanel.add(emailField);
		
		rightPanel.add(emailPanel);
		
		
		
		//Check boxes for pets
		
		rightPanel.add(createPetCheckBoxes());
		
		
		//Total Amount Spent Panel
		
		JPanel spentPanel = new JPanel();
		
		spentLabel = new JLabel("Total Amount Spent: ");
		
		spentField = new JTextField(FIELD_WIDTH);
		
		spentField.setText("0.0");
			
		spentPanel.add(spentLabel);
		
		spentPanel.add(spentField);
		
		rightPanel.add(spentPanel);
		
		
		
		//Home Store Location Panel
		
		JPanel locationPanel = new JPanel();
		
		locationLabel = new JLabel("Home Store Location: ");
		
		locationCombo = new JComboBox<>();
		
		locationCombo.addItem("Irvine");
		locationCombo.addItem("Los Angeles");
		locationCombo.addItem("Paris");
		locationCombo.addItem("Shanghai");
		locationCombo.addItem("New York");
		locationCombo.addItem("London");
				
		
		locationPanel.add(locationLabel);
		locationPanel.add(locationCombo);
		
		rightPanel.add(locationPanel);
		
		
		//Notes Panel
		
		JPanel notesPanel = new JPanel();
		
		notesLabel = new JLabel("Notes: ");
		
		//notesPanel.setSize(300, 200);
		
		notesArea = new JTextArea(3, 20);
		notesArea.setText("");
		notesArea.setEditable(true);
		
		
		
		notesPanel.add(notesLabel);
		notesPanel.add(notesArea);
		
		JScrollPane notesScroll = new JScrollPane(notesArea);
		
		notesPanel.add(notesScroll);

		
		rightPanel.add(notesPanel);
		
		
		
		
		//Buttons for Customer Panel
		
		JPanel buttonPanel = new JPanel();
		
		
		
		//Save Customer Button
		
		JButton saveCustomer = new JButton("Save Customer");
		buttonPanel.add(saveCustomer);
		
		//First implementing adding new customer
		class saveCustomerListener implements ActionListener 
		{
			
			private FillFrame frame;  // Instance variable to store the reference to FillFrame

		    // Constructor that takes a FillFrame instance as a parameter
		    public saveCustomerListener(FillFrame frame)
		    {
		        this.frame = frame;
		    }
		    
			
			public void actionPerformed(ActionEvent event) 
			{
							
				//Check for duplicates
				String custName = nameField.getText();
				
				String custEmail = emailField.getText();
				
				boolean custDog = dogCheckBox.isSelected();
				boolean custCat = catCheckBox.isSelected();
				boolean custBird = birdCheckBox.isSelected();
				boolean custFish = fishCheckBox.isSelected();
				boolean custOther = otherCheckBox.isSelected();
								
				double custTotal;
				
				try 
				{
					custTotal = Double.parseDouble(spentField.getText());
					
				}
				catch (NumberFormatException ex) 
				{
									
					JOptionPane.showMessageDialog(frame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
					
					return;
			    }
				
				
				String custLocation = (String)locationCombo.getSelectedItem();

				String notes = notesArea.getText();
				
				
				if(custEmail.length() == 0 || custEmail == null)
				{

					JOptionPane.showMessageDialog(frame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				//Selected item
				
				if(!nameList.isSelectionEmpty())
				{
					
					
					
					
					//Deletes current 
					int sIndex = nameList.getSelectedIndex();
					
					String selectedName = nameListModel.getElementAt(sIndex);
		            
		            //Find the chosen customer
		            Customer customerToDelete = null;
		            
		            for (Customer customer : custList) 
		            {
		                if (customer.getName().equals(selectedName)) 
		                {
		                    customerToDelete = customer;
		                    break;
		                }
		            }

		
		            if (customerToDelete != null) 
		            {
		            	
		                // Remove the customer from the list model and custList
		            	
		            	setNames.remove(selectedName);
		            	
		            	if(custName.length() == 0 || custName == null || setNames.contains(custName))
						{
							JOptionPane.showMessageDialog(frame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
							
							return;
						}
		            	
		                nameListModel.removeElement(selectedName);
		                custList.remove(customerToDelete);
		                
		                
		            }
		            
		            
		       
		            
		            //Creates new
		            
		            Customer custEdit = new Customer();
		    
	                    
		            custEdit.setName(custName);
					
					setNames.add(custName);
					
					custEdit.setEmail(custEmail);
					
					custEdit.setDog(custDog);
					custEdit.setCat(custCat);
					custEdit.setBird(custBird);
					custEdit.setFish(custFish);
					custEdit.setOther(custOther);
					
					custEdit.setTotal(custTotal);
					
					custEdit.setLocation(custLocation);
					
					custEdit.setNotes(notes);
                    
					nameListModel.addElement(custEdit.getName());					
					
					custList.add(custEdit);
										
					sortNameListModel();
					
					int newIndex = nameListModel.indexOf(custEdit.getName());
		            nameList.setSelectedIndex(newIndex);
					
					JOptionPane.showMessageDialog(frame, "Customer Saved!", "Success", JOptionPane.PLAIN_MESSAGE);
					
					
				}
				
				//Creating new customer
				else
				{
					
					if(custName.length() == 0 || custName == null || setNames.contains(custName))
					{
						JOptionPane.showMessageDialog(frame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					Customer cust = new Customer();
					
					cust.setName(custName);
					
					setNames.add(custName);
					
					cust.setEmail(custEmail);
					
					cust.setDog(custDog);
					cust.setCat(custCat);
					cust.setBird(custBird);
					cust.setFish(custFish);
					cust.setOther(custOther);
					
					cust.setTotal(custTotal);
					
					cust.setLocation(custLocation);
					
					cust.setNotes(notes);
					
					nameListModel.addElement(cust.getName());					
					
					custList.add(cust);
										
					sortNameListModel();
					
					int newIndex = nameListModel.indexOf(cust.getName());
		            nameList.setSelectedIndex(newIndex);
					
					JOptionPane.showMessageDialog(frame, "Customer Saved!", "Success", JOptionPane.PLAIN_MESSAGE);
					
				}
				
				
				
			}
		}
		
		ActionListener listenerSaveC = new saveCustomerListener(this);
		
		saveCustomer.addActionListener(listenerSaveC);
		

		//New Customer Button
		
		JButton newCustomer = new JButton("New Customer");
				
		
		//When new Customer is pressed
		class newCustomerListener implements ActionListener 
		{
			public void actionPerformed(ActionEvent event) 
			{
				
				nameList.clearSelection();
				
				nameField.setText("");
				
				emailField.setText("");
				
				dogCheckBox.setSelected(false);
				catCheckBox.setSelected(false);
				birdCheckBox.setSelected(false);
				fishCheckBox.setSelected(false);
				otherCheckBox.setSelected(false);
				
				spentField.setText("0.0");
				
				locationCombo.setSelectedItem("Irvine");
				
				notesArea.setText("");			
				
				//editC = false;
				
			}
		}
		
		ActionListener listenerNewC = new newCustomerListener();
		
		newCustomer.addActionListener(listenerNewC);
		
		buttonPanel.add(newCustomer);
		

		
		
		rightPanel.add(buttonPanel);
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------
		
		//Add panel to overall frame

		overallPanel.add(leftPanel);
		
		overallPanel.add(rightPanel);
		
		
		
		add(overallPanel);
		
		
		
	
		

		
	}
	
	
	private void sortNameListModel() 
	{
		
		
		
        List<String> n = new ArrayList<>();
        
        for (int i = 0; i < nameListModel.size(); i++) {
            n.add(nameListModel.getElementAt(i));
        }

        Collections.sort(n);

        nameListModel.clear();

        for (String name : n) {
            nameListModel.addElement(name);
        }
        
        Collections.sort(custList);
    }

	
	
	//Pet section with check-boxes
	
	public JPanel createPetCheckBoxes() 
	{
		
		petLabel = new JLabel("Pets: ");
		
		dogCheckBox = new JCheckBox("Dog(s)");
		
		dogCheckBox.addActionListener(listener);
		
		
		catCheckBox = new JCheckBox("Cat(s)");
		
		catCheckBox.addActionListener(listener);
		
		
		birdCheckBox = new JCheckBox("Bird(s)");
		
		birdCheckBox.addActionListener(listener);
		
		
		fishCheckBox = new JCheckBox("Fish");
		
		fishCheckBox.addActionListener(listener);
		
		
		otherCheckBox = new JCheckBox("Other");
		
		otherCheckBox.addActionListener(listener);
		
		
		JPanel petPanel = new JPanel();
		
		petPanel.add(petLabel);
		
		petPanel.add(dogCheckBox);
		
		petPanel.add(catCheckBox);
		
		petPanel.add(birdCheckBox);
		
		petPanel.add(fishCheckBox);
		
		petPanel.add(otherCheckBox);
		
		return petPanel;
		
	}
}