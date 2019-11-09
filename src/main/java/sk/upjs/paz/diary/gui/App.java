package sk.upjs.paz.diary.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.upjs.paz.diary.utils.Utils;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		String fileName = "mainWindow.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			Utils.LOGGER.error("Cant load fxml file: \"" + fileName + "\"", e);
		}
	}
}