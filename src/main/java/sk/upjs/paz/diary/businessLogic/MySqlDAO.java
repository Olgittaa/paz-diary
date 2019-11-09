package sk.upjs.paz.diary.businessLogic;

import java.util.List;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.HomeWork;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.StudyObject;


public interface MySqlDAO {
	List<Exam> getAllExams();

	List<HomeWork> getAllHomework();

	List<Lesson> getAllLessons(); // is it ok?

	void removeStudyObject(StudyObject studyObject);

	void saveStudyObject(StudyObject studyObject);

}
