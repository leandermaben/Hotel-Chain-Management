package application;

import static utils.Connect.dbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.State;

public class AdminNotificationController {

    @FXML
    private Label stat;

    @FXML
    private JFXTextArea notif;

    @FXML
    private JFXCheckBox sup;

    @FXML
    private JFXCheckBox staff;
    
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
    	try {
    		Connection con=dbConnect();
    		PreparedStatement ps;
    		int id=genMessId();
    		if(id==-1)
    			throw new Exception("Could not generte valid Id");
    		ps=con.prepareStatement("insert into messages values('"+id+"',current_date,'"+state.getEmp_id()+"',?,'nAdmin')");
    		ps.setString(1,notif.getText());
    		ps.execute();
    		if(sup.isSelected()) {
    			ps=con.prepareStatement("select emp_id from users where clearance='supervisor'");
        		ResultSet rs=ps.executeQuery();
        		while(rs.next()) {
        			ps=con.prepareStatement("insert into inbox values('"+id+"','"+rs.getString(1)+"','y')");
    	    		ps.execute();
        		}
        	}
    		if(staff.isSelected()) {
    			ps=con.prepareStatement("select emp_id from users where clearance='employee'");
        		ResultSet rs=ps.executeQuery();
        		while(rs.next()) {
        			ps=con.prepareStatement("insert into inbox values('"+id+"','"+rs.getString(1)+"','y')");
    	    		ps.execute();
        		}
        	}
    		stat.setTextFill(Color.GREEN);
    		stat.setText("Successful");
    	}catch(Exception e) {
    		stat.setTextFill(Color.RED);
    		stat.setText("Try Again.");
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
