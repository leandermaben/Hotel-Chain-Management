package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Room extends RecursiveTreeObject<Room>{
	private StringProperty booking_id;
	private StringProperty booked_from;
	private StringProperty booked_to;
	private StringProperty room_number;
	private StringProperty floor;
	private StringProperty serviced;
	private DoubleProperty basic_cost;
	private StringProperty branch_id;
	private StringProperty room_status;
	private StringProperty customer_id;
	private StringProperty fname;
	private StringProperty lname;
	Room(StringProperty a,StringProperty b,StringProperty c,StringProperty d,StringProperty f,DoubleProperty g,StringProperty h,StringProperty i,StringProperty j,StringProperty k,StringProperty l,StringProperty m){
		branch_id=a;
		room_number=b;
		floor=c;
		room_status=d;
		serviced=f;
		basic_cost=g;
		booking_id=h;
		booked_from=i;
		booked_to=j;
		fname=k;
		lname=l;
		customer_id=m;
	}
static ObservableList<Room> getRoomData(java.util.Date from,java.util.Date to){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1=null;
				if(from==null && to==null) {
					ps1=con.prepareStatement("select room.branch_id,room.room_num,floor,room_status,serviced,basic_cost,booking.booking_id,booked_from,booked_to,fname,lname,customer.customer_id from room left outer join booking on booking.branch_id=room.branch_id and booking.room_num=room.room_num left outer join customer on customer.customer_id=booking.customer_id and stat not in('cancelled','taken')");
				}else {
					java.sql.Date frF=new java.sql.Date(from.getTime());
					java.sql.Date toF=new java.sql.Date(to.getTime());
					ps1=con.prepareStatement("select room.branch_id,room.room_num,floor,room_status,serviced,basic_cost,booking.booking_id,booked_from,booked_to,fname,lname,customer.customer_id from room left outer join booking on booking.branch_id=room.branch_id and booking.room_num=room.room_num left outer join customer on customer.customer_id=booking.customer_id where booking_id is null or booked_from>? or booked_to<? and stat not in('cancelled','taken')");
					ps1.setDate(1, toF);
					ps1.setDate(2, frF);
				}
			
			ResultSet rs=ps1.executeQuery();
			ObservableList<Room> room=FXCollections.observableArrayList();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			while(rs.next()) {
				java.sql.Date f=rs.getDate(8);
				java.sql.Date t=rs.getDate(9);
				String fs=null;
				String ts=null;
				if(f!=null) {
					fs=f.toLocalDate().format(df);
					ts=t.toLocalDate().format(df);
				}
				room.add(new Room(new SimpleStringProperty(rs.getString(1)),new SimpleStringProperty(rs.getString(2)),new SimpleStringProperty(rs.getString(3)),new SimpleStringProperty(rs.getString(4)),new SimpleStringProperty(rs.getString(5)),new SimpleDoubleProperty(rs.getDouble(6)),new SimpleStringProperty(rs.getString(7)),new SimpleStringProperty(fs),new SimpleStringProperty(ts),new SimpleStringProperty(rs.getString(10)),new SimpleStringProperty(rs.getString(11)),new SimpleStringProperty(rs.getString(12))));
			}
			return room;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public StringProperty getBooking_id() {
	return booking_id;
}
public void setBooking_id(StringProperty booking_id) {
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
public StringProperty getFloor() {
	return floor;
}
public void setFloor(StringProperty floor) {
	this.floor = floor;
}
public StringProperty getServiced() {
	return serviced;
}
public void setServiced(StringProperty serviced) {
	this.serviced = serviced;
}
public DoubleProperty getBasic_cost() {
	return basic_cost;
}
public void setBasic_cost(DoubleProperty basic_cost) {
	this.basic_cost = basic_cost;
}
public StringProperty getBranch_id() {
	return branch_id;
}
public void setBranch_id(StringProperty branch_id) {
	this.branch_id = branch_id;
}
public StringProperty getRoom_status() {
	return room_status;
}
public void setRoom_status(StringProperty room_status) {
	this.room_status = room_status;
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
