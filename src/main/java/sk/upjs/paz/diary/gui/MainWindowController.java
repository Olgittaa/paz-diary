package sk.upjs.paz.diary.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.ExamFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.IExamDAO;
import sk.upjs.paz.diary.storage.IHomeworkDAO;

@SuppressWarnings("rawtypes")
public class MainWindowController {
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

	@FXML
	private ImageView downloadImageView;

	@FXML
	private ImageView scheduleImageView;


	@FXML
	private TableView examsTableView;
	@FXML
	private TableColumn<ExamFXModel, Subject> subjectTableColumn;
	@FXML
	private TableColumn<ExamFXModel, LocalDate> dateTableColumn;
	@FXML
	private TableColumn<ExamFXModel, LocalTime> timeTableColumn;
	@FXML
	private TableColumn<ExamFXModel, String> audienceTableColumn;

	@FXML
	private FlowPane homeWorkFlowPane;

	@FXML
	private JFXButton addHomeWorkButton;

	@FXML
	private JFXButton addExamButton;

	private IHomeworkDAO homeworkDao = DaoFactory.getHomeworkDao();
	private IExamDAO examDao = DaoFactory.getExamDao();

	@FXML
	void initialize() {
		initHomeworkCheckBoxes();
		initExamTableView();
	}

	private void initHomeworkCheckBoxes() {
		List<Homework> hw = homeworkDao.getHomeworkOnWeek();
		for (Homework homework : hw) {
			String subjectName = homework.getSubject().getName();

			JFXCheckBox checkBox = new JFXCheckBox(subjectName + ". Until " + homework.getFormatDeadline());
			checkBox.setCheckedColor(Color.valueOf("#661616"));
			checkBox.setSelected(homework.isDone());
			homeWorkFlowPane.getChildren().add(checkBox);

			checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					homework.setStatus(newValue);
					homeworkDao.refreshHomework(homework);
				}
			});

			checkBox.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.SECONDARY) {
					loadWindow("editHomework.fxml", "Edit homework");
				} else if (event.getClickCount() == 2) {
					loadWindow("homeworkDescription.fxml", "Description",
							new HomeworkDescriptionController(homework.getDescription()));
				}
			});
		}
	}

	
	@SuppressWarnings("unchecked")
	private void initExamTableView() {
		List<Exam> exams = examDao.getAllExams();
		subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subjectProperty"));
		dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));
		timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("timeProperty"));
		audienceTableColumn.setCellValueFactory(new PropertyValueFactory<>("locationProperty"));
		
		//examsTableView.setItems(FXCollections.observableArrayList(new ExamFXModel(new Date)));
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
	 * @param controller  - controller to fxml file
	 */
	private void loadWindow(String fxmlFileName, String windowTitle, Object controller) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			fxmlLoader.setController(controller);
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setTitle(windowTitle);
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait();
		} catch (LoadException e) {
			LOGGER.error("Wrong controller\"" + controller + "\"", e);
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
	}

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle - title of a window(stage)
	 */
	private void loadWindow(String fxmlFileName, String windowTitle) {
		loadWindow(fxmlFileName, windowTitle, null);
	}

	@FXML
	void extractPdfImageViewOnMouseClicked(MouseEvent event) {
		loadWindow("fileChooser.fxml", "FileChooser");
	}

}