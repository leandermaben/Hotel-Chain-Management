package application;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Header;
import utils.State;

public class AdminPageController {

    @FXML
    private JFXBadge book;

    @FXML
    private JFXButton bookbtn;

    @FXML
    private LineChart<?, ?> profits;

    @FXML
    private JFXBadge allot;

    @FXML
    private Button allotbtn;

    @FXML
    private JFXBadge access;

    @FXML
    private Button accessbtn;

    @FXML
    private JFXBadge hire;

    @FXML
    private Button hirebtn;

    @FXML
    private JFXBadge notif;

    @FXML
    private JFXBadge update;

    @FXML
    private Button updatebtn;

    @FXML
    private JFXBadge search;

    @FXML
    private Button searchbtn;
    
    @FXML
    private Label name;

    @FXML
    private Label clock;
    
    private Header header;
    private State state;
    
    public void setState(State st) {
    	state=st;
    	header=new Header(state,name,clock);
    }
    @FXML
    void launchAccess() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Clearance.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		((ClearanceController)fx.getController()).setState(state);
    		state.getStage().setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void launchAllot() {

    }
    @FXML
    void launchSearch() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		((SearchController)fx.getController()).setState(state);
    		state.getStage().setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void launchBooking() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Booking.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		((BookingController)fx.getController()).setState(state);
    		state.getStage().setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }


    @FXML
    void launchLogistics() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Logistics.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		((LogisticsController)fx.getController()).setState(state);
    		state.getStage().setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

}

