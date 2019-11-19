package sk.upjs.paz.diary.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ILessonDAO;
import sk.upjs.paz.diary.storage.ISubjectDAO;

public class ScheduleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@FXML
	private JFXListView<Lesson> mondayListView;

	@FXML
	private JFXListView<Lesson> wednesdayListView;

	@FXML
	private JFXListView<Lesson> tuesdayListView;

	@FXML
	private JFXListView<Lesson> thursdayListView;

	@FXML
	private JFXListView<Lesson> fridayListView;

	@FXML
	private JFXListView<Subject> subjectListView;

	@FXML
	private Label monthLabel;

	@FXML
	private JFXButton removeSubjectButton;

	@FXML
	private JFXButton addSubjectButton;

	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao();
	private ILessonDAO lessonDao = DaoFactory.getLessonDao();
	
	private SubjectFXModel subjectFXModel = new SubjectFXModel();
	private Subject currentSubject = new Subject();

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		String fxmlFileName = "editSubject.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			fxmlLoader.setController(new EditSubjectController(currentSubject));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setTitle("Edit subject");
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait(); // код за loadWindow не будет выполняться пока окно открыто
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}

		refreshListView(subjectFXModel.getSubject());
	}

	/**
	 * 
	 * @param newSubject - added/edited subject
	 */
	private void refreshListView(Subject newSubject) {
		List<Subject> currentSubjects = subjectDao.getAllSubjects();

		for (Subject subject : currentSubjects) {
			if (subject.equals(newSubject)) {
				subject = newSubject;
				currentSubjects.add(subject);
				break;
			}
		}
		subjectListView.setItems(FXCollections.observableArrayList(currentSubjects));
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {
		
	}

	@FXML
	void initialize() {
		setItemsToSubjectsListView();
		setItemsToLessonsListViews();
	}

	private void setItemsToSubjectsListView() {
		List<Subject> list = subjectDao.getAllSubjects();
		Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		subjectListView.setItems(FXCollections.observableArrayList(list));
	}
	
	private void setItemsToLessonsListViews() {
		mondayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(2)));
		tuesdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(3)));
		wednesdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(4)));
		thursdayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(5)));
		fridayListView.setItems(FXCollections.observableArrayList(lessonDao.getDaySchedule(6)));
	}
}
