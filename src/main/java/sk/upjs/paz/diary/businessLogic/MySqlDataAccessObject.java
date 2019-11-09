package sk.upjs.paz.diary.businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.HomeWork;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.StudyObject;

public class MySqlDataAccessObject implements MySqlDAO {

	private JdbcTemplate jdbcTemplate;

	public MySqlDataAccessObject(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// ResultSetExtructor - nacitava
	// RawMapper - nacitava

	@Override
	public List<Exam> getAllExams() {
		String query = "SELECT * FROM exam";

		List<Exam> list = jdbcTemplate.query(query, new RowMapper<Exam>() {
			@Override
			public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam exam = new Exam();

				return exam;
			}
		});

		return list;
	}

	@Override
	public List<HomeWork> getAllHomework() {

		return null;
	}

	@Override
	public List<Lesson> getAllLessons() {
		return null;
	}

	@Override
	public void removeStudyObject(StudyObject studyObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveStudyObject(StudyObject studyObject) {
		// TODO Auto-generated method stub

	}

}
