package sk.upjs.paz.diary.persistence;

import java.time.DayOfWeek;
import java.util.List;

import sk.upjs.paz.diary.entity.Lesson;

public interface ILessonDAO {

	List<Lesson> getLessonsBySubjectId(Long id);

	List<Lesson> getWeekSchedule();

	List<Lesson> getDaySchedule(DayOfWeek dayOfWeek);

	void remove(Lesson lesson);

	Lesson save(Lesson lesson);

	List<Lesson> getAllLessons();

}
