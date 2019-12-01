package sk.upjs.paz.diary.gui;

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
import sk.upjs.paz.diary.perzistent.SubjectFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ISubjectDAO;

public class EditSubjectController extends Controller{
	@FXML
    private JFXButton cancelSubjectButton;

    @FXML
    private JFXButton addSubjectButton;

    @FXML
    private JFXTextField siteTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXComboBox<String> dayOfWeekLectureComboBox;

    @FXML
    private JFXComboBox<String> dayOfWeekPracticeComboBox;

    @FXML
    private JFXTimePicker timeLectureTimePicker;

    @FXML
    private JFXTimePicker timePracticeTimePicker;

    @FXML
    private JFXTextField quantityLectureTextField;

    @FXML
    private JFXTextField QuantityPracticeTextField;

    @FXML
    private JFXListView<Lesson> lecturesListView;

    @FXML
    private JFXListView<Lesson> practicesListView;
    
	private SubjectFXModel editedSubject;
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao(); 
	
	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
	}

	@FXML
	void initialize() {
		nameTextField.textProperty().bindBidirectional(editedSubject.getNameProperty());
		siteTextField.textProperty().bindBidirectional(editedSubject.getSiteProperty());
		emailTextField.textProperty().bindBidirectional(editedSubject.getEmailProperty());

		// FIXME
		nameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null || newValue.trim().isEmpty())
					addSubjectButton.setDisable(true);
				else
					addSubjectButton.setDisable(false);
			}
		});
	}

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		Subject subject = editedSubject.getSubject();
		subjectDao.save(subject);
		
		addSubjectButton.getScene().getWindow().hide();
	}

	@FXML
	void cancelSubjectButtonClick(ActionEvent event) {
		addSubjectButton.getScene().getWindow().hide();
	}

}
