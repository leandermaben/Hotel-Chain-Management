package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import utils.Connect;
import utils.State;

public class LogisticsController {

    @FXML
    private JFXTextField epfname;

    @FXML
    private JFXTextField eplname;

    @FXML
    private JFXTextField epbr;

    @FXML
    private JFXTextField eppos;

    @FXML
    private JFXTextField epsal;

    @FXML
    private JFXTextField epad;

    @FXML
    private JFXTextField epid;

    @FXML
    private JFXButton release;

    @FXML
    private Label epinfo;

    @FXML
    private JFXTextField rmbr;

    @FXML
    private JFXTextField rmrm;

    @FXML
    private JFXTextField rmfl;

    @FXML
    private JFXTextField rmbc;

    @FXML
    private JFXRadioButton adbtn;

    @FXML
    private ToggleGroup adr;

    @FXML
    private JFXRadioButton rmbtn;

    @FXML
    private JFXButton room;

    @FXML
    private JFXTextField fdit;

    @FXML
    private JFXTextField fdpr;

    @FXML
    private JFXRadioButton fdad;

    @FXML
    private JFXRadioButton rmbtn1;

    @FXML
    private JFXTextField brst;

    @FXML
    private JFXTextField brct;

    @FXML
    private JFXTextField brupbr;

    @FXML
    private JFXTextField brgm;

    @FXML
    private JFXTextField brmp;

    @FXML
    private JFXRadioButton adbn2;

    @FXML
    private JFXRadioButton adbtn21;

    @FXML
    private JFXTextField brbr;

    @FXML
    private JFXDatePicker tbdt;

    @FXML
    private JFXTextField tbkt;

    @FXML
    private JFXTextField tbtx;

    @FXML
    private JFXTextField tbbl;

    @FXML
    private JFXTextField tbot;

    @FXML
    private JFXTextField tbbr;
    
    @FXML
    private JFXDatePicker deldt;
    
    @FXML
    private JFXTextField delbr;
    
    @FXML
    private HeaderController headerController;

    @FXML
    private SideBarController sideBarController;
    
    private State state;
    
    public void setState(State st) {
    	System.out.println("Setting state");
    	state=st;
    	headerController.setState(state);
    	sideBarController.setState(state);
    }

    @FXML
    void tabulate() {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	String branch=tbbr.getText();
    	double kitch=Double.parseDouble(tbkt.getText());
    	double bill=Double.parseDouble(tbbl.getText());
    	double tax=Double.parseDouble(tbtx.getText());
    	double oth=Double.parseDouble(tbot.getText());
    	try {
    		java.sql.Date date=new java.sql.Date(sdf.parse(tbdt.getValue().toString()).getTime());
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		ps=con.prepareStatement("insert into accounts(log_date,branch_id,kitchen,taxes,bills,other) values(?,'"+branch+"',"+kitch+","+tax+","+bill+","+oth+")");
    		ps.setDate(1, date);
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void delete(){
    	String branch=delbr.getText();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		java.sql.Date date=new java.sql.Date(sdf.parse(deldt.getValue().toString()).getTime());
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		ps=con.prepareStatement("update accounts set kitchen=0,taxes=0,bills=0,others=0 where branch_id='"+branch+"' and log_date like ?");
    		ps.setDate(1, date);
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void branch() {
    	String branch=brbr.getText();
    	String city=brct.getText();
    	String street=brst.getText();
    	String choice=((JFXRadioButton)adr.getSelectedToggle()).getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		if(choice.equals("Add"))
    			ps=con.prepareStatement("insert into branch values('"+branch+"','"+street+"','"+city+"',null,null,'active')");
    		else
    			ps=con.prepareStatement("update branch set branch_status='inactive' where branch_id='"+branch+"'");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void branchUpdate() {
    	String branch=brupbr.getText();
    	String manag=brgm.getText();
    	String phone=brmp.getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		ps=con.prepareStatement("update branch set gm_id='"+manag+"',phone='"+phone+"' where branch_id='"+branch+"'");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void food() {
    	String item=fdit.getText();
    	double cost=Double.parseDouble(fdpr.getText());
    	String choice=((JFXRadioButton)adr.getSelectedToggle()).getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		if(choice.equals("Add"))
    			ps=con.prepareStatement("insert into food values('"+item+"',"+cost+",0,'y')");
    		else
    			ps=con.prepareStatement("update food set available='n' where item='"+item+"'");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void room() {
    	String branch=rmbr.getText();
    	String room=rmrm.getText();
    	String floor=rmfl.getText();
    	double cost=Double.parseDouble(rmbc.getText());
    	String choice=((JFXRadioButton)adr.getSelectedToggle()).getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps;
    		if(choice.equals("Add"))
    			ps=con.prepareStatement("insert into room values('"+branch+"','"+room+"','"+floor+"','vacant','y',"+cost+")");
    		else
    			ps=con.prepareStatement("update room set room_status='unavailable' where room_num='"+room+"' and branch_id='"+branch+"'");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void hire() {
    	String fname=epfname.getText();
    	String lname=eplname.getText();
    	String branch=epbr.getText();
    	String pos=eppos.getText();
    	String id=generateEmployeeId();
    	double salary=Double.parseDouble(epsal.getText());
    	String ad=epad.getText();
    	String gender=((JFXRadioButton)adr.getSelectedToggle()).getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps=con.prepareStatement("insert into employee values('"+id+"','"+fname+"','"+lname+"','"+gender.charAt(0)+"','"+pos+"',current_date,null,'"+salary+"','"+ad+"','"+branch+"')");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void showEmployee() {
    	String id=epid.getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps=con.prepareStatement("select * from employee where emp_id='"+id+"'");
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		String info="First Name:   "+rs.getString(2)+"\n\nLast Name:   "+rs.getString(3)+"\n\nBranch Id:   "+rs.getString(10)+"\n\nAadhar Number:   "+rs.getString(9);
    		epinfo.setText(info);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    @FXML
    void release(){
    	String id=epid.getText();
    	try {
    		Connection con=Connect.dbConnect();
    		PreparedStatement ps=con.prepareStatement("update employee set released=current_date where emp_id='"+id+"'");
    		ps.execute();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    String generateEmployeeId() {
    	try {
    		Connection conn=Connect.dbConnect();
    		PreparedStatement ps4=conn.prepareStatement("select max(emp_id) from employee");
	     	ResultSet rs4=ps4.executeQuery();
	     	rs4.next();
	     	int empId;
	     	String temp=rs4.getString(1);
	     	if(temp==null)
	     		empId=1000;
	     	else
	     		empId=Integer.parseInt(temp)+1;
	     	return empId+"";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    
    String getCustomerId(String pass){
    	try {
    		Connection conn=Connect.dbConnect();
        	PreparedStatement ps1=conn.prepareStatement("select customer_id from customer where passport_number='"+pass+"' or aadhar_number='"+pass+"'");
        	ResultSet rs=ps1.executeQuery();
        	rs.next();
        	return rs.getString(1);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
   
}
