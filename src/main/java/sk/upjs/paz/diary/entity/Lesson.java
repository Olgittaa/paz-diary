package sk.upjs.paz.diary.entity;

//import java.time.DayOfWeek;

public class Lesson extends StudyObject {
//	private ? date;
	private String location;
	private int duration;
	private LessonType type;

	public enum LessonType {
		LECTURE, PRACTICE;
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

}
