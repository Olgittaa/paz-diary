package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Homework {
	private Long id;
	private LocalDateTime deadline;
	private String description;
	private boolean status;
	private Subject subject;
	
	public boolean getStatus() {
		return status;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}
	public String getFormatDeadline() {
		return deadline.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
	}
	
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDone() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Homework [deadline=" + deadline + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Homework other = (Homework) obj;
		return Objects.equals(id, other.id);
	}

	
}
