package utils;

/*
 * TODO
 * Create state class 
 * Move all state maintainance out of header
 * 
 * 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.AdminPageController;
import application.BookingController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Header {
	State state;
	Label name;
	Label clock;
	public Header(State s,Label n,Label c){
		state=s;
		name=n;
		clock=c;
		setHeader();
	}
	void setHeader() {
		name.setText("Welcome  "+state.getEmpName());
		new Timer(clock);
	}
	
	public void back() {
		try {
			Stage stage=state.getStage();
			FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/AdminPage.fxml"));
			AnchorPane root=(AnchorPane)fx.load();
			Scene scene=new Scene(root,1321,881);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			((AdminPageController)fx.getController()).setState(state);
			stage.setScene(scene);
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