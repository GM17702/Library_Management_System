package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class AddBook_Controller {

    @FXML
    public TextField authorfield;
    public TextField namefield;
    public DatePicker publicationdatefield;
    public TextField publisherfield;
    public TextField subjectfield;
    public ToggleButton hometoolbar;
    public TextField isbnfield;
    public TextField quantityfield;
    public ToggleButton operationtoolbar;
    public ToggleButton signouttoolbar;
    public ToggleButton submitbutton;
    public Label errortext;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformAddBook(ActionEvent event) throws IOException 
    {
    	boolean check;
    	int num = 0;
    	LocalDate now = LocalDate.now();
    	
    	
    	try 
    	{
    		num = Integer.parseInt(quantityfield.getText());
    		check=true;
    	}
    	catch (Exception e)
    	{
    		check=false;
    	}
    	
    	if (isbnfield.getText().isBlank() || namefield.getText().isBlank() || authorfield.getText().isBlank() || subjectfield.getText().isBlank() || quantityfield.getText().isBlank() || publisherfield.getText().isBlank() || (publicationdatefield.getValue()==null))
    	{
    		errortext.setText("Fill all the fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (check==false)
    	{
    		errortext.setText("Enter an Integer in Quantity Field!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (num<1 || num>100)
    	{
    		errortext.setText("Quantity has to be between 1 and 100!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (now.isBefore(publicationdatefield.getValue()))
    	{
    		errortext.setText("Publication date needs to be legitimate!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (isbnfield.getText().contains(" "))
    	{
    		
    		errortext.setText("Spaces not allowed in ISBN!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		LocalDate date = publicationdatefield.getValue();
        	
    		boolean decision = l.Check_BookAddition(isbnfield.getText(), namefield.getText(), authorfield.getText(), subjectfield.getText(), quantityfield.getText(), publisherfield.getText(), date);
    		
    		if (decision==true)
    		{
    			
        		errortext.setText("Already a Book with provided ISBN exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
        		errortext.setText("Book successfully Added!");
        		errortext.setStyle(successMessage);
    		}
    	}
    	
    }

    @FXML
    void goToAHome(ActionEvent event) throws IOException 
    {
    	String decision = l.FindUsers();
    	
    	
    	if (decision.equals("2"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Librarian_home.fxml");
    		
    	}
    	else if (decision.equals("3"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Admin_home.fxml");
    	}
    }

    @FXML
    void goToAOperation(ActionEvent event) throws IOException 
    {
    	String decision = l.FindUsers();
    	
    	if (decision.equals("2"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/ManageBooks.fxml");
    		
    	}
    	else if (decision.equals("3"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/ManageMembers.fxml");
    	}
    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Main_Login_Screen.fxml");
    }

}

