package sk.upjs.paz.diary.gui;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.storage.DaoFactory;

public class MainWindowController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

	@FXML
	private ImageView downloadImageView;

	@FXML
	private ImageView scheduleImageView;

	@FXML
	private TableView<Exam> examsTableView;

	@FXML
	private FlowPane homeWorkFlowPane;

	@FXML
	private Button addHomeWorkButton;

	@FXML
	private Button addExamButton;

	@FXML
	private Button refreshHomeWorkButton;

	@FXML
	void initialize() {
		List<Homework> hw = DaoFactory.getHomeworkDao().getAllHomework();
		for (Homework homework : hw) {
			CheckBox checkBox = new CheckBox(homework.getDescription());
			checkBox.setSelected(homework.isDone());
			homeWorkFlowPane.getChildren().add(checkBox);
		}
	}

	@FXML
	void addExamButtonClick(ActionEvent event) {
		loadWindow("editExam.fxml", "Edit exam");
	}

	@FXML
	void addHomeWorkButtonClick(ActionEvent event) {
		loadWindow("editHomework.fxml", "Edit homework");
	}

	@FXML
	void refreshHomeWorkButtonClick(ActionEvent event) {
		List<Homework> hw = DaoFactory.getHomeworkDao().refreshHomework();
		for (Homework homework : hw) {
			CheckBox checkBox = new CheckBox(homework.getDescription());
			homeWorkFlowPane.getChildren().add(checkBox);
		}
	}

	@FXML
	void scheduleImageViewOnMouseClicked(MouseEvent event) {
		if (event.getButton() != MouseButton.PRIMARY)
			return;
		loadWindow("schedule.fxml", "Schedule");
	}

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle - title of a window(stage)
	 */
	private void loadWindow(String fxmlFileName, String windowTitle) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setTitle(windowTitle);
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait(); // код за loadWindow не будет выполняться пока окно открыто
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
	}

	@FXML // TODO think about the method name
	void extractPdfImageViewOnMouseClicked(MouseEvent event) {

	}

}