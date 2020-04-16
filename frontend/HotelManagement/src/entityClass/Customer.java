package entityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer extends RecursiveTreeObject<Customer>{
	private StringProperty customer_id;
	private StringProperty fname;
	private StringProperty lname;
	private StringProperty email;
	private StringProperty aadhar_number;
	private StringProperty passport_number;
	private StringProperty gender;
	private DoubleProperty spent;
	private IntegerProperty duration;
	private StringProperty phone;
	Customer(StringProperty a,StringProperty b,StringProperty c,StringProperty d,StringProperty e,StringProperty f,StringProperty g,DoubleProperty h,IntegerProperty i,StringProperty j){
		customer_id=a;
		fname=b;
		lname=c;
		email=d;
		aadhar_number=e;
		passport_number=f;
		gender=g;
		spent=h;
		duration=i;
		phone=j;
	}
	public StringProperty getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(StringProperty customer_id) {
		this.customer_id = customer_id;
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
	public StringProperty getEmail() {
		return email;
	}
	public void setEmail(StringProperty email) {
		this.email = email;
	}
	public StringProperty getAadhar_number() {
		return aadhar_number;
	}
	public void setAadhar_number(StringProperty aadhar_number) {
		this.aadhar_number = aadhar_number;
	}
	public StringProperty getPassport_number() {
		return passport_number;
	}
	public void setPassport_number(StringProperty passport_number) {
		this.passport_number = passport_number;
	}
	public StringProperty getGender() {
		return gender;
	}
	public void setGender(StringProperty gender) {
		this.gender = gender;
	}
	public DoubleProperty getSpent() {
		return spent;
	}
	public void setSpent(DoubleProperty spent) {
		this.spent = spent;
	}
	public IntegerProperty getDuration() {
		return duration;
	}
	public void setDuration(IntegerProperty duration) {
		this.duration = duration;
	}
	public StringProperty getPhone() {
		return phone;
	}
	public void setPhone(StringProperty phone) {
		this.phone = phone;
	}
	public static ObservableList<Customer> getCustomerData(String fname,String lname){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
				if(fname==null && lname==null) 
					ps1=con.prepareStatement("with spentfood(customer_id,amount1) as (select customer_id,sum(price) from invoice group by customer_id),stayinfo(customer_id,amount2,duration) as (select customer_id,sum(basic_cost*(check_out-check_in)+extra_cost),sum(check_out-check_in) from stay natural join room group by customer_id),expenditure(customer_id,spent,duration) as (select spentfood.customer_id,amount1+amount2,duration from spentfood full outer join stayinfo on spentfood.customer_id=stayinfo.customer_id), target(customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration) as (select customer.customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration from customer left outer join expenditure on customer.customer_id=expenditure.customer_id) select * from target");
				else if(fname==null)
					ps1=con.prepareStatement("with spentfood(customer_id,amount1) as (select customer_id,sum(price) from invoice group by customer_id)," + 
											"stayinfo(customer_id,amount2,duration) as (select customer_id,sum(basic_cost*(check_out-check_in)+extra_cost),sum(check_out-check_in) from stay natural join room group by customer_id),"+ 
											"expenditure(customer_id,spent,duration) as (select spentfood.customer_id,amount1+amount2,duration from spentfood full outer join stayinfo on spentfood.customer_id=stayinfo.customer_id)," + 
											"target(customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration) as (select customer.customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration from customer left outer join expenditure on customer.customer_id=expenditure.customer_id) select * from target where lname='"+lname+"'");
				else if(lname==null) 
					ps1=con.prepareStatement("with spentfood(customer_id,amount1) as (select customer_id,sum(price) from invoice group by customer_id)," + 
											"stayinfo(customer_id,amount2,duration) as (select customer_id,sum(basic_cost*(check_out-check_in)+extra_cost),sum(check_out-check_in) from stay natural join room group by customer_id)," + 
											"expenditure(customer_id,spent,duration) as (select spentfood.customer_id,amount1+amount2,duration from spentfood full outer join stayinfo on spentfood.customer_id=stayinfo.customer_id)," + 
											"target(customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration) as (select customer.customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration from customer left outer join expenditure on customer.customer_id=expenditure.customer_id) select * from target where fname='"+fname+"'");
				else 
					ps1=con.prepareStatement("with spentfood(customer_id,amount1) as (select customer_id,sum(price) from invoice group by customer_id)," + 
							"stayinfo(customer_id,amount2,duration) as (select customer_id,sum(basic_cost*(check_out-check_in)+extra_cost),sum(check_out-check_in) from stay natural join room group by customer_id),"+ 
							"expenditure(customer_id,spent,duration) as (select spentfood.customer_id,amount1+amount2,duration from spentfood full outer join stayinfo on spentfood.customer_id=stayinfo.customer_id)," + 
							"target(customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration) as (select customer.customer_id,fname,lname,email,aadhar_number,passport_number,gender,spent,duration from customer left outer join expenditure on customer.customer_id=expenditure.customer_id) select * from target where fname='"+fname+"'and lname='"+lname+"'");
			ResultSet rs=ps1.executeQuery();
			ObservableList<Customer> customers=FXCollections.observableArrayList();
			while(rs.next()) {
				PreparedStatement ps2=con.prepareStatement("select phone from phone where customer_id ='"+rs.getString(1)+"'");
				ResultSet rs2=ps2.executeQuery();
				String phone="";
				while(rs2.next()) {
					phone+=rs2.getString(1);
				}
				double spent;
				int duration;
				spent=rs.getDouble(8);
				if(rs.wasNull())
					spent=0;
				duration=rs.getInt(9);
				if(rs.wasNull())
					duration=0;
				customers.add(new Customer(new SimpleStringProperty(rs.getString(1)!=null?rs.getString(1):""),new SimpleStringProperty(rs.getString(2)!=null?rs.getString(2):""),new SimpleStringProperty(rs.getString(3)!=null?rs.getString(3):""),new SimpleStringProperty(rs.getString(4)!=null?rs.getString(4):""),new SimpleStringProperty(rs.getString(5)!=null?rs.getString(5):""),new SimpleStringProperty(rs.getString(6)!=null?rs.getString(6):""),new SimpleStringProperty(rs.getString(7)!=null?rs.getString(7):""),new SimpleDoubleProperty(spent),new SimpleIntegerProperty(duration),new SimpleStringProperty(phone)));
			}
			return customers;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
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
