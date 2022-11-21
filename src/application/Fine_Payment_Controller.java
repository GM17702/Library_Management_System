package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Book_Issuance_Record;
import business_Layer.Fine_Record;
import business_Layer.LMS_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Fine_Payment_Controller implements Initializable {

    @FXML
    public Button addpaymentbutton;
    public Label errortext;
    public Button finebutton;
    public TableColumn<Fine_Record_Wrapper,Integer> finecolumn;
    public TableView<Fine_Record_Wrapper> finetable;
    public Label finetext;
    public ToggleButton homettolbar;
    public TableColumn<Fine_Record_Wrapper,String> isbncolumn;
    public TableColumn<Fine_Record_Wrapper,String> memberidcolumn;
    public ToggleButton operationstoolbar;
    public Button searchbutton;
    public TextField searchfield;
    public ToggleButton signouttoolbar;
    public Font x1;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformFinePayment(ActionEvent event) 
    {
    	errortext.setText("");
    	int row = finetable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		Fine_Record_Wrapper f = finetable.getItems().get(row);
    		Fine_Record b = new Fine_Record(f.getBook_ISBN(),f.getMemberID(),f.getFineamount(),f.getFinestatus());
        	b.setFinestatus("paid");
        	
        	String bookid = b.getB().getISBN();
        	String memberid = b.getM().getMemberid();
        	int fine = b.getFineamount();
        	String status = b.getFinestatus();
        	
        	
        	
        	if (l.MEMBER_CheckCardStatus(searchfield.getText()))
        	{
        		l.UPDATE_FineRecord(bookid, memberid, fine, status);
        		finetext.setText("Fine Paid Successfully!");
            	finetext.setStyle(successMessage);
            	
            	finetable.getItems().remove(row);
            	finetable.getSelectionModel().clearSelection();
        		finetable.getItems().clear();
        	}
        	else
        	{
        		addpaymentbutton.setVisible(true);
        		finetext.setText("No Payment Method found!");
            	finetext.setStyle(errorMessage);
        	}
        	
    	}
    		
    		
    	else
    	{
    		finetext.setText("Select a fine Record!");
			finetext.setStyle(errorMessage);
    	}
    }

    @FXML
    void PerformSearch(ActionEvent event) 
    {
    	addpaymentbutton.setVisible(false);
    	finetext.setText("");
    	finetable.getItems().clear();
    	if (searchfield.getText().isBlank())
    	{
    		errortext.setText("Fill the search field!");
    		errortext.setStyle(errorMessage);
    	}
    	else
    	{
    		finetable.getItems().clear();
    		
    		boolean decision1 = l.DoesMemberExists(searchfield.getText());
    		
    		if(decision1==false)
    		{
    			errortext.setText("No such Member with Provided MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
 
        		finetable.getItems().clear();
        		ArrayList<Fine_Record> result = l.MEMBERID_FineRecordSearch(searchfield.getText());
            		
            	if (result.isEmpty())
            	{
            		errortext.setText("No fine is due!");
                	errortext.setStyle(errorMessage);
            	}
            	else
            	{
            		TableDisplay(result);
            	}
    		}
    	}
    }
    
    void TableDisplay(ArrayList<Fine_Record> result)
    {
    	errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		isbncolumn.setCellValueFactory(new PropertyValueFactory <Fine_Record_Wrapper, String>("Book_ISBN"));
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Fine_Record_Wrapper, String>("MemberID"));
		finecolumn.setCellValueFactory(new PropertyValueFactory <Fine_Record_Wrapper, Integer>("fineamount"));
		
		isbncolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		finecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		ArrayList<Fine_Record_Wrapper> newresult = new ArrayList<Fine_Record_Wrapper>();
		
		for (int i=0; i<result.size(); i++)
		{
			Fine_Record_Wrapper r = new Fine_Record_Wrapper(result.get(i).getB().getISBN(),result.get(i).getM().getMemberid(),result.get(i).getFineamount(),result.get(i).getFinestatus());
			newresult.add(r);
		}
		
		for (int i=0; i<newresult.size(); i++)
		{
			finetable.getItems().add(newresult.get(i));
		}
    }

    @FXML
    void goToBookOperations(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Book_Operations.fxml");
    }

    @FXML
    void goToCardRegisteration(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/Add_Payment_Method.fxml");
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

