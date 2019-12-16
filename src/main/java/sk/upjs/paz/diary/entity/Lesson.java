package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Lesson {
	private Long id;
	private LocalDateTime dateTime;
	private String location;
	private int duration;
	private LessonType type;
	private Subject subject;

	public enum LessonType {
		LECTURE, PRACTICE;

		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	}

	public Lesson() {
		location = new String();
	}

	public Lesson(Long id, LocalDateTime date, String location, int duration, LessonType type) {
		this.id = id;
		this.dateTime = date;
		this.location = location;
		this.duration = duration;
		this.type = type;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LessonType getType() {
		return type;
	}

	public void setType(LessonType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public LocalTime getStartTime() {
		return dateTime.toLocalTime();
	}

	public LocalTime getEndTime() {
		return dateTime.toLocalTime().plusMinutes(duration);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(subject.getName()).append(" ");
		if (type != null) {
			sb.append(Character.toUpperCase(type.toString().charAt(0))).append(" ");
		}
		sb.append(getStartTime()).append("-").append(getEndTime()).append(" ").append(location);
		return sb.toString();
	}

}
