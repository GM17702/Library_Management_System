package application;

import java.io.IOException;
import java.util.ArrayList;

import business_Layer.Book;
import business_Layer.LMS_Controller;
import business_Layer.Member;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Update_Member_Details_Controller 
{

    @FXML
    public TableColumn<Member, String> addresscolumn;
    public Label errortext;
    public ToggleButton hometoolbar;
    public TableColumn<Member, String> memberidcolumn;
    public MenuItem memberidoptionbutton;
    public TextField searchfield;
    public TableColumn<Member, String> namecolumn;
    public MenuItem nameoptionbutton;
    public ToggleButton operationstoolbar;
    public TableColumn<Member, String> phonenumcolumn;
    public Button searchbutton;
    public TableView<Member> searchmembertable;
    public MenuButton searchtypebutton;
    public ToggleButton signouttoolbar;
    public Button updatebutton;
    public Label updatetext;
    public Font x1;
    
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
    void PerformSearch(ActionEvent event) 
    {
    	updatetext.setText("");
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
    		searchmembertable.getItems().clear();
    		ArrayList<Member> result = l.ID_MemberSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Member with such MemberID exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplayAndEditable(result);
    		}
    	}
    	
    	else if (searchtypebutton.getText().equals("Name"))
    	{
    		searchmembertable.getItems().clear();
    		ArrayList<Member> result = l.NAME_MemberSearch(searchfield.getText());
    		
    		if (result.isEmpty())
    		{
    			errortext.setText("No Member with such Name exists!");
        		errortext.setStyle(errorMessage);
    		}
    		else
    		{
    			TableDisplayAndEditable(result);
    		}
    	}
    	
    }
    
    void TableDisplayAndEditable(ArrayList<Member> result)
    {
    	searchmembertable.setEditable(true);
		errortext.setText("Search completed successfully!");
		errortext.setStyle(successMessage);
		
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Member, String>("memberid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory <Member, String>("name"));
		addresscolumn.setCellValueFactory(new PropertyValueFactory <Member, String>("address"));
		phonenumcolumn.setCellValueFactory(new PropertyValueFactory <Member, String>("phonenumber"));
		namecolumn.setCellFactory(TextFieldTableCell.forTableColumn()); 
		namecolumn.setEditable(true);
		namecolumn.setOnEditCommit(new EventHandler <CellEditEvent<Member,String>>(){

			@Override
			public void handle(CellEditEvent<Member, String> event) 
			{
				errortext.setText("");
				try 
				{
					//Integer.parseInt(event.getNewValue());
					Member m =event.getRowValue();
					m.setName(event.getNewValue());
					
					updatetext.setText("New Name accepted!");
					updatetext.setStyle(successMessage);
				}
				catch(Exception e )
				{
					updatetext.setText("Value Acceptence Failed!");
					updatetext.setStyle(errorMessage);
				}

				
			}
			
			
		});
		
		addresscolumn.setCellFactory(TextFieldTableCell.forTableColumn()); 
		addresscolumn.setEditable(true);
		addresscolumn.setOnEditCommit(new EventHandler <CellEditEvent<Member,String>>(){

			@Override
			public void handle(CellEditEvent<Member, String> event) 
			{
				errortext.setText("");
				try 
				{
					//Integer.parseInt(event.getNewValue());
					Member m =event.getRowValue();
					m.setAddress(event.getNewValue());
					
					updatetext.setText("New Address accepted!");
					updatetext.setStyle(successMessage);
				}
				catch(Exception e )
				{
					updatetext.setText("Value Acceptence Failed!");
					updatetext.setStyle(errorMessage);
				}

				
			}
			
			
		});
		
		phonenumcolumn.setCellFactory(TextFieldTableCell.forTableColumn()); 
		phonenumcolumn.setEditable(true);
		phonenumcolumn.setOnEditCommit(new EventHandler <CellEditEvent<Member,String>>(){

			@Override
			public void handle(CellEditEvent<Member, String> event) 
			{
				errortext.setText("");
				try 
				{
					Integer.parseInt(event.getNewValue());
					Member m =event.getRowValue();
					m.setPhonenumber(event.getNewValue());
					
					updatetext.setText("New Phone number accepted!");
					updatetext.setStyle(successMessage);
				}
				catch(Exception e )
				{
					updatetext.setText("Only Integer value is acceptable!");
					updatetext.setStyle(errorMessage);
				}

				
			}
			
			
		});
		
		
		
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		namecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		addresscolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		phonenumcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		for (int i=0; i<result.size(); i++)
		{
			searchmembertable.getItems().add(result.get(i));
		}
    }

    @FXML
    void PerformUpdate(ActionEvent event) 
    {
    	if (searchmembertable.getItems().isEmpty())
    	{
    		updatetext.setText("The Table is empty!");
			updatetext.setStyle(errorMessage);
    	}
    	else
    	{
    		ArrayList<Member> records = new ArrayList<Member>();
    		
    		for (int i=0; i<searchmembertable.getItems().size(); i++)
    		{
    			Member m = new Member (searchmembertable.getItems().get(i).getMemberid(),searchmembertable.getItems().get(i).getName(),searchmembertable.getItems().get(i).getAddress(), searchmembertable.getItems().get(i).getPhonenumber());
    			records.add(m);
    		}
    		
    		l.UPDATE_MemberRecord(records);
    		
    		updatetext.setText("Member Records Updated!");
			updatetext.setStyle(successMessage);
    	}
    }
    

    @FXML
    void goToAOperation(ActionEvent event) throws IOException 
    {
    	Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
		s.setWidth(1100);
		s.setHeight(720);
		s.centerOnScreen();
		m.changeScene(s,"src/application/ManageMembers.fxml");
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

}
