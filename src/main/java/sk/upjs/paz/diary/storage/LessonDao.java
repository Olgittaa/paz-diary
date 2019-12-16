package sk.upjs.paz.diary.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.entity.Subject;

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
		final String sql = "SELECT * FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getLessonsBySubjectId(Long id) {
		final String sql = "SELECT * FROM lesson l LEFT JOIN subject s ON l.id_subject=s.id_subject WHERE s.id_subject = ?";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl(), id);
	}

	@Override
	public List<Lesson> getWeekSchedule() {
		final String sql = "SELECT * FROM lesson where week(date, 1) = week(current_date(), 1) AND year(date)=year(current_date()) ORDER BY date";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getWeekScheduleBySubjectId(Long id) {
		final String sql = "SELECT * FROM lesson WHERE id_subject = ? GROUP BY DAYOFWEEK(date) ORDER BY date";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl(), id);
	}

	@Override
	public List<Lesson> getDaySchedule(int day) {
		if (day < 2 || day > 6) {
			return Collections.emptyList();
		}
		final String sql = "SELECT * FROM lesson WHERE dayofweek(date) =? AND week(date, 1) = week(current_date(), 1) ORDER BY date";
		return jdbcTemplate.query(sql, new LessonRowMapperImpl(), day);
	}

	@Override
	public void remove(Lesson lesson) {
		final String sql = "DELETE FROM lesson WHERE id_lesson=" + lesson.getId();
		jdbcTemplate.execute(sql);
	}

	@Override
	public Lesson save(Lesson lesson) {
		if (lesson == null)
			return null;

		// INSERT
		if (lesson.getId() == null) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("lesson")
					.usingGeneratedKeyColumns("id_lesson");

			final Map<String, Object> values = new HashMap<>(6);
			values.put("date", lesson.getDateTime());
			values.put("location", lesson.getLocation());
			values.put("duration", lesson.getDuration());
			values.put("type", lesson.getType());
			values.put("id_subject", lesson.getSubject().getId());

			long id = jdbcInsert.executeAndReturnKey(values).longValue();
			lesson.setId(id);
		}
		// UPDATE
		else {
			String sql = "UPDATE lesson SET date=?, location=?, duration=?, type=?, id_subject=? WHERE id_lesson=?";
			jdbcTemplate.update(sql, lesson.getDateTime(), lesson.getLocation(), lesson.getDuration(), lesson.getType(),
					lesson.getSubject().getId(), lesson.getId());
		}
		return lesson;
	}

	@Override
	public Lesson getLastLessonOfSubject(Subject subject) {
		final String sql = "SELECT * FROM lesson WHERE date=(SELECT max(date) FROM lesson WHERE id_subject=?)";
		return jdbcTemplate.queryForObject(sql, new LessonRowMapperImpl(), subject.getId());
	}

	/**
	 * Helping class for mapping rows from database to Lesson objects
	 */
	private class LessonRowMapperImpl implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getLong("id_lesson"));
			lesson.setDateTime(rs.getTimestamp("date").toLocalDateTime());
			lesson.setDuration(rs.getInt("duration"));
			lesson.setLocation(rs.getString("location"));
			lesson.setType("lecture".equalsIgnoreCase(rs.getString("type")) ? LessonType.LECTURE : LessonType.PRACTICE);
			lesson.setSubject(DaoFactory.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return lesson;
		}
	}

}
