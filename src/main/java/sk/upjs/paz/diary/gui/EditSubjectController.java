package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

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
import javafx.util.converter.NumberStringConverter;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.gui.models.LessonFxModel;
import sk.upjs.paz.diary.gui.models.SubjectFxModel;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.ILessonDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;
import sk.upjs.paz.diary.entity.Subject;

//FIXME бай дифолт устанавливаются значения 0
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
	private JFXTimePicker lessonStartTimePicker;

	@FXML
	private JFXDatePicker lastLessonDatePicker;

	@FXML
	private JFXTextField locationTextField;

	@FXML
	private JFXTextField durationTextField;

	@FXML
	private JFXComboBox<LessonType> typeOfLessonComboBox;

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
		lessonsModel.addAll(lessonDao.getWeekScheduleBySubjectId(editedSubject.getId()));
	}

	@FXML
	void initialize() {
		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();

		lessonsListView.setItems(lessonsModel);
		initComboBoxes();

		addInputValidators();

		if (nameTextField.getText() == null || nameTextField.getText().trim().length() == 0) {
			saveSubjectButton.setDisable(true);
			removeSubjectButton.setDisable(true);
		}
		lessonStartTimePicker.set24HourView(true);

		showLessonInfoClick();
		showLessonsOfSubjectWritingName();
	}

	private void showLessonInfoClick() {
		lessonsListView.setOnMouseClicked(e -> {
			Lesson selectedItem = lessonsListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				lessonFxModel.load(selectedItem);
				lessonStartTimePicker.setValue(lessonFxModel.getDateTime().toLocalTime());
				dayOfWeekComboBox.setValue(DayOfWeek.from(lessonFxModel.getDateTime()));
				lastLessonDatePicker.setValue(
						lessonDao.getLastLessonOfSubject(selectedItem.getSubject()).getDateTime().toLocalDate());
				removeLessonButton.setDisable(false);
			} else {
				removeLessonButton.setDisable(true);				
			}
		});
	}

	private void showLessonsOfSubjectWritingName() {
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			String subjectName = nameTextField.getText();

			if (subjectName != null && !newValue.trim().isEmpty()) {
				Subject subject = subjectDao.getSubjectByName(newValue);
				if (subject != null) {// if subject exists
					addLessonButton.setDisable(false);
					removeSubjectButton.setDisable(false);

					lessonsModel.setAll(lessonDao.getWeekScheduleBySubjectId(subject.getId()));
					editedSubject.load(subject);
				} else {
					editedSubject.clearValuesWithoutName();
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
		lastLessonDatePicker.setValue(null);
		lessonStartTimePicker.setValue(null);
		locationTextField.setText(null);
		dayOfWeekComboBox.setValue(null);
		typeOfLessonComboBox.setValue(null);
		lastLessonDatePicker.setValue(null);
	}

	private void initComboBoxes() {
		dayOfWeekComboBox.setItems(FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		typeOfLessonComboBox.setItems(FXCollections.observableArrayList(LessonType.LECTURE, LessonType.PRACTICE));
	}

	private void addInputValidators() {
		durationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || newValue.isEmpty())
				return;
			if (newValue.matches("\\D*")) {
				durationTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) > 300) {
				durationTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});

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

	private void bindBiderectionalWithSubjectFXModel() {
		nameTextField.textProperty().bindBidirectional(editedSubject.getNameProperty());
		siteTextField.textProperty().bindBidirectional(editedSubject.getSiteProperty());
		emailTextField.textProperty().bindBidirectional(editedSubject.getEmailProperty());
	}

	private void bindBiderectionalWithLessonFXModel() {
		locationTextField.textProperty().bindBidirectional(lessonFxModel.getLocationProperty());
		durationTextField.textProperty().bindBidirectional(lessonFxModel.getDurationProperty(),
				new NumberStringConverter());
		typeOfLessonComboBox.valueProperty().bindBidirectional(lessonFxModel.getTypeProperty());
	}

	@FXML
	void addLessonButtonClick(ActionEvent event) {
		final boolean allNeccesaryFieldsAreFilled = dayOfWeekComboBox.getSelectionModel().getSelectedItem() != null
				&& lessonStartTimePicker.getValue() != null && lastLessonDatePicker.getValue() != null
				&& durationTextField.getText() != null;

		if (allNeccesaryFieldsAreFilled) {
			lessonFxModel.setSubject(editedSubject.getSubject());

			LocalDate end = lastLessonDatePicker.getValue();
			LocalDate now = LocalDate.now();

			long countOfWeeks = ChronoUnit.WEEKS.between(now, end);

			DayOfWeek dayOfWeek = dayOfWeekComboBox.getValue();

			LocalTime time = lessonStartTimePicker.getValue();
			LocalDate date = now.with(TemporalAdjusters.next(dayOfWeek));

			Lesson lesson = null;
			for (int i = 0; i < countOfWeeks; i++) {
				lessonFxModel.setDateTime(LocalDateTime.of(date, time));

				lesson = lessonFxModel.getLesson();
				lessonDao.save(lesson);

				date = date.plusWeeks(1);
			}
			if (lesson != null) {
				lessonsModel.add(lesson);
				clearInputs();
			}
		} else {
			showAlert(AlertType.ERROR, "Warning", "Fail!", "Please fill all neccessary fields");
		}
	}

	private void clearInputs() {
		dayOfWeekComboBox.getSelectionModel().clearSelection();
		lessonStartTimePicker.setValue(null);
		lastLessonDatePicker.setValue(null);
		locationTextField.clear();
		durationTextField.clear();
		typeOfLessonComboBox.getSelectionModel().clearSelection();
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
