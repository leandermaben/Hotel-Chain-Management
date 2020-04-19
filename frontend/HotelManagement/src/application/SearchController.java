package application;

import java.text.SimpleDateFormat;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import utils.Header;
import utils.State;
import entityClass.Bookings;
import entityClass.Employee;
import entityClass.Food;
import entityClass.Invoice;
import entityClass.Customer;
import entityClass.Room;
import entityClass.Stay;
public class SearchController {

    @FXML
    private JFXTreeTableView<Employee> emptable;

    @FXML
    private JFXTextField empfname;

    @FXML
    private JFXTextField emplname;

    @FXML
    private JFXToggleButton working;

    @FXML
    private JFXTreeTableView<Customer> custable;

    @FXML
    private JFXTextField cusfname;

    @FXML
    private JFXTextField cuslname;

    @FXML
    private JFXTreeTableView<?> accountTable;

    @FXML
    private JFXTreeTableView<Stay> stayTable;
    
    @FXML
    private JFXTreeTableView<Invoice> invoiceTable;

    @FXML
    private JFXTreeTableView<Room> roomTable;

    @FXML
    private JFXTreeTableView<Bookings> bookTable;

    @FXML
    private JFXTextField bookfname;

    @FXML
    private JFXTextField booklname;

    @FXML
    private JFXTreeTableView<Food> foodTable;

 private State state;
    
    private Header header;
    @FXML
    private Label name;

    @FXML
    private Label clock;
    
