package sk.upjs.paz.diary.gui;

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
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ILessonDAO;
import sk.upjs.paz.diary.storage.ISubjectDAO;

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
	private JFXButton addSubjectButton;

	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.getLessonDao();

	private Subject currentSubject = new Subject();

	@FXML
	void initialize() {
		setOrRefreshItemsToLessonsListViews();
		initMonthLabel();
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
		for (int i = 0; i < views.size(); i++) {
			views.get(i).getItems().clear();
			views.get(i).setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(i + 2)));
		}
	}

	/**
	 * Initializes label according to month of the first lesson in a week
	 */
	private void initMonthLabel() {
		Month month = null;
		int monday = 0;
		int tuesday = 0;
		int wednesday = 0;
		int thursday = 0;
		int friday = 0;
		int week = 0;
		if (!mondayListView.getItems().isEmpty()) {
			month = mondayListView.getItems().get(0).getDateTime().getMonth();
			monday = mondayListView.getItems().get(0).getDateTime().getDayOfMonth();
			week = mondayListView.getItems().get(0).getDateTime().getDayOfMonth();
		} else if (!tuesdayListView.getItems().isEmpty()) {
			month = tuesdayListView.getItems().get(0).getDateTime().getMonth();
			tuesday = tuesdayListView.getItems().get(0).getDateTime().getDayOfMonth();
		} else if (!wednesdayListView.getItems().isEmpty()) {
			month = wednesdayListView.getItems().get(0).getDateTime().getMonth();
			wednesday = wednesdayListView.getItems().get(0).getDateTime().getDayOfMonth();
		} else if (!thursdayListView.getItems().isEmpty()) {
			month = thursdayListView.getItems().get(0).getDateTime().getMonth();
			thursday = thursdayListView.getItems().get(0).getDateTime().getDayOfMonth();
		} else if (!fridayListView.getItems().isEmpty()) {
			month = fridayListView.getItems().get(0).getDateTime().getMonth();
			friday = fridayListView.getItems().get(0).getDateTime().getDayOfMonth();
		} else {
			monthLabel.setText(null);
			modayDateLabel.setText(null);
			tuesdayDateLabel.setText(null);
			wednesdayDateLabel.setText(null);
			thursdayDateLabel.setText(null);
			fridayDateLabel.setText(null);
			return;
		}
		monthLabel.setText(month.toString());
		if (monday != 0) {
			modayDateLabel.setText(String.valueOf(monday));
			tuesdayDateLabel.setText(String.valueOf(monday + 1));
			wednesdayDateLabel.setText(String.valueOf(monday + 2));
			thursdayDateLabel.setText(String.valueOf(monday + 3));
			fridayDateLabel.setText(String.valueOf(monday + 4));
		} else if (tuesday != 0) {
			modayDateLabel.setText(String.valueOf(tuesday - 2));
			tuesdayDateLabel.setText(String.valueOf(tuesday));
			wednesdayDateLabel.setText(String.valueOf(tuesday + 1));
			thursdayDateLabel.setText(String.valueOf(tuesday + 2));
			fridayDateLabel.setText(String.valueOf(tuesday + 3));
		} else if (wednesday != 0) {
			modayDateLabel.setText(String.valueOf(wednesday - 2));
			tuesdayDateLabel.setText(String.valueOf(wednesday - 1));
			wednesdayDateLabel.setText(String.valueOf(wednesday));
			thursdayDateLabel.setText(String.valueOf(wednesday + 1));
			fridayDateLabel.setText(String.valueOf(wednesday + 2));
		} else if (thursday != 0) {
			modayDateLabel.setText(String.valueOf(thursday - 3));
			tuesdayDateLabel.setText(String.valueOf(thursday - 2));
			wednesdayDateLabel.setText(String.valueOf(thursday - 1));
			thursdayDateLabel.setText(String.valueOf(thursday));
			fridayDateLabel.setText(String.valueOf(thursday + 1));
		} else if (friday != 0) {
			modayDateLabel.setText(String.valueOf(friday - 4));
			tuesdayDateLabel.setText(String.valueOf(friday - 3));
			wednesdayDateLabel.setText(String.valueOf(friday - 2));
			thursdayDateLabel.setText(String.valueOf(friday - 1));
			fridayDateLabel.setText(String.valueOf(friday));
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
						new SubjectDescriptionController(selectedSubject));
			}
		});
		subjectListView.setItems(FXCollections.observableArrayList(subjectDao.getAllSubjectsSorted()));
	}

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		loadWindow("editSubject.fxml", "Edit subject", new EditSubjectController(currentSubject));
		setItemsToSubjectsListView();
	}

//	@FXML
//	void removeSubjectButtonClick(ActionEvent event) {
//		Subject sbj = subjectListView.getSelectionModel().getSelectedItem();
//		subjectListView.getItems().remove(sbj);
//		subjectDao.remove(sbj);
//		setOrRefreshItemsToLessonsListViews();
//	}

}
