package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.Header;
import utils.State;

import static utils.Connect.dbConnect;

public class ClearanceController {

    @FXML
    private JFXBadge addAccount;

    @FXML
    private JFXListView<String> nacemp;

    @FXML
    private JFXListView<String> acemp;

    @FXML
    private JFXBadge addSupervisor;

    @FXML
    private JFXBadge addAdmin;

    @FXML
    private JFXListView<String> nsup;

    @FXML
    private JFXListView<String> sup;

    @FXML
    private JFXListView<String> nadmin;

    @FXML
    private JFXListView<String> admin;

    private State state;
    
    private Header header;
    @FXML
    private Label name;

    @FXML
    private Label clock;
    
    public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
		this.header=new Header(state,name,clock);
	}

	ObservableList<String> nemp;
	ObservableList<String> emp;
	ObservableList<String> nsupervisor;
	ObservableList<String> supervisor;
	ObservableList<String> nad;
	ObservableList<String> ad;
	
    
    public void initialize() {
    	nemp=FXCollections.observableArrayList();
    	emp=FXCollections.observableArrayList();
    	nsupervisor=FXCollections.observableArrayList();
    	supervisor=FXCollections.observableArrayList();
    	nad=FXCollections.observableArrayList();
    	ad=FXCollections.observableArrayList();
    	try {
    		Connection con=dbConnect();
    		PreparedStatement ps=con.prepareStatement("select emp_id,lname,fname from employee where emp_id not in(select emp_id from users)");
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			nemp.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		nacemp.setItems(nemp);
    		ps=con.prepareStatement("select emp_id,lname,fname from users natural join employee");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			emp.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		acemp.setItems(emp);
    		ps=con.prepareStatement("select emp_id,lname,fname from users natural join employee where clearance='employee'");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			nsupervisor.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		nsup.setItems(nsupervisor);
    		ps=con.prepareStatement("select emp_id,lname,fname from users natural join employee where clearance='supervisor'");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			supervisor.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		sup.setItems(supervisor);
    		ps=con.prepareStatement("select emp_id,lname,fname from users natural join employee where clearance<>'admin'");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			nad.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		nadmin.setItems(nad);
    		ps=con.prepareStatement("select emp_id,lname,fname from users natural join employee where clearance='admin'");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			ad.add(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
    		}
    		admin.setItems(ad);
    	}catch(Exception e) {
    		e.printStackTrace();
    		}
    }
    @FXML
    void grantAdmin() {

    }

    @FXML
    void grantEmployee() {

    }

    @FXML
    void grantSupervisor() {

    }

    @FXML
    void revokeAdmin() {

    }

    @FXML
    void revokeEmployee() {

    }

    @FXML
    void revokeSupervisor() {

    }
    @FXML
    void back(){
    	header.back();
    }

}
