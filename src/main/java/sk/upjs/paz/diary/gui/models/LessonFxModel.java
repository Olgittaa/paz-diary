package sk.upjs.paz.diary.gui.models;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.entity.Subject;

public class LessonFxModel {
	private Long id;
	private ObjectProperty<LocalDateTime> dateTimeProperty = new SimpleObjectProperty<>();
	private StringProperty locationProperty = new SimpleStringProperty();
	private StringProperty durationProperty = new SimpleStringProperty();
	private ObjectProperty<LessonType> typeProperty = new SimpleObjectProperty<>();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public LessonFxModel() {
	}

	public LessonFxModel(Lesson lesson) {
		load(lesson);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTimeProperty.get();
	}

	public void setDateTime(LocalDateTime dateTime) {
		dateTimeProperty.set(dateTime);
	}

	public String getLocation() {
		return locationProperty.get();
	}

	public void setLocation(String location) {
		locationProperty.set(location);
	}

	public Integer getDuration() {
		return Integer.parseInt(durationProperty.get());
	}

	public void setDuration(Integer duration) {
		durationProperty.set(String.valueOf(duration));
	}

	public LessonType getType() {
		return typeProperty.get();
	}

	public void setType(LessonType type) {
		typeProperty.set(type);
	}

	public Subject getSubject() {
		return subjectProperty.get();
	}

	public void setSubject(Subject subject) {
		subjectProperty.set(subject);
	}

	public ObjectProperty<LocalDateTime> getDateProperty() {
		return dateTimeProperty;
	}

	public void setDateTimeProperty(ObjectProperty<LocalDateTime> dateTimeProperty) {
		this.dateTimeProperty = dateTimeProperty;
	}

	public StringProperty getLocationProperty() {
		return locationProperty;
	}

	public void setLocationProperty(StringProperty locationProperty) {
		this.locationProperty = locationProperty;
	}

	public StringProperty getDurationProperty() {
		return durationProperty;
	}

	public void setDurationProperty(StringProperty durationProperty) {
		this.durationProperty = durationProperty;
	}

	public ObjectProperty<LessonType> getTypeProperty() {
		return typeProperty;
	}

	public void setTypeProperty(ObjectProperty<LessonType> typeProperty) {
		this.typeProperty = typeProperty;
	}

	public ObjectProperty<Subject> getSubjectProperty() {
		return subjectProperty;
	}

	public void setSubjectProperty(ObjectProperty<Subject> subjectProperty) {
		this.subjectProperty = subjectProperty;
	}

	public final void load(Lesson lesson) {
		setId(lesson.getId());
		setDateTime(lesson.getDateTime());
		setLocation(lesson.getLocation());
		setDuration(lesson.getDuration());
		setType(lesson.getType());
		setSubject(lesson.getSubject());
	}

	public Lesson getLesson() {
		Lesson lesson = new Lesson();
		lesson.setId(getId());
		lesson.setDateTime(getDateTime());
		lesson.setLocation(getLocation());
		lesson.setDuration(getDuration());
		lesson.setType(getType());
		lesson.setSubject(getSubject());
		return lesson;
	}

	@Override
	public String toString() {
		return "LessonFXModel [id=" + id + ", dateTimeProperty=" + dateTimeProperty + ", locationProperty="
				+ locationProperty + ", durationProperty=" + durationProperty + ", typeProperty=" + typeProperty
				+ ", subjectProperty=" + subjectProperty + "]";
	}

}
