package sk.upjs.paz.diary.gui;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	protected Stage loadWindow(String fxmlFileName, String windowTitle, Object controller, double minWidth,
			double minHeigth, double maxWidth, double maxHeigth) {
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
			modalStage.setMinHeight(minHeigth);
			modalStage.setMinWidth(minWidth);
			modalStage.setMaxHeight(maxHeigth);
			modalStage.setMaxWidth(maxWidth);
			modalStage.showAndWait();
		} catch (LoadException e) {
			LOGGER.error("Wrong controller\"" + controller + "\"", e);
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
		return modalStage;
	}

	protected Stage loadWindow(String fxmlFileName, String windowTitle) {
		return loadWindow(fxmlFileName, windowTitle, null, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	}

	protected void closeWindow(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	protected void showAlert(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

}