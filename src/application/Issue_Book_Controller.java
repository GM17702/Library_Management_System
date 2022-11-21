package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Book;
import business_Layer.Book_Issuance_Record;
import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Issue_Book_Controller implements Initializable {

    @FXML
    public TableColumn< Book , String> authorcolumn;
    public ToggleButton hometoolbar;
    public TableColumn<Book , String> isbncolumn;
    public MenuItem isbnoptionbutton;
    public ToggleButton issuebutton;
    public TextField memberidfield;
    public TableColumn<Book , String> namecolumn;
    public MenuItem nameoptionbutton;
    public ToggleButton operationstoolbar;
    public TableColumn<Book , String> publishercolumn;
    public TableColumn<Book , String> quantitycolumn;
    public ToggleButton searchbutton;
    public TextField searchfield;
    public TableView<Book> searchtable;
    public MenuButton searchtypebutton;
    public ToggleButton signouttoolbar;
    public Label errortext;
    public Label updatetext;
    public String memberid;

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
    void NameAction(ActionEvent event) 
    {
    	searchtypebutton.setText(nameoptionbutton.getText());
    }

    @FXML
    void PerformIssueBook(ActionEvent event) throws IOException 
    {
    	errortext.setText("");
    	int row = searchtable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		if (Integer.parseInt(searchtable.getItems().get(row).getQuantity())<=0)
    		{
    			errortext.setText("Sorry, The Book is not available!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			Book b = searchtable.getItems().get(row);
    			int newquantity = Integer.parseInt(b.getQuantity());
    			newquantity=newquantity-1;
    			b.setQuantity(String.valueOf(newquantity));
        		
        		String bookid= searchtable.getItems().get(row).getISBN();
        		String Memberid= memberid;
        		LocalDate issuedate = LocalDate.now();
        		LocalDate duedate = issuedate.plusDays(14);
        		LocalDate returndate = null;
        		
        		
        		l.StoreBook_IssuanceRecord(bookid, Memberid, issuedate, duedate, returndate);
        		
        		ArrayList<Book> book = new ArrayList<Book>();
        		book.add(b);
        		
        		l.UPDATE_BookRecord(book);
        		
        		searchtable.getItems().remove(row);
        		searchtable.getSelectionModel().clearSelection();
        		
        		updatetext.setText("Book Issued Successfully, remember to return within 2 weeks!");
    			updatetext.setStyle(successMessage);
    			
    			searchtable.getItems().clear();
    		}
    		
    		
    	}
    	else
    	{
    		updatetext.setText("No Book row Selected!");
			updatetext.setStyle(errorMessage);
    	}
    }

    @FXML
    void PerformSearch(ActionEvent event) 
    {
    	updatetext.setText("");
    	LocalDate now = LocalDate.now();
    	searchtable.getItems().clear();
    	
    	if (searchtypebutton.getText().equals("Select Search Type"))
    	{
    		
    		errortext.setText("A Search type is not selected!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (searchfield.getText().isBlank() || memberidfield.getText().isBlank())
    	{
    		
    		errortext.setText("Fill all the fields!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (l.MEMBER_CheckFineStatus(memberidfield.getText()))
    	{
    		errortext.setText("The specified Member has a pending fine, Book Issuance is restricted!");
    		errortext.setStyle(errorMessage);
    	}
    	
    	else if (searchtypebutton.getText().equals("ISBN"))
    	{
    		searchtable.getItems().clear();
    		
    		boolean decision1 = l.DoesMemberExists(memberidfield.getText());
    		
    		if(decision1==false)
    		{
    			errortext.setText("No such Member with Provided MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			boolean decision = l.DoubleCheck_IssueEligibility(memberidfield.getText());
        		
        		if (decision==true)
        		{
        			errortext.setText("The Member already has a book issued!");
            		errortext.setStyle(errorMessage);
        		}
        		else if (decision==false)
        		{
        			memberid = memberidfield.getText();
        			searchtable.getItems().clear();
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
    		}
    		
    	}
    	
    	else if (searchtypebutton.getText().equals("Name"))
    	{
    		
    		boolean decision1 = l.DoesMemberExists(memberidfield.getText());
    		
    		if(decision1==false)
    		{
    			errortext.setText("No such Member with Provided MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else if (decision1==true)
    		{
    			boolean decision = l.DoubleCheck_IssueEligibility(memberidfield.getText());
        		
        		if (decision==true)
        		{
        			errortext.setText("The Member already has a book issued!");
            		errortext.setStyle(errorMessage);
        		}
        		else if (decision==false)
        		{
        			memberid = memberidfield.getText();
        			searchtable.getItems().clear();
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
    		}	
    		
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
		quantitycolumn.setCellValueFactory(new PropertyValueFactory <Book , String>("quantity"));
		
		isbncolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		namecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		authorcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		publishercolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		quantitycolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		for (int i=0; i<result.size(); i++)
		{
			searchtable.getItems().add(result.get(i));
		}
    }

    @FXML
    void goToAHome(ActionEvent event) throws IOException 
    {
    	String decision = l.FindUsers();
    	
    	
    	if (decision.equals("1"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Member_home.fxml");
    		
    	}
    	else if (decision.equals("2"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Librarian_home.fxml");
    	}
    }

    @FXML
    void goToAOperation(ActionEvent event) throws IOException 
    {
    	String decision = l.FindUsers();
    	
    	
    	if (decision.equals("1"))
    	{
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Book_Operations.fxml");
    	}
    	else if (decision.equals("2"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/ManageBooks.fxml");
    	}
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
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

