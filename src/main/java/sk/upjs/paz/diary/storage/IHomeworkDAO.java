package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Homework;

public interface IHomeworkDAO {
	List<Homework> getAllHomework();
	
	List<Homework> getHomeworkBySubjectName(String name);
}
