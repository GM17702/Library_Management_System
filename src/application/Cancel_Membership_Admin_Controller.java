package application;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business_Layer.Book;
import business_Layer.Fine_Record;
import business_Layer.LMS_Controller;
import business_Layer.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cancel_Membership_Admin_Controller implements Initializable {

    @FXML
    public TableColumn<Member,String> addresscolumn;
    public Button cancelmembershipbutton;
    public Label errortext;
    public TableColumn<Fine_Record, String> feestatuscolumn;
    public TableView<Fine_Record> feetable;
    public ToggleButton hometoolbar;
    public TableView<Member> memberdetailtable;
    public TableColumn<Member,String> memberidcolumn;
    public TableColumn<Member,String> namecolumn;
    public ToggleButton operationstoolbar;
    public TableColumn<Member,String> pnumcolumn;
    public ToggleButton signouttoolbar;
    public Font x2;
    
    public String successMessage = String.format("-fx-text-fill: GREEN;");
    public String errorMessage = String.format("-fx-text-fill: RED;");
    
    Main m = new Main();
    LMS_Controller l = new LMS_Controller();

    @FXML
    void PerformCancelMembership(ActionEvent event) 
    {
    	int row = memberdetailtable.getSelectionModel().getSelectedIndex();
    	
    	if (row >= 0)
    	{
    		Member mem = memberdetailtable.getItems().get(row);
    		l.DELETE_MemberRecord(mem);
    		l.DELETE_PaymentMethod(mem.getMemberid());
    		
    		memberdetailtable.getItems().remove(row);
    		feetable.getItems().remove(row);
    		memberdetailtable.getSelectionModel().clearSelection();
    		
    		errortext.setText("Membership Cancelled!");
			errortext.setStyle(successMessage);
    		
    	}
    	else
    	{
    		errortext.setText("No Member Record Selected!");
			errortext.setStyle(errorMessage);
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
    void goToOperations(ActionEvent event) throws IOException 
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
		feetable.setSelectionModel(null);
		
		
		memberidcolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("memberid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("name"));
		addresscolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("address"));
		pnumcolumn.setCellValueFactory(new PropertyValueFactory <Member , String>("phonenumber"));
		feestatuscolumn.setCellValueFactory(new PropertyValueFactory <Fine_Record , String>("finestatus"));
		
		memberidcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		namecolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		addresscolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		pnumcolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		feestatuscolumn.setStyle("-fx-alignment: BASELINE_CENTER;");
		
		memberidcolumn.setSortable(false);
		namecolumn.setSortable(false);
		addresscolumn.setSortable(false);
		pnumcolumn.setSortable(false);
		feestatuscolumn.setSortable(false);
		
		ArrayList<Member> member = l.GetAllMemberDetails();
		
		if (member.isEmpty())
		{
			errortext.setText("No members exist!");
    		errortext.setStyle(errorMessage);
		}
		else
		{
			ArrayList<String> memberids = new ArrayList<String>();
			
			for (int i=0; i<member.size(); i++)
			{
				memberids.add(member.get(i).getMemberid());
			}
			
			ArrayList<Fine_Record> statuses = l.GetAllMemberFineStatus(memberids);
			
			for (int i=0; i<memberids.size(); i++)
			{
				memberdetailtable.getItems().add(member.get(i));
				feetable.getItems().add(statuses.get(i));
			}
		}
	}

}
