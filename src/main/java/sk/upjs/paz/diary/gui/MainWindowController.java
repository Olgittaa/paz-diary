package sk.upjs.paz.diary.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.utils.Utils;

public class MainWindowController {
	@FXML
	private ImageView downloadImageView;

	@FXML
	private ImageView scheduleImageView;

	@FXML
	private TableView<?> examsTableView;

	@FXML
	private FlowPane homeWorkFlowPane;

	@FXML
	private Button addHomeWorkButton;

	@FXML
	private Button addExamButton;

	@FXML
	void initialize() {

	}

	@FXML
	void addExamButtonClick(ActionEvent event) {
		loadWindow("editExam.fxml");
	}

	@FXML
	void addHomeWorkButtonClick(ActionEvent event) {
		loadWindow("editHomework.fxml");
	}

	@FXML
	void scheduleImageViewOnMouseClicked(MouseEvent event) {
		if (event.getButton() != MouseButton.PRIMARY)
			return;
		loadWindow("schedule.fxml");
	}

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName name of a fxml file which will be loaded
	 */
	private void loadWindow(String fxmlFileName) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait();
		} catch (IOException e) {
			Utils.LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
	}

	@FXML // TODO think about the method name
	void extractPdfImageViewOnMouseClicked(MouseEvent event) {
		// TODO
		System.out.println("Extracting pdf...");
	}

}