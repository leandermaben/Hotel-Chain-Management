package entityClass;

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
import static utils.Connect.dbConnect;

public class Invoice extends RecursiveTreeObject<Invoice>{
	private StringProperty bill_id;
	private StringProperty issued;
	private DoubleProperty price;
	private StringProperty transaction_id;
	private StringProperty customer_id;
	private StringProperty items;
	Invoice(StringProperty a,StringProperty b,DoubleProperty c,StringProperty d,StringProperty e,StringProperty f){
		bill_id=a;
		issued=b;
		price=c;
		transaction_id=d;
		customer_id=e;
		items=f;
	}
	public StringProperty getBill_id() {
		return bill_id;
	}
	public void setBill_id(StringProperty bill_id) {
		this.bill_id = bill_id;
	}
	public StringProperty getIssued() {
		return issued;
	}
	public void setIssued(StringProperty issued) {
		this.issued = issued;
	}
	public DoubleProperty getPrice() {
		return price;
	}
	public void setPrice(DoubleProperty price) {
		this.price = price;
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
	public StringProperty getItems() {
		return items;
	}
	public void setItems(StringProperty items) {
		this.items = items;
	}
	
	public static ObservableList<Invoice> getInvoiceData(){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1,ps2;
			ps1=con.prepareStatement("select bill_id,issued,price,transaction_id,customer_id from invoice");
			ResultSet rs=ps1.executeQuery();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			ObservableList<Invoice> invoice=FXCollections.observableArrayList();
			while(rs.next()) {
				String dishes="";
				ps2=con.prepareStatement("select food_item,quantity from invoice natural join contain where customer_id='"+rs.getString(5)+"'");
				ResultSet rs2=ps2.executeQuery();
				while(rs2.next()) {
					dishes+=(rs2.getString(1)+" : "+rs2.getString(2)+"\n");
				}
				double spent=rs.getDouble(3);
				if(rs.wasNull())
					spent=0;
				invoice.add(new Invoice(new SimpleStringProperty(rs.getString(1)!=null?rs.getString(1):""),new SimpleStringProperty(rs.getDate(2)!=null?rs.getDate(2).toLocalDate().format(df):""),new SimpleDoubleProperty(spent),new SimpleStringProperty(rs.getString(4)!=null?rs.getString(4):""),new SimpleStringProperty(rs.getString(5)!=null?rs.getString(5):""),new SimpleStringProperty(dishes)));
			}
			
			return invoice;
			
		}catch(Exception e){
			System.out.println("Here: "+e);
			return null;
		}
	}
}
