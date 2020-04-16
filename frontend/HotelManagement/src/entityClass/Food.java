package entityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class Food extends RecursiveTreeObject<Food>{
	private StringProperty item;
	private DoubleProperty price;
	private IntegerProperty sold;
	private StringProperty available;
	public StringProperty getItem() {
		return item;
	}
	public void setItem(StringProperty item) {
		this.item = item;
	}
	public DoubleProperty getPrice() {
		return price;
	}
	public void setPrice(DoubleProperty price) {
		this.price = price;
	}
	public IntegerProperty getSold() {
		return sold;
	}
	public void setSold(IntegerProperty sold) {
		this.sold = sold;
	}
	public StringProperty getAvailable() {
		return available;
	}
	public void setAvailable(StringProperty available) {
		this.available = available;
	}
	Food(StringProperty item,DoubleProperty price, IntegerProperty sold, StringProperty available) {
		this.item = item;
		this.price = price;
		this.sold = sold;
		this.available = available;
	}
	public static ObservableList<Food> getFoodData(){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			ps1=con.prepareStatement("select * from food");
			ResultSet rs=ps1.executeQuery();
			ObservableList<Food> food=FXCollections.observableArrayList();
			while(rs.next()) {
				int sold=rs.getInt(3);
				if(rs.wasNull())
					sold=0;
				food.add(new Food(new SimpleStringProperty(rs.getString(1)!=null?rs.getString(1):""),new SimpleDoubleProperty(rs.getDouble(2)),new SimpleIntegerProperty(sold),new SimpleStringProperty(rs.getString(4)!=null?rs.getString(4):"")));
			}
			return food;
			
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
