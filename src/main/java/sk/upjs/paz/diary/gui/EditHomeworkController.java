package sk.upjs.paz.diary.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.HomeworkFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import tornadofx.control.DateTimePicker;

public class EditHomeworkController {

	@FXML
	private Button saveHomeworkButton;

	@FXML
	private TextArea descriptionTextArea;
	
	@FXML
	private DatePicker deadlineDatePicker;

	@FXML
	private ComboBox<Subject> subjectComboBox;

	private HomeworkFXModel fxmodel;

	private ObservableList<Subject> subjectsModel;

	public EditHomeworkController() {
		fxmodel = new HomeworkFXModel();
	}

	@FXML
	void initialize() {
		subjectsModel = FXCollections.observableArrayList(DaoFactory.getSubjectDao().getAllSubjects());
		subjectComboBox.setItems(subjectsModel);

		// deadlineDatePicker.valueProperty().bindBidirectional(fxmodel.deadlineProperty());
		descriptionTextArea.textProperty().bindBidirectional(fxmodel.descriptionProperty());

		descriptionTextArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null && newValue.trim().length() > 0) {
					saveHomeworkButton.setDisable(false);
				} else {
					saveHomeworkButton.setDisable(true);
				}
			}
		});
		
	}

	@FXML
	void saveHomework(ActionEvent event) {
		DaoFactory.getHomeworkDao()
				.save(fxmodel.getHomework(subjectComboBox.getSelectionModel().getSelectedItem().getId()));
		saveHomeworkButton.getScene().getWindow().hide();
	}

}
