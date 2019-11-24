package sk.upjs.paz.diary.gui;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle - title of a window(stage)
	 * @param controller  - controller to fxml file
	 */
	protected Stage loadWindow(String fxmlFileName, String windowTitle, Object controller) {
		Stage modalStage = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			fxmlLoader.setController(controller);
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			modalStage = new Stage();
			modalStage.setTitle(windowTitle);
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait();
		} catch (LoadException e) {
			LOGGER.error("Wrong controller\"" + controller + "\"", e);
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
		return modalStage;
	}

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle - title of a window(stage)
	 */
	protected Stage loadWindow(String fxmlFileName, String windowTitle) {
		return loadWindow(fxmlFileName, windowTitle, null);
	}

	/**
	 * 
	 * @param node
	 */
	protected void closeWindow(Node node) {
		node.getScene().getWindow().hide();
	}

}