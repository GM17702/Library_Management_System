package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class ManageBooks_Controller 
{

    @FXML
    private Button addbookbutton;
    private Button searchbookbutton;
    private Button deletebookbutton;
    private ToggleButton hometoolbar;
    private ToggleButton operationtoolbar;
    private ToggleButton signouttoolbar;
    private Button updatebookbutton;
    

	Main m = new Main();
	LMS_Controller l = new LMS_Controller();

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
    void goToAddBook(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/AddBook.fxml");
    }

    @FXML
    void goToDeleteBook(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/DeleteBook.fxml");
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

    @FXML
    void goToUpdateBook(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/UpdateBook.fxml");
    }
    
    @FXML
    void goToSearchBook(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Search_Book.fxml");
    }
    
    

}

