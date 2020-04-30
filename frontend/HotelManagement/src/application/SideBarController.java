package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.State;

public class SideBarController {
	 private State state;
	    public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}
		
		public void changePassword() {
			try {
				ArrayList<Object> fx=state.getPage("ChangePassword");
	    		if((boolean)fx.get(2)) {
	    			Stage stage=new Stage();
	        		((ChangePasswordController)fx.get(1)).setStage(stage);
	        		((ChangePasswordController)fx.get(1)).setState(state);
	        		stage.initStyle(StageStyle.TRANSPARENT);
	        		fx.add(stage);
	        		stage.setScene((Scene)fx.get(0));
	    		}

	    		Stage stage=((Stage)fx.get(3));
	    		stage.toFront();
	    		stage.show();	    	}
	    	catch(Exception e) 
	    	{
	    		e.printStackTrace();
	    	}
		}
		@FXML
	    void logout() {
			state.getStage().close();
			state.closeAllPopUps();
			Stage primaryStage=new Stage();
			try {
				FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/Home.fxml"));
				AnchorPane root=(AnchorPane)fx.load();
				Scene scene = new Scene(root,994,657);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				((HomeController)fx.getController()).setLoginWindow(primaryStage);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void message() {
	    	try {
	    		ArrayList<Object> fx=state.getPage("Message");
	    		if((boolean)fx.get(2)) {
	    			Stage stage=new Stage();
	        		((MessageController)fx.get(1)).setStage(stage);
	        		((MessageController)fx.get(1)).setState(state);
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
	    void notifyAdmin() {
	    	try {
	    		ArrayList<Object> fx=state.getPage("NotifyAdmin");
	    		if((boolean)fx.get(2)) {
	    			Stage stage=new Stage();
	        		((NotifyAdminController)fx.get(1)).setStage(stage);
	        		((NotifyAdminController)fx.get(1)).setState(state);
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

	  
}
