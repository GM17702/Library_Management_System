package application;

import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Register_Membership_Controller 
{

    @FXML
    public TextField addressfield;
    public TextField cnicfield;
    public TextField namefield;
    public TextField passwordfield;
    public TextField phonenumfield;
    public ToggleButton register_to_login_button;
    public ToggleButton registerbutton;
    public TextField usernamefield;
    public Font x1;
    public Label errortext;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m =new Main();
    LMS_Controller l = new LMS_Controller();
    
    @FXML
    void PerformRegistration(ActionEvent event) throws IOException 
    {
    	
    	
    	if (namefield.getText().isBlank() || usernamefield.getText().isBlank() || addressfield.getText().isBlank() || phonenumfield.getText().isBlank() || cnicfield.getText().isBlank() || passwordfield.getText().isBlank())
    	{
    		errortext.setText("Fill all the fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (cnicfield.getText().contains(" ") || usernamefield.getText().contains(" ") || passwordfield.getText().contains(" "))
    	{
    		
    		errortext.setText("Spaces not allowed in CNIC, Username and Password!");
    		errortext.setStyle(errorMessage);
    	}
    	
    	else
    	{
    		try
    		{
    			Long.parseLong(phonenumfield.getText());
    			boolean decision = l.M_Check_Signup(usernamefield.getText(), passwordfield.getText(),cnicfield.getText(), namefield.getText(), addressfield.getText(),phonenumfield.getText());
        		
        		if (decision==true)
        		{
        			
            		errortext.setText("Already a Member with provided CNIC exists!");
            		errortext.setStyle(errorMessage);
        		}
        		else
        		{
            		errortext.setText("Signup successfull!");
            		errortext.setStyle(successMessage);
        		}
    		}
    		catch (Exception e)
    		{
    			errortext.setText("Phone number must be a number!");
        		errortext.setStyle(errorMessage);
    		}
    		
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
