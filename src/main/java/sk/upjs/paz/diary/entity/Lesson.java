package sk.upjs.paz.diary.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Lesson {
	private Long id;
	private LocalTime startTime;
	private DayOfWeek dayOfWeek;
	private LocalDateTime tillDate;
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
		
	}

	public Lesson(Long id, LocalDateTime fromDate, LocalDateTime tillDate, String location, int duration,
			LessonType type) {
		this.id = id;
		this.tillDate = tillDate;
		this.location = location;
		this.duration = duration;
		this.type = type;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getTillDate() {
		return tillDate;
	}

	public void setTillDate(LocalDateTime tillDate) {
		this.tillDate = tillDate;
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
		return startTime;
	}

	public LocalTime getEndTime() {
		return startTime.plusMinutes(duration);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(subject.getName()).append(" ");
		if (type != null) {
			sb.append(Character.toUpperCase(type.toString().charAt(0))).append(" ");
		}
		sb.append(getStartTime()).append("-").append(getEndTime()).append(" ");
		if(location != null) {
			sb.append(location);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dayOfWeek, duration, location, startTime, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return dayOfWeek == other.dayOfWeek && duration == other.duration && Objects.equals(location, other.location)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(subject, other.subject);
	}
}
