package application;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.State;
import static utils.Connect.dbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class NotifyAdminController {
		private State state;
		
		private Stage stage;

	  @FXML
	    private Label mess;

	    @FXML
	    private JFXTextArea notif;

		private double xOffset;

		private double yOffset;
		
		public Stage getStage() {
			return stage;
		}

		public void setStage(Stage stage) {
			this.stage = stage;
		}
		
		public State getState() {
			return state;
		}
	
		public void setState(State state) {
			this.state = state;
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

	    @FXML
	    void quit() {
	    	stage.close();
	    }

	    @FXML
	    void submit() {
	    	String message=notif.getText();
	    	try {
	    		Connection con=dbConnect();
	    		PreparedStatement ps;
	    		ResultSet rs;
	    		int id=genMessId();
	    		if(id==-1)
	    			throw new Exception("Could not generte valid Id");
	    		ps=con.prepareStatement("insert into messages values('"+id+"','"+state.getEmp_id()+"',null,'"+message+"','notifyAdmin','y')");
	    		ps.execute();
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
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
