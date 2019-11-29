package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exam {
	private Long id;
	private LocalDateTime dateTime;
	private String location;
	private Subject subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getStringDateTime() {
		return getDateTime().format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
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

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Subject getSubject() {
		return subject;
	}

	@Override
	public String toString() {
		return "Exam [date=" + dateTime + ", location=" + location + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