    public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
		this.header=new Header(state,name,clock);
	}
	
	 @FXML
	 void back(){
	    header.back();
	  }

    @FXML
    void refreshAccount() {

    }

    @FXML
    void refreshBooking() {
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
    	if(bookfname.getText().isEmpty()) {
    		fname=null;
    	}else {
    		fname=bookfname.getText();
    	}
    	if(booklname.getText().isEmpty()) {
    		lname=null;
    	}else {
    		lname=booklname.getText();
    	}
    	ObservableList<Bookings> bookings=Bookings.getBookingsData(fname,lname,false);
    	final TreeItem<Bookings> root=new RecursiveTreeItem<Bookings>(bookings,RecursiveTreeObject::getChildren);
    	bookTable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9);
    	bookTable.setRoot(root);
    	bookTable.setShowRoot(false);
    }

    @FXML
    void refreshCustomer() {
    	JFXTreeTableColumn<Customer,String> col1=new JFXTreeTableColumn<>("Customer_id");
    	col1.setMinWidth(200);
    	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getCustomer_id();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col2=new JFXTreeTableColumn<>("First Name");
    	col2.setMinWidth(200);
    	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getFname();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col3=new JFXTreeTableColumn<>("Last Name");
    	col3.setMinWidth(200);
    	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getLname();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col4=new JFXTreeTableColumn<>("Email");
    	col4.setMinWidth(200);
    	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getEmail();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col5=new JFXTreeTableColumn<>("Aadhar Number");
    	col5.setMinWidth(200);
    	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getAadhar_number();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col6=new JFXTreeTableColumn<>("Passport Number");
    	col6.setMinWidth(200);
    	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getPassport_number();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col7=new JFXTreeTableColumn<>("Gender");
    	col7.setMinWidth(200);
    	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getGender();
    		}
    	});
    	JFXTreeTableColumn<Customer,Number> col8=new JFXTreeTableColumn<>("Spent");
    	col8.setMinWidth(200);
    	col8.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Customer,Number> param){
    			return param.getValue().getValue().getSpent();
    		}
    	});
    	JFXTreeTableColumn<Customer,Number> col9=new JFXTreeTableColumn<>("Duration");
    	col9.setMinWidth(200);
    	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Customer,Number> param){
    			return param.getValue().getValue().getDuration();
    		}
    	});
    	JFXTreeTableColumn<Customer,String> col10=new JFXTreeTableColumn<>("Phone Numbers");
    	col10.setMinWidth(200);
    	col10.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Customer,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Customer,String> param){
    			return param.getValue().getValue().getPhone();
    		}
    	});
    	String fname,lname;
    	if(cusfname.getText().isEmpty()) {
    		fname=null;
    	}else {
    		fname=cusfname.getText();
    	}
    	if(cuslname.getText().isEmpty()) {
    		lname=null;
    	}else {
    		lname=cuslname.getText();
    	}
    	ObservableList<Customer> customers=Customer.getCustomerData(fname,lname);
    	final TreeItem<Customer> root=new RecursiveTreeItem<Customer>(customers,RecursiveTreeObject::getChildren);
    	custable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10);
    	custable.setRoot(root);
    	custable.setShowRoot(false);
    }

    @FXML
    void refreshEmployee() {
    	JFXTreeTableColumn<Employee,String> col1=new JFXTreeTableColumn<>("Employee ID");
    	col1.setMinWidth(200);
    	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getEmp_id();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col2=new JFXTreeTableColumn<>("First Name");
    	col2.setMinWidth(200);
    	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getFname();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col3=new JFXTreeTableColumn<>("Last Name");
    	col3.setMinWidth(200);
    	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getLname();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col4=new JFXTreeTableColumn<>("Gender");
    	col4.setMinWidth(200);
    	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getGender();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col5=new JFXTreeTableColumn<>("Position");
    	col5.setMinWidth(200);
    	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getPosition();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col6=new JFXTreeTableColumn<>("Joined");
    	col6.setMinWidth(200);
    	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getJoined();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col7=new JFXTreeTableColumn<>("Released");
    	col7.setMinWidth(200);
    	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getReleased();
    		}
    	});
    	JFXTreeTableColumn<Employee,Number> col8=new JFXTreeTableColumn<>("Salary");
    	col8.setMinWidth(200);
    	col8.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Employee,Number> param){
    			return param.getValue().getValue().getSalary();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col9=new JFXTreeTableColumn<>("Aadhar Number");
    	col9.setMinWidth(200);
    	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getAadhar_number();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col10=new JFXTreeTableColumn<>("Branch ID");
    	col10.setMinWidth(200);
    	col10.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getBranch_id();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col11=new JFXTreeTableColumn<>("PIN");
    	col11.setMinWidth(200);
    	col11.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getPin();
    		}
    	});
    	JFXTreeTableColumn<Employee,String> col12=new JFXTreeTableColumn<>("Clearance");
    	col12.setMinWidth(200);
    	col12.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Employee,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Employee,String> param){
    			return param.getValue().getValue().getClearance();
    		}
    	});
    	String fname,lname;
    	if(empfname.getText().isEmpty()) {
    		fname=null;
    	}else {
    		fname=empfname.getText();
    	}
    	if(empfname.getText().isEmpty()) {
    		lname=null;
    	}else {
    		lname=empfname.getText();
    	}
    	ObservableList<Employee> employee=Employee.getEmployeeData(fname,lname,working.isSelected());
    	final TreeItem<Employee> root=new RecursiveTreeItem<Employee>(employee,RecursiveTreeObject::getChildren);
    	emptable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12);
    	emptable.setRoot(root);
    	emptable.setShowRoot(false);
    }

    @FXML
    void refreshInvoices() {
    	JFXTreeTableColumn<Invoice,String> col1=new JFXTreeTableColumn<>("Bill ID");
    	col1.setMinWidth(200);
    	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Invoice,String> param){
    			return param.getValue().getValue().getBill_id();
    		}
    	});
    	JFXTreeTableColumn<Invoice,Number> col3=new JFXTreeTableColumn<>("Price");
    	col3.setMinWidth(200);
    	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Invoice,Number> param){
    			return param.getValue().getValue().getPrice();
    		}
    	});
    	JFXTreeTableColumn<Invoice,String> col2=new JFXTreeTableColumn<>("Issued");
    	col2.setMinWidth(200);
    	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Invoice,String> param){
    			return param.getValue().getValue().getIssued();
    		}
    	});
    	JFXTreeTableColumn<Invoice,String> col4=new JFXTreeTableColumn<>("Transaction ID");
    	col4.setMinWidth(200);
    	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Invoice,String> param){
    			return param.getValue().getValue().getTransaction_id();
    		}
    	});
    	JFXTreeTableColumn<Invoice,String> col5=new JFXTreeTableColumn<>("Customer ID");
    	col5.setMinWidth(200);
    	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Invoice,String> param){
    			return param.getValue().getValue().getCustomer_id();
    		}
    	});
    	JFXTreeTableColumn<Invoice,String> col6=new JFXTreeTableColumn<>("Items");
    	col6.setMinWidth(200);
    	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Invoice,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Invoice,String> param){
    			return param.getValue().getValue().getItems();
    		}
    	});
    	ObservableList<Invoice> invoice=Invoice.getInvoiceData();
    	final TreeItem<Invoice> root=new RecursiveTreeItem<Invoice>(invoice,RecursiveTreeObject::getChildren);
    	invoiceTable.getColumns().setAll(col1,col2,col3,col4,col5,col6);
    	invoiceTable.setRoot(root);
    	invoiceTable.setShowRoot(false);
    }


    @FXML
    void refreshRoom() {
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
    	ObservableList<Room> rooms=Room.getRoomData(null,null);
    	final TreeItem<Room> root1=new RecursiveTreeItem<Room>(rooms,RecursiveTreeObject::getChildren);
    	roomTable.getColumns().setAll(col1,col4,col5,col2,col3,col7,col10,col11,col12,col13);
    	roomTable.setRoot(root1);
    	roomTable.setShowRoot(false);
    }

    @FXML
    void refreshStay() {
    	JFXTreeTableColumn<Stay,String> col1=new JFXTreeTableColumn<>("Transaction ID");
    	col1.setMinWidth(200);
    	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getTransaction_id();
    		}
    	});
    	JFXTreeTableColumn<Stay,String> col2=new JFXTreeTableColumn<>("Customer ID");
    	col2.setMinWidth(200);
    	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getCustomer_id();
    		}
    	});
    	JFXTreeTableColumn<Stay,String> col3=new JFXTreeTableColumn<>("Check In");
    	col3.setMinWidth(200);
    	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getCheck_in();
    		}
    	});
    	JFXTreeTableColumn<Stay,String> col4=new JFXTreeTableColumn<>("Check Out");
    	col4.setMinWidth(200);
    	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getCheck_out();
    		}
    	});
    	JFXTreeTableColumn<Stay,String> col5=new JFXTreeTableColumn<>("Room Number");
    	col5.setMinWidth(200);
    	col5.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getRoom_num();
    		}
    	});
    	JFXTreeTableColumn<Stay,String> col6=new JFXTreeTableColumn<>("Branch ID");
    	col6.setMinWidth(200);
    	col6.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Stay,String> param){
    			return param.getValue().getValue().getBranch_id();
    		}
    	});
    	JFXTreeTableColumn<Stay,Number> col7=new JFXTreeTableColumn<>("Extra Cost");
    	col7.setMinWidth(200);
    	col7.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Stay,Number> param){
    			return param.getValue().getValue().getExtra_cost();
    		}
    	});
    	JFXTreeTableColumn<Stay,Number> col8=new JFXTreeTableColumn<>("Discount");
    	col8.setMinWidth(200);
    	col8.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Stay,Number> param){
    			return param.getValue().getValue().getDiscount();
    		}
    	});
    	JFXTreeTableColumn<Stay,Number> col9=new JFXTreeTableColumn<>("Basic Cost");
    	col9.setMinWidth(200);
    	col9.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Stay,Number> param){
    			return param.getValue().getValue().getBasic_cost();
    		}
    	});
    	JFXTreeTableColumn<Stay,Number> col10=new JFXTreeTableColumn<>("Food Cost");
    	col10.setMinWidth(200);
    	col10.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Stay,Number> param){
    			return param.getValue().getValue().getFood();
    		}
    	});
    	JFXTreeTableColumn<Stay,Number> col11=new JFXTreeTableColumn<>("Total Cost");
    	col11.setMinWidth(200);
    	col11.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Stay,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Stay,Number> param){
    			return param.getValue().getValue().getTotal();
    		}
    	});
    	ObservableList<Stay> stay=Stay.getStayData();
    	final TreeItem<Stay> root=new RecursiveTreeItem<Stay>(stay,RecursiveTreeObject::getChildren);
    	stayTable.getColumns().setAll(col1,col2,col3,col4,col5,col6,col9,col7,col8,col10,col11);
    	stayTable.setRoot(root);
    	stayTable.setShowRoot(false);
    }

    @FXML
    void refreshfood() {
    	JFXTreeTableColumn<Food,String> col1=new JFXTreeTableColumn<>("Item");
    	col1.setMinWidth(200);
    	col1.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Food,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Food,String> param){
    			return param.getValue().getValue().getItem();
    		}
    	});
    	JFXTreeTableColumn<Food,Number> col2=new JFXTreeTableColumn<>("Price");
    	col2.setMinWidth(200);
    	col2.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Food,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Food,Number> param){
    			return param.getValue().getValue().getPrice();
    		}
    	});
    	JFXTreeTableColumn<Food,Number> col3=new JFXTreeTableColumn<>("Sold");
    	col3.setMinWidth(200);
    	col3.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Food,Number>,ObservableValue<Number>>(){
    		public ObservableValue<Number> call(JFXTreeTableColumn.CellDataFeatures<Food,Number> param){
    			return param.getValue().getValue().getSold();
    		}
    	});
    	JFXTreeTableColumn<Food,String> col4=new JFXTreeTableColumn<>("Available");
    	col4.setMinWidth(200);
    	col4.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Food,String>,ObservableValue<String>>(){
    		public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Food,String> param){
    			return param.getValue().getValue().getAvailable();
    		}
    	});
    	ObservableList<Food> food=Food.getFoodData();
    	final TreeItem<Food> root=new RecursiveTreeItem<Food>(food,RecursiveTreeObject::getChildren);
    	foodTable.getColumns().setAll(col1,col2,col3,col4);
    	foodTable.setRoot(root);
    	foodTable.setShowRoot(false);
    }

}
