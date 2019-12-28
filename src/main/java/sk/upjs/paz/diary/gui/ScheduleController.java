package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.ILessonDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

public class ScheduleController extends Controller {

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
	private Label modayDateLabel;
	@FXML
	private Label tuesdayDateLabel;
	@FXML
	private Label wednesdayDateLabel;
	@FXML
	private Label thursdayDateLabel;
	@FXML
	private Label fridayDateLabel;

	@FXML
	private JFXButton removeSubjectButton;

	@FXML
	private JFXButton editSubjectButton;

	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.INSTANCE.getLessonDao();

	@FXML
	void initialize() {
		setOrRefreshItemsToLessonsListViews();
		initMonthAndDaysLabel();
		setItemsToSubjectsListView();
	}

	/**
	 * Initializes lessons lists with current week lessons
	 */
	private void setOrRefreshItemsToLessonsListViews() {
		final ArrayList<JFXListView<Lesson>> views = new ArrayList<>(6);
		views.add(mondayListView);
		views.add(tuesdayListView);
		views.add(wednesdayListView);
		views.add(thursdayListView);
		views.add(fridayListView);
		DayOfWeek[] values = DayOfWeek.values();
		for (int i = 0; i < views.size(); i++) {
			views.get(i).getItems().clear();
			views.get(i).setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(values[i])));
		}
	}

	/**
	 * Initializes label according to month and dates of the first lesson in a week
	 */
	private void initMonthAndDaysLabel() {
		LocalDate now = LocalDate.now();
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		monthLabel.setText(Month.from(LocalDateTime.now()).toString());
		switch (dayOfWeek) {
		case MONDAY:
			modayDateLabel.setText(String.valueOf(now.getDayOfMonth()));
			tuesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 1));
			wednesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 2));
			thursdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 3));
			fridayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 4));
			break;
		case TUESDAY:
			modayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 1));
			tuesdayDateLabel.setText(String.valueOf(now.getDayOfMonth()));
			wednesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 1));
			thursdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 2));
			fridayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 3));
			break;
		case WEDNESDAY:
			modayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 2));
			tuesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 1));
			wednesdayDateLabel.setText(String.valueOf(now.getDayOfMonth()));
			thursdayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 1));
			fridayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 2));
			break;
		case THURSDAY:
			modayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 3));
			tuesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 2));
			wednesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 1));
			thursdayDateLabel.setText(String.valueOf(now.getDayOfMonth()));
			fridayDateLabel.setText(String.valueOf(now.getDayOfMonth() + 1));
			break;
		case FRIDAY:
			modayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 4));
			tuesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 3));
			wednesdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 2));
			thursdayDateLabel.setText(String.valueOf(now.getDayOfMonth() - 1));
			fridayDateLabel.setText(String.valueOf(now.getDayOfMonth()));
			break;
		default:
			break;
		}

	}

	/**
	 * Initializes subject's list with all subjects
	 */
	private void setItemsToSubjectsListView() {
		subjectListView.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				Subject selectedSubject = subjectListView.getSelectionModel().getSelectedItem();
				loadWindow("subjectDescription.fxml", selectedSubject.getName(),
						new SubjectDescriptionController(selectedSubject), 500, 350, 500, 350);
			}
		});
		subjectListView.setItems(FXCollections.observableArrayList(subjectDao.getAllSubjectsSorted()));
	}

	@FXML
	void editSubjectButtonClick(ActionEvent event) {
		Subject currentSubject = subjectListView.getSelectionModel().getSelectedItem();
		if (currentSubject == null) {
			loadWindow("editSubject.fxml", "Edit subject", new EditSubjectController(), 520, 700, 520, 700);
		} else {
			loadWindow("editSubject.fxml", "Edit subject", new EditSubjectController(currentSubject), 520, 700, 520,
					700);
		}
		setItemsToSubjectsListView();
		setOrRefreshItemsToLessonsListViews();
	}
}