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

import javafx.beans.property.DoubleProperty;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;

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
	    private JFXTextField btfn;
	    
	    @FXML
	    private JFXTextField btln;
	    
	    @FXML
	    private JFXToggleButton btbk;
	    
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
	    private JFXTreeTableView<Room> rmTable;

	    @FXML
	    private JFXDatePicker rmfr;

	    @FXML
	    private JFXDatePicker rmto;
	    
	    @FXML
	    private JFXTextField ckinbr;

	    @FXML
	    private JFXListView<String> roomList;

	    @FXML
	    private JFXButton ckinbtn;

	    @FXML
	    private JFXTextField ckinfname;

	    @FXML
	    private JFXTextField ckinlname;

	    @FXML
	    private JFXTextField ckinpn;

	    @FXML
	    private JFXTextField ckinem;

	    @FXML
	    private JFXTextField ckinac;

	    @FXML
	    private JFXTextField ckinpt;

	    @FXML
	    private JFXTextField ckinapt;

	    @FXML
	    private JFXTextField ckinapn;

	    @FXML
	    private JFXRadioButton ckinmale;

	    @FXML
	    private JFXRadioButton ckinfemale;

	    @FXML
	    private JFXTextField ckotec;

	    @FXML
	    private JFXTextField ckotbr;

	    @FXML
	    private JFXTextField ckotrm;

	    @FXML
	    private JFXTextField ckotad;

	    @FXML
	    private Label ckotex;
	    
	    @FXML
	    private Label ckotinfo;
	    
	    @FXML
	    private Label ckottx;

	    @FXML
	    private Label ckotdt;

	    @FXML
	    private Label ckotap;

	    @FXML
	    private JFXTextField ackinpt;
	    
	    @FXML
	    private JFXDatePicker ckinto;
	    
	    HashMap<String,Double> h;
	    
	    ArrayList<String> orlist;
	    
	    double cost=0;
	    
	    HashMap<String,Integer> o;
	    
	    
//Booking Table
	    
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
	JFXTreeTableColumn<Bookings,String> col4=new JFXTreeTableColumn<>("room_num");
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
	String fname,lname;
	if(btfn.getText().isEmpty()) {
		fname=null;
	}else {
		fname=btfn.getText();
	}
	if(btln.getText().isEmpty()) {
		lname=null;
	}else {
		lname=btln.getText();
	}
	ObservableList<Bookings> bookings=Bookings.getBookingsData(fname,lname,btbk.isSelected());
	final TreeItem<Bookings> root=new RecursiveTreeItem<Bookings>(bookings,RecursiveTreeObject::getChildren);
	bookTable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9);
	bookTable.setRoot(root);
	bookTable.setShowRoot(false);
	    }


//Room Table


