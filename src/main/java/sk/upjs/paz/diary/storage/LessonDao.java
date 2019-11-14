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
		String sql = "SELECT * FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getLessonsBySubjectName(String name) {
		String sql = "SELECT * FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject WHERE s.name=\"" + name
				+ "\"";
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
			lesson.setDate(rs.getTimestamp("date").toLocalDateTime());
			lesson.setDuration(rs.getInt("duration"));
			lesson.setLocation(rs.getString("location"));
			lesson.setType("lecture".equals(rs.getString("type")) ? "lecture" : "practice");
			return lesson;
		}
	}

}
