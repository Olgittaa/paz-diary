package sk.upjs.paz.diary.gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.ExamFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.IExamDAO;
import sk.upjs.paz.diary.storage.IHomeworkDAO;

@SuppressWarnings("rawtypes")
public class MainWindowController extends Controller {
	/** Logger */
	static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

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
	private FlowPane homeworkFlowPane;

	@FXML
	private JFXButton addHomeWorkButton;

	@FXML
	private JFXButton addExamButton;

	private IHomeworkDAO homeworkDao = DaoFactory.getHomeworkDao();
	private IExamDAO examDao = DaoFactory.getExamDao();

	//private ObservableList<Homework> onWeekHomework;

	@FXML
	void initialize() {
		initHomeworkCheckBoxes();
		initExamTableView();
	}

	private void initHomeworkCheckBoxes() {
		List<Homework> onWeekHomework = homeworkDao.getHomeworkOnWeekSorted();

		for (Homework homework : onWeekHomework) {
			String subjectName = homework.getSubject().getName();

			JFXCheckBox checkBox = new JFXCheckBox(subjectName + ". Until " + homework.getFormatDeadline());
			checkBox.setCheckedColor(Color.valueOf("#661616"));
			checkBox.setSelected(homework.isDone());
			homeworkFlowPane.getChildren().add(checkBox);

			checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					homework.setStatus(newValue);
					homeworkDao.save(homework);
				}
			});

			checkBox.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.SECONDARY) {
					loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController(homework));
					refreshHomework();
				} else if (event.isControlDown()) { // сделал через контрол читай {@link TODO} пункт 2
					loadWindow("homeworkDescription.fxml", "Description", new HomeworkDescriptionController(homework));
				}
			});
		}
	}

	@SuppressWarnings("all")
	private void initExamTableView() {
		List<Exam> exams = examDao.getAllExams();
		subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subjectProperty"));
		dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));
		timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("timeProperty"));
		audienceTableColumn.setCellValueFactory(new PropertyValueFactory<>("locationProperty"));

		// examsTableView.setItems(FXCollections.observableArrayList(new ExamFXModel(new
		// Date)));
	}

	@FXML
	void addExamButtonClick(ActionEvent event) {
		loadWindow("editExam.fxml", "Edit exam");
	}

	@FXML
	void addHomeWorkButtonClick(ActionEvent event) {
		loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController());
		refreshHomework();
	}

	private void refreshHomework() {
		homeworkFlowPane.getChildren().clear();
		initHomeworkCheckBoxes();
	}

	@FXML
	void scheduleImageViewOnMouseClicked(MouseEvent event) {
		if (event.getButton() != MouseButton.PRIMARY)
			return;
		loadWindow("schedule.fxml", "Schedule");
	}

	@FXML
	void extractPdfImageViewOnMouseClicked(MouseEvent event) {
		loadWindow("fileChooser.fxml", "FileChooser");
	}

}