package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	private JFXButton cancelSubjectButton;

	@FXML
	private JFXButton saveSubjectButton;

	@FXML
	private JFXButton removePracticeButton;

	@FXML
	private JFXButton removeLectureButton;

	@FXML
	private JFXButton addPracticeButton;

	@FXML
	private JFXButton addLectureButton;

	@FXML
	private JFXTextField siteTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXComboBox<DayOfWeek> dayOfWeekLectureComboBox;

	@FXML
	private JFXComboBox<DayOfWeek> dayOfWeekPracticeComboBox;

	@FXML
	private JFXTimePicker timeLectureTimePicker;

	@FXML
	private JFXTimePicker timePracticeTimePicker;

	@FXML
	private JFXTextField quantityLectureTextField;

	@FXML
	private JFXTextField quantityPracticeTextField;

	@FXML
	private JFXListView<Lesson> lecturesListView;

	@FXML
	private JFXListView<Lesson> practicesListView;

	@FXML
	private JFXTextField lectureDurationTextField;

	@FXML
	private JFXTextField practiceDurationTextField;

	@FXML
	private JFXTextField lectureAudienceTextField;

	@FXML
	private JFXTextField practiceAudienceTextField;

	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();

	private SubjectFXModel editedSubject;
	private LessonFXModel practiceFxModel;
	private LessonFXModel lectureFxModel;

	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
		practiceFxModel = new LessonFXModel();
		lectureFxModel = new LessonFXModel();
	}

	public EditSubjectController(Subject subject) {
		editedSubject.load(subject);
	}

	@FXML
	void initialize() {
		initComboBoxes();

		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();

		addValidators();

		nameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null && newValue.trim().length() > 0) {
					saveSubjectButton.setDisable(false);
					addLectureButton.setDisable(false);
					addPracticeButton.setDisable(false);
					removeLectureButton.setDisable(false);
					removePracticeButton.setDisable(false);
				} else {
					saveSubjectButton.setDisable(true);
					addLectureButton.setDisable(true);
					addPracticeButton.setDisable(true);
					removeLectureButton.setDisable(true);
					removePracticeButton.setDisable(true);					
				}
			}
		});
	}

	private void initComboBoxes() {
		ObservableList<DayOfWeek> observableArrayList = FXCollections.observableArrayList(DayOfWeek.MONDAY,
				DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
		dayOfWeekLectureComboBox.setItems(observableArrayList);
		dayOfWeekPracticeComboBox.setItems(observableArrayList);
	}

	private void addValidators() {
		// validators(ChangeListeners) for durations textfield

		// we wanna see only numbers from 0 to 300
		lectureDurationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;
			if (!newValue.matches("\\d*")) {
				lectureDurationTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) > 300) {
				lectureDurationTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});
		practiceDurationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;
			if (!newValue.matches("\\d*")) {
				practiceDurationTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) > 300) {
				practiceDurationTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});

		// we wanna see only numbers from 0 to 30
		quantityLectureTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;
			if (!newValue.matches("\\d*")) {
				quantityLectureTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) < 1) {
				quantityLectureTextField.setText("1");
			} else if (Integer.parseInt(newValue) > 30) {
				quantityLectureTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});
		quantityPracticeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty())
				return;
			if (!newValue.matches("\\d*")) {
				quantityPracticeTextField.setText(newValue.replaceAll("\\D", ""));
			} else if (Integer.parseInt(newValue) < 1) {
				quantityPracticeTextField.setText("1");
			} else if (Integer.parseInt(newValue) > 30) {
				quantityPracticeTextField.setText(newValue.substring(0, newValue.length() - 1));
			}
		});
	}

	private void bindBiderectionalWithSubjectFXModel() {
		nameTextField.textProperty().bindBidirectional(editedSubject.getNameProperty());
		siteTextField.textProperty().bindBidirectional(editedSubject.getSiteProperty());
		emailTextField.textProperty().bindBidirectional(editedSubject.getEmailProperty());
	}

	private void bindBiderectionalWithLessonFXModel() {
		lectureAudienceTextField.textProperty().bindBidirectional(lectureFxModel.getLocationProperty());
		lectureDurationTextField.textProperty().bindBidirectional(lectureFxModel.getDurationProperty(),
				new NumberStringConverter());

		practiceAudienceTextField.textProperty().bindBidirectional(practiceFxModel.getLocationProperty());
		practiceAudienceTextField.textProperty().bindBidirectional(practiceFxModel.getDurationProperty(),
				new NumberStringConverter());

	}

	@FXML
	void saveSubjectButtonClick(ActionEvent event) {
		Subject subject = editedSubject.getSubject();
		subjectDao.save(subject);
		closeWindow(saveSubjectButton);
	}

	@FXML
	void cancelSubjectButtonClick(ActionEvent event) {
		closeWindow(cancelSubjectButton);
	}

	@FXML
	void addLecture(ActionEvent event) {
		// for (int i = 0; i < Integer.parseInt(quantityLectureTextField.getText());
		// i++) {
		//lectureFxModel.setDa
		//Lesson lesson = lectureFxModel.getLesson();
		//lesson.setType("Lecture");
		//lesson.set
		//lecturesListView.getItems().add(lesson);
		// обновить фх модел
		// добавить в лист
		// }
	}

	@FXML
	void addPractice(ActionEvent event) {
		for (int i = 0; i < Integer.parseInt(quantityPracticeTextField.getText()); i++) {
			Lesson lesson = lectureFxModel.getLesson();
			lesson.setType("Practice");
			practicesListView.getItems().add(lesson);

		}
	}

	@FXML
	void removeLecture(ActionEvent event) {
		lecturesListView.getItems().remove(lecturesListView.getSelectionModel().getSelectedIndex());
	}

	@FXML
	void removePractice(ActionEvent event) {
		practicesListView.getItems().remove(practicesListView.getSelectionModel().getSelectedIndex());
	}
}
