package sk.upjs.paz.diary.gui;

import java.time.DayOfWeek;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.LessonFXModel;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ISubjectDAO;

public class EditSubjectController extends Controller {
	@FXML
	private JFXButton cancelSubjectButton;

	@FXML
	private JFXButton saveSubjectButton;

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

	private SubjectFXModel editedSubject;
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();

	private LessonFXModel practiceFxModel;
	private LessonFXModel lectureFxModel;

	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
	}

	@FXML
	void initialize() {
		bindBiderectionalWithSubjectFXModel();
		bindBiderectionalWithLessonFXModel();

		nameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null || newValue.trim().isEmpty())
					saveSubjectButton.setDisable(true);
				else
					saveSubjectButton.setDisable(false);
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
	//	lectureDurationTextField.textProperty().bindBidirectional(lectureFxModel.getDurationProperty());
		
		
		practiceAudienceTextField.textProperty().bindBidirectional(practiceFxModel.getLocationProperty());
	//	practiceAudienceTextField.textProperty().bindBidirectional(practiceFxModel.getDurationProperty());
	
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

	}

	@FXML
	void addPractice(ActionEvent event) {

	}

	@FXML
	void removeLecture(ActionEvent event) {

	}

	@FXML
	void removePractice(ActionEvent event) {

	}
}
