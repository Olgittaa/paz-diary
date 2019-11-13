package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Lesson;

public interface ILessonDAO {
	List<Lesson> getAllLessons();
	
	List<Lesson> getLessonsBySubjectName(String name);
}
