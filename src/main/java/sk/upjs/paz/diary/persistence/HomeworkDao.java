package sk.upjs.paz.diary.persistence;

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
		return getJdbcTemplate().query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public List<Homework> getHomeworkOnWeekSorted() {
		String sql = "SELECT * FROM homework WHERE id_homework NOT IN (SELECT id_homework FROM homework "
				+ "WHERE DATEDIFF(deadline, NOW()) < -7 AND status = 1) ORDER BY deadline";
		return getJdbcTemplate().query(sql, new HomeworkRowMapperImpl());
	}

	@Override
	public Homework save(Homework homework) {
		if (homework == null) {
			return homework;
		}
		if (homework.getId() == null) { // INSERT
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("homework")
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
			getJdbcTemplate().update(sql, homework.getDeadline(), homework.getDescription(), homework.getStatus(),
					homework.getSubject().getId());
		}
		return homework;
	}

	@Override
	public void remove(Homework homework) {
		String sql = "DELETE FROM homework WHERE id_homework=" + homework.getId();
		getJdbcTemplate().execute(sql);
	}

	private class HomeworkRowMapperImpl implements RowMapper<Homework> {
		@Override
		public Homework mapRow(ResultSet rs, int rowNum) throws SQLException {
			Homework hw = new Homework();
			hw.setId(rs.getLong("id_homework"));
			hw.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
			hw.setDescription(rs.getString("description"));
			hw.setStatus(rs.getBoolean("status"));
			hw.setSubject(DaoFactory.INSTANCE.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return hw;
		}
	}

	@Override
	public List<Homework> getHomeworkBySubjectId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}