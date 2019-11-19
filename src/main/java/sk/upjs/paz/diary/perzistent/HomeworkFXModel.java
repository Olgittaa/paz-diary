package sk.upjs.paz.diary.perzistent;

import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.storage.DaoFactory;

public class HomeworkFXModel {
	private LongProperty idProperty = new SimpleLongProperty();
	private ObjectProperty<LocalDateTime> deadlineProperty = new SimpleObjectProperty<>();
	private StringProperty descriptionProperty = new SimpleStringProperty();
	private BooleanProperty statusProperty = new SimpleBooleanProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public Homework getHomework(Long idSubject) {
		Homework hw = new Homework();
		hw.setDeadline(getDeadline());
		hw.setDescription(getDescription());
		hw.setStatus(getStatus());
		hw.setSubject(DaoFactory.getSubjectDao().getSubjectById(idSubject));
		return hw;
	}

	public LocalDateTime getDeadline() {
		return deadlineProperty.get();
	}

	public ObjectProperty<LocalDateTime> deadlineProperty() {
		if (deadlineProperty == null) {
			deadlineProperty = new SimpleObjectProperty<>();
		}
		return deadlineProperty;
	}

	public void setDeadline(ObjectProperty<LocalDateTime> deadline) {
		this.deadlineProperty = deadline;
	}

	public String getDescription() {
		return descriptionProperty.get();
	}

	public StringProperty descriptionProperty() {
		return descriptionProperty;
	}

	public void setDescription(String description) {
		this.descriptionProperty.set(description);
	}

	public Boolean getStatus() {
		return statusProperty.get();
	}

	public BooleanProperty statusProperty() {
		return statusProperty;
	}

	public void statusProperty(Boolean status) {
		this.statusProperty.set(status);
	}

}
