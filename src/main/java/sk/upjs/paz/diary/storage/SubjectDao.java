package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.paz.diary.entity.Subject;

public class SubjectDao implements ISubjectDAO {

	private JdbcTemplate jdbcTemplate;
	
	private IExamDAO examDao;
	private ILessonDAO lessonDao;
	private IHomeworkDAO homeworkDao;
	
	public SubjectDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		examDao = DaoFactory.getExamDao();
		lessonDao = DaoFactory.getLessonDao();
		homeworkDao = DaoFactory.getHomeworkDao();
	}

	@Override
	public List<Subject> getAllSubjects() {
		String query = "SELECT * FROM subject;";

		return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
			String name = rs.getString("name");
			String site = rs.getString("site");
			String email = rs.getString("email");
			Subject subject = new Subject(name, site, email);

			subject.setExams(examDao.getExamsBySubjectName(name));
			subject.setLessons(lessonDao.getLessonsBySubjectName(name));
			subject.setHomework(homeworkDao.getHomeworkBySubjectName(name));
			
			return subject;
		});
	}

}
