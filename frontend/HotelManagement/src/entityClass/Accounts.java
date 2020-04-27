package entityClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utils.Connect.dbConnect;
public class Accounts extends RecursiveTreeObject<Accounts>{

	private StringProperty log_date;
	private StringProperty branch_id;
	private DoubleProperty kitchen;
	private DoubleProperty taxes;
	private DoubleProperty bills;
	private DoubleProperty others;
	private DoubleProperty wages;
	private DoubleProperty revenue;
	private DoubleProperty profit;
	public Accounts(StringProperty log_date, StringProperty branch_id, DoubleProperty kitchen, DoubleProperty taxes,
			DoubleProperty bills, DoubleProperty others, DoubleProperty wages, DoubleProperty revenue,
			DoubleProperty profit) {
		super();
		this.log_date = log_date;
		this.branch_id = branch_id;
		this.kitchen = kitchen;
		this.taxes = taxes;
		this.bills = bills;
		this.others = others;
		this.wages = wages;
		this.revenue = revenue;
		this.profit = profit;
	}
	public StringProperty getLog_date() {
		return log_date;
	}
	public void setLog_date(StringProperty log_date) {
		this.log_date = log_date;
	}
	public StringProperty getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(StringProperty branch_id) {
		this.branch_id = branch_id;
	}
	public DoubleProperty getKitchen() {
		return kitchen;
	}
	public void setKitchen(DoubleProperty kitchen) {
		this.kitchen = kitchen;
	}
	public DoubleProperty getTaxes() {
		return taxes;
	}
	public void setTaxes(DoubleProperty taxes) {
		this.taxes = taxes;
	}
	public DoubleProperty getBills() {
		return bills;
	}
	public void setBills(DoubleProperty bills) {
		this.bills = bills;
	}
	public DoubleProperty getOthers() {
		return others;
	}
	public void setOthers(DoubleProperty others) {
		this.others = others;
	}
	public DoubleProperty getWages() {
		return wages;
	}
	public void setWages(DoubleProperty wages) {
		this.wages = wages;
	}
	public DoubleProperty getRevenue() {
		return revenue;
	}
	public void setRevenue(DoubleProperty revenue) {
		this.revenue = revenue;
	}
	public DoubleProperty getProfit() {
		return profit;
	}
	public void setProfit(DoubleProperty profit) {
		this.profit = profit;
	}

	public static ObservableList<Accounts> getAccountsData(String branch){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			CallableStatement cst;
			if(branch==null) 
				ps1=con.prepareStatement("select log_date,branch_id,kitchen,taxes,bills,other,wages from accounts");
			else 
				ps1=con.prepareStatement("select log_date,branch_id,kitchen,taxes,bills,other,wages from accounts where branch_id='"+branch+"'");
			ResultSet rs=ps1.executeQuery();
			ObservableList<Accounts> account=FXCollections.observableArrayList();
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			while(rs.next()) {
				System.out.println("Here");
				cst=con.prepareCall("{call calcRevenue(?,?,?)}");
				cst.setDate(1,rs.getDate(1));
				cst.registerOutParameter(2, Types.DOUBLE);
				cst.registerOutParameter(3, Types.DOUBLE);
				cst.execute();
				account.add(new Accounts(new SimpleStringProperty(rs.getDate(1).toLocalDate().format(df)),new SimpleStringProperty(rs.getString(2)),new SimpleDoubleProperty(rs.getDouble(3)),new SimpleDoubleProperty(rs.getDouble(4)),new SimpleDoubleProperty(rs.getDouble(5)),new SimpleDoubleProperty(rs.getDouble(6)),new SimpleDoubleProperty(rs.getDouble(7)),new SimpleDoubleProperty(cst.getDouble(2)),new SimpleDoubleProperty(cst.getDouble(3))));
			}
			System.out.println(account.size());
			return account;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
