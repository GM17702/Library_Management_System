package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Book;
import business_Layer.LMS_Controller;
import business_Layer.Request_Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Manage_Request_Controller implements Initializable 
{

    @FXML
    public ToggleButton acceptbutton;
    public TableColumn<Request_Book_Wrapper, String> authornamecolumn;
    public TableColumn<Request_Book_Wrapper, String> booknamecolumn;
    public Label errortext;
    public ToggleButton hometoolbar;
    public TableColumn<Request_Book_Wrapper, String> memberidcolumn;
    public ToggleButton operationtoolbar;
    public ToggleButton rejectbutton;
    public TableView<Request_Book_Wrapper> requesttable;
    public ToggleButton signouttoolbar;
    public Font x1;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void Perform_AcceptRequest(ActionEvent event) throws IOException 
    {
    	if (requesttable.getSelectionModel()!=null)
    	{
    		int row = requesttable.getSelectionModel().getSelectedIndex();
        	
        	if (row >= 0)
        	{
        		Request_Book_Wrapper rw = requesttable.getItems().get(row);
        		Request_Book r = new Request_Book(rw.getMemberID(),rw.getBook_Name(),rw.getAuthor_Name());
        		l.DELETE_PendingRequest(r);
        		l.Store_AcceptedRequest(r.getM().getMemberid(), r.getBook_Name(), r.getAuthor_Name());
        		
        		requesttable.getItems().remove(row);
        		requesttable.getSelectionModel().clearSelection();
        		
        		errortext.setText("Book Request Accepted, Proceed to Order the book or Add it manually.");
    			errortext.setStyle(successMessage);
        		
        	}
        	else
        	{
        		errortext.setText("No Request row Selected!");
        		errortext.setStyle(errorMessage);
        	}
    	}
    	
    }

    @FXML
    void Perform_RejectRequest(ActionEvent event) throws IOException 
    {
    	if (requesttable.getSelectionModel()!=null)
    	{
    		int row = requesttable.getSelectionModel().getSelectedIndex();
        	
        	if (row >= 0)
        	{
        		Request_Book_Wrapper rw = requesttable.getItems().get(row);
        		Request_Book r = new Request_Book(rw.getMemberID(),rw.getBook_Name(),rw.getAuthor_Name());
        		l.DELETE_PendingRequest(r);
        		l.Store_RejectedRequest(r.getM().getMemberid(), r.getBook_Name(), r.getAuthor_Name());
        		
        		requesttable.getItems().remove(row);
        		requesttable.getSelectionModel().clearSelection();
        		
        		errortext.setText("Book Request Rejected successfully!");
    			errortext.setStyle(successMessage);
        		
        	}
        	else
        	{
        		errortext.setText("No Request row Selected!");
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Request_Book_Wrapper , String>("MemberID"));
		booknamecolumn.setCellValueFactory(new PropertyValueFactory <Request_Book_Wrapper , String>("Book_Name"));
		authornamecolumn.setCellValueFactory(new PropertyValueFactory <Request_Book_Wrapper , String>("Author_Name"));
		
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		booknamecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		authornamecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		ArrayList<Request_Book> req = l.GetAllRequests();
		
		if (req.isEmpty())
		{
			errortext.setText("No member requests exist!");
    		errortext.setStyle(errorMessage);
		}
		else
		{
			ArrayList<Request_Book_Wrapper> newresult = new ArrayList<Request_Book_Wrapper>();
			
			for (int i=0; i<req.size(); i++)
			{
				Request_Book_Wrapper r = new Request_Book_Wrapper(req.get(i).getM().getMemberid(),req.get(i).getBook_Name(),req.get(i).getAuthor_Name());
				newresult.add(r);
			}
			
			for (int i=0; i<newresult.size(); i++)
			{
				requesttable.getItems().add(newresult.get(i));
			}
		}
	}

}
