package sk.upjs.paz.diary.storage;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import sk.upjs.paz.diary.entity.Lesson;

public abstract class DAO {
	protected JdbcTemplate jdbcTemplate;

	public DAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Lesson> getLessonsBySubjectId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}