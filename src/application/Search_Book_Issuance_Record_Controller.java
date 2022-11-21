package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import business_Layer.Book;
import business_Layer.Book_Issuance_Record;
import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

public class Search_Book_Issuance_Record_Controller 
{

    @FXML
    public TableColumn<Book_Issuance_Wrapper, String> bookidcolumn;
    public MenuItem bookidoptionbutton;
    public ToggleButton hometoolbar;
    public TableView<Book_Issuance_Wrapper> issuancetable;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> issuedatecolumn;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> duedatecolumn;
    public TableColumn<Book_Issuance_Wrapper, String> memberidcolumn;
    public MenuItem memberidoptionbutton;
    public ToggleButton operationtoolbar;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> returndatecolumn;
    public ToggleButton searchbutton;
    public TextField searchfield;
    public MenuButton searchoptionbutton;
    public ToggleButton signouttoolbar;
    public Font x1;
    public Label errortext;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void ISBN_Action(ActionEvent event) 
    {
    	searchoptionbutton.setText(bookidoptionbutton.getText());
    }

    @FXML
    void MEMBERID_Action(ActionEvent event) 
    {
    	searchoptionbutton.setText(memberidoptionbutton.getText());
    }

    @FXML
    void PerformSearch(ActionEvent event)
    {
    	issuancetable.getItems().clear();
    	if (searchoptionbutton.getText().equals("Select Search Type"))
    	{
    		
    		errortext.setText("A Search type is not selected!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (searchfield.getText().isBlank())
    	{
    		
    		errortext.setText("Fill the Search field!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (searchoptionbutton.getText().equals("ISBN"))
    	{
    		issuancetable.getItems().clear();
    		ArrayList<Book_Issuance_Record> result = l.BOOKID_GetIssuanceRecords(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Issuance Records for such ISBN exist!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    	else if (searchoptionbutton.getText().equals("Member ID"))
    	{
    		issuancetable.getItems().clear();
    		ArrayList<Book_Issuance_Record> result = l.MEMBERID_GetIssuanceRecords(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Issuance Records for such Member exist!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}

    }
    
    
    
    void TableDisplay(ArrayList<Book_Issuance_Record> result)
    {
    	errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		bookidcolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper , String>("Book_ISBN"));
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper , String>("Member_ID"));
		issuedatecolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper , LocalDate>("issuedate"));
		duedatecolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper , LocalDate>("duedate"));
		returndatecolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper , LocalDate>("returndate"));
		
		bookidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		issuedatecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		duedatecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		returndatecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		ArrayList<Book_Issuance_Wrapper> newresult = new ArrayList<Book_Issuance_Wrapper>();
		
		for (int i=0; i<result.size(); i++)
		{
			Book_Issuance_Wrapper r = new Book_Issuance_Wrapper(result.get(i).getB().getISBN(),result.get(i).getM().getMemberid(),result.get(i).getIssuedate(),result.get(i).getDuedate(),result.get(i).getReturndate());
			newresult.add(r);
		}
		
		for (int i=0; i<newresult.size(); i++)
		{
			issuancetable.getItems().add(newresult.get(i));
				
		}
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
    void goToLogin(ActionEvent event) throws IOException 
    {
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
		m.changeScene(s,"src/application/ManageMembers.fxml");
    }
}