package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Request_Book_Controller implements Initializable
{

    @FXML
    public TextField authornamefield;
    public TextField booknamefield;
    public Label errortext;
    public ToggleButton hometoolbar;
    public TextField memberidfield;
    public ToggleButton operationtoolbar;
    public ToggleButton signouttoolbar;
    public ToggleButton submitbutton;
    public Font x1;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void Perform_RequestBook(ActionEvent event) throws IOException 
    {
    	errortext.setText("");
    	
    	if (memberidfield.getText().isBlank() || booknamefield.getText().isBlank() || authornamefield.getText().isBlank())
    	{
    		errortext.setText("Fill all the fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		l.Store_Request(memberidfield.getText(), booknamefield.getText(),  authornamefield.getText());
    		
    		errortext.setText("Request submitted successfully!");
    		errortext.setStyle(successMessage);
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
    void goToOperation(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Book_Operations.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try 
		{
			String result =l.Member_ObtainSession();
			
			if (result!=null)
			{
				
				memberidfield.setText(result);
				memberidfield.setEditable(false);
				Image image = new Image("file:///C:/Users/DELL/Desktop/New%20folder%20(2)/SDA_Project/src/pictures/error.png");
				memberidfield.setCursor(new ImageCursor(image));
				memberidfield.setFocusTraversable(false);
				
			}
			
		} catch (IOException e) 
		
		{
			e.printStackTrace();
		}
		
	}

}
