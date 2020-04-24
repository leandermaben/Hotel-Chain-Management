package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.stage.Stage;

public class State {
	private Stage stage;
	private String emp_id;
	private String empName;
	private String clearance;
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
		this.stage=stage;
		this.emp_id=emp_id;
		try {
			Connection con=Connect.dbConnect();
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
		this.stage=stage;
		this.emp_id=emp_id;
		this.clearance=clearance;
		empName=fname+" "+lname;
	}
	
	
}
