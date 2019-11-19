package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.paz.diary.entity.Lesson;

/**
 * Class responses for access to subjects' lessons data
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public class LessonDao extends DAO implements ILessonDAO {

	public LessonDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Lesson> getAllLessons() {
		final String sql = "SELECT id_lesson, date, location, duration, `type` FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getLessonsBySubjectId(Long id) {
		final String sql = "SELECT id_lesson, `date`, location, duration, `type` FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject WHERE l.id_subject = "
				+ id;
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getWeekSchedule() {
		final String sql = "SELECT * FROM lesson where week(date, 1) = week(current_date(), 1) AND year(date)=year(current_date()) ORDER BY date";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getDaySchedule(int day) {
		if (day < 2 || day > 6) {
			return Collections.emptyList();
		}
		final String sql = "SELECT * FROM lesson WHERE dayofweek(date) =? ORDER BY date";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl(), day);
	}

	/**
	 * Helping class for reading lessons from database
	 */
	private class LessonRowMapperImpl implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getLong("id_lesson"));
			lesson.setDateTime(rs.getTimestamp("date").toLocalDateTime());
			lesson.setDuration(rs.getInt("duration"));
			lesson.setLocation(rs.getString("location"));
			lesson.setType("lecture".equals(rs.getString("type")) ? "lecture" : "practice");
			lesson.setSubject(DaoFactory.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return lesson;
		}
	}

}
