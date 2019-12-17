package sk.upjs.paz.diary.persistence;

import java.util.List;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;

public interface ILessonDAO {

	List<Lesson> getLessonsBySubjectId(Long id);

	List<Lesson> getWeekSchedule();

	List<Lesson> getDaySchedule(int day);

	List<Lesson> getWeekScheduleBySubjectId(Long id);

	void remove(Lesson lesson);

	Lesson save(Lesson lesson);

	Lesson getLastLessonOfSubject(Subject subject);

	List<Lesson> getAllLessons();

}
