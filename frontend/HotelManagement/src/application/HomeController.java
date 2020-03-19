package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    
    private Stage stage;
    
    public void setStage(Stage st) {
    	stage=st;
    }
    @FXML
    void login() {
    	String id=empid.getText();
    	String ps=spass.getText();
    	try {
    		Connection con=dbConnect();
    		PreparedStatement p=con.prepareStatement("select pin,clearance from users where emp_id='"+id+"'");
    		ResultSet rs=p.executeQuery();
    		if(!rs.next()) {
    			throw new Exception("Account does not exist");
    		}else {
    			if(!rs.getString(1).equals(ps)){
    				throw new Exception("Invalid Password");
    			}else {
    				if(rs.getString(2).equals("admin")) {
    					FXMLLoader fx=new FXMLLoader(getClass().getResource("AdminPage.fxml"));
    					AnchorPane root=(AnchorPane)fx.load();
    					Scene scene=new Scene(root,1321,881);
    					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    					((AdminPageController)fx.getController()).setStage(stage);
    					stage.setScene(scene);
    				}
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    
    @FXML
    void setup() {
    	try {
    		Connection con=dbConnect();
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
    			FXMLLoader fx=new FXMLLoader(getClass().getResource("AdminPage.fxml"));
				AnchorPane root=(AnchorPane)fx.load();
				Scene scene=new Scene(root,1321,881);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				((AdminPageController)fx.getController()).setStage(stage);
				System.out.println("Here");
				stage.setScene(scene);
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
    	System.out.println("Here");
    	set.setVisible(false);
    	sign.setOpacity(1);
    	sign.setVisible(true);
    }
    
    static Connection dbConnect() {
    	Connection con=null;
    	try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");  
	    con=DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1521:XE","system","Leroyale7"); 
        }
    	catch(Exception e) {e.printStackTrace();}
    	return con;
   }

}
