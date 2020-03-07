package application;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

import java.sql.*;

public class BookingController {
		static int customerId=1000;
		static int bookingId=1000;
	 @FXML
	    private JFXTextField cifname;

	    @FXML
	    private JFXTextField cilname;

	    @FXML
	    private JFXTextField cipn;

	    @FXML
	    private JFXTextField ciem;

	    @FXML
	    private JFXTextField ciac;

	    @FXML
	    private JFXTextField cipt;

	    @FXML
	    private JFXTextField ciapt;

	    @FXML
	    private JFXTextField ciapn;

	    @FXML
	    private JFXButton cibtn;

	    @FXML
	    private JFXButton ciabtn;

	    @FXML
	    private JFXRadioButton cimale;

	    @FXML
	    private ToggleGroup cinfo;

	    @FXML
	    private JFXRadioButton cifemale;

	    @FXML
	    private JFXTextField bkpn;

	    @FXML
	    private JFXTextField bkrm;

	    @FXML
	    private JFXButton bkbtn;

	    @FXML
	    private JFXDatePicker bkfr;

	    @FXML
	    private JFXDatePicker bkto;

	    @FXML
	    private JFXTextField bkbr_id;

	    @FXML
	    private JFXTextField bkupid;

	    @FXML
	    private JFXRadioButton bkupcn;

	    @FXML
	    private ToggleGroup bookUp;

	    @FXML
	    private JFXRadioButton bkupup;

	    @FXML
	    private JFXTextField bkupbr_id;

	    @FXML
	    private JFXDatePicker bkupfr;

	    @FXML
	    private JFXDatePicker bkupto;

	    @FXML
	    private JFXTextField bkuprm;

	    @FXML
	    private JFXButton bkupbtn;

    @FXML
    void addCustomer() {
    	try {
    		customerId++;
   	     	Connection conn=dbConnect();
   	     	String fname=cifname.getText().toString();
   	     	String lname=cilname.getText().toString();
   	     	String phone=cipn.getText();
   	     	String email=ciem.getText();
   	     	String adr=ciac.getText();
   	     	String pass=cipt.getText();
   	     	JFXRadioButton gen=(JFXRadioButton)cinfo.getSelectedToggle();
   	     	PreparedStatement ps=conn.prepareStatement("insert into customer values('"+customerId+"','"+fname+"','"+lname+"','"+email+"','"+adr+"','"+pass+"')");
   	     	ps.execute();
   	     	System.out.println("UPDATED");
   	    	}catch(Exception e) {System.out.print(e);}
    }

    @FXML
    void addPhone() {

    }

    @FXML
    void book() {

    }

    @FXML
    void updateBook() {

    }
    static Connection dbConnect() {
    	Connection con=null;
    	try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");  
	    con=DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1521:XE","system","Leroyale7"); 
        }
    	catch(Exception e) {System.out.print(e);}
    	return con;
   }

}
