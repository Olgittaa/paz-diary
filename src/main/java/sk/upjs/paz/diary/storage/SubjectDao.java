package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Subject;

/**
 * Class responses for access to subjects' data
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public class SubjectDao implements ISubjectDAO {

	private JdbcTemplate jdbcTemplate;

	public SubjectDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Subject> getAllSubjects() {
		String query = "SELECT * FROM subject;";
		return jdbcTemplate.query(query, new SubjectRowMapper());
	}

	@Override
	public List<Subject> getAllSubjectsSorted() {
		String query = "SELECT * FROM subject ORDER BY name";
		List<Subject> list = jdbcTemplate.query(query, new SubjectRowMapper());
		return list;
	}

	@Override
	public String getNameById(Long id) {
		String sql = "SELECT name FROM subject WHERE id_subject=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	@Override
	public Subject getSubjectById(Long id) {
		String sql = "SELECT * FROM subject WHERE id_subject=" + id;
		return jdbcTemplate.queryForObject(sql, new SubjectRowMapper());
	}

	private class SubjectRowMapper implements RowMapper<Subject> {
		@Override
		public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id_subject");
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			return new Subject(id, name, site, email);
		}
	}

	@Override
	public Subject save(Subject subject) {
		if (subject == null)
			return null;

		// INSERT
		if (subject.getId() == null) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("subject")
					.usingGeneratedKeyColumns("subject_id");
			jdbcInsert.usingColumns("name", "site", "email");

			final Map<String, Object> values = new HashMap<>(4);
			values.put("name", subject.getName());
			values.put("site", subject.getSite());
			values.put("email", subject.getEmail());

			long id = jdbcInsert.executeAndReturnKey(values).longValue();
			subject.setId(id);

		}
		// UPDATE
		else {
			String sql = "UPDATE subject SET name=?, site=?, email=? WHERE id_subject=?";
			jdbcTemplate.update(sql, subject.getName(), subject.getSite(), subject.getEmail(), subject.getId());
		}
		return subject;
	}

	@Override
	public void remove(Subject subject) {
		String sql = "DELETE FROM subject WHERE id_subject=" + subject.getId();
		jdbcTemplate.execute(sql);
	}

}