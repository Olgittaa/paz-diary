package sk.upjs.paz.diary.entity;

import java.time.LocalDate;

public class Homework extends StudyObject {
	
	private long id;
	private LocalDate deadline;
	private String description;
	private boolean status;
	private Long idSubject;
	
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
	
	public boolean isDone() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}
	
	@Override
	public String toString() {
		return "Homework [deadline=" + deadline + ", description=" + description + "]\n";
	}

}
