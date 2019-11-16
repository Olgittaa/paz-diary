package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;

public class Lesson extends StudyObject {
	
	private Long id;
	private LocalDateTime date;
	private String location;
	private int duration;
	private String type;
	private Long idSubject;
	
	public Lesson() {
		location = new String();
	}
	
	public Lesson(Long id, LocalDateTime date, String location, int duration, String type) {
		this.id=id;
		this.date = date;
		this.location = location;
		this.duration = duration;
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}
	
	@Override
	public String toString() {
		return "Lesson [date=" + date + ", location=" + location + ", duration=" + duration + ", type=" + type + "]";
	}
	
}
