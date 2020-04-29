package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.State;
import static utils.Connect.dbConnect;

public class MessageController {
	 	@FXML
	    private JFXTextField toField;
	 	@FXML
	    private JFXTextArea message;
	    @FXML
	    private JFXListView<String> sendList;

	    @FXML
	    private JFXListView<String> inbox;

	    @FXML
	    private JFXListView<String> sentList;
	    
	    @FXML
	    private Label addlbl;
	    @FXML
	    private Label sendlbl;
	    @FXML
	    private Label lim;
	    
	    private State state;
	    public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

		private Stage stage;

		private double xOffset;

		private double yOffset;
	    public Stage getStage() {
			return stage;
		}

		public void setStage(Stage stage) {
			this.stage = stage;
		}

		@FXML
	    void quit() {
			stage.close();
	    }
		
	    @FXML
	    void add() {
	    	String send=toField.getText();
	    	String name;
	    	boolean found=false;
	    	addlbl.setText("");
	    	try {
	    		Connection con=dbConnect();
	    		PreparedStatement ps=con.prepareStatement("select fname,lname from employee natural join users where emp_id='"+send+"'");
	    		ResultSet rs=ps.executeQuery();
	    		while(rs.next()) {
	    			found=true;
	    			toField.setText("");
	    			name=send+" "+rs.getString(1)+" "+rs.getString(2);
	    			ObservableList<String> receipients=sendList.getItems();
	    			receipients.add(name);
	    			sendList.setItems(receipients);
	    		}
	    		if(!found) {
	    			addlbl.setTextFill(Color.RED);
		    		addlbl.setText("User does not exist");
	    		}
	    	}catch(Exception e) {
	    		addlbl.setTextFill(Color.RED);
	    		addlbl.setText("Oops! Something went wrong");
	    		e.printStackTrace();
	    	}
	    }
	    
	    @FXML
	    void sendMessage() {
	    	try {
	    		Connection con=dbConnect();
	    		int id=genMessId();
	    		PreparedStatement ps=con.prepareStatement("insert into messages values('"+id+"',current_date,'"+state.getEmp_id()+"',?,'regular')");
	    		ps.setString(1, message.getText());
	    		ps.execute();
	    		for(String x:sendList.getItems()) {
	    			ps=con.prepareStatement("insert into inbox values('"+id+"','"+x.substring(0,x.indexOf(" "))+"','y')");
	    			ps.execute();
	    		}
	    		message.setText("");
	    		sendlbl.setTextFill(Color.GREEN);
	    		sendlbl.setText("Sent Successfully");
	    		sendList.setItems(FXCollections.observableArrayList());
	    	}catch(Exception e) {
	    		sendlbl.setTextFill(Color.RED);
	    		sendlbl.setText("Oops! Something went wrong");
	    		e.printStackTrace();
	    	}
	    }
	    @FXML
	    void wordLim() {
	    	String mess=message.getText();
	    	int len=mess.length();
	    	lim.setTextFill(Color.LIMEGREEN);
	    	if(len<=500) {
	    		lim.setText("Character Limit is 500. Current Length is "+len);
	    		
	    	}else {
	    		lim.setTextFill(Color.RED);
	    		lim.setText("Character Limit Exceeded!!");
	    	}
	    }
	    
