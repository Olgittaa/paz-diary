package sk.upjs.paz.diary.perzistent;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;

public class LessonFXModel {
	private Long id;
	private ObjectProperty<LocalDateTime> dateProperty = new SimpleObjectProperty<>();
	private StringProperty locationProperty = new SimpleStringProperty();
	private IntegerProperty durationProperty = new SimpleIntegerProperty();
	private StringProperty typeProperty = new SimpleStringProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateProperty.get();
	}

	public void setDate(LocalDateTime dateTime) {
		dateProperty.set(dateTime);
	}

	public String getLocation() {
		return locationProperty.get();
	}

	public void setLocation(String location) {
		locationProperty.set(location);
	}

	public Integer getDuration() {
		return durationProperty.get();
	}

	public void setDuration(Integer duration) {
		durationProperty.set(duration);
	}

	public String getType() {
		return typeProperty.get();
	}

	public void setType(String type) {
		typeProperty.set(type);
	}

	public Subject getSubject() {
		return subjectProperty.get();
	}

	public void setSubject(Subject subject) {
		subjectProperty.set(subject);
	}

	public ObjectProperty<LocalDateTime> getDateProperty() {
		return dateProperty;
	}

	public void setDateProperty(ObjectProperty<LocalDateTime> dateProperty) {
		this.dateProperty = dateProperty;
	}

	public StringProperty getLocationProperty() {
		return locationProperty;
	}

	public void setLocationProperty(StringProperty locationProperty) {
		this.locationProperty = locationProperty;
	}

	public IntegerProperty getDurationProperty() {
		return durationProperty;
	}

	public void setDurationProperty(IntegerProperty durationProperty) {
		this.durationProperty = durationProperty;
	}

	public StringProperty getTypeProperty() {
		return typeProperty;
	}

	public void setTypeProperty(StringProperty typeProperty) {
		this.typeProperty = typeProperty;
	}

	public ObjectProperty<Subject> getSubjectProperty() {
		return subjectProperty;
	}

	public void setSubjectProperty(ObjectProperty<Subject> subjectProperty) {
		this.subjectProperty = subjectProperty;
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
}
