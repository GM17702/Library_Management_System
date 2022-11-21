package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Fine_Record;
import business_Layer.LMS_Controller;
import business_Layer.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cancel_Membership_Member_Controller implements Initializable
{

    @FXML
    public Button cancelmembershipbutton;
    public Label errortext;
    public ToggleButton hometoolbar;
    public ToggleButton operationstoolbar;
    public TextField searchfield;
    public ToggleButton signouttoolbar;
    public Font x1;
    public Font x2;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformCancelMembership(ActionEvent event) throws IOException 
    {
    	errortext.setText("");
    	if (searchfield.getText().isBlank())
    	{
    		errortext.setText("Fill the search field!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		
    		boolean decision1 = l.DoesMemberExists(searchfield.getText());
    		
    		if(decision1==false)
    		{
    			errortext.setText("No such Member with Provided MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
 
    			ArrayList<Member> member = l.ID_MemberSearch(searchfield.getText());
    			Member mem= member.get(0);
        		l.DELETE_MemberRecord(mem);
        		l.DELETE_PaymentMethod(mem.getMemberid());
        		
        		errortext.setText("Membership Cancelled!");
    			errortext.setStyle(successMessage);
    			
    			l.Member_SessionTermination();
    			Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    			s.setWidth(1100);
    			s.setHeight(720);
    			s.centerOnScreen();
    			m.changeScene(s,"src/application/Main_Login_Screen.fxml");
    		}
    	}
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Member_home.fxml");
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
    void goToOperations(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Book_Operations.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		try 
		{
			String result =l.Member_ObtainSession();
			
			if (result!=null)
			{
				
				searchfield.setText(result);
				searchfield.setEditable(false);
				Image image = new Image("file:///C:/Users/DELL/Desktop/New%20folder%20(2)/SDA_Project/src/pictures/error.png");
				searchfield.setCursor(new ImageCursor(image));
				searchfield.setFocusTraversable(false);
				
			}
			
		} catch (IOException e) 
		
		{
			e.printStackTrace();
		}
	}

}

