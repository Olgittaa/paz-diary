package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.paz.diary.entity.Exam;

public class ExamDao extends DAO implements IExamDAO {
	
	public ExamDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Exam> getAllExams() {
		String sql = "SELECT * FROM exam";
		return jdbcTemplate.query(sql, new ExamRowMapperImpl());
	}

	@Override
	public List<Exam> getExamsBySubjectName(String name) {
		String sql = "SELECT * FROM exam e LEFT JOIN subject s ON e.id_subject=s.id_subject WHERE s.name=\"" + name
				+ "\"";
		return jdbcTemplate.query(sql, new ExamRowMapperImpl());
	}

	private class ExamRowMapperImpl implements RowMapper<Exam> {
		@Override
		public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Exam(rs.getTimestamp("date").toLocalDateTime(), rs.getString("location"));
		}
	}

}
