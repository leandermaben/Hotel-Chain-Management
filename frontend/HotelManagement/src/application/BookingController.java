package application;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class BookingController {
	 @FXML
	    private JFXTextField cifname;

	    @FXML
	    private JFXTextField cilname;

	    @FXML
	    private JFXTextField cipn;

	    @FXML
	    private JFXTextField ciem;

	    @FXML
	    private JFXTextField ciac;

	    @FXML
	    private JFXTextField cipt;

	    @FXML
	    private JFXTextField ciapt;

	    @FXML
	    private JFXTextField ciapn;

	    @FXML
	    private JFXButton cibtn;

	    @FXML
	    private JFXButton ciabtn;

	    @FXML
	    private JFXRadioButton cimale;

	    @FXML
	    private ToggleGroup cinfo;

	    @FXML
	    private JFXRadioButton cifemale;

	    @FXML
	    private JFXTextField bkpn;

	    @FXML
	    private JFXTextField bkrm;

	    @FXML
	    private JFXButton bkbtn;

	    @FXML
	    private JFXDatePicker bkfr;

	    @FXML
	    private JFXDatePicker bkto;

	    @FXML
	    private JFXTextField bkbr_id;

	    @FXML
	    private JFXTextField bkupid;

	    @FXML
	    private JFXRadioButton bkupcn;

	    @FXML
	    private ToggleGroup bookUp;

	    @FXML
	    private JFXRadioButton bkupup;

	    @FXML
	    private JFXTextField bkupbr_id;

	    @FXML
	    private JFXDatePicker bkupfr;

	    @FXML
	    private JFXDatePicker bkupto;

	    @FXML
	    private JFXTextField bkuprm;

	    @FXML
	    private JFXButton bkupbtn;

	    @FXML
	    private JFXTreeTableView<Bookings> bookTable;
	    
	    @FXML
	    private JFXTextField qty;
	    
	    @FXML
	    private JFXListView<String> menu;
	    
	    @FXML
	    private JFXTextField fbpt;

	    @FXML
	    private Label ordered;

	    @FXML
	    private Label price;
	    @FXML
	    private JFXToggleButton stay;
	    
	    @FXML
	    private JFXTextField rm;

	    @FXML
	    private JFXTextField fcifname;

	    @FXML
	    private JFXTextField fcilname;

	    @FXML
	    private JFXTextField fcipn;

	    @FXML
	    private JFXTextField fciem;

	    @FXML
	    private JFXTextField fciac;

	    @FXML
	    private JFXTextField fcipt;

	    @FXML
	    private JFXTextField fciapt;

	    @FXML
	    private JFXTextField fciapn;

	    @FXML
	    private JFXRadioButton fcimale;

	    @FXML
	    private ToggleGroup fcinfo;

	    @FXML
	    private JFXRadioButton fcifemale;
	    
	    @FXML
	    private JFXTreeTableView<?> rmTable;

	    @FXML
	    private JFXDatePicker rmfr;

	    @FXML
	    private JFXDatePicker rmto;
	    
	    HashMap<String,Double> h;
	    
	    ArrayList<String> orlist;
	    
	    double cost=0;
	    
	    HashMap<String,Integer> o;
	    
@FXML
void initialize() {
	JFXTreeTableColumn<Bookings,String> col1=new JFXTreeTableColumn<>("Booking_id");
	col1.setMinWidth(200);
	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooking_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col2=new JFXTreeTableColumn<>("Booked_from");
	col2.setMinWidth(200);
	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooked_from();
		}
	});
	JFXTreeTableColumn<Bookings,String> col3=new JFXTreeTableColumn<>("Booked_to");
	col3.setMinWidth(200);
	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooked_to();
		}
	});
	JFXTreeTableColumn<Bookings,String> col4=new JFXTreeTableColumn<>("Room_Number");
	col4.setMinWidth(200);
	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getRoom_number();
		}
	});
	JFXTreeTableColumn<Bookings,String> col5=new JFXTreeTableColumn<>("Branch_id");
	col5.setMinWidth(200);
	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBranch_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col6=new JFXTreeTableColumn<>("Status");
	col6.setMinWidth(200);
	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getStat();
		}
	});
	JFXTreeTableColumn<Bookings,String> col7=new JFXTreeTableColumn<>("Customer_id");
	col7.setMinWidth(200);
	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getCustomer_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col8=new JFXTreeTableColumn<>("First Name");
	col8.setMinWidth(200);
	col8.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getFname();
		}
	});
	JFXTreeTableColumn<Bookings,String> col9=new JFXTreeTableColumn<>("Last Name");
	col9.setMinWidth(200);
	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getLname();
		}
	});
	ObservableList<Bookings> bookings=Bookings.getBookingsData(null,null,false);
	final TreeItem<Bookings> root=new RecursiveTreeItem<Bookings>(bookings,RecursiveTreeObject::getChildren);
	bookTable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9);
	bookTable.setRoot(root);
	bookTable.setShowRoot(false);
	    }

