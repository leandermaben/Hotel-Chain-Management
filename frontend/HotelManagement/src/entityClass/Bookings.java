package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bookings extends RecursiveTreeObject<Bookings>{
	private StringProperty booking_id;
	private StringProperty booked_from;
	private StringProperty booked_to;
	private StringProperty room_number;
	private StringProperty branch_id;
	private StringProperty stat;
	private StringProperty customer_id;
	private StringProperty fname;
	private StringProperty lname;
	
	public StringProperty getBooking_id() {
		return booking_id;
	}
	public void StringProperty(StringProperty booking_id) {
		this.booking_id = booking_id;
	}
	public StringProperty getBooked_from() {
		return booked_from;
	}
	public void setBooked_from(StringProperty booked_from) {
		this.booked_from = booked_from;
	}
	public StringProperty getBooked_to() {
		return booked_to;
	}
	public void setBooked_to(StringProperty booked_to) {
		this.booked_to = booked_to;
	}
	public StringProperty getRoom_number() {
		return room_number;
	}
	public void setRoom_number(StringProperty room_number) {
		this.room_number = room_number;
	}
	public StringProperty getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(StringProperty branch_id) {
		this.branch_id = branch_id;
	}
	public StringProperty getStat() {
		return stat;
	}
	public void setStat(StringProperty stat) {
		this.stat = stat;
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
	Bookings(StringProperty a,StringProperty b,StringProperty c,StringProperty d,StringProperty e,StringProperty f,StringProperty g,StringProperty h,StringProperty i){
		booking_id=a;
		booked_from=b;
		booked_to=c;
		room_number=d;
		branch_id=e;
		stat=f;
		customer_id=g;
		fname=h;
		lname=i;
	}
	static ObservableList<Bookings> getBookingsData(String fname,String lname,boolean booked){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			if(!booked) {
				if(fname==null && lname==null) {
					ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer");
				}else if(fname==null) {
					ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where lname='"+lname+"'");
				}else if(lname==null) {
					ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where fname='"+fname+"'");
				}else {
					ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where fname='"+fname+"'and lname='"+lname+"'");
				}
			}else {
					if(fname==null && lname==null) {
						ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where stat='booked'");
					}else if(fname==null) {
						ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where lname='"+lname+"' and stat='booked'");
					}else if(lname==null) {
						ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where fname='"+fname+"' and stat='booked'");
					}else {
						ps1=con.prepareStatement("select booking_id,booked_from,booked_to,room_num,branch_id,stat,customer_id,fname,lname from booking natural join customer where fname='"+fname+"'and lname='"+lname+"' and stat='booked'");
					}
			}
			ResultSet rs=ps1.executeQuery();
			ObservableList<Bookings> bookings=FXCollections.observableArrayList();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			while(rs.next()) {
				bookings.add(new Bookings(new SimpleStringProperty(rs.getString(1)),new SimpleStringProperty(rs.getDate(2).toLocalDate().format(df)),new SimpleStringProperty(rs.getDate(3).toLocalDate().format(df)),new SimpleStringProperty(rs.getString(4)),new SimpleStringProperty(rs.getString(5)),new SimpleStringProperty(rs.getString(6)),new SimpleStringProperty(rs.getString(7)),new SimpleStringProperty(rs.getString(8)),new SimpleStringProperty(rs.getString(9))));
			}
			return bookings;
			
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
