package sk.upjs.paz.diary.gui;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.upjs.paz.diary.storage.DaoFactory;

public class App extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		launch(args);
		//System.out.println(DaoFactory.getSubjectDao().getAllSubjects());
	}

	@Override
	public void start(Stage primaryStage) {
		String fileName = "mainWindow.fxml";
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(fileName));
			Scene scene = new Scene(parent);
			primaryStage.setTitle("Diary");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file: \"" + fileName + "\"", e);
		}
	}

	@Override
	public void stop() {
		LOGGER.info("Closed");
		System.exit(1);
	}
}