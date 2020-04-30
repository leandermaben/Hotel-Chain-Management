package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import application.AdminPageController;
import application.BookingController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.State;

public class HeaderController {
	State state;
	@FXML
	private JFXButton backbtn;
	

	@FXML
	Label name;
	@FXML
	Label clock;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
		setHeader();
	}
	void setHeader() {
		name.setText("Welcome  "+state.getEmpName());
		new Timer(clock);
	}
	public JFXButton getBackbtn() {
		return backbtn;
	}
	public void setBackbtn(JFXButton backbtn) {
		this.backbtn = backbtn;
	}
	public void back() {
		try {
			if(state.getClearance().equals("admin")){
				ArrayList<Object> fx=state.getPage("AdminPage");
				state.getStage().setScene((Scene)fx.get(0));
			}else {
				ArrayList<Object> fx=state.getPage("SupervisorPage");
				state.getStage().setScene((Scene)fx.get(0));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

class Timer implements Runnable{
	Label clock;
	Timer(Label clck){
		clock=clck;
		Thread t=new Thread(this);
		t.start();
		
	}
	public void run() {
		while(true) {
			Platform.runLater(new Runnable() {
				public void run() {
					LocalDateTime now=LocalDateTime.now();
					DateTimeFormatter df=DateTimeFormatter.ofPattern("E, dd MMM \t HH : mm : ss");
					clock.setText(now.format(df));
				}
			});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
}