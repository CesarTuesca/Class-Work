package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Cesar
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(IOException | IllegalStateException e) {
			e.printStackTrace();
		}
	}
/**
 * 
 * @param args launch inherited by the Application class, eventually call the start() method
 */
	public static void main(String[] args) {
		launch(args);
	}
}