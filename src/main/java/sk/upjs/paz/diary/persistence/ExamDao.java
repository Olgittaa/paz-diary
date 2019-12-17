package sk.upjs.paz.diary.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Exam;

public class ExamDao extends DAO implements IExamDAO {

	public ExamDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Exam> getAllExams() {
		String sql = "SELECT * FROM exam ORDER BY date";
		return getJdbcTemplate().query(sql, new ExamRowMapperImpl());
	}

	@Override
	public void save(Exam exam) {
		if (exam == null) {
			return;
		}
		// INSERT
		if (exam.getId() == null) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("exam")
					.usingGeneratedKeyColumns("id_exam");
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("date", exam.getDateTime());
			parameters.put("location", exam.getLocation());
			parameters.put("id_subject", exam.getSubject().getId());

			long id = jdbcInsert.executeAndReturnKey(parameters).longValue();
			exam.setId(id);
		} else {
			// UPDATE
			String sql = "UPDATE exam SET date=?, location=?, id_subject=? WHERE id_exam = " + exam.getId();
			getJdbcTemplate().update(sql, exam.getDateTime(), exam.getLocation(), exam.getSubject().getId());
		}
	}

	@Override
	public void remove(Exam exam) {
		String sql = "DELETE FROM exam WHERE id_exam=" + exam.getId();
		getJdbcTemplate().execute(sql);
	}

	private class ExamRowMapperImpl implements RowMapper<Exam> {
		@Override
		public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
			Exam exam = new Exam();
			exam.setId(rs.getLong("id_exam"));
			exam.setDateTime(rs.getTimestamp("date").toLocalDateTime());
			String location = rs.getString("location");
			if (location != null) {
				exam.setLocation(location);
			}
			exam.setSubject(DaoFactory.INSTANCE.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return exam;
		}
	}
}
