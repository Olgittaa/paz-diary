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
		String sql = "SELECT * FROM homework WHERE id_homework NOT IN (SELECT id_homework FROM homework "
				+ "WHERE DATEDIFF(deadline, NOW()) < - 7 AND status = 1) ORDER BY deadline;";
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public List<Homework> getHomeworkBySubjectId(Long id) {
		String sql = "SELECT id_homework, deadline, description, `status` FROM homework hw "
				+ "LEFT JOIN subject s ON hw.id_subject=s.id_subject WHERE hw.id_subject =" + id;
		return jdbcTemplate.query(sql, new HomeworkRowMapperImpl());

	}

	@Override
	public void refreshHomework(Homework homework) {
		String sql = "UPDATE homework SET status = ? WHERE id_homework = " + homework.getId();
		jdbcTemplate.update(sql, homework.isDone());
	}

	private class HomeworkRowMapperImpl implements RowMapper<Homework> {
		@Override
		public Homework mapRow(ResultSet rs, int rowNum) throws SQLException {
			Homework hw = new Homework();
			hw.setId(rs.getLong("id_homework"));
			hw.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
			hw.setDescription(rs.getString("description"));
			hw.setStatus(rs.getBoolean("status"));
			hw.setIdSubject(rs.getLong("id_subject"));
			return hw;
		}
	}

	@Override
	public Homework save(Homework hw) {
		if (hw == null) {
			return null;
		} else {
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("homework")
					.usingGeneratedKeyColumns("id_homework").usingColumns("description");
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("description", hw);
			long id = sjinsert.executeAndReturnKey(parameters).longValue();
			hw.setId(id);
		}
		return hw;
	}

}