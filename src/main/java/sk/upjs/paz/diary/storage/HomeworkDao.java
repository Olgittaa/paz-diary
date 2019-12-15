package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Homework;

public class HomeworkDao extends DAO implements IHomeworkDAO {

	public HomeworkDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Homework> getAllHomework() {
		String sql = "SELECT * FROM homework";
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public List<Homework> getAllHomeworkSorted() {
		String sql = "SELECT * FROM homework ORDER BY deadline";
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public List<Homework> getHomeworkOnWeekSorted() {
		String sql = "SELECT * FROM homework WHERE id_homework NOT IN (SELECT id_homework FROM homework "
				+ "WHERE DATEDIFF(deadline, NOW()) < -7 AND status = 1) ORDER BY deadline";
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public List<Homework> getHomeworkBySubjectId(Long id) {
		String sql = "SELECT * FROM homework hw LEFT JOIN subject s ON hw.id_subject=s.id_subject WHERE s.id_subject = ?";
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl(), id);
	}

	@Override
	public Homework save(Homework homework) {
		if (homework == null) {
			return homework;
		}
		if (homework.getId() == null) { // INSERT
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("homework")
					.usingGeneratedKeyColumns("id_homework");
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("deadline", homework.getDeadline());
			parameters.put("description", homework.getDescription());
			parameters.put("status", homework.getStatus());
			parameters.put("id_subject", homework.getSubject().getId());

			long id = jdbcInsert.executeAndReturnKey(parameters).longValue();
			homework.setId(id);
		} else { // UPDATE
			String sql = "UPDATE homework SET deadline=?, description=?, status=?, id_subject=? WHERE id_homework = "
					+ homework.getId();
			jdbcTemplate.update(sql, homework.getDeadline(), homework.getDescription(), homework.getStatus(),
					homework.getSubject().getId());
		}
		return homework;
	}

	@Override
	public void remove(Homework homework) {
		String sql = "DELETE FROM homework WHERE id_homework=" + homework.getId();
		jdbcTemplate.execute(sql);
	}

	
	private class HomeworkRowMapperImpl implements RowMapper<Homework> {
		@Override
		public Homework mapRow(ResultSet rs, int rowNum) throws SQLException {
			Homework hw = new Homework();
			hw.setId(rs.getLong("id_homework"));
			hw.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
			hw.setDescription(rs.getString("description"));
			hw.setStatus(rs.getBoolean("status"));
			hw.setSubject(DaoFactory.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return hw;
		}
	}

}