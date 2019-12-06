package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.HomeworkFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.IHomeworkDAO;

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

	private IHomeworkDAO homeworkDao = DaoFactory.getHomeworkDao();

	/**
	 * Adapter between jfoenix components and database
	 */
	private HomeworkFXModel fxmodel;

	/**
	 * Homework we are going to edit
	 */
	private Homework selectedHomework; // помогает понять мы редактируем или создаем новые хв(см конструкторы)

	private ObservableList<Subject> subjectsModel;
	private boolean wereChanges;

	public boolean wereChanges() {
		return wereChanges;
	}

	public EditHomeworkController() {
		fxmodel = new HomeworkFXModel();
	}

	public EditHomeworkController(Homework selectedHomework) {
		this();
		this.selectedHomework = selectedHomework;
		fxmodel.loadFromHomework(selectedHomework);
	}

	@FXML
	void initialize() {
		subjectsModel = FXCollections.observableArrayList(DaoFactory.getSubjectDao().getAllSubjects());
		subjectComboBox.setItems(subjectsModel);

		if (selectedHomework == null) { // if creating homework
			subjectComboBox.getSelectionModel().selectFirst();
		} else { // if editing homework
			subjectComboBox.getSelectionModel().select(selectedHomework.getSubject());
		}

		deadlineDatePicker.valueProperty().bindBidirectional(fxmodel.getDateProperty());
		deadlineTimePicker.valueProperty().bindBidirectional(fxmodel.getTimeProperty());
		descriptionTextArea.textProperty().bindBidirectional(fxmodel.descriptionProperty());

		descriptionTextArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (oldValue == null || oldValue.trim().length() == 0) {// FIXME doesn't work
					saveHomeworkButton.setDisable(false);
				} else {
					saveHomeworkButton.setDisable(true);
				}
			}
		});
	}

	@FXML
	void saveHomework(ActionEvent event) {
		Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
		Homework homework = fxmodel.getHomework(selectedSubject.getId());
		homeworkDao.save(homework);
		wereChanges = true;
		closeWindow(saveHomeworkButton);
	}

	@FXML
	void removeHomework(ActionEvent event) {
		Homework homework = fxmodel.getHomework(fxmodel.getSubject().getId());
		homeworkDao.remove(homework);
		wereChanges = true;
		closeWindow(removeHomeworkButton);
	}
}
