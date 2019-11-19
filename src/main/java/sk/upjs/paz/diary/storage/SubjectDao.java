package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
	public String getNameById(Long id) {
		String sql = "SELECT name FROM subject WHERE id_subject=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	@Override
	public Subject getSubjectById(Long id) {
		String sql = "SELECT * FROM subject WHERE id_subject=" + id;
		return jdbcTemplate.queryForObject(sql, new SubjectRowMapper());
	}
	
	private class SubjectRowMapper implements RowMapper<Subject>{

		@Override
		public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id_subject");
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			Subject subject = new Subject(id, name, site, email);
			return subject;
		}
		
	}
}