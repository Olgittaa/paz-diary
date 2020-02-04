package sk.upjs.paz.diary.gui.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;

public class HomeworkFxModel {

	private Long id;
	private StringProperty descriptionProperty = new SimpleStringProperty();
	private BooleanProperty statusProperty = new SimpleBooleanProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
	private ObjectProperty<LocalTime> timeProperty = new SimpleObjectProperty<>();

	public void setSubject(Subject subject) {
		subjectProperty.set(subject);
	}

	public LocalDate getDate() {
		return dateProperty.get();
	}

	public ObjectProperty<LocalDate> getDateProperty() {
		return dateProperty;
	}

	public void setDate(LocalDate date) {
		dateProperty.set(date);
	}

	public LocalTime getTime() {
		return timeProperty.get();
	}

	public StringProperty getDescriptionProperty() {
		return descriptionProperty;
	}

	public void setDescriptionProperty(StringProperty descriptionProperty) {
		this.descriptionProperty = descriptionProperty;
	}

	public BooleanProperty getStatusProperty() {
		return statusProperty;
	}

	public void setStatusProperty(BooleanProperty statusProperty) {
		this.statusProperty = statusProperty;
	}

	public ObjectProperty<Subject> getSubjectProperty() {
		return subjectProperty;
	}

	public void setSubjectProperty(ObjectProperty<Subject> subjectProperty) {
		this.subjectProperty = subjectProperty;
	}

	public ObjectProperty<LocalTime> getTimeProperty() {
		return timeProperty;
	}

	public void setTimeProperty(ObjectProperty<LocalTime> timeProperty) {
		this.timeProperty = timeProperty;
	}

	public void setDateProperty(ObjectProperty<LocalDate> dateProperty) {
		this.dateProperty = dateProperty;
	}

	public void setTime(LocalTime time) {
		timeProperty.set(time);
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

	public void setStatus(Boolean status) {
		this.statusProperty.set(status);
	}

	public Subject getSubject() {
		return subjectProperty.get();
	}

	public Homework getHomework() {
		Homework hw = new Homework();
		hw.setId(id);
		hw.setDeadline(LocalDateTime.of(getDate(), getTime()));
		hw.setDescription(getDescription());
		hw.setStatus(getStatus());
		hw.setSubject(getSubject());
		return hw;
	}

	public void load(Homework homework) {
		setDate(homework.getDeadline().toLocalDate());
		setTime(homework.getDeadline().toLocalTime());
		setDescription(homework.getDescription());
		setStatus(homework.getStatus());
		id = homework.getId();
		setSubject(homework.getSubject());
	}

	@Override
	public String toString() {
		return "HomeworkFXModel [descriptionProperty=" + descriptionProperty + ", statusProperty=" + statusProperty
				+ ", subjectProperty=" + subjectProperty + ", dateProperty=" + dateProperty + ", timeProperty="
				+ timeProperty + "]";
	}

}
