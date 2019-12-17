package sk.upjs.paz.diary.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
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
public class SubjectDao extends DAO implements ISubjectDAO {

	public SubjectDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Subject> getAllSubjects() {
		String query = "SELECT * FROM subject;";
		return getJdbcTemplate().query(query, new SubjectRowMapperImpl());
	}

	@Override
	public List<Subject> getAllSubjectsSorted() {
		String query = "SELECT * FROM subject ORDER BY name";
		List<Subject> list = getJdbcTemplate().query(query, new SubjectRowMapperImpl());
		return list;
	}

	@Override
	public Subject getSubjectById(Long id) {
		String sql = "SELECT * FROM subject WHERE id_subject=" + id;
		return getJdbcTemplate().queryForObject(sql, new SubjectRowMapperImpl());
	}

	// returns null if subject does not exist
	@Override
	public Subject getSubjectByName(String name) {
		try {
			String sql = "SELECT * FROM subject WHERE name=?";
			return getJdbcTemplate().queryForObject(sql, new SubjectRowMapperImpl(), name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Subject save(Subject subject) {
		if (subject == null)
			return null;

		// INSERT
		if (subject.getId() == null) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("subject")
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
			getJdbcTemplate().update(sql, subject.getName(), subject.getSite(), subject.getEmail(), subject.getId());
		}
		return subject;
	}

	@Override
	public void remove(Subject subject) {
		String sql = "DELETE FROM subject WHERE id_subject=" + subject.getId();
		getJdbcTemplate().execute(sql);
	}

	private class SubjectRowMapperImpl implements RowMapper<Subject> {
		@Override
		public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id_subject");
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			return new Subject(id, name, site, email);
		}
	}

}