package application;

import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class Main_Login_Screen_Controller 
{
	public Main_Login_Screen_Controller() 
    {
		
	}
    @FXML
	public void initialize()
	{
		
	}
    

    public MenuItem adminoptionbutton;
    public ToggleButton exitbutton;
    public MenuItem librarianoptionbutton;
    public Button login_to_register_button;
    public Button login_to_signup_button;
    public Button loginbutton;
    public MenuItem memberoptionbutton;
    public PasswordField passwordfield;
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
    void ExitLibrary(ActionEvent event) throws IOException 
    {
		Stage s=(Stage)exitbutton.getScene().getWindow();
		s.close();
    }

	@FXML
    public void LibrarianAction(ActionEvent event) 
    {
    	usertypebutton.setText(librarianoptionbutton.getText());
    }

	@FXML
    public void PerformLogin (ActionEvent event) throws IOException 
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
    	else if (usertypebutton.getText().equals("Member"))
		{
    		
    		boolean decision = l.M_Check_Login(usernamefield.getText(), passwordfield.getText());
    		
    		if (decision==true)
    		{
    			errortext.setText("Login successfull!");
        		errortext.setStyle(successMessage);
        		
        		l.Identity_Handling("1", "0","0");
        		l.Member_SessionCreation(usernamefield.getText());
    			Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    			s.setWidth(1100);
    			s.setHeight(720);
    			s.centerOnScreen();
    			m.changeScene(s,"src/application/Member_home.fxml");
    			
        		
    		}
    		else
    		{		
    			errortext.setText("No such Member exists!");
        		errortext.setStyle(errorMessage);
    		}
    		
		}
    	else if (usertypebutton.getText().equals(librarianoptionbutton.getText()))
    	{
    		boolean decision = l.L_Check_Login(usernamefield.getText(), passwordfield.getText());
    		
    		if (decision==true)
    		{
    			errortext.setText("Login successfull!");
        		errortext.setStyle(successMessage);
        		
        		l.Identity_Handling("0", "1","0");
    			Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    			s.setWidth(1100);
    			s.setHeight(720);
    			s.centerOnScreen();
    			m.changeScene(s,"src/application/Librarian_home.fxml");
    			
        		
    		}
    		else
    		{		
    			errortext.setText("No such Librarian exists!");
        		errortext.setStyle(errorMessage);
    		}
    	}
    	
    	else if (usertypebutton.getText().equals(adminoptionbutton.getText()))
    	{
    		boolean decision = l.A_Check_Login(usernamefield.getText(), passwordfield.getText());
    		
    		if (decision==true)
    		{
    			errortext.setStyle(successMessage);
        		errortext.setText("Signup successfull!");
        		
        		l.Identity_Handling("0", "0" ,"1");
    			Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    			s.setWidth(1100);
    			s.setHeight(720);
    			s.centerOnScreen();
    			m.changeScene(s,"src/application/Admin_home.fxml");
    			
    			
    		}
    		else
    		{
    			errortext.setText("No such Admin exists!");
    			errortext.setStyle(errorMessage);
        		
    		}
    	}
    	
		
    }

    @FXML
    public void MemberAction(ActionEvent event) 
    {
    	usertypebutton.setText(memberoptionbutton.getText());
    }

    @FXML
    void goToRegisterMember(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Register_Membership.fxml");
    }

    @FXML
    void goToSignup(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Main_Signup_Screen.fxml");
    }

}
