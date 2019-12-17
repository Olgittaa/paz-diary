package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.gui.models.HomeworkFxModel;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IHomeworkDAO;

public class EditHomeworkController extends Controller {

	@FXML
	private JFXButton saveHomeworkButton;

	@FXML
	private JFXTextArea descriptionTextArea;

	@FXML
	private JFXDatePicker deadlineDatePicker;
	@FXML
	private JFXTimePicker deadlineTimePicker;

	@FXML
	private JFXComboBox<Subject> subjectComboBox;

	@FXML
	private JFXButton removeHomeworkButton;

	private IHomeworkDAO homeworkDao = DaoFactory.INSTANCE.getHomeworkDao();

	/**
	 * Adapter between jfoenix components and database
	 */
	private HomeworkFxModel fxmodel;

	/**
	 * Homework we are going to edit
	 */
	private Homework selectedHomework;

	private ObservableList<Subject> subjectsModel;
	private boolean wereChanges;

	public boolean wereChanges() {
		return wereChanges;
	}

	public EditHomeworkController() {
		fxmodel = new HomeworkFxModel();
	}

	public EditHomeworkController(Homework selectedHomework) {
		this();
		this.selectedHomework = selectedHomework;
		fxmodel.load(selectedHomework);
	}

	@FXML
	void initialize() {
		subjectsModel = FXCollections.observableArrayList(DaoFactory.INSTANCE.getSubjectDao().getAllSubjects());
		subjectComboBox.setItems(subjectsModel);

		if (selectedHomework == null) { // if creating homework
			subjectComboBox.getSelectionModel().selectFirst();
		} else { // if editing homework
			subjectComboBox.getSelectionModel().select(selectedHomework.getSubject());
		}
		bindBidirectionalWithhomeworkFxModel();
		descriptionTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.trim().length() != 0 && oldValue != newValue) {
				saveHomeworkButton.setDisable(false);
			} else {
				saveHomeworkButton.setDisable(true);
			}
		});
	}

	private void bindBidirectionalWithhomeworkFxModel() {
		deadlineDatePicker.valueProperty().bindBidirectional(fxmodel.getDateProperty());
		deadlineTimePicker.valueProperty().bindBidirectional(fxmodel.getTimeProperty());
		descriptionTextArea.textProperty().bindBidirectional(fxmodel.descriptionProperty());
	}

	@FXML
	void saveHomework(ActionEvent event) {
		Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
		Homework homework = fxmodel.getHomework(selectedSubject.getId());
		homeworkDao.save(homework);
		wereChanges = true;
		closeWindow(event);
		showAlert(AlertType.INFORMATION, "Information", "Success!", "Homework was added");
	}

	@FXML
	void removeHomework(ActionEvent event) {
		Homework homework = fxmodel.getHomework(fxmodel.getSubject().getId());
		homeworkDao.remove(homework);
		wereChanges = true;
		closeWindow(event);
		showAlert(AlertType.INFORMATION, "Information", "Success!", "Homework was removed");
	}
}
