package sk.upjs.paz.diary.gui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
		
		examsTableView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				loadWindow("editExam.fxml", "Edit exam",
						new EditExamsController(examsTableView.getSelectionModel().getSelectedItem()), 420, 302, 420,
						302);
				refreshExams();
			}
		});
	}

	private void refreshHomework() {
		homeworkFlowPane.getChildren().clear();
		initHomeworkCheckBoxes();
	}
	
	private void initHomeworkCheckBoxes() {
		List<Homework> onWeekHomework = homeworkDao.getHomeworkOnWeekSorted();

		for (Homework homework : onWeekHomework) {
			JFXCheckBox checkBox = new JFXCheckBox(homework.toString());
			checkBox.setSelected(homework.isDone());
			checkBox.setCheckedColor(Color.valueOf("#661616"));

			Duration between = Duration.between(homework.getDeadline(), LocalDateTime.now());
			long seconds = between.getSeconds();
			boolean left1day = seconds <= 3600 * 24 && seconds > 60;
			if (homework.getDeadline().isBefore(LocalDateTime.now())) {
				checkBox.setTextFill(Color.rgb(194, 31, 31));
				// https://stackoverflow.com/questions/41459107/how-to-show-a-title-for-image-when-i-hover-over-it-in-javafx
				Tooltip.install(checkBox, new Tooltip("Time is over"));
			}
			if (left1day) {
				checkBox.setTextFill(Color.rgb(255, 255, 0));
				// https://stackoverflow.com/questions/41459107/how-to-show-a-title-for-image-when-i-hover-over-it-in-javafx
				Tooltip.install(checkBox, new Tooltip("1 day left"));
			}

			checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				homework.setStatus(newValue);
				homeworkDao.save(homework);
			});
			checkBox.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.SECONDARY) {
					loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController(homework), 420, 280,
							420, 280);
					refreshHomework();
				}
				if (event.getButton() == MouseButton.PRIMARY && event.isControlDown()) {
					loadWindow("homeworkDescription.fxml", "Description", new HomeworkDescriptionController(homework),
							420, 302, 420, 302);
				}
			});
			homeworkFlowPane.getChildren().add(checkBox);
		}
	}

	private void refreshExams() {
		examsTableView.getItems().clear();
		initExamTableView();
	}
	
	private void initExamTableView() {
		List<Exam> allExams = examDao.getAllExams().stream()
				.filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());

		ObservableList<Exam> exams = FXCollections.observableArrayList(allExams);
		subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
		dateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		audienceTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

		examsTableView.setItems(exams);
	}

	@FXML
	void addExamButtonClick(ActionEvent event) {
		loadWindow("editExam.fxml", "Edit exam", new EditExamsController(), 420, 302, 420, 302);
		refreshExams();
	}

	@FXML
	void addHomeWorkButtonClick(ActionEvent event) {
		loadWindow("editHomework.fxml", "Edit homework", new EditHomeworkController(), 420, 302, 420, 302);
		refreshHomework();
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
		loadWindow("directoryChooser.fxml", "Browse directory");
	}
}