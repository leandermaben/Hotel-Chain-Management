package application;

import java.sql.Connection;

import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.State;

import static utils.Connect.dbConnect;
public class ChangePasswordController {
	 @FXML
	    private JFXTextField pass;

	    @FXML
	    private JFXTextField cpass;

	    @FXML
	    private Label mess;
	    
	    private State state;
	    public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

		private Stage stage;
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
	    void submit() {
	    	String p=pass.getText();
	    	String c=cpass.getText();
	    	if(p.equals(c)) {
	    		try {
	    			Connection con=dbConnect();
	    			con.prepareStatement("update users set pin='"+p+"'where emp_id='"+state.getEmp_id()+"'").execute();
	    			mess.setTextFill(Color.GREEN);
	    			mess.setText("Password Successfully Changed");
	    			new CloseWindow(stage);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			mess.setTextFill(Color.RED);
		    		mess.setText("Failed");
	    		}
	    	}else {
	    		mess.setTextFill(Color.RED);
	    		mess.setText("Passwords Do not Match");
	    	}
	    }
	    
}

class CloseWindow implements Runnable{
	Stage window;
	CloseWindow(Stage w){
		window=w;
		Thread t=new Thread(this);
		t.start();
	}
	public void run() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException i) {
			i.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			public void run() {
				window.close();
			}
		});
	}
}



