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
import business_Layer.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Return_Book_Controller implements Initializable
{

    @FXML
    public TableColumn<Book_Issuance_Wrapper, String> memberidcolumn;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> duedatecolumn;
    public Label errortext;
    public DatePicker returndatefield;
    public ToggleButton hometoolbar;
    public TableColumn<Book_Issuance_Wrapper, String> isbncolumn;
    public TableColumn<Book_Issuance_Wrapper, LocalDate> issuedatecolumn;
    public ToggleButton operationstoolbar;
    public ToggleButton returnbutton;
    public Label returntext;
    public ToggleButton searchbutton;
    public TextField searchfield;
    public TableView<Book_Issuance_Wrapper> searchtable;
    public ToggleButton signouttoolbar;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformReturn(ActionEvent event) throws IOException 
    {
    	errortext.setText("");
    	int row = searchtable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		Book_Issuance_Wrapper r = searchtable.getItems().get(row);
    		Book_Issuance_Record b = new Book_Issuance_Record(r.getBook_ISBN(),r.getMember_ID(),r.getIssuedate(),r.getDuedate());
        		
        	LocalDate duedate = searchtable.getItems().get(row).getDuedate();
        	LocalDate returndate = returndatefield.getValue();
        	b.setReturndate(returndatefield.getValue());
        	
        	
        		
        	ArrayList<Book_Issuance_Record> book = new ArrayList<Book_Issuance_Record>();
        	book.add(b);
        	
        	l.UPDATE_BookIssuanceRecord(book);
        	
        	try 
        	{
        		ArrayList<Book> result1 = l.ISBN_BookSearch(b.getB().getISBN());
            	int newquantity = Integer.parseInt(result1.get(0).getQuantity());
    			newquantity=newquantity+1;
    			result1.get(0).setQuantity(String.valueOf(newquantity));
    			
    			l.UPDATE_BookRecord(result1);
        	}
        	catch (Exception e)
        	{

        	}
        	
        	long days = ChronoUnit.DAYS.between(duedate, returndate);
        	
        	if (days>=1)
        	{
            	l.Store_FineDetails(b.getB().getISBN(),b.getM().getMemberid(),days);
            	
            	returntext.setText("Book Returned and Fine Generated!");
            	returntext.setStyle(successMessage);
            	//screen to add here

        	}
        	else
        	{
        		returntext.setText("Book Returned Successfully on time!");
            	returntext.setStyle(successMessage);
        	}
        	
        	
        	searchtable.getItems().remove(row);
        	searchtable.getSelectionModel().clearSelection();
        	
        	
    			
    		searchtable.getItems().clear();
    	}
    		
    		
    	else
    	{
    		returntext.setText("No Book row Selected!");
			returntext.setStyle(errorMessage);
    	}
    }

    @FXML
    void PerformSearch(ActionEvent event) 
    {
    	returntext.setText("");
    	LocalDate now = LocalDate.now();
    	searchtable.getItems().clear();
    	if (searchfield.getText().isBlank() || returndatefield.getValue()==null)
    	{
    		errortext.setText("Fill all the field!");
    		errortext.setStyle(errorMessage);
    	}
    	else if (now.isAfter(returndatefield.getValue()) || now.isEqual(returndatefield.getValue()))
    	{
    		errortext.setText("Return Date is Invalid!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		searchtable.getItems().clear();
    		
    		boolean decision1 = l.DoesMemberExists(searchfield.getText());
    		
    		if(decision1==false)
    		{
    			errortext.setText("No such Member with Provided MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			boolean decision = l.DoubleCheck_ReturnEligibility(searchfield.getText());
        		
        		if (decision==false)
        		{
        			errortext.setText("Member doesn't have an issued book!!");
            		errortext.setStyle(errorMessage);
        		}
        		else
        		{
        			searchtable.getItems().clear();
        			ArrayList<Book_Issuance_Record> result = l.MEMBERID_BookIssuanceSearch(searchfield.getText());
            		
            		if (result.isEmpty())
            		{
            			errortext.setText("Woof!");
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

