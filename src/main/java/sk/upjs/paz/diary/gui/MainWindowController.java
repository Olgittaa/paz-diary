package sk.upjs.paz.diary.gui;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IExamDAO;
import sk.upjs.paz.diary.persistence.IHomeworkDAO;

public class MainWindowController extends Controller {
	/** Logger */
	static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

	@FXML
	private ImageView downloadImageView;

	@FXML
	private ImageView scheduleImageView;

	@FXML
	private TableView<Exam> examsTableView;
	@FXML
	private TableColumn<Exam, Subject> subjectTableColumn;
	@FXML
	private TableColumn<Exam, LocalDate> dateTimeTableColumn;
	@FXML
	private TableColumn<Exam, String> audienceTableColumn;

	@FXML
	private FlowPane homeworkFlowPane;

	@FXML
	private JFXButton addHomeWorkButton;

	@FXML
	private JFXButton addExamButton;

	private IHomeworkDAO homeworkDao = DaoFactory.INSTANCE.getHomeworkDao();
	private IExamDAO examDao = DaoFactory.INSTANCE.getExamDao();

	@FXML
	void initialize() {
		initHomeworkCheckBoxes();
		initExamTableView();
	}

	private void initHomeworkCheckBoxes() {
		List<Homework> onWeekHomework = homeworkDao.getHomeworkOnWeekSorted();

		for (Homework homework : onWeekHomework) {
			String subjectName = homework.getSubject().getName();

			JFXCheckBox checkBox = new JFXCheckBox(
					subjectName + " until " + homework.getFormatDeadline() + "\n-" + homework.getDescription());
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
					loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController(homework), 420, 305,
							420, 305);
					refreshHomework();
				}
			});

		}
	}

	private void initExamTableView() {
		ObservableList<Exam> exams = FXCollections.observableArrayList(examDao.getAllExams());
		subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
		dateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		audienceTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

		examsTableView.setItems(exams);

		examsTableView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				loadWindow("editExam.fxml", "Edit exam",
						new EditExamsController(examsTableView.getSelectionModel().getSelectedItem()), 420, 305, 420,
						305);
				refreshExams();
			}
		});
	}

	@FXML
	void addExamButtonClick(ActionEvent event) {
		loadWindow("editExam.fxml", "Edit exam", new EditExamsController(), 420, 305, 420, 305);
		refreshExams();
	}

	@FXML
	void addHomeWorkButtonClick(ActionEvent event) {
		loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController(), 420, 305, 420, 305);
		refreshHomework();
	}

	private void refreshHomework() {
		homeworkFlowPane.getChildren().clear();
		initHomeworkCheckBoxes();
	}

	private void refreshExams() {
		examsTableView.getItems().clear();
		initExamTableView();
	}

	@FXML
	void scheduleImageViewOnMouseClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			loadWindow("schedule.fxml", "Schedule", new ScheduleController(), 615, 560, 615, 560);
			refreshExams();
			refreshHomework();
		}
	}

	@FXML
	void extractPdfImageViewOnMouseClicked(MouseEvent event) {
		loadWindow("fileChooser.fxml", "FileChooser");
	}

}