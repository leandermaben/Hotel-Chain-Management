package application;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.animation.KeyValue.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.State;
import static utils.Connect.dbConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdminPageController {

    @FXML
    private JFXBadge book;

    @FXML
    private JFXButton bookbtn;

    @FXML
    private LineChart<String, Double> accountsGraph;
    
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
 
    @FXML
    private JFXBadge allot;

    @FXML
    private Button allotbtn;

    @FXML
    private JFXBadge access;

    @FXML
    private Button accessbtn;

    @FXML
    private JFXBadge hire;

    @FXML
    private Button hirebtn;

    @FXML
    private JFXBadge notif;

    @FXML
    private JFXBadge update;

    @FXML
    private Button updatebtn;

    @FXML
    private JFXBadge search;

    @FXML
    private Button searchbtn;
    @FXML
    private JFXListView<String> notifbar;
    
    @FXML
    private HeaderController headerController;
    @FXML
    private SideBarController sideBarController;
    
    private State state;
    
    public void setState(State st) {
    	state=st;
    	headerController.setState(state);
        headerController.getBackbtn().setVisible(false);
        sideBarController.setState(state);
        showGraph();
    	showNotif();
    }
    @FXML
    void launchAccess() {
    	try {
    		ArrayList<Object> fx=state.getPage("Clearance");
    		if((boolean)fx.get(2))
    			((ClearanceController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void launchSchedule() {
    	try {
    		ArrayList<Object> fx=state.getPage("Schedule");
    		if((boolean)fx.get(2))
    			((ScheduleController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void launchSearch() {
    	try {
    		ArrayList<Object> fx=state.getPage("Search");
    		if((boolean)fx.get(2))
    			((SearchController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void launchBooking() {
    	try {
    		ArrayList<Object> fx=state.getPage("Booking");
    		if((boolean)fx.get(2))
    			((BookingController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }


    @FXML
    void launchLogistics() {
    	try {
    		ArrayList<Object> fx=state.getPage("Logistics");
    		if((boolean)fx.get(2))
    			((LogisticsController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void notifyByAdmin() {
    	try {
    		
    		ArrayList<Object> fx=state.getPage("AdminNotification");
    		if((boolean)fx.get(2)) {
    			Stage stage=new Stage();
        		((AdminNotificationController)fx.get(1)).setStage(stage);
        		((AdminNotificationController)fx.get(1)).setState(state);
        		stage.initStyle(StageStyle.TRANSPARENT);
        		fx.add(stage);
        		stage.setScene((Scene)fx.get(0));
    		}

    		Stage stage=((Stage)fx.get(3));
    		stage.toFront();
    		stage.show();
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void showNotif() {
    	try {
    		Connection con=dbConnect();
    		PreparedStatement ps=con.prepareStatement("select mess,sender,mess_id,sent_date from messages natural join inbox where receipient='"+state.getEmp_id()+"' and disp='y' and category='notifyAdmin'");
    		ResultSet rs=ps.executeQuery();
    		String name,text,sent,id;
    		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		ObservableList<String> mess=FXCollections.observableArrayList();
    		while(rs.next()) {
    			text=rs.getString(1);
    			sent=rs.getString(2);
    			id=rs.getString(3);
    			ps=con.prepareStatement("select fname,lname from employee where emp_id='"+sent+"'");
    			ResultSet rs2=ps.executeQuery();
    			rs2.next();
    			name=rs2.getString(1)+" "+rs2.getString(2);
    			mess.add(id+": "+text+"\nSent by: "+sent+" "+name+" "+rs.getDate(4).toLocalDate().format(df));
    		}
    		notifbar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    		notifbar.setItems(mess);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void clear() {
    	try {
    		Connection con=dbConnect();
    		ObservableList<String> mess=notifbar.getSelectionModel().getSelectedItems();
    		PreparedStatement ps;
    		int col;
    		String mess_id;
    		for(String x:mess) {
    			col=x.indexOf(":");
    			mess_id=x.substring(0,col);
    			ps=con.prepareStatement("update inbox set disp='n' where mess_id='"+mess_id+"' and receipient='"+state.getEmp_id()+"'");
    			ps.execute();
    		}
    		showNotif();
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void clearAll() {
    	try {
    		Connection con=dbConnect();
    		PreparedStatement ps=con.prepareStatement("select mess_id from messages natural join inbox where receipient='"+state.getEmp_id()+"' and disp='y' and category='notifyAdmin'");
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			ps=con.prepareStatement("update inbox set disp='n' where mess_id='"+rs.getString(1)+"' and receipient='"+state.getEmp_id()+"'");
    			ps.execute();
    		}
    		showNotif();
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    void showGraph() {
    	try {
    		Connection con=dbConnect();
    		DateTimeFormatter df= DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		CallableStatement cl=con.prepareCall("{call calcRevenue(?,?,?)}");
    		CallableStatement cl2=con.prepareCall("{call calcCost(?,?)}");
    		PreparedStatement ps=con.prepareStatement("select log_date from accounts");
    		ResultSet rs=ps.executeQuery();
    		XYChart.Series profit=new XYChart.Series<String,Double>();
    		XYChart.Series revenue=new XYChart.Series<String,Double>();
    		XYChart.Series cost=new XYChart.Series<String,Double>();
    		while(rs.next()) {
    			cl.setDate(1,rs.getDate(1));
    			cl2.setDate(1,rs.getDate(1));
    			cl.registerOutParameter(2,Types.DOUBLE);
    			cl.registerOutParameter(3,Types.DOUBLE);
    			cl2.registerOutParameter(2,Types.DOUBLE);
    			cl.execute();
    			cl2.execute();
    			profit.getData().add(new XYChart.Data(rs.getDate(1).toLocalDate().format(df),cl.getDouble(3)));
    			revenue.getData().add(new XYChart.Data(rs.getDate(1).toLocalDate().format(df),cl.getDouble(2)));
    			cost.getData().add(new XYChart.Data(rs.getDate(1).toLocalDate().format(df),cl2.getDouble(2)));
    		}
    		revenue.setName("Revenue");
    		profit.setName("Profits");
    		cost.setName("Costs");
    		accountsGraph.getData().addAll(revenue,profit,cost);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}

