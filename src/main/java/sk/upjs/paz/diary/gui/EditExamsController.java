package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.gui.models.ExamFxModel;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IExamDAO;

public class EditExamsController extends Controller {

	@FXML
	private JFXDatePicker datePicker;

	@FXML
	private JFXTimePicker timePicker;

	@FXML
	private JFXComboBox<Subject> subjectComboBox;

	@FXML
	private JFXTextField locationTextField;

	@FXML
	private JFXButton removeExamButton;

	@FXML
	private JFXButton saveExamButton;

	private IExamDAO examDao = DaoFactory.INSTANCE.getExamDao();

	private ExamFxModel fxmodel;
	private Exam selectedExam;
	private ObservableList<Subject> subjectModel;

	public EditExamsController() {
		fxmodel = new ExamFxModel();
	}

	public EditExamsController(Exam selectedExam) {
		this();
		this.selectedExam = selectedExam;
		fxmodel.loadFromExam(selectedExam);
	}

	@FXML
	void initialize() {
		subjectModel = FXCollections.observableArrayList(DaoFactory.INSTANCE.getSubjectDao().getAllSubjects());
		subjectComboBox.setItems(subjectModel);

		timePicker.set24HourView(true);

		if (selectedExam == null) { // if creating exam
			subjectComboBox.getSelectionModel().selectFirst();
		} else { // if editing exam
			subjectComboBox.getSelectionModel().select(selectedExam.getSubject());
		}

		bindBidirectionalWithExamFxModel();
	}

	private void bindBidirectionalWithExamFxModel() {
		datePicker.valueProperty().bindBidirectional(fxmodel.getDateProperty());
		timePicker.valueProperty().bindBidirectional(fxmodel.getTimeProperty());
		locationTextField.textProperty().bindBidirectional(fxmodel.getLocationProperty());
		subjectComboBox.valueProperty().bindBidirectional(fxmodel.getSubjectProperty());
	}

	@FXML
	void removeExam(ActionEvent event) {
		final boolean allFieldsAreFilled = datePicker.getValue() != null && timePicker.getValue() != null
				&& locationTextField.getText() != null && subjectComboBox.getSelectionModel().getSelectedItem() != null;
		if (allFieldsAreFilled) {
			if (examDao.remove(fxmodel.getExam()) != 0) {
				closeWindow(event);
				showAlert(AlertType.INFORMATION, "Information", "Success!", "Exam was removed");
			} else {
				showAlert(AlertType.INFORMATION, "Information", "Failed!", "No such exam");
			}
		} else {
			showAlert(AlertType.ERROR, "Error", "Failed!", "Fill all necessary fields");
		}
	}

	@FXML
	void saveExam(ActionEvent event) {
		final boolean allFieldsAreFilled = datePicker.getValue() != null && timePicker.getValue() != null
				&& subjectComboBox.getSelectionModel().getSelectedItem() != null;
		if (allFieldsAreFilled) {
			examDao.save(fxmodel.getExam());
			closeWindow(event);
			showAlert(AlertType.INFORMATION, "Information", "Success!", "Exam was added");
		} else {
			showAlert(AlertType.ERROR, "Error", "Failed!", "Fill all necessary fields");
		}
	}
}
