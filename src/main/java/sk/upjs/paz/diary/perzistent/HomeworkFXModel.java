package sk.upjs.paz.diary.perzistent;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomeworkFXModel {
	
	private LocalDate deadline;
	private StringProperty description = new SimpleStringProperty();
	private BooleanProperty status = new SimpleBooleanProperty();
	
	public LocalDate getDeadline() {
		return deadline;
	}
	
	public void setDeadline(LocalDate deadline) {
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
