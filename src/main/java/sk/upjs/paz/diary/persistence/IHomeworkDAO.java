package sk.upjs.paz.diary.persistence;

import java.util.List;

import sk.upjs.paz.diary.entity.Homework;

public interface IHomeworkDAO {
	List<Homework> getAllHomework();

	List<Homework> getHomeworkOnWeekSorted();

	Homework save(Homework homework);

	void remove(Homework homework);

}
