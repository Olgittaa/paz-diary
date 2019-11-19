package sk.upjs.paz.diary.perzistent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Subject;

public class ExamFXModel {
	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
	private ObjectProperty<LocalTime> timeProperty = new SimpleObjectProperty<>();
	private StringProperty locationProperty = new SimpleStringProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public ExamFXModel(LocalDate date, LocalTime time, String location, Subject subject) {
		setDate(date);
		setTime(time);
		setLocation(location);
		setSubject(subject);
	}

	public LocalDate getDate() {
		return dateProperty.get();
	}

	public void setDate(LocalDate date) {
		dateProperty.set(date);
	}

	public LocalTime getTime() {
		return timeProperty.get();
	}

	public void setTime(LocalTime time) {
		timeProperty.set(time);
	}

	public String getLocation() {
		return locationProperty.get();
	}

	public void setLocation(String location) {
		locationProperty.set(location);
	}

	public Subject getSubject() {
		return subjectProperty.get();
	}

	public void setSubject(Subject subject) {
		subjectProperty.set(subject);
	}

	public ObjectProperty<LocalDate> getDateProperty() {
		return dateProperty;
	}

	public void setDateProperty(ObjectProperty<LocalDate> dateProperty) {
		this.dateProperty = dateProperty;
	}

	public ObjectProperty<LocalTime> getTimeProperty() {
		return timeProperty;
	}

	public void setTimeProperty(ObjectProperty<LocalTime> timeProperty) {
		this.timeProperty = timeProperty;
	}

	public StringProperty getLocationProperty() {
		return locationProperty;
	}

	public void setLocationProperty(StringProperty locationProperty) {
		this.locationProperty = locationProperty;
	}

	public ObjectProperty<Subject> getSubjectProperty() {
		return subjectProperty;
	}

	public void setSubjectProperty(ObjectProperty<Subject> subjectProperty) {
		this.subjectProperty = subjectProperty;
	}

	public Exam getExam() {
		Exam exam = new Exam();
		exam.setDateTime(LocalDateTime.of(getDate(), getTime()));
		exam.setLocation(getLocation());
		exam.setSubject(getSubject());
		return exam;
	}
}
