package sk.upjs.paz.diary.gui;

import java.time.Month;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ILessonDAO;
import sk.upjs.paz.diary.storage.ISubjectDAO;

public class ScheduleController extends Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@FXML
	private JFXListView<Lesson> mondayListView;

	@FXML
	private JFXListView<Lesson> wednesdayListView;

	@FXML
	private JFXListView<Lesson> tuesdayListView;

	@FXML
	private JFXListView<Lesson> thursdayListView;

	@FXML
	private JFXListView<Lesson> fridayListView;

	@FXML
	private JFXListView<Subject> subjectListView;

	@FXML
	private Label monthLabel;

	@FXML
	private JFXButton removeSubjectButton;

	@FXML
	private JFXButton addSubjectButton;

	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.getLessonDao();

	private Subject currentSubject = new Subject();

	@FXML
	void initialize() {
		setItemsToLessonsListViews();
		initMonthLabel();
		setItemsToSubjectsListView();
	}

	/**
	 * Initializes lessons lists with current week lessons
	 */
	private void setItemsToLessonsListViews() {
		mondayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(2)));
		tuesdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(3)));
		wednesdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(4)));
		thursdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(5)));
		fridayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(6)));
	}

	/**
	 * Initializes label according to month of the first lesson in a week
	 */
	private void initMonthLabel() {
		Month month = null;
		if (!mondayListView.getItems().isEmpty()) {
			month = mondayListView.getItems().get(0).getDateTime().getMonth();
		} else if (!tuesdayListView.getItems().isEmpty()) {
			month = tuesdayListView.getItems().get(0).getDateTime().getMonth();
		} else if (!wednesdayListView.getItems().isEmpty()) {
			month = wednesdayListView.getItems().get(0).getDateTime().getMonth();
		} else if (!thursdayListView.getItems().isEmpty()) {
			month = thursdayListView.getItems().get(0).getDateTime().getMonth();
		} else if (!fridayListView.getItems().isEmpty()) {
			month = fridayListView.getItems().get(0).getDateTime().getMonth();
		} else {
			monthLabel.setText(null);
			return;
		}
		monthLabel.setText(month.toString());
	}

	/**
	 * Initializes subject's list with all subjects
	 */
	private void setItemsToSubjectsListView() {
		List<Subject> list = subjectDao.getAllSubjects();
		Collections.sort(list);
		subjectListView.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		loadWindow("editSubject.fxml", "Edit subject", new EditSubjectController(currentSubject));
		setItemsToSubjectsListView();
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {
		Subject sbj = subjectListView.getSelectionModel().getSelectedItem();
		subjectListView.getItems().remove(sbj);
		subjectDao.remove(sbj);
	}

}
