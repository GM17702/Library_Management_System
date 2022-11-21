package application;

import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main_Signup_Screen_Controller 
{
	
	public Main_Signup_Screen_Controller() 
    {
		
	}
    @FXML
	public void initialize()
	{
		
	}

    @FXML
    public MenuItem adminoptionbutton;
    public ToggleButton exitbutton;
    public MenuItem librarianoptionbutton;
    public PasswordField passwordfield;
    public Button signup_to_login_button;
    public Button signupbutton;
    public TextField usernamefield;
    public MenuButton usertypebutton;
    public Label errortext;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m =new Main();
    LMS_Controller l = new LMS_Controller();
    
    @FXML
    void AdminAction(ActionEvent event) 
    {
    	usertypebutton.setText(adminoptionbutton.getText());
    }

    @FXML
    void ExitLibrary(ActionEvent event) 
    {
    	Stage s=(Stage)exitbutton.getScene().getWindow();
		s.close();
    }

    @FXML
    void LibrarianAction(ActionEvent event) 
    {
    	usertypebutton.setText(librarianoptionbutton.getText());
    }

    @FXML
    void PerformSignup(ActionEvent event) throws IOException
    {
    	
    	if (usertypebutton.getText().equals("Select User Type"))
    	{
    		
    		errortext.setText("A User type is not selected!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (usernamefield.getText().isBlank() || passwordfield.getText().isBlank())
    	{
    		
    		errortext.setText("Fill both Username and Password fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (usernamefield.getText().contains(" ") || passwordfield.getText().contains(" "))
    	{
    		
    		errortext.setText("Spaces not allowed in Username and Password!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (usertypebutton.getText().equals(librarianoptionbutton.getText()))
    	{
    		boolean decision = l.L_Check_Signup(usernamefield.getText(), passwordfield.getText());
    		
    		if (decision==true)
    		{
    			
        		errortext.setText("Already a "+usertypebutton.getText()+" with provided Username exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			
        		errortext.setText("Signup successfull!");
        		errortext.setStyle(successMessage);
    		}
    	}
    	
    	else if (usertypebutton.getText().equals(adminoptionbutton.getText()))
    	{
    		boolean decision = l.A_Check_Signup(usernamefield.getText(), passwordfield.getText());
    		
    		if (decision==true)
    		{
    			errortext.setStyle(errorMessage);
        		errortext.setText("Already a "+usertypebutton.getText()+" with provided Username exists!");
    		}
    		else
    		{
    			errortext.setStyle(successMessage);
        		errortext.setText("Signup successfull!");
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
