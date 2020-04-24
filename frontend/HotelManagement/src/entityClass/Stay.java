package entityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utils.Connect.dbConnect;

public class Stay extends RecursiveTreeObject<Stay>{
	private StringProperty transaction_id;
	private StringProperty customer_id;
	private StringProperty check_in;
	private StringProperty check_out;
	private StringProperty room_num;
	private StringProperty branch_id;
	private DoubleProperty extra_cost;
	private DoubleProperty discount;
	private DoubleProperty basic_cost;
	private DoubleProperty food;
	private DoubleProperty total;
	public Stay(StringProperty transaction_id, StringProperty customer_id, StringProperty check_in,
			StringProperty check_out, StringProperty room_num, StringProperty branch_id, DoubleProperty extra_cost,
			DoubleProperty discount, DoubleProperty basic_cost, DoubleProperty food, DoubleProperty total) {
		this.transaction_id = transaction_id;
		this.customer_id = customer_id;
		this.check_in = check_in;
		this.check_out = check_out;
		this.room_num = room_num;
		this.branch_id = branch_id;
		this.extra_cost = extra_cost;
		this.discount = discount;
		this.basic_cost = basic_cost;
		this.food = food;
		this.total = total;
	}
	public static ObservableList<Stay> getStayData(){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			ps1=con.prepareStatement("with foodInfo(transaction_id,food_cost) as (select stay.transaction_id,sum(price) from stay left outer join invoice on stay.transaction_id=invoice.transaction_id group by stay.transaction_id) select transaction_id,customer_id,check_in,check_out,room_num,branch_id,extra_cost,discount,basic_cost,food_cost,(check_out-check_in)*basic_cost+extra_cost-discount+food_cost as total from stay natural join foodInfo natural join room");
			ResultSet rs=ps1.executeQuery();
			ObservableList<Stay> stay=FXCollections.observableArrayList();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			while(rs.next()) {
				double ec=rs.getDouble(7);
				if(rs.wasNull())
					ec=0;
				double d=rs.getDouble(7);
				if(rs.wasNull())
					d=0;
				double bc=rs.getDouble(7);
				if(rs.wasNull())
					bc=0;
				double fd=rs.getDouble(7);
				if(rs.wasNull())
					fd=0;
				double tot=rs.getDouble(7);
				if(rs.wasNull())
					tot=0;
				
				stay.add(new Stay(new SimpleStringProperty(rs.getString(1)!=null?rs.getString(1):""),new SimpleStringProperty(rs.getString(2)!=null?rs.getString(2):""),new SimpleStringProperty((rs.getDate(3)!=null)?rs.getDate(3).toLocalDate().format(df):" "),new SimpleStringProperty((rs.getDate(4)!=null)?rs.getDate(4).toLocalDate().format(df):" "),new SimpleStringProperty(rs.getString(5)!=null?rs.getString(5):""),new SimpleStringProperty(rs.getString(6)!=null?rs.getString(6):""),new SimpleDoubleProperty(ec),new SimpleDoubleProperty(d),new SimpleDoubleProperty(bc),new SimpleDoubleProperty(fd),new SimpleDoubleProperty(tot)));
			}
			return stay;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public StringProperty getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(StringProperty transaction_id) {
		this.transaction_id = transaction_id;
	}
	public StringProperty getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(StringProperty customer_id) {
		this.customer_id = customer_id;
	}
	public StringProperty getCheck_in() {
		return check_in;
	}
	public void setCheck_in(StringProperty check_in) {
		this.check_in = check_in;
	}
	public StringProperty getCheck_out() {
		return check_out;
	}
	public void setCheck_out(StringProperty check_out) {
		this.check_out = check_out;
	}
	public StringProperty getRoom_num() {
		return room_num;
	}
	public void setRoom_num(StringProperty room_num) {
		this.room_num = room_num;
	}
	public StringProperty getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(StringProperty branch_id) {
		this.branch_id = branch_id;
	}
	public DoubleProperty getExtra_cost() {
		return extra_cost;
	}
	public void setExtra_cost(DoubleProperty extra_cost) {
		this.extra_cost = extra_cost;
	}
	public DoubleProperty getDiscount() {
		return discount;
	}
	public void setDiscount(DoubleProperty discount) {
		this.discount = discount;
	}
	public DoubleProperty getBasic_cost() {
		return basic_cost;
	}
	public void setBasic_cost(DoubleProperty basic_cost) {
		this.basic_cost = basic_cost;
	}
	public DoubleProperty getFood() {
		return food;
	}
	public void setFood(DoubleProperty food) {
		this.food = food;
	}
	public DoubleProperty getTotal() {
		return total;
	}
	public void setTotal(DoubleProperty total) {
		this.total = total;
	}
}
