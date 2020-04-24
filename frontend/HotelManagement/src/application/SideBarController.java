package application;

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
				Stage stage=new Stage();
	    		FXMLLoader fx=new FXMLLoader(getClass().getResource("/views/ChangePassword.fxml"));
	    		AnchorPane root=(AnchorPane)fx.load();
	    		Scene scene=new Scene(root,336,267);
	    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    		((ChangePasswordController)fx.getController()).setState(state);
	    		((ChangePasswordController)fx.getController()).setStage(stage);
	    		stage.setScene(scene);
	    		stage.initStyle(StageStyle.TRANSPARENT);
	    		stage.show();
	    	}
	    	catch(Exception e) 
	    	{
	    		e.printStackTrace();
	    	}
		}
		@FXML
	    void logout() {
			state.getStage().close();
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

	    }

	    @FXML
	    void notifyAdmin() {

	    }

	    @FXML
	    void notifyByAdmin() {

	    }
}
