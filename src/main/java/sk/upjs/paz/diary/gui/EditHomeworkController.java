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

		deadlineTimePicker.set24HourView(true);

		if (selectedHomework == null) { // if creating homework
			subjectComboBox.getSelectionModel().selectFirst();
		} else { // if editing homework
			subjectComboBox.getSelectionModel().select(selectedHomework.getSubject());
		}
		bindBidirectionalWithhomeworkFxModel();

	}

	private void bindBidirectionalWithhomeworkFxModel() {
		deadlineDatePicker.valueProperty().bindBidirectional(fxmodel.getDateProperty());
		deadlineTimePicker.valueProperty().bindBidirectional(fxmodel.getTimeProperty());
		subjectComboBox.valueProperty().bindBidirectional(fxmodel.getSubjectProperty());
		descriptionTextArea.textProperty().bindBidirectional(fxmodel.descriptionProperty());
	}

	@FXML
	void saveHomework(ActionEvent event) {
		final boolean allFieldsAreFilled = deadlineDatePicker.getValue() != null
				&& deadlineTimePicker.getValue() != null && descriptionTextArea.getText() != null
				&& subjectComboBox.getSelectionModel().getSelectedItem() != null;
		if (allFieldsAreFilled) {
			homeworkDao.save(fxmodel.getHomework());
			closeWindow(event);
			showAlert(AlertType.INFORMATION, "Information", "Success!", "Homework was added");
		} else {
			showAlert(AlertType.ERROR, "Error", "Failed!", "Fill all necessary fields");
		}
	}

	@FXML
	void removeHomework(ActionEvent event) {
		final boolean allFieldsAreFilled = deadlineDatePicker.getValue() != null
				&& deadlineTimePicker.getValue() != null && descriptionTextArea.getText() != null
				&& subjectComboBox.getSelectionModel().getSelectedItem() != null;
		if (allFieldsAreFilled) {
			if (homeworkDao.remove(fxmodel.getHomework()) != 0) {
				closeWindow(event);
				showAlert(AlertType.INFORMATION, "Information", "Success!", "Homework was removed");
			} else {
				showAlert(AlertType.INFORMATION, "Information", "Failed!", "No such homework");
			}
		} else {
			showAlert(AlertType.ERROR, "Error", "Failed!", "Fill all necessary fields");
		}
	}
}
