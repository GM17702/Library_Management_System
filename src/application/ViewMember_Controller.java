package application;

import java.io.IOException;
import java.util.ArrayList;

import business_Layer.Book;
import business_Layer.LMS_Controller;
import business_Layer.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewMember_Controller 
{

    @FXML
    public TableColumn<Member , String> addresscolumn;
    public MenuItem memberidoptionbutton;
    public TableColumn<Member , String> memberidcolumn;
    public TableColumn<Member , String> namecolumn;
    public MenuItem nameoptionbutton;
    public TableColumn<Member , String> phonenumbercolumn;
    public Button searchbutton;
    public TextField searchfield;
    public TableView<Member> searchtable;
    public MenuButton searchtypebutton;
    public ToggleButton hometoolbar,operationtoolbar,signouttoolbar;
    public Font x1;
    public Label errortext;
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");

    @FXML
    void MemberIDAction(ActionEvent event) 
    {
    	searchtypebutton.setText(memberidoptionbutton.getText());
    }

    @FXML
    void NameAction(ActionEvent event) 
    {
    	searchtypebutton.setText(nameoptionbutton.getText());
    }
    
    @FXML
    void goToHome(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Admin_home.fxml");
    }
    
    @FXML
    void goToOperation(ActionEvent event) throws IOException 
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
    
    @FXML
    void PerformSearch(ActionEvent event) throws IOException 
    {
    	if (searchtypebutton.getText().equals("Select Search Type"))
    	{
    		
    		errortext.setText("A Search type is not selected!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (searchfield.getText().isBlank())
    	{
    		
    		errortext.setText("Fill the Search field!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (searchtypebutton.getText().equals("MemberID"))
    	{
    		searchtable.getItems().clear();
    		ArrayList<Member> result = l.ID_MemberSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Member with such MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    	
    	else if (searchtypebutton.getText().equals("Name"))
    	{
    		searchtable.getItems().clear();
    		ArrayList<Member> result = l.NAME_MemberSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Member with such Name exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    	
    }
    
    void TableDisplay(ArrayList<Member> result)
    {
    	errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("memberid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("name"));
		addresscolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("address"));
		phonenumbercolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("phonenumber"));
		
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		namecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		addresscolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		phonenumbercolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		for (int i=0; i<result.size(); i++)
		{
			searchtable.getItems().add(result.get(i));
		}
    }

}
