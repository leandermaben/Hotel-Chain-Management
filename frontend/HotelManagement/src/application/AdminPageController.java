package application;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    
    private Stage stage;
    
    void setStage(Stage stage) {
    	this.stage=stage;
    }

    @FXML
    void launchAllot() {

    }

    @FXML
    void launchBooking() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("Booking.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		stage.setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void launchClearance() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("Clearance.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		((ClearanceController)fx.getController()).initialize();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		stage.setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void launchLogistics() {
    	try {
    		FXMLLoader fx=new FXMLLoader(getClass().getResource("Logistics.fxml"));
    		AnchorPane root=(AnchorPane)fx.load();
    		Scene scene=new Scene(root,1321,881);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		stage.setScene(scene);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

}

