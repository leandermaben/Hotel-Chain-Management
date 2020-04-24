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

	/*public static ObservableList<Accounts> getAccountsData(String branch){
		try {
			Connection con=dbConnect();
			PreparedStatement ps1;
			if(branch!=null) 
				ps1=con.prepareStatement("select employee.emp_id,fname,lname,gender,position,joined,released,salary,aadhar_number,branch_id,pin,clearance from employee left outer join users on employee.emp_id=users.emp_id");
			else 
				ps1=con.prepareStatement("select employee.emp_id,fname,lname,gender,position,joined,released,salary,aadhar_number,branch_id,pin,clearance from employee left outer join users on employee.emp_id=users.emp_id where lname='"+lname+"'");
			
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
	}*/
	/*
	 * with stayRev(log_date,branch_id,amount1) as (select check_out,branch_id,sum((check_out-check_in)*basic_cost+extra_cost-discount) from stay natural join room group by check_out,branch_id),
foodRev(log_date,branch_id,amount2) as (select issued,branch_id,sum(price) from invoice group by issued,branch_id),
initAccount(log_date,branch_id,kitchen,taxes,bills,other,revenue) as (select accounts.log_date,accounts.branch_id,kitchen,taxes,bills,other,amount1+amount2 from accounts full outer join foodRev on accounts.log_date=foodRev.log_date and accounts.branch_id=foodRev.branch_id full outer join stayRev on stayRev.log_date=foodRev.log_date and stayRev.branch_id = foodRev.branch_id)
select log_date,t.branch_id,kitchen,taxes,bills,other,wages,revenue,revenue-wages from initAccount t,lateral(select sum(salary)/30 as wages from employee where joined<=log_date and (released is null or released>=log_date) and employee.branch_id=t.branch_id)
*/

}
