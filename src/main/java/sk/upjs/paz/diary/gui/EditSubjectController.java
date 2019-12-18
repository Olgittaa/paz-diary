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
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.LessonFXModel;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ILessonDAO;
import sk.upjs.paz.diary.storage.ISubjectDAO;

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
	private JFXTextField siteTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXComboBox<DayOfWeek> dayOfWeekComboBox;

	@FXML
	private JFXTimePicker lessonStartTimePicker;

	@FXML
	private JFXDatePicker lastLessonDateTextField;

	@FXML
	private JFXTextField locationTextField;

	@FXML
	private JFXTextField durationTextField;

	@FXML
	private JFXComboBox<String> typeOfLessonComboBox;

	@FXML
	private JFXListView<Lesson> lessonsListView;

	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.getLessonDao();

	private SubjectFXModel editedSubject;
	private LessonFXModel lessonFxModel;
	private ObservableList<Lesson> lessonsModel;

	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
		lessonFxModel = new LessonFXModel();
		lessonsModel = FXCollections.observableArrayList();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
		lessonFxModel = new LessonFXModel();
		lessonsModel = FXCollections.observableArrayList(lessonDao.getWeekScheduleBySubjectId(editedSubject.getId()));
	}

	@FXML
	void initialize() {
		initComboBoxes();

		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();

		addValidators();

		if (nameTextField.getText() == null || nameTextField.getText().trim().length() == 0) {
			saveSubjectButton.setDisable(true);
			removeSubjectButton.setDisable(true);
		}
		lessonsListView.setItems(lessonsModel);
		lessonStartTimePicker.set24HourView(true);
	}

	private void initComboBoxes() {
		dayOfWeekComboBox.setItems(FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		typeOfLessonComboBox.setItems(FXCollections.observableArrayList("Lecture", "Practice"));
	}

	private void addValidators() {
		durationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
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
		if (dayOfWeekComboBox.getSelectionModel().getSelectedItem() != null && lessonStartTimePicker.getValue() != null
				&& lastLessonDateTextField.getValue() != null && durationTextField.getText() != null) {
			lessonFxModel.setSubject(editedSubject.getSubject());
			
			LocalDate end = lastLessonDateTextField.getValue();
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
				clearInputFields();
			}
		} else {
			showAlert(AlertType.ERROR, "Warning!", "Failed", "Please fill all neccessary fields");
		}
	}

	private void clearInputFields() {
		dayOfWeekComboBox.getSelectionModel().clearSelection();
		lessonStartTimePicker.setValue(null);
		lastLessonDateTextField.setValue(null);
		locationTextField.clear();
		durationTextField.clear();
		typeOfLessonComboBox.getSelectionModel().clearSelection();
	}

	@FXML
	void removeLessonButtonClick(ActionEvent event) {
		Lesson selectedLesson = lessonsListView.getSelectionModel().getSelectedItem();
		lessonsModel.remove(selectedLesson);
		lessonDao.remove(selectedLesson);
	}

	@FXML
	void saveSubjectButtonClick(ActionEvent event) {
		Subject subject = subjectDao.save(editedSubject.getSubject());
		editedSubject.load(subject);
		showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Subject was edited");
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {
		if (subjectDao.getAllSubjects().contains(editedSubject.getSubject())) {
			subjectDao.remove(editedSubject.getSubject());
			showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Subject was deleted");
			closeWindow((Node) event.getSource());			
		} else {
			showAlert(AlertType.ERROR, "Error", "Failed!", "Subject does not exists");
		}
	}
}