@FXML
void initializeRoom() {
	JFXTreeTableColumn<Room,String> col1=new JFXTreeTableColumn<>("Booking id");
	col1.setMinWidth(200);
	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getBooking_id();
		}
	});
	JFXTreeTableColumn<Room,String> col2=new JFXTreeTableColumn<>("Booking from");
	col2.setMinWidth(200);
	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getBooked_from();
		}
	});
	JFXTreeTableColumn<Room,String> col3=new JFXTreeTableColumn<>("Booking to");
	col3.setMinWidth(200);
	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getBooked_to();
		}
	});
	JFXTreeTableColumn<Room,String> col4=new JFXTreeTableColumn<>("Branch Id");
	col4.setMinWidth(200);
	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getBranch_id();
		}
	});
	JFXTreeTableColumn<Room,String> col5=new JFXTreeTableColumn<>("Room Number");
	col5.setMinWidth(200);
	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getRoom_number();
		}
	});
	JFXTreeTableColumn<Room,String> col6=new JFXTreeTableColumn<>("Floor");
	col6.setMinWidth(200);
	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getRoom_status();
		}
	});
	JFXTreeTableColumn<Room,String> col7=new JFXTreeTableColumn<>("Status");
	col7.setMinWidth(200);
	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getRoom_status();
		}
	});
	JFXTreeTableColumn<Room,String> col9=new JFXTreeTableColumn<>("Serviced");
	col9.setMinWidth(200);
	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getServiced();
		}
	});
	JFXTreeTableColumn<Room,Number> col10=new JFXTreeTableColumn<>("Basic Cost");
	col10.setMinWidth(200);
	col10.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,Number>,ObservableValue<Number>>(){
		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Room,Number> param){
			return param.getValue().getValue().getBasic_cost();
		}
	});
	JFXTreeTableColumn<Room,String> col11=new JFXTreeTableColumn<>("First Name");
	col11.setMinWidth(200);
	col11.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getFname();
		}
	});
	JFXTreeTableColumn<Room,String> col12=new JFXTreeTableColumn<>("Last Name");
	col12.setMinWidth(200);
	col12.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getLname();
		}
	});
	JFXTreeTableColumn<Room,String> col13=new JFXTreeTableColumn<>("Customer Id");
	col13.setMinWidth(200);
	col13.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){
		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Room,String> param){
			return param.getValue().getValue().getCustomer_id();
		}
	});
	SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd");
	try {
		java.util.Date from;
		java.util.Date to;
		if(rmfr.getValue()==null) {
			from=null;
		}else {
			from=form.parse(rmfr.getValue().toString());
		}
		if(rmto.getValue()==null) {
			to=null;
		}else {
			to=form.parse(rmto.getValue().toString());
		}
		ObservableList<Room> rooms=Room.getRoomData(from,to);
		final TreeItem<Room> root1=new RecursiveTreeItem<Room>(rooms,RecursiveTreeObject::getChildren);
		rmTable.getColumns().setAll(col1,col4,col5,col2,col3,col7,col10,col11,col12,col13);
		rmTable.setRoot(root1);
		rmTable.setShowRoot(false);
	}catch(Exception e) {
		e.printStackTrace();
		}
	}


    @FXML
    void addCustomer() {
    	try {
   	     	Connection conn=dbConnect();
	     	String customerId=generateCustomerId();
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
        	String id=getCustomerId(pass);
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
        	String stat="booked";
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        	java.util.Date from=sdf.parse(bkfr.getValue().toString());
        	java.util.Date to=sdf.parse(bkto.getValue().toString());
        	java.sql.Date fromF=new java.sql.Date(from.getTime());
        	java.sql.Date toF=new java.sql.Date(to.getTime());
        	System.out.println(from);
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
        	PreparedStatement ps2=conn.prepareStatement("insert into booking values('"+bookingId+"',?,?,'"+rm+"','"+br+"','"+stat+"','"+id+"')");
        	System.out.println("Here");
        	ps2.setDate(1, fromF);
        	ps2.setDate(2,toF);
        	ps2.execute();
        	
    	}catch(Exception e) {e.printStackTrace();}
    	
    }

    @FXML
    void updateBook() {
    	try {
    		Connection con=dbConnect();
    		String bookId=bkupid.getText();
        	JFXRadioButton opt=(JFXRadioButton)bookUp.getSelectedToggle();
        	PreparedStatement ps;    	
        	if(opt.getText().equals("cancel")) {
        		ps=con.prepareStatement("update booking set stat='cancelled' where booking_id='"+bookId+"'");
        	    ps.execute();
        	}else {
        		String br=bkupbr_id.getText();
            	String rm=bkuprm.getText();
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            	java.util.Date from=sdf.parse(bkupfr.getValue().toString());
            	java.util.Date to=sdf.parse(bkupto.getValue().toString());
            	java.sql.Date fromF=new java.sql.Date(from.getTime());
            	java.sql.Date toF=new java.sql.Date(to.getTime());
            	ps=con.prepareStatement("update booking set booked_to=?,booked_from=?,room_num=?,branch_id=? where booking_id='"+bookId+"'");
        	    ps.setDate(1,toF);
        	    ps.setDate(2,fromF);
        	    ps.setString(3, rm);
        	    ps.setString(4, br);
            	ps.execute();
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    void updateBookTable() {
    	ObservableList<Bookings> bookings=Bookings.getBookingsData(null,null,false);
    }
    
    //Food Tab
    
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
        		PreparedStatement ps3=con.prepareStatement("select transaction_id from avail natural join room where room_num='"+rmno+"' and stat='occupied' and branch_id='"+pass+"'");
            	ResultSet rs3=ps3.executeQuery();
            	rs3.next();
            	tid=rs3.getString(1);
        	}
        	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        	 LocalDateTime now = LocalDateTime.now();
        	 SimpleDateFormat fd=new SimpleDateFormat("yyyy-MM-dd");
        	 java.sql.Date dt=new java.sql.Date(fd.parse(dtf.format(now)).getTime());
        	 PreparedStatement ps2;
        	 if(tid!=null)
        		 ps2=con.prepareStatement("insert into invoice values('"+bid+"',?,"+cost+",'"+tid+"','"+id+"')");
        	 else
        		 ps2=con.prepareStatement("insert into invoice values('"+bid+"',?,"+cost+",null,'"+id+"')");
        	 ps2.setDate(1, dt);
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
    
    // Customer Info for Check - In
    
    @FXML
    void ckinAddPhone() {
    	try {
    		Connection conn=dbConnect();
        	String pass=ckinapt.getText();
        	String phone=ckinapn.getText();
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
    void ckinAddCustomer() {
    	try {
    		Connection conn=dbConnect();
	     	String customerId=generateCustomerId();
   	     	String fname=ckinfname.getText().toString();
   	     	String lname=ckinlname.getText().toString();
   	     	String phone=ckinpn.getText();
   	     	String email=ckinem.getText();
   	     	String adr=ckinac.getText();
   	     	String pass=ckinpt.getText();
   	     	JFXRadioButton gen=(JFXRadioButton)fcinfo.getSelectedToggle();
   	     	PreparedStatement ps1=conn.prepareStatement("insert into customer values('"+customerId+"','"+fname+"','"+lname+"','"+email+"','"+adr+"','"+pass+"','"+gen.getText().charAt(0)+"')");
   	     	ps1.execute();
   	     	PreparedStatement ps2=conn.prepareStatement("insert into phone values('"+customerId+"','"+phone+"')");
	     	ps2.execute();
   	     	System.out.println("UPDATED");
   	    	}catch(Exception e) {e.printStackTrace();}
    }
    
    // Check - In Tab
    
    @FXML
    void checkin() {
    	try {
    		Connection conn=dbConnect();
        	String pass=ackinpt.getText();
        	String branch=ckinbr.getText();
        	String id=getCustomerId(pass);
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        	java.sql.Date checkinto;
        	ObservableList<String> select=roomList.getSelectionModel().getSelectedItems();
        	for(int i=0;i<select.size();i++) {
        		String room=select.get(i).substring(0,select.indexOf(" "));
            	String transactionId=generateTransactionId();
            	System.out.println(id);
            	PreparedStatement ps1=conn.prepareStatement("update room set room_status='occupied' where room_num='"+room+"' and branch_id='"+branch+"'");
    	     	ps1.execute();
    	     	
    	     	PreparedStatement ps3=conn.prepareStatement("select room_num,basic_cost from booking natural join room where stat='booked' booked_from like current_date and customer_id='"+id+"' and room_status='vacant' and branch_id='"+branch+"'");
            	ResultSet rs2=ps3.executeQuery();
            	if(rs2.next()) {
            		PreparedStatement ps5=conn.prepareStatement("select booked_to from booking where stat='booked' and booked_from like current_date and room_num='"+room+"' and branch_id='"+branch+"'");
                	ResultSet rs5=ps5.executeQuery();
                	rs5.next();
                	checkinto=rs5.getDate(1);
            		PreparedStatement ps4=conn.prepareStatement("update booking set stat='taken' where booked_from like current_date and room_num='"+room+"' and branch_id='"+branch+"' and stat='booked'");
                	ps4.execute();
            	}else {
            		checkinto=new java.sql.Date(sdf.parse(ckinto.getValue().toString()).getTime());
            	}
            	PreparedStatement ps2=conn.prepareStatement("insert into stay values('"+transactionId+"','"+id+"',current_date,?,'"+room+"','"+branch+"',0,0)");
    	     	ps2.setDate(1, checkinto);
    	     	ps2.execute();
        	}
        	
    	}catch(Exception e) {e.printStackTrace();}

    }
   
    @FXML
    void popRoom() {
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    		roomList.setItems(null);
    		System.out.println();
    		Connection con = dbConnect();
    		boolean flag=false;
    		String rmNum;
    		double cost;
    		String customerId=getCustomerId(ackinpt.getText());
        	ObservableList<String> rm=FXCollections.observableArrayList();
        	PreparedStatement ps2=con.prepareStatement("select room_num,basic_cost from booking natural join room where stat='booked' and booked_from like current_date and customer_id='"+customerId+"' and room_status='vacant' and branch_id='"+ckinbr.getText()+"'");
        	ResultSet rs2=ps2.executeQuery();
        	if(rs2.next()) {
        		do {
        			rmNum=rs2.getString(1);
            		cost=rs2.getDouble(2);
            		rm.add(rmNum+"    "+cost);	
            		System.out.println("Hi");
        		}while(rs2.next());
        	}else {
        		PreparedStatement ps=con.prepareStatement("select room.room_num,basic_cost from room left outer join booking on room.branch_id=booking.branch_id and room.room_num=booking.room_num where room_status='vacant' and (booked_from>? or booked_from is null) and room.branch_id='"+ckinbr.getText()+"'");
        		ps.setDate(1, new java.sql.Date(sdf.parse(ckinto.getValue().toString()).getTime()));
        		rs2=ps.executeQuery();
        		while(rs2.next()) {
        			rm.add(rs2.getString(1)+"    "+rs2.getDouble(2));
        		}
        	}
        	roomList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        	roomList.setItems(rm);
        	System.out.println("There "+rm.size());
        	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // Check-Out Tab
    
    @FXML
    void transaction() {
    	try {
    		Connection con=dbConnect();
    		String branch=ckotbr.getText();
        	String room=ckotrm.getText();
        	double basic_cost;
        	double extra;
        	String extraS=ckotec.getText();
        	if(extraS.isEmpty())
        		extra=0;
        	else
        		extra=Double.parseDouble(extraS);
        	String ediscountS=ckotad.getText();
        	double ediscount;
        	if(ediscountS.isEmpty())
        		ediscount=0;
        	else
        		ediscount=Double.parseDouble(ediscountS);
        	double discount=0;
        	int current_stay;
        	PreparedStatement ps1=con.prepareStatement("select transaction_id,customer_id,basic_cost,check_out-check_in from stay natural join room where room_num='"+room+"' and room_status='occupied' and branch_id='"+branch+"'");
        	ResultSet rs1=ps1.executeQuery();
        	rs1.next();
        	String tid=rs1.getString(1);
        	String id=rs1.getString(2);
        	basic_cost=rs1.getDouble(3);
        	current_stay=rs1.getInt(4);
        	int stayed;
        	ps1=con.prepareStatement("select sum(check_out-check_in) from stay where customer_id='"+id+"'");
        	rs1=ps1.executeQuery();
        	rs1.next();
        	stayed=rs1.getInt(1);
        	if(stayed>100)
        		discount=0.2*basic_cost*current_stay;
        	ps1=con.prepareStatement("select fname,lname,aadhar_number,passport_number from customer where customer_id='"+id+"'");
        	rs1=ps1.executeQuery();
        	rs1.next();
        	String info;
        	info="First Name: "+rs1.getString(1)+"\nLast Name: "+rs1.getString(2)+"\nAadhar Number: "+rs1.getString(3)+"\nPassport Number: "+rs1.getString(4)+"\nCurrent Stay: "+current_stay+"\nTotal Stay: "+stayed;
        	double total=basic_cost*current_stay+extra+0.1*(basic_cost*current_stay+extra)-(discount+ediscount);
        	ckotinfo.setText(info);
        	ckotex.setText("\t$ "+(basic_cost*current_stay+extra));
        	ckottx.setText("\t$ "+(0.1*(basic_cost*current_stay+extra)));
        	ckotdt.setText("\t$ "+(discount+ediscount));
        	ckotap.setText("\t$ "+(total));
        	ps1=con.prepareStatement("update room set room_status='vacant' where room_num='"+room+"' and branch_id='"+branch+"'");
        	ps1.execute();
        	ps1=con.prepareStatement("update stay set discount="+(discount+ediscount)+",extra_cost='"+extra+"' where transaction_id='"+tid+"'");
        	ps1.execute();
        	ckotinfo.setText("");
        	ckotex.setText("");
        	ckottx.setText("");
        	ckotdt.setText("");
        	ckotap.setText("");
        	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}   	
    	
    }
    @FXML
    void showDetails(){
    	try {
    		Connection con=dbConnect();
    		String branch=ckotbr.getText();
        	String room=ckotrm.getText();
        	double basic_cost;
        	double extra;
        	String extraS=ckotec.getText();
        	System.out.println("Here"+extraS+"Here");
        	if(extraS.isEmpty())
        		extra=0;
        	else
        		extra=Double.parseDouble(extraS);
        	String ediscountS=ckotad.getText();
        	double ediscount;
        	if(ediscountS.isEmpty())
        		ediscount=0;
        	else
        		ediscount=Double.parseDouble(ediscountS);
        	double discount=0;
        	int current_stay;
        	PreparedStatement ps1=con.prepareStatement("select transaction_id,customer_id,basic_cost,check_out-check_in from stay natural join room where room_num='"+room+"' and room_status='occupied' and branch_id='"+branch+"'");
        	ResultSet rs1=ps1.executeQuery();
        	rs1.next();
        	String tid=rs1.getString(1);
        	String id=rs1.getString(2);
        	basic_cost=rs1.getDouble(3);
        	current_stay=rs1.getInt(4);
        	int stayed;
        	ps1=con.prepareStatement("select sum(check_out-check_in) from stay where customer_id='"+id+"'");
        	rs1=ps1.executeQuery();
        	rs1.next();
        	stayed=rs1.getInt(1);
        	if(stayed>100)
        		discount=0.2*basic_cost*current_stay;
        	ps1=con.prepareStatement("select fname,lname,aadhar_number,passport_number from customer where customer_id='"+id+"'");
        	rs1=ps1.executeQuery();
        	rs1.next();
        	String info;
        	info="First Name: "+rs1.getString(1)+"\nLast Name: "+rs1.getString(2)+"\nAadhar Number: "+rs1.getString(3)+"\nPassport Number: "+rs1.getString(4)+"\nCurrent Stay: "+current_stay+"\nTotal Stay: "+stayed;
        	double total=basic_cost*current_stay+extra+0.1*(basic_cost*current_stay+extra)-(discount+ediscount);
        	ckotinfo.setText(info);
        	ckotex.setText("\t$ "+(basic_cost*current_stay+extra));
        	ckottx.setText("\t$ "+(0.1*(basic_cost*current_stay+extra)));
        	ckotdt.setText("\t$ "+(discount+ediscount));
        	ckotap.setText("\t$ "+(total));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}   	
    }
    String getCustomerId(String pass){
    	try {
    		Connection conn=dbConnect();
        	PreparedStatement ps1=conn.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	return rs.getString(1);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    String generateCustomerId() {
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
	     	return customerId+"";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    String generateTransactionId() {
    	try {
    		Connection conn=dbConnect();
    		PreparedStatement ps4=conn.prepareStatement("select max(transaction_id) from stay");
	     	ResultSet rs4=ps4.executeQuery();
	     	rs4.next();
	     	int id;
	     	String temp=rs4.getString(1);
	     	if(temp==null)
	     		id=1000;
	     	else
	     		id=Integer.parseInt(temp)+1;
	     	return id+"";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
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
