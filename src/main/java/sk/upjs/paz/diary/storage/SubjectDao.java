package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

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
		return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
			Long id = rs.getLong("id_subject");
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			Subject subject = new Subject(id, name, site, email);

//			subject.setExams(DaoFactory.getExamDao().getExamsBySubjectId(id));
//			subject.setLessons(DaoFactory.getLessonDao().getLessonsBySubjectId(id));
//			subject.setHomework(DaoFactory.getHomeworkDao().getHomeworkBySubjectId(id));

			return subject;
		});
	}

	@Override
	public String getNameById(Long id) {
		String sql = "SELECT name FROM subject WHERE id_subject=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}
}