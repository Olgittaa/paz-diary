package sk.upjs.paz.diary.entity;

import java.time.LocalDate;

public class Homework extends StudyObject {
	
	private long id;
	private LocalDate deadline;
	private String description;
	private boolean status;

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Homework [deadline=" + deadline + ", description=" + description + "]\n";
	}

}
