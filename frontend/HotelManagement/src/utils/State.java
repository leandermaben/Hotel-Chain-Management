package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import static utils.Connect.dbConnect;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class State {
	private Stage stage=null;
	private String emp_id=null;
	private String empName=null;
	private String clearance=null;
	private HashMap<String,ArrayList<Object>> store=new HashMap();
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public State(Stage stage,String emp_id){
		this();
		this.stage=stage;
		this.emp_id=emp_id;
		try {
			Connection con=dbConnect();
			PreparedStatement ps1=con.prepareStatement("select fname,lname from employee where emp_id='"+emp_id+"'");
			ResultSet rs=ps1.executeQuery();
			rs.next();
			empName=rs.getString(1)+" "+rs.getString(2);
			ps1=con.prepareStatement("select clearance from users where emp_id='"+emp_id+"'");
			rs=ps1.executeQuery();
			rs.next();
			clearance=rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public State(Stage stage,String emp_id,String fname,String lname,String clearance){
		this();
		this.stage=stage;
		this.emp_id=emp_id;
		this.clearance=clearance;
		empName=fname+" "+lname;
	}
	State(){
		store.put("AdminPage", new ArrayList());
		store.put("AdminNotification", new ArrayList());
		store.put("Booking", new ArrayList());
		store.put("ChangePassword", new ArrayList());
		store.put("Clearance", new ArrayList());
		store.put("Logistics", new ArrayList());
		store.put("Message", new ArrayList());
		store.put("NotifyAdmin", new ArrayList());
		store.put("Search", new ArrayList());
	}

	public ArrayList<Object> getPage(String page){
		if(store.get(page).size()!=0) {
			return store.get(page);
		}
		switch(page) {
			case "AdminPage":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/AdminPage.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,1321,881);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("AdminPage").add(scene);
					store.get("AdminPage").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "AdminNotification":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/AdminNotification.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,354,303);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("AdminNotification").add(scene);
					store.get("AdminNotification").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "Booking":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Booking.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,1321,881);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("Booking").add(scene);
					store.get("Booking").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "ChangePassword":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/ChangePassword.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,336,267);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("ChangePassword").add(scene);
					store.get("ChangePassword").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "Clearance":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Clearance.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,1321,881);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("Clearance").add(scene);
					store.get("Clearance").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "Logistics":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Logistics.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,1321,881);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("Logistics").add(scene);
					store.get("Logistics").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "Message":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Message.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,463,629);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("Message").add(scene);
					store.get("Message").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "NotifyAdmin":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/NotifyAdmin.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,336,267);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("NotifyAdmin").add(scene);
					store.get("NotifyAdmin").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
			case "Search":{
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
				AnchorPane root;
				try {
					root = (AnchorPane)fx.load();
					Scene scene=new Scene(root,1321,881);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					store.get("Search").add(scene);
					store.get("Search").add(fx.getController());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				}
		}
		return store.get(page);
	}
}
