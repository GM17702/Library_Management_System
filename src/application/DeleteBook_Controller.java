package application;

import java.io.IOException;
import java.util.ArrayList;

import business_Layer.Book;
import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class DeleteBook_Controller 
{

    @FXML
    public TableColumn<Book, String > authorcolumn;
    public Button deleteButton;
    public ToggleButton hometoolbar;
    public TableColumn<Book, String > isbncolumn;
    public MenuItem isbnoptionbutton;
    public TableColumn<Book, String > namecolumn;
    public MenuItem nameoptionbutton;
    public MenuItem authoroptionbutton;
    public ToggleButton operationbutton;
    public TableColumn<Book, String > publishercolumn;
    public TableView<Book> searchbooktable;
    public TextField searchfield;
    public MenuButton searchtypebutton;
    public ToggleButton signouttoolbar;
    public ToggleButton searchbutton;
    public Label errortext;
    public Label deletetext;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void ISBNAction(ActionEvent event) 
    {
    	searchtypebutton.setText(isbnoptionbutton.getText());
    }
    
    @FXML
    void AuthorAction(ActionEvent event) 
    {
    	searchtypebutton.setText(authoroptionbutton.getText());
    }

    @FXML
    void NameAction(ActionEvent event) 
    {
    	searchtypebutton.setText(nameoptionbutton.getText());
    }

    @FXML
    void PerformDeleteBook(ActionEvent event) 
    {
    	int row = searchbooktable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		Book b = searchbooktable.getItems().get(row);
    		l.DELETE_BookRecord(b);
    		
    		searchbooktable.getItems().remove(row);
    		searchbooktable.getSelectionModel().clearSelection();
    		
    		deletetext.setText("Book Record Deleted!");
			deletetext.setStyle(successMessage);
    		
    	}
    	else
    	{
    		deletetext.setText("No Book row Selected!");
			deletetext.setStyle(errorMessage);
    	}
    }
    
    void TableDisplay(ArrayList<Book> result)
    {
		errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		isbncolumn.setCellValueFactory(new PropertyValueFactory <Book , String>("ISBN"));
		namecolumn.setCellValueFactory(new PropertyValueFactory <Book , String>("name"));
		authorcolumn.setCellValueFactory(new PropertyValueFactory <Book , String>("author"));
		publishercolumn.setCellValueFactory(new PropertyValueFactory <Book , String>("publisher"));
		
		isbncolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		namecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		authorcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		publishercolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		for (int i=0; i<result.size(); i++)
		{
			searchbooktable.getItems().add(result.get(i));
		}
    }
    
    
	
    @FXML
    void PerformSearch(ActionEvent event) 
    {
    	deletetext.setText("");
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
    	else if (searchtypebutton.getText().equals("ISBN"))
    	{
    		searchbooktable.getItems().clear();
    		ArrayList<Book> result = l.ISBN_BookSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Book with such ISBN exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    	
    	else if (searchtypebutton.getText().equals("Name"))
    	{
    		searchbooktable.getItems().clear();
    		ArrayList<Book> result = l.NAME_BookSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Book with such Name exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    	
    	else if (searchtypebutton.getText().equals("Author"))
    	{
    		searchbooktable.getItems().clear();
    		ArrayList<Book> result = l.AUTHOR_BookSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Book with such Author Name exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
    		}
    	}
    }

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
    void goToLogin(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Main_Login_Screen.fxml");
    }

}

