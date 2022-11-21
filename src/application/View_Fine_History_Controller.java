package application;

import java.io.IOException;
import java.net.URL;
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

public class View_Fine_History_Controller implements Initializable
{

    @FXML
    public Label errortext;
    public TableColumn<Fine_Record_Wrapper, Integer> finecolumn;
    public TableView<Fine_Record_Wrapper> finetable;
    public ToggleButton homettolbar;
    public TableColumn<Fine_Record_Wrapper, String> isbncolumn;
    public TableColumn<Fine_Record_Wrapper, String> memberidcolumn;
    public TableColumn<Fine_Record_Wrapper, String> statuscolumn;
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
    void PerformSearch(ActionEvent event) 
    {
    	finetable.getItems().clear();
    	
    	if (searchfield.getText().isBlank())
    	{
    		
    		errortext.setText("Fill the Search field!");
    		errortext.setStyle(errorMessage);
    	}
    	else 
    	{
    		finetable.getItems().clear();
    		ArrayList<Fine_Record> result = l.MEMBERID_GetFineHistory(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Fine Records for such Member exist!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplay(result);
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
		statuscolumn.setCellValueFactory(new PropertyValueFactory <Fine_Record_Wrapper, String>("finestatus"));
		
		isbncolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		finecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		statuscolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
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
    	String decision = l.FindUsers();
    	
    	if (decision.equals("1"))
    	{
    		
    		Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
    		s.setWidth(1100);
    		s.setHeight(720);
    		s.centerOnScreen();
    		m.changeScene(s,"src/application/Book_Operations.fxml");
    		
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
    void goToHome(ActionEvent event) throws IOException 
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

