package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Lesson;

public interface ILessonDAO {
	/**
	 * Returns a list of subjects
	 * 
	 * @return list of Subject
	 */
	List<Lesson> getAllLessons();

	/**
	 * Returns a list of lessons of subject with name defined by parameter name
	 * 
	 * @param name - name of each subject
	 * @return list of Lesson
	 */
	List<Lesson> getLessonsBySubjectName(String name);
}
