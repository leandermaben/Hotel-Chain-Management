package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fx=new FXMLLoader(getClass().getResource("Search.fxml"));
			AnchorPane root=(AnchorPane)fx.load();
			Scene scene = new Scene(root,994,657);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//((HomeController)fx.getController()).setStage(primaryStage);
			primaryStage.initStyle(StageStyle.DECORATED);
			passStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void passStage(Stage window) {
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
