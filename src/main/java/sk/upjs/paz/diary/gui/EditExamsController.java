package sk.upjs.paz.diary.gui;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

		if (selectedExam == null) { // if creating exam
			subjectComboBox.getSelectionModel().selectFirst();
		} else { // if editing exam
			subjectComboBox.getSelectionModel().select(selectedExam.getSubject());
		}

		datePicker.valueProperty().bindBidirectional(fxmodel.getDateProperty());
		timePicker.valueProperty().bindBidirectional(fxmodel.getTimeProperty());
		locationTextField.textProperty().bindBidirectional(fxmodel.getLocationProperty());

		datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (timePicker.valueProperty() != null && datePicker.valueProperty() != null) {
					saveExamButton.setDisable(false);
				} else {
					saveExamButton.setDisable(true);
				}
			}
		});
	}

	@FXML
	void removeExam(ActionEvent event) {
		Exam exam = fxmodel.getExam(fxmodel.getSubject().getId());
		examDao.remove(exam);
		closeWindow(event);
		showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Exam was removed");
	}

	@FXML
	void saveExam(ActionEvent event) {
		Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
		Exam exam = fxmodel.getExam(selectedSubject.getId());
		examDao.save(exam);
		closeWindow(event);
		showAlert(AlertType.INFORMATION, "Information", "Succesfully!", "Exam was added");
	}
}
