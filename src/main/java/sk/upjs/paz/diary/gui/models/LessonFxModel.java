package sk.upjs.paz.diary.gui.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.entity.Subject;

public class LessonFxModel {
	private Long id;
	private ObjectProperty<LocalTime> startTimeProperty = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> tillDateProperty = new SimpleObjectProperty<>();
	private ObjectProperty<DayOfWeek> dayOfWeekProperty = new SimpleObjectProperty<>();
	private StringProperty locationProperty = new SimpleStringProperty();
	private StringProperty durationProperty = new SimpleStringProperty();
	private ObjectProperty<LessonType> typeProperty = new SimpleObjectProperty<>();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public LessonFxModel() {
	}

	public LessonFxModel(Lesson lesson) {
		load(lesson);
	}

	public ObjectProperty<DayOfWeek> getDayOfWeekProperty() {
		return dayOfWeekProperty;
	}

	public void setDayOfWeekProperty(ObjectProperty<DayOfWeek> dayOfWeekProperty) {
		this.dayOfWeekProperty = dayOfWeekProperty;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeekProperty.get();
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		dayOfWeekProperty.set(dayOfWeek);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return startTimeProperty.get();
	}

	public void setTime(LocalTime time) {
		startTimeProperty.set(time);
	}

	public LocalDate getTillDate() {
		return tillDateProperty.get();
	}

	public void setTillDate(LocalDate dateTime) {
		tillDateProperty.set(dateTime);
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

	public ObjectProperty<LocalDate> getTillDateProperty() {
		return tillDateProperty;
	}

	public void setTillDateTimeProperty(ObjectProperty<LocalDate> dateProperty) {
		this.tillDateProperty = dateProperty;
	}

	public ObjectProperty<LocalTime> getTimeProperty() {
		return startTimeProperty;
	}

	public void setTimeProperty(ObjectProperty<LocalTime> timeProperty) {
		this.startTimeProperty = timeProperty;
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
		setTillDate(lesson.getTillDate().toLocalDate());
		setLocation(lesson.getLocation());
		setDuration(lesson.getDuration());
		setDayOfWeek(lesson.getDayOfWeek());
		setType(lesson.getType());
		setTime(lesson.getStartTime());
		setSubject(lesson.getSubject());
	}

	public Lesson getLesson() {
		Lesson lesson = new Lesson();
		lesson.setId(getId());
		lesson.setTillDate(LocalDateTime.of(getTillDate(), getTime()));
		lesson.setLocation(getLocation());
		lesson.setDuration(getDuration());
		lesson.setType(getType());
		lesson.setDayOfWeek(getDayOfWeek());
		lesson.setStartTime(getTime());
		lesson.setSubject(getSubject());
		return lesson;
	}

	@Override
	public String toString() {
		return "LessonFxModel [id=" + id + ", startTimeProperty=" + startTimeProperty + ", tillDateProperty="
				+ tillDateProperty + ", dayOfWeekProperty=" + dayOfWeekProperty + ", locationProperty="
				+ locationProperty + ", durationProperty=" + durationProperty + ", typeProperty=" + typeProperty
				+ ", subjectProperty=" + subjectProperty + "]";
	}

}
