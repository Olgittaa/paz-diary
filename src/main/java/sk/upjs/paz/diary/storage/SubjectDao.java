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
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			Subject subject = new Subject(name, site, email);

			subject.setExams(DaoFactory.getExamDao().getExamsBySubjectName(name));
			subject.setLessons(DaoFactory.getLessonDao().getLessonsBySubjectName(name));
			subject.setHomework(DaoFactory.getHomeworkDao().getHomeworkBySubjectName(name));
			
			return subject;
		});
	}

}
