package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Homework;

/**
 * Interface for
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public interface IHomeworkDAO {
	List<Homework> getAllHomework();

	List<Homework> getHomeworkBySubjectId(Long id);

	List<Homework> getHomeworkOnWeekSorted();

	void save(Homework homework);

	void remove(Homework homework);

}
