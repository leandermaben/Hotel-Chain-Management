package entityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utils.Connect.dbConnect;

public class Employee extends RecursiveTreeObject<Employee>{
	
	private StringProperty emp_id;
	private StringProperty fname;
	private StringProperty lname;
	private StringProperty gender;
	private StringProperty position;
	private StringProperty joined;
	private StringProperty released;
	private DoubleProperty salary;
	private StringProperty aadhar_number;
	private StringProperty branch_id;
	private StringProperty pin;
	private StringProperty clearance;
	
	public StringProperty getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(StringProperty emp_id) {
		this.emp_id = emp_id;
	}
	public StringProperty getFname() {
		return fname;
	}
	public void setFname(StringProperty fname) {
		this.fname = fname;
	}
	public StringProperty getLname() {
		return lname;
	}
	public void setLname(StringProperty lname) {
		this.lname = lname;
	}
	public StringProperty getGender() {
		return gender;
	}
	public void setGender(StringProperty gender) {
		this.gender = gender;
	}
	public StringProperty getPosition() {
		return position;
	}
	public void setPosition(StringProperty position) {
		this.position = position;
	}
	public StringProperty getJoined() {
		return joined;
	}
	public void setJoined(StringProperty joined) {
		this.joined = joined;
	}
	public StringProperty getReleased() {
		return released;
	}
	public void setReleased(StringProperty released) {
		this.released = released;
	}
	public DoubleProperty getSalary() {
		return salary;
	}
	public void setSalary(DoubleProperty salary) {
		this.salary = salary;
	}
	public StringProperty getAadhar_number() {
		return aadhar_number;
	}
	public void setAadhar_number(StringProperty aadhar_number) {
		this.aadhar_number = aadhar_number;
	}
	public StringProperty getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(StringProperty branch_id) {
		this.branch_id = branch_id;
	}
	public StringProperty getPin() {
		return pin;
	}
	public void setPin(StringProperty pin) {
		this.pin = pin;
	}
	public StringProperty getClearance() {
		return clearance;
	}
	public void setClearance(StringProperty clearance) {
		this.clearance = clearance;
	}
	
	Employee(StringProperty a,StringProperty b,StringProperty c,StringProperty d,StringProperty e,StringProperty f,StringProperty g,DoubleProperty h,StringProperty i,StringProperty j,StringProperty k,StringProperty l){
		emp_id=a;
		fname=b;
		lname=c;
		gender=d;
		position=e;
		joined=f;
		released=g;
		salary=h;
		aadhar_number=i;
		branch_id=j;
		pin=k;
		clearance=l;
	}
	public static ObservableList<Employee> getEmployeeData(String fname,String lname,boolean working){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			if(!working) {
				if(fname==null && lname==null) {
					ps1=con.prepareStatement("select employee.emp_id,fname,lname,gender,position,joined,released,salary,aadhar_number,branch_id,pin,clearance from employee left outer join users on employee.emp_id=users.emp_id");
				}else if(fname==null) {
					ps1=con.prepareStatement("select employee.emp_id,fname,lname,gender,position,joined,released,salary,aadhar_number,branch_id,pin,clearance from employee left outer join users on employee.emp_id=users.emp_id where lname='"+lname+"'");
				}else if(lname==null) {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where fname='"+fname+"'");
				}else {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where fname='"+fname+"'and lname='"+lname+"'");
				}
			}else {
				if(fname==null && lname==null) {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where released is null");
				}else if(fname==null) {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where lname='"+lname+"' and released is null");
				}else if(lname==null) {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where fname='"+fname+"' and released is null");
				}else {
					ps1=con.prepareStatement("select * from employee left outer join users on employee.emp_id=users.emp_id where fname='"+fname+"'and lname='"+lname+"' and released is null");
				}
			}
			ResultSet rs=ps1.executeQuery();
			ObservableList<Employee> employee=FXCollections.observableArrayList();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			while(rs.next()) {
				employee.add(new Employee(new SimpleStringProperty(rs.getString(1)),new SimpleStringProperty(rs.getString(2)),new SimpleStringProperty(rs.getString(3)),new SimpleStringProperty(rs.getString(4)),new SimpleStringProperty(rs.getString(5)),new SimpleStringProperty(rs.getDate(6).toLocalDate().format(df)),new SimpleStringProperty((rs.getDate(7)!=null)?rs.getDate(7).toLocalDate().format(df):" "),new SimpleDoubleProperty(rs.getDouble(8)),new SimpleStringProperty(rs.getString(9)),new SimpleStringProperty(rs.getString(10)),new SimpleStringProperty(rs.getString(11)),new SimpleStringProperty(rs.getString(12))));
			}
			return employee;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
}
