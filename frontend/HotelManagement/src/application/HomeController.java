package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Connect;
import utils.State;

public class HomeController {

	@FXML
	private GridPane sign;
	
	@FXML
	private GridPane set;
	  
    @FXML
    private JFXTextField empid;

    @FXML
    private JFXPasswordField spass;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField aadhar;

    @FXML
    private ToggleGroup gd;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField conpass;
    
    private Stage loginWindow;

	private double xOffset;

	private double yOffset;
    
    public void setLoginWindow(Stage st) {
    	loginWindow=st;
    }
    @FXML
    public void initialize() {
    	set.setVisible(false);
    	sign.setVisible(true);
    }
    @FXML
    void login() {
    	Stage stage=new Stage();
    	String id=empid.getText();
    	String ps=spass.getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement p=con.prepareStatement("select pin,clearance from users where emp_id='"+id+"'");
    		ResultSet rs=p.executeQuery();
    		if(!rs.next()) {
    			throw new Exception("Account does not exist");
    		}else {
    			if(!rs.getString(1).equals(ps)){
    				throw new Exception("Invalid Password");
    			}else {
    				loginWindow.close();
					State state=new State(stage,id);
    				if(rs.getString(2).equals("admin")) {
    					ArrayList<Object> fx=state.getPage("AdminPage");
    					((AdminPageController)fx.get(1)).setState(state);
    					stage.setScene((Scene)fx.get(0));
    					stage.show();
    				}else if(rs.getString(2).equals("supervisor")) {
    					
    				}else {
    					ArrayList<Object> fx=state.getPage("Booking");
    					((BookingController)fx.get(1)).setState(state);
    					stage.setScene((Scene)fx.get(0));
    					stage.show();
    				}
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    
    @FXML
    void setup() {
    	Stage stage=new Stage();
    	try {
    		Connection con=Connect.dbConnect();
    		String fn=fname.getText();
    		String ln=lname.getText();
    		String pn=phone.getText();
    		String ad=aadhar.getText();
    		String gen=((JFXRadioButton)gd.getSelectedToggle()).getText();
    		String ps=pass.getText();
    		String cps=conpass.getText();
    		PreparedStatement p=con.prepareStatement("select count(*) from employee");
    		ResultSet rs=p.executeQuery();
    		rs.next();
    		if(rs.getInt(1)!=0)
    			throw new Exception("Alredy Set Up !!!");
    		 p=con.prepareStatement("insert into employee values('"+1000+"','"+fn+"','"+ln+"','"+gen.charAt(0)+"','main admin',current_date,null,null,'"+ad+"',null)");
    		p.execute();
    		if(ps.equals(cps)) {
    			p=con.prepareStatement("insert into users values('"+1000+"','"+ps+"','admin')");
    			p.execute();
				State state=new State(stage,"1000");
				loginWindow.close();
				ArrayList<Object> fx=state.getPage("AdminPage");
				((AdminPageController)fx.get(1)).setState(state);
				stage.setScene((Scene)fx.get(0));
				stage.show();
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void setupDisp() {
    	set.setVisible(true);
    	sign.setVisible(false);
    }

    @FXML
    void signinDisp() {
    	set.setVisible(false);
    	sign.setVisible(true);
    }
    
    @FXML
    void move(MouseEvent event) {
	 loginWindow.setX(event.getScreenX() - xOffset);
     loginWindow.setY(event.getScreenY() - yOffset);
    }

    @FXML
    void register(MouseEvent event) {
    	 xOffset = event.getSceneX();
         yOffset = event.getSceneY();
    }
    
    @FXML
    void close() {
    	loginWindow.close();
    	System.exit(0);
    }


}
