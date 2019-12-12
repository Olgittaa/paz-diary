package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;

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
import sk.upjs.paz.diary.storage.ISubjectDAO;

//FIXME бай дифолт устанавливаются значения 0
public class EditSubjectController extends Controller {
	@FXML
	private JFXButton removeSubjectButton;

	@FXML
	private JFXButton saveSubjectButton;

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

	private SubjectFXModel editedSubject;
	private LessonFXModel lessonFxModel;
	private ObservableList<Lesson> lessonsModel;
	private ObservableList<Subject> subjectsModel;

	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
		lessonFxModel = new LessonFXModel();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
		lessonFxModel = new LessonFXModel();
	}

	@FXML
	void initialize() {
		initComboBoxes();

		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();

		addValidators();
	}

	private void initComboBoxes() {
		dayOfWeekComboBox.setItems(FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		typeOfLessonComboBox.setItems(FXCollections.observableArrayList("Lecture", "Practice"));
	}

	private void addValidators() {
		// we wanna see only numbers from 0 to 300
		durationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;
			if (newValue.matches("\\D*")) {
				durationTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) > 300) {
				durationTextField.setText(newValue.substring(0, newValue.length() - 1));
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

	}

	@FXML
	void removeLessonButtonClick(ActionEvent event) {
		lessonsListView.getItems().remove(lessonsListView.getSelectionModel().getSelectedIndex());
	}

	@FXML
	void saveSubjectButtonClick(ActionEvent event) {
		subjectDao.save(editedSubject.getSubject());
		showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Subject was edited");
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {
		subjectDao.remove(editedSubject.getSubject());
		showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Subject was deleted");
		closeWindow((Node) event.getSource());
	}
	
	private void refreshSubjectsListView() {
		
	}
}
