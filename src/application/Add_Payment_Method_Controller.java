package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Add_Payment_Method_Controller implements Initializable{

    @FXML
    public TextField cardfield;
    public TextField memberidfield;
    public MenuButton cardtypebutton;
    public MenuItem creditoptionbutton;
    public TextField cvvfield;
    public MenuItem debitoptionbutton;
    public Label errortext;
    public DatePicker expdatefield;
    public ToggleButton hometoolbar;
    public ToggleButton operationstoolbar;
    public ToggleButton signouttoolbar;
    public ToggleButton submitbutton;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void CreditAction(ActionEvent event) 
    {
    	cardtypebutton.setText(creditoptionbutton.getText());
    }

    @FXML
    void DebitAction(ActionEvent event) 
    {
    	cardtypebutton.setText(debitoptionbutton.getText());
    }

    @FXML
    void PerformAddPaymentMethod(ActionEvent event) 
    {
    	LocalDate now = LocalDate.now();
    	if (cardtypebutton.getText().equals("Select Card Type"))
    	{
    		errortext.setText("Select a card type!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (cardfield.getText().isBlank() || expdatefield.getValue()==null || cvvfield.getText().isBlank() || memberidfield.getText().isBlank())
    	{
    		errortext.setText("Fill all the fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (now.isAfter(expdatefield.getValue()) || now.equals(expdatefield.getValue()))
    	{
    		errortext.setText("Expired card is unacceptable!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		try 
    		{
    			long card = Long.parseLong(cardfield.getText());
    			int cvv = Integer.parseInt(cvvfield.getText());
    			
    			boolean decision1 = l.DoesMemberExists(memberidfield.getText());
        		
        		if(decision1==false)
        		{
        			errortext.setText("No such Member with Provided MemberID exists!");
            		errortext.setStyle(errorMessage);
        		}
        		else
        		{
        			if (l.MEMBER_CheckCardStatus(memberidfield.getText()))
        			{
        				errortext.setText("The member already has a Payment Method!");
                		errortext.setStyle(errorMessage);
        			}
        			else
        			{
        				String member = memberidfield.getText();
            			String cardtype = cardtypebutton.getText();
            			String cardno = cardfield.getText();
            			String expdate = expdatefield.getValue().toString();
            			
            			l.Store_CardDetails(member, cardtype, cardno, expdate, cvv);
            			
            			errortext.setText("Payment Method added successfully!");
                		errortext.setStyle(successMessage);
        			}
        			
        		}
    			
    		}
    		catch (Exception e)
    		{
    			errortext.setText("Enter integers in Card number and CVV fields!");
        		errortext.setStyle(errorMessage);
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
