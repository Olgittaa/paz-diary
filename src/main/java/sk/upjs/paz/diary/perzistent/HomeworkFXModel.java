package sk.upjs.paz.diary.perzistent;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Homework;

public class HomeworkFXModel {

	private ObjectProperty<LocalDateTime> deadline;
	private StringProperty description = new SimpleStringProperty();
	private BooleanProperty status = new SimpleBooleanProperty();

	public Homework getHomework(Long idSubject) {
		Homework hw = new Homework();
		hw.setDeadline(getDeadline());
		hw.setDescription(getDescription());
		hw.setStatus(getStatus());
		hw.setIdSubject(idSubject);
		return hw;
	}

	public LocalDateTime getDeadline() {
		return deadline.get();
	}

	public ObjectProperty<LocalDateTime> deadlineProperty() {
		if (deadline == null) {
			deadline = new SimpleObjectProperty<>();
		}
		return deadline;
	}

	public void setDeadline(ObjectProperty<LocalDateTime> deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public Boolean getStatus() {
		return status.get();
	}

	public BooleanProperty statusProperty() {
		return status;
	}

	public void statusProperty(Boolean status) {
		this.status.set(status);
	}

}
