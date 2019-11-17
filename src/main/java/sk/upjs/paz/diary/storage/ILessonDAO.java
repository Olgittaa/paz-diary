package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Lesson;

/**
 * 
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public interface ILessonDAO {
	/**
	 * Returns a list of lessons
	 * 
	 * @return list of Lesson
	 */
	List<Lesson> getAllLessons();

	/**
	 * Returns a list of lessons of subject with id defined by parameter id
	 * 
	 * @param id - id of each subject
	 * @return list of Lesson
	 */
	List<Lesson> getLessonsBySubjectId(Long id);

	/**
	 * Returns a list of lessons on this week
	 * 
	 * @return list of lessons
	 */
	List<Lesson> getWeekSchedule();
}
