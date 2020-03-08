package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	private StringProperty booked;
	private StringProperty serviced;
	private DoubleProperty basic_cost;
	private StringProperty branch_id;
	private StringProperty room_status;
	private StringProperty customer_id;
	private StringProperty fname;
	private StringProperty lname;
	Room(StringProperty a,StringProperty b,StringProperty c,StringProperty d,StringProperty e,StringProperty f,DoubleProperty g,StringProperty h,StringProperty i,StringProperty j){
		branch_id=a;
		room_number=b;
		floor=c;
		room_status=d;
		booked=e;
		serviced=f;
		basic_cost=g;
		booking_id=h;
		booked_from=i;
		booked_to=j;
	}
static ObservableList<Room> getRoomData(String from,String to){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1=null;
				if(from==null && to==null) {
					ps1=con.prepareStatement("select branch_id,room_number,floor,room_status,booked,serviced,basic_cost,booking_id,booked_from,booked_to from room natural join booking");
				}else {
					//ps1=con.prepareStatement("select branch_id,room_number,floor,room_status,booked,serviced,basic_cost,booking_id,booked_from,booked_to from room natural join booking  where not exists() ");
				}
			
			ResultSet rs=ps1.executeQuery();
			ObservableList<Room> room=FXCollections.observableArrayList();
			while(rs.next()) {
				room.add(new Room(new SimpleStringProperty(rs.getString(1)),new SimpleStringProperty(rs.getString(2)),new SimpleStringProperty(rs.getString(3)),new SimpleStringProperty(rs.getString(4)),new SimpleStringProperty(rs.getString(5)),new SimpleStringProperty(rs.getString(6)),new SimpleDoubleProperty(rs.getDouble(7)),new SimpleStringProperty(rs.getString(8)),new SimpleStringProperty(rs.getString(9)),new SimpleStringProperty(rs.getString(10))));
			}
			return room;
			
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
