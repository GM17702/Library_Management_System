package application;

import java.io.IOException;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class Member_home_Controller 
{

    @FXML
    public ToggleButton bookoperationsbutton;
    public ToggleButton bookoperationstoolbar;
    public ToggleButton cancelmembershipbutton;
    public ToggleButton finepaymentbutton;
    public ToggleButton hometoolbar;
    public ToggleButton searchbookbutton;
    public ToggleButton signouttoolbar;
    public ToggleButton finehistorybutton;
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformMember_CancelMembership(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Cancel_Membership_Member.fxml");
    }
    
    @FXML
    void goToRequestBook(ActionEvent event) throws IOException
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Request_Book.fxml");
    }

    @FXML
    void goToBookOperations(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Book_Operations.fxml");
    }

    @FXML
    void goToFinePayment(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Fine_Payment.fxml");
    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException 
    {
    	l.Member_SessionTermination();
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Main_Login_Screen.fxml");
    }

    @FXML
    void goToMember_SearchBook(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Search_Book.fxml");
    }
    
    @FXML
    void goToFineHistory(ActionEvent event) throws IOException
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/View_Fine_History.fxml");
    }

}
