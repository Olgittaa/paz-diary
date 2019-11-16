package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.paz.diary.entity.Lesson;

/**
 * Class responses for access to subjects' lessons data
 * 
 * @author Yevhenii Kozhevin
 * @author Olha Charna
 */
public class LessonDao extends DAO implements ILessonDAO {

	public LessonDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Lesson> getAllLessons() {
		String sql = "SELECT id_lesson, `date`, location, duration, `type` FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getLessonsBySubjectId(Long id) {
		String sql = "SELECT id_lesson, `date`, location, duration, `type` FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject WHERE l.id_subject = " + id;
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	/**
	 * Helping class for reading lessons from database
	 * 
	 * @author Yevhenii Kozhevin, Olha Charna
	 */
	private class LessonRowMapperImpl implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getLong("id_lesson"));
			lesson.setDate(rs.getTimestamp("date").toLocalDateTime());
			lesson.setDuration(rs.getInt("duration"));
			lesson.setLocation(rs.getString("location"));
			lesson.setType("lecture".equals(rs.getString("type")) ? "lecture" : "practice");
			return lesson;
		}
	}

}
