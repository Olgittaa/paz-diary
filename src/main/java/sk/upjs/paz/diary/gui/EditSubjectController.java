package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.gui.models.LessonFxModel;
import sk.upjs.paz.diary.gui.models.SubjectFxModel;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.ILessonDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

public class EditSubjectController extends Controller {
	@FXML
	private JFXButton removeSubjectButton;
	@FXML
	private JFXButton saveSubjectButton;
	@FXML
	private JFXButton removeLessonButton;
	@FXML
	private JFXButton addLessonButton;

	@FXML
	private JFXTextField nameTextField;
	@FXML
	private JFXTextField siteTextField;
	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXComboBox<DayOfWeek> dayOfWeekComboBox;
	@FXML
	private JFXComboBox<LessonType> typeOfLessonComboBox;

	@FXML
	private JFXTimePicker lessonStartTimePicker;
	@FXML
	private JFXDatePicker untilDatePicker;

	@FXML
	private JFXTextField locationTextField;
	@FXML
	private JFXTextField durationTextField;

	@FXML
	private JFXListView<Lesson> lessonsListView;

	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.INSTANCE.getLessonDao();

	private SubjectFxModel editedSubject;
	private LessonFxModel lessonFxModel;
	private ObservableList<Lesson> lessonsModel;

	public EditSubjectController() {
		editedSubject = new SubjectFxModel();
		lessonFxModel = new LessonFxModel();
		lessonsModel = FXCollections.observableArrayList();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
		lessonFxModel = new LessonFxModel();

	}

	@FXML
	void initialize() {
		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();
		initLessonsListView();
		initComboBoxes();
		addDurationTextFieldValidator();
		disableSaveRemoveButtonsIfNameIsAbsent();

		if (nameTextField.getText() == null || nameTextField.getText().trim().length() == 0) {
			saveSubjectButton.setDisable(true);
			removeSubjectButton.setDisable(true);
		}
		lessonStartTimePicker.set24HourView(true);

		fillFieldsByClick();
		showLessonsOfSubjectWritingName();
	}

	private void bindBiderectionalWithSubjectFXModel() {
		nameTextField.textProperty().bindBidirectional(editedSubject.getNameProperty());
		siteTextField.textProperty().bindBidirectional(editedSubject.getSiteProperty());
		emailTextField.textProperty().bindBidirectional(editedSubject.getEmailProperty());
	}

	private void bindBiderectionalWithLessonFXModel() {
		dayOfWeekComboBox.valueProperty().bindBidirectional(lessonFxModel.getDayOfWeekProperty());
		untilDatePicker.valueProperty().bindBidirectional(lessonFxModel.getTillDateProperty());
		lessonStartTimePicker.valueProperty().bindBidirectional(lessonFxModel.getTimeProperty());
		locationTextField.textProperty().bindBidirectional(lessonFxModel.getLocationProperty());
		durationTextField.textProperty().bindBidirectional(lessonFxModel.getDurationProperty());
		typeOfLessonComboBox.valueProperty().bindBidirectional(lessonFxModel.getTypeProperty());
	}

	private void initLessonsListView() {
		lessonsModel.clear();
		lessonsModel.addAll(lessonDao.getLessonsBySubjectId(editedSubject.getId()));
		lessonsListView.setItems(lessonsModel);
	}