@FXML
void initializeRoom() {
	JFXTreeTableColumn<Bookings,String> col1=new JFXTreeTableColumn<>("Booking_id");
	col1.setMinWidth(200);
	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooking_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col2=new JFXTreeTableColumn<>("Booked_from");
	col2.setMinWidth(200);
	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooked_from();
		}
	});
	JFXTreeTableColumn<Bookings,String> col3=new JFXTreeTableColumn<>("Booked_to");
	col3.setMinWidth(200);
	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBooked_to();
		}
	});
	JFXTreeTableColumn<Bookings,String> col4=new JFXTreeTableColumn<>("Room_Number");
	col4.setMinWidth(200);
	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getRoom_number();
		}
	});
	JFXTreeTableColumn<Bookings,String> col5=new JFXTreeTableColumn<>("Branch_id");
	col5.setMinWidth(200);
	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getBranch_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col6=new JFXTreeTableColumn<>("Status");
	col6.setMinWidth(200);
	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getStat();
		}
	});
	JFXTreeTableColumn<Bookings,String> col7=new JFXTreeTableColumn<>("Customer_id");
	col7.setMinWidth(200);
	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getCustomer_id();
		}
	});
	JFXTreeTableColumn<Bookings,String> col8=new JFXTreeTableColumn<>("First Name");
	col8.setMinWidth(200);
	col8.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getFname();
		}
	});
	JFXTreeTableColumn<Bookings,String> col9=new JFXTreeTableColumn<>("Last Name");
	col9.setMinWidth(200);
	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Bookings,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Bookings,String> param){
			return param.getValue().getValue().getLname();
		}
	});
	ObservableList<Bookings> bookings=Bookings.getBookingsData(null,null,false);
	final TreeItem<Bookings> root=new RecursiveTreeItem<Bookings>(bookings,RecursiveTreeObject::getChildren);
	bookTable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9);
	bookTable.setRoot(root);
	bookTable.setShowRoot(false);
	    }


    @FXML
    void addCustomer() {
    	try {
   	     	Connection conn=dbConnect();
	   	     PreparedStatement ps4=conn.prepareStatement("select max(customer_id) from customer");
	     	ResultSet rs4=ps4.executeQuery();
	     	rs4.next();
	     	int customerId;
	     	String temp=rs4.getString(1);
	     	if(temp==null)
	     		customerId=1000;
	     	else
	     		customerId=Integer.parseInt(temp)+1;
	     	
   	     	String fname=cifname.getText().toString();
   	     	String lname=cilname.getText().toString();
   	     	String phone=cipn.getText();
   	     	String email=ciem.getText();
   	     	String adr=ciac.getText();
   	     	String pass=cipt.getText();
   	     	JFXRadioButton gen=(JFXRadioButton)cinfo.getSelectedToggle();
   	     	PreparedStatement ps1=conn.prepareStatement("insert into customer values('"+customerId+"','"+fname+"','"+lname+"','"+email+"','"+adr+"','"+pass+"','"+gen.getText().charAt(0)+"')");
   	     	ps1.execute();
   	     	PreparedStatement ps2=conn.prepareStatement("insert into phone values('"+customerId+"','"+phone+"')");
	     	ps2.execute();
   	     	System.out.println("UPDATED");
   	    	}catch(Exception e) {e.printStackTrace();}
    }

    @FXML
    void addPhone() {
    	try {
    		Connection conn=dbConnect();
        	String pass=ciapt.getText();
        	String phone=ciapn.getText();
        	PreparedStatement ps1=conn.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	String id=rs.getString(1);
        	System.out.println(id);
        	PreparedStatement ps2=conn.prepareStatement("insert into phone values('"+id+"','"+phone+"')");
	     	ps2.execute();
    	}catch(Exception e) {e.printStackTrace();}
    	
    }

    @FXML
    void book() {
    	try {
    		Connection conn=dbConnect();
        	String pass=bkpn.getText();
        	String br=bkbr_id.getText();
        	String rm=bkrm.getText();
        	String stat="Booked";
        	PreparedStatement ps4=conn.prepareStatement("select max(booking_id) from booking");
        	ResultSet rs4=ps4.executeQuery();
        	rs4.next();
        	int bookingId;
        	String temp=rs4.getString(1);
        	if(temp==null)
        		bookingId=1000;
        	else
        		bookingId=Integer.parseInt(temp)+1;
        	PreparedStatement ps1=conn.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	String id=rs.getString(1);
        	System.out.println(bkfr.getValue().toString());
        	PreparedStatement ps2=conn.prepareStatement("insert into booking values('"+bookingId+"','"+bkfr.getValue().toString().trim()+"','"+bkto.getValue().toString().trim()+"','"+rm+"','"+br+"','"+stat+"','"+id+"')");
        	System.out.println("Here");
        	//ps2.setDate(1, Date.valueOf(bkfr.getValue()));
        	//ps2.setDate(2, Date.valueOf(bkto.getValue()));
        	ps2.execute();
        	
    	}catch(Exception e) {e.printStackTrace();}
    	
    }

    @FXML
    void updateBook() {

    	
    }
    void updateBookTable() {
    	ObservableList<Bookings> bookings=Bookings.getBookingsData(null,null,false);
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
    @FXML
    void bill() {
    	try {
    		String pass=fbpt.getText();
    		String rmno=rm.getText();
    		boolean st=stay.isSelected();
    		Connection con=dbConnect();
    		PreparedStatement ps1=con.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	String id=rs.getString(1);
        	PreparedStatement ps4=con.prepareStatement("select max(bill_id) from invoice");
        	ResultSet rs4=ps4.executeQuery();
        	rs4.next();
        	int bid;
        	String temp=rs4.getString(1);
        	if(temp==null)
        		bid=1000;
        	else
        		bid=Integer.parseInt(temp)+1;
        	String tid=null;
        	if(st){
        		PreparedStatement ps3=con.prepareStatement("select transaction_id from avail natural join room where room_number='"+rmno+"' and stat='occupied'");
            	ResultSet rs3=ps3.executeQuery();
            	rs3.next();
            	tid=rs3.getString(1);
        	}
        	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        	 LocalDateTime now = LocalDateTime.now();
        	 PreparedStatement ps2;
        	 if(tid!=null)
        		 ps2=con.prepareStatement("insert into invoice values('"+bid+"','"+dtf.format(now)+"',"+cost+",'"+tid+"','"+id+"')");
        	 else
        		 ps2=con.prepareStatement("insert into invoice values('"+bid+"','"+dtf.format(now)+"',"+cost+",null,'"+id+"')");
        	 ps2.executeQuery();
     		
        	 Iterator oIterator = o.entrySet().iterator();
        	while (oIterator.hasNext()) { 
                Map.Entry mapElement = (Map.Entry)oIterator.next(); 
                int qt = (int)mapElement.getValue(); 
                String item=(String)mapElement.getKey(); 
                ps2=con.prepareStatement("insert into contain values('"+bid+"','"+item+"',"+qt+")");
                ps2.execute();
                System.out.println("Here");
                ps2=con.prepareStatement("update food set sold=sold+"+qt+" where item='"+item+"'");
                ps2.execute();
            } 
    		
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    }
    
    @FXML
    void addItem() {
    	try {
    		String temp=menu.getSelectionModel().getSelectedItem();
        	temp=temp.substring(0,temp.indexOf("      "));
        	int qt=Integer.parseInt(qty.getText());
        	System.out.println(temp);
        	cost+=(qt*h.get(temp));
        	price.setText(cost+"");
        	o.put(temp,qt);
        	ordered.setText(ordered.getText()+"\n"+temp);
    	}catch(Exception e) {e.printStackTrace();}
    	
    	
    }
    @FXML
    void popMenu() {
    	cost=0;
    	o=new HashMap<>();
    	h=new HashMap<>();
    	price.setText("0");
    	ordered.setText("");
    	ObservableList<String> items=FXCollections.observableArrayList();
    	try {
    		Connection con=dbConnect();
    		PreparedStatement ps=con.prepareStatement("select * from food");
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			items.add(rs.getString(1)+"      "+rs.getDouble(2));
    			h.put(rs.getString(1),rs.getDouble(2));
    		}
    		menu.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    		menu.setItems(items);
    	}catch(Exception e) {e.printStackTrace();}
    	
    	
    }
    @FXML
    void faddCustomer() {
    	try {
   	     	Connection conn=dbConnect();
	   	     PreparedStatement ps4=conn.prepareStatement("select max(customer_id) from customer");
	     	ResultSet rs4=ps4.executeQuery();
	     	rs4.next();
	     	int customerId;
	     	String temp=rs4.getString(1);
	     	if(temp==null)
	     		customerId=1000;
	     	else
	     		customerId=Integer.parseInt(temp)+1;
	     	
   	     	String fname=fcifname.getText().toString();
   	     	String lname=fcilname.getText().toString();
   	     	String phone=fcipn.getText();
   	     	String email=fciem.getText();
   	     	String adr=fciac.getText();
   	     	String pass=fcipt.getText();
   	     	JFXRadioButton gen=(JFXRadioButton)fcinfo.getSelectedToggle();
   	     	PreparedStatement ps1=conn.prepareStatement("insert into customer values('"+customerId+"','"+fname+"','"+lname+"','"+email+"','"+adr+"','"+pass+"','"+gen.getText().charAt(0)+"')");
   	     	ps1.execute();
   	     	PreparedStatement ps2=conn.prepareStatement("insert into phone values('"+customerId+"','"+phone+"')");
	     	ps2.execute();
   	     	System.out.println("UPDATED");
   	    	}catch(Exception e) {e.printStackTrace();}
    }

    @FXML
    void faddPhone() {
    	try {
    		Connection conn=dbConnect();
        	String pass=fciapt.getText();
        	String phone=fciapn.getText();
        	PreparedStatement ps1=conn.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	String id=rs.getString(1);
        	System.out.println(id);
        	PreparedStatement ps2=conn.prepareStatement("insert into phone values('"+id+"','"+phone+"')");
	     	ps2.execute();
    	}catch(Exception e) {e.printStackTrace();}

    }

}
