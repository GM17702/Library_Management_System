package application;
import java.io.FileWriter;
import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class Librarian_home_Controller 
{

    @FXML
    public ToggleButton bookrenewalbutton;
    public ToggleButton bookreturnbutton;
    public ToggleButton hometoolbar;
    public ToggleButton issuebooksbutton;
    public ToggleButton librariantoolbar;
    public ToggleButton managebooksbutton;
    public ToggleButton signouttoolbar;
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void goToLibrarian_BookRenewal(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Renew_Book.fxml");
    }

    @FXML
    void goToLibrarian_ManageBooks(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/ManageBooks.fxml");
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
    void gotoLibrarian_BookReturn(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Return_Book.fxml");
    }

    @FXML
    void gotoLibrarian_IssueBooks(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Issue_Book.fxml");
    }

}
