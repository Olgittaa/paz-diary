package sk.upjs.paz.diary.gui.adapters;

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
import sk.upjs.paz.diary.persistence.DaoFactory;

public class HomeworkFxModel {
	private Long id;
	private StringProperty descriptionProperty = new SimpleStringProperty();
	private BooleanProperty statusProperty = new SimpleBooleanProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
	private ObjectProperty<LocalTime> timeProperty = new SimpleObjectProperty<>();

	/**
	 * Casts HomeworkFXModel to Homework
	 * 
	 * @param idSubject - homeworks's subject's id
	 * @return homework
	 */
	public Homework getHomework(Long idSubject) {
		Homework hw = new Homework();
		hw.setDeadline(LocalDateTime.of(getDate(), getTime()));
		hw.setDescription(getDescription());
		hw.setStatus(getStatus());
		hw.setSubject(DaoFactory.getSubjectDao().getSubjectById(idSubject));
		hw.setId(id);
		return hw;
	}

	/**
	 * Casts Homework to HomeworkFXModel
	 * 
	 * @param homework - homework to cast
	 * @return homewworkFXModel
	 */
	public void load(Homework homework) {
		setDate(homework.getDeadline().toLocalDate());
		setTime(homework.getDeadline().toLocalTime());
		setDescription(homework.getDescription());
		id = homework.getId();
		setStatus(homework.getStatus());
		setSubject(homework.getSubject());
	}
	
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

	@Override
	public String toString() {
		return "HomeworkFXModel [id=" + id + ", descriptionProperty=" + descriptionProperty + ", statusProperty="
				+ statusProperty + ", subjectProperty=" + subjectProperty + ", dateProperty=" + dateProperty
				+ ", timeProperty=" + timeProperty + "]";
	}
	
}
