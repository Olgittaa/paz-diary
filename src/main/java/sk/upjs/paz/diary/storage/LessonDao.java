package sk.upjs.paz.diary.storage;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import sk.upjs.paz.diary.entity.Lesson;

public class LessonDao extends DAO implements ILessonDAO {

	public LessonDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Lesson> getAllLessons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> getLessonsBySubjectName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
