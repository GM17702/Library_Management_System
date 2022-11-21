package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Book;
import business_Layer.Book_Issuance_Record;
import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Renew_Book_Controller implements Initializable{

    @FXML
    public TableColumn<Book_Issuance_Wrapper, LocalDate> duedatecolumn;
    public Label errortext;
    public ToggleButton hometoolbar;
    public TableColumn<Book_Issuance_Wrapper, String> isbncolumn;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> issuedatecolumn;
    public TableColumn<Book_Issuance_Wrapper, String> memberidcolumn;
    public TextField memberidfield;
    public ToggleButton operationstoolbar;
    public ToggleButton renewbutton;
    public Label renewtext;
    public ToggleButton searchbutton;
    public TableView<Book_Issuance_Wrapper> searchtable;
    public ToggleButton signouttoolbar;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformRenewBook(ActionEvent event) 
    {
    	errortext.setText("");
    	int row = searchtable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		
    		Book_Issuance_Wrapper r = searchtable.getItems().get(row);
    		Book_Issuance_Record b = new Book_Issuance_Record(r.getBook_ISBN(),r.getMember_ID(),r.getIssuedate(),r.getDuedate());
        		
        	b.setDuedate(b.getDuedate().plusDays(7));
        	b.setRenewflag("yes");
        	
        		
        	ArrayList<Book_Issuance_Record> book = new ArrayList<Book_Issuance_Record>();
        	book.add(b);
        	
        	l.UPDATE_BookIssuanceRecord(book);
        	
        	renewtext.setText("Book Renewed Successfully for an additional week!");
        	renewtext.setStyle(successMessage);
        	
        	

        	searchtable.getItems().remove(row);
        	searchtable.getSelectionModel().clearSelection();
        	
        	
    			
    		searchtable.getItems().clear();
    	}
    		
    		
    	else
    	{
    		renewtext.setText("No Table row Selected!");
			renewtext.setStyle(errorMessage);
    	}
    }

    @FXML
    void PerformSearch(ActionEvent event) 
    {
    	renewtext.setText("");
    	LocalDate now = LocalDate.now();
    	searchtable.getItems().clear();
    	if (memberidfield.getText().isBlank())
    	{
    		errortext.setText("Fill all the field!");
    		errortext.setStyle(errorMessage);
    	}
    	else
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
 
        		searchtable.getItems().clear();
        		ArrayList<Book_Issuance_Record> result = l.MEMBERID_BookRenewenceSearch(memberidfield.getText());
            		
            	if (result.isEmpty())
            	{
            		errortext.setText("No Book Issued OR Already Renewed once!");
                	errortext.setStyle(errorMessage);
            	}
            	else
            	{
            		TableDisplay(result);
            	}
    		}
    	}
    }
    
    void TableDisplay(ArrayList<Book_Issuance_Record> result)
    {
    	errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		isbncolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper, String>("Book_ISBN"));
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper, String>("Member_ID"));
		issuedatecolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper, LocalDate>("issuedate"));
		duedatecolumn.setCellValueFactory(new PropertyValueFactory <Book_Issuance_Wrapper, LocalDate>("duedate"));
		
		isbncolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		issuedatecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		duedatecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		ArrayList<Book_Issuance_Wrapper> newresult = new ArrayList<Book_Issuance_Wrapper>();
		
		for (int i=0; i<result.size(); i++)
		{
			Book_Issuance_Wrapper r = new Book_Issuance_Wrapper(result.get(i).getB().getISBN(),result.get(i).getM().getMemberid(),result.get(i).getIssuedate(),result.get(i).getDuedate());
			newresult.add(r);
		}
		
		for (int i=0; i<newresult.size(); i++)
		{
			searchtable.getItems().add(newresult.get(i));
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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

