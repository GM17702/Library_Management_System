package application;

import java.io.FileWriter;
import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class Admin_home_Controller 
{

    @FXML
    private ToggleButton admintoolbar;
    private ToggleButton hometoolbar;
    private ToggleButton managebooksbutton;
    private ToggleButton managemembersbutton;
    private ToggleButton searchfinebutton;
    private ToggleButton searchrecordbutton;
    private ToggleButton signouttoolbar;
    
    LMS_Controller l = new LMS_Controller();
    Main m = new Main();

    @FXML
    void goToAdmin_ManageBooks(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/ManageBooks.fxml");
    }
    
    @FXML
    void goToAdmin_ManageRequest(ActionEvent event) throws IOException
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Manage_Request.fxml");
    }
    
    @FXML
    void goToAdmin_SearchRecords(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Search_Book_Issuance_Record.fxml");
    }

    @FXML
    void goToAdmin_FineHistory(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/View_Fine_History.fxml");
    }


    @FXML
    void goToAdmin_ManageMembers(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/ManageMembers.fxml");
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