	    @FXML
	    void popInbox() {
	    	try {
	    		Connection con=dbConnect();
	    		int id=genMessId();
	    		PreparedStatement ps=con.prepareStatement("select sender,category,mess,sent_date,mess_id from messages natural join inbox where receipient='"+state.getEmp_id()+"' and disp='y'");
	    		ResultSet rs=ps.executeQuery();
	    		ObservableList<String> list=FXCollections.observableArrayList();
	    		while(rs.next()) {
	    			ps=con.prepareStatement("select fname,lname from employee where emp_id='"+rs.getString(1)+"'");
	    			ResultSet rs2=ps.executeQuery();
	    			rs2.next();
	    			if(rs.getString(2).equals("fAdmin")) {
	    				list.add(rs.getString(5)+" "+rs.getDate(4)+"ADMIN NOTIFICATION\n"+rs.getString(3)+"\n"+rs.getString(1)+" "+rs2.getString(1)+" "+rs2.getString(2));
	    			}else if(rs.getString(2).equals("schedule")) {
	    				list.add(rs.getString(5)+" "+rs.getDate(4)+"SCHEDULE\n"+rs.getString(3)+"\n"+rs.getString(1)+" "+rs2.getString(1)+" "+rs2.getString(2));
	    			}else {
	    				list.add(rs.getString(5)+" "+rs.getDate(4)+"\n"+rs.getString(3)+"\nFrom:  "+rs.getString(1)+" "+rs2.getString(1)+" "+rs2.getString(2));
	    			}
	    		}
	    		inbox.setItems(list);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    @FXML
	    void popSent() {
	    	try {
	    		Connection con=dbConnect();
	    		int id=genMessId();
	    		String receipient;
	    		PreparedStatement ps=con.prepareStatement("select category,mess,sent_date,mess_id from messages where sender='"+state.getEmp_id()+"'");
	    		ResultSet rs=ps.executeQuery();
	    		ObservableList<String> list=FXCollections.observableArrayList();
	    		while(rs.next()) {
	    			receipient="";
	    			ps=con.prepareStatement("select emp_id,fname,lname from employee join inbox on emp_id=receipient where mess_id='"+rs.getString(4)+"'");
	    			ResultSet rs2=ps.executeQuery();
	    			while(rs2.next()){
	    				receipient+=(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+"\t");
	    			}
	    			
	    			list.add(rs.getString(3)+" "+rs.getDate(3)+" Type: "+rs.getString(1).toUpperCase()+"\n"+rs.getString(2)+"\n To: "+receipient);
	    		}
	    		sentList.setItems(list);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }

	    
	    @FXML
	    void clearAllIn() {
	    	try {
	    		Connection con=dbConnect();
	    		PreparedStatement ps=con.prepareStatement("update inbox set disp='n' where receipient='"+state.getEmp_id()+"'");
	    		ps.execute();
	    		inbox.setItems(FXCollections.observableArrayList());
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void clearIn() {
	    	try {
	    		Connection con=dbConnect();
	    		ObservableList<String> all=inbox.getItems();
	    		ObservableList<String> list=inbox.getSelectionModel().getSelectedItems();
	    		for(String x:list) {
	    			all.remove(x);
	    			PreparedStatement ps=con.prepareStatement("update inbox set disp='n' where mess_id="+x.substring(0,x.indexOf(" "))+"' and receipient='"+state.getEmp_id()+"'");
	    			ps.execute();
	    		}
	    		inbox.setItems(all);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }


	    @FXML
	    void remove() {
	    	ObservableList<String> ob=sendList.getItems();
	    	for(String x:sendList.getSelectionModel().getSelectedItems()) {
	    		ob.remove(x);
	    	}
	    	sendList.setItems(ob);
	    }
	    
	    @FXML
	    void move(MouseEvent event) {
	    	stage.setX(event.getScreenX() - xOffset);
	    	stage.setY(event.getScreenY() - yOffset);
	    }

	    @FXML
	    void register(MouseEvent event) {
	    	 xOffset = event.getSceneX();
	         yOffset = event.getSceneY();
	    }
	    static int genMessId() {
	    	try {
	    		Connection con=dbConnect();
	    		PreparedStatement ps;
	    		ResultSet rs;
	    		ps=con.prepareStatement("select max(mess_id) from messages");
	    		rs=ps.executeQuery();
	    		rs.next();
	    		if(rs.getString(1)==null)
	    			return 1000;
	    		return Integer.parseInt(rs.getString(1))+1;
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		return -1;
	    	}
	    }
	    
}