	private void initComboBoxes() {
		dayOfWeekComboBox.setItems(FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		typeOfLessonComboBox.setItems(FXCollections.observableArrayList(LessonType.LECTURE, LessonType.PRACTICE));
	}

	private void addDurationTextFieldValidator() {
		// only numbers are allowed
		durationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || newValue.trim().isEmpty()) {
				return;
			}
			if (newValue.chars().anyMatch(ch -> ch < 48 || ch > 57) || newValue.charAt(0) == '0') {
				durationTextField.setText(oldValue);
			} else if (Integer.parseInt(newValue) > 300) {
				durationTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});
	}

	private void disableSaveRemoveButtonsIfNameIsAbsent() {
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (nameTextField != null && nameTextField.getText().trim().length() > 0) {
				saveSubjectButton.setDisable(false);
				removeSubjectButton.setDisable(false);
			} else {
				saveSubjectButton.setDisable(true);
				removeSubjectButton.setDisable(true);
			}
		});
	}

	private void fillFieldsByClick() {
		lessonsListView.setOnMouseClicked(e -> {
			Lesson selectedItem = lessonsListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				lessonFxModel.load(selectedItem);
				lessonStartTimePicker.setValue(lessonFxModel.getTime());
				dayOfWeekComboBox.setValue(lessonFxModel.getDayOfWeek());
				untilDatePicker.setValue(lessonFxModel.getTillDate());
				removeLessonButton.setDisable(false);
			} else {
				removeLessonButton.setDisable(true);
			}
		});
	}

	private void showLessonsOfSubjectWritingName() {
		if(nameTextField.getText() != null && nameTextField.getText().trim().length() > 0) {
			addLessonButton.setDisable(false);
		}
		
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			String subjectName = nameTextField.getText();

			if (subjectName != null && !newValue.trim().isEmpty()) {
				Subject subject = subjectDao.getSubjectByName(newValue);
				if (subject != null) {// if subject exists
					addLessonButton.setDisable(false);
					removeSubjectButton.setDisable(false);

					lessonsModel.setAll(lessonDao.getLessonsBySubjectId(subject.getId()));
					editedSubject.load(subject);
				} else {
					editedSubject.clearValuesBesidesName();
					clearLessonFields();

					addLessonButton.setDisable(true);
					removeLessonButton.setDisable(true);
					removeSubjectButton.setDisable(true);
				}
			}
		});
	}

	private void clearLessonFields() {
		lessonsListView.getItems().clear();
		durationTextField.setText(null);
		untilDatePicker.setValue(null);
		lessonStartTimePicker.setValue(null);
		locationTextField.setText(null);
		dayOfWeekComboBox.setValue(null);
		typeOfLessonComboBox.setValue(null);
		untilDatePicker.setValue(null);
	}

	@FXML
	void addLessonButtonClick(ActionEvent event) {
		final boolean allNecessaryFieldsAreFilled = dayOfWeekComboBox.getSelectionModel().getSelectedItem() != null
				&& lessonStartTimePicker.getValue() != null && untilDatePicker.getValue() != null
				&& durationTextField.getText() != null;

		if (allNecessaryFieldsAreFilled) {
			lessonFxModel.setSubject(editedSubject.getSubject());

			LocalDate end = untilDatePicker.getValue();
			LocalDate now = LocalDate.now();
			if (now.isAfter(end)) {
				clearLessonFields();
				showAlert(AlertType.ERROR, "Error", "Fail!", "Please fill right date");
				return;
			}
			lessonDao.save(lessonFxModel.getLesson());
			initLessonsListView();
		} else {
			showAlert(AlertType.ERROR, "Error", "Fail!", "Please fill all neccessary fields");
		}
	}

	@FXML
	void removeLessonButtonClick(ActionEvent event) {
		Lesson selectedLesson = lessonsListView.getSelectionModel().getSelectedItem();
		if (selectedLesson == null) {
			showAlert(AlertType.ERROR, "Error", "Fail!", "Select lesson first");
			return;
		}
		lessonsModel.remove(selectedLesson);
		lessonDao.remove(selectedLesson);
	}

	@FXML
	void saveSubjectButtonClick(ActionEvent event) {
		Subject subject = subjectDao.save(editedSubject.getSubject());
		editedSubject.load(subject);
		showAlert(AlertType.INFORMATION, "Information", "Success!", "Subject was edited");
		addLessonButton.setDisable(false);
		removeLessonButton.setDisable(false);
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {
		if (subjectDao.getAllSubjects().contains(editedSubject.getSubject())) {
			subjectDao.remove(subjectDao.getSubjectByName(editedSubject.getSubject().getName()));
			showAlert(AlertType.INFORMATION, "Information", "Success!", "Subject was deleted");
			closeWindow(event);
		} else {
			showAlert(AlertType.ERROR, "Error", "Fail!", "Subject does not exists");
		}
	}
}
