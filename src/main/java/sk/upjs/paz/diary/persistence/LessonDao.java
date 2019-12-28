package sk.upjs.paz.diary.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;

public class LessonDao extends DAO implements ILessonDAO {

	public LessonDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Lesson> getAllLessons() {
		final String sql = "SELECT * FROM lesson";
		return getJdbcTemplate().query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getLessonsBySubjectId(Long id) {
		final String sql = "SELECT * FROM lesson WHERE id_subject = ? ORDER BY start_time";
		return getJdbcTemplate().query(sql, new LessonRowMapperImpl(), id);
	}

	@Override
	public List<Lesson> getWeekSchedule() {
		final String sql = "SELECT * FROM lesson WHERE current_date() < till_date";
		return getJdbcTemplate().query(sql, new LessonRowMapperImpl());
	}

	@Override
	public List<Lesson> getDaySchedule(final DayOfWeek dayOfWeek) {
		if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
			return Collections.emptyList();
		}
		final String sql = "SELECT * FROM lesson WHERE day_of_week='" + dayOfWeek + "' ORDER BY start_time";
		return getJdbcTemplate().query(sql, new LessonRowMapperImpl());
	}

	@Override
	public void remove(final Lesson lesson) {
		final String sql = "DELETE FROM lesson WHERE id_lesson=" + lesson.getId();
		getJdbcTemplate().execute(sql);
	}

	@Override
	public Lesson save(final Lesson lesson) {
		if (lesson == null)
			return null;

		// INSERT
		if (lesson.getId() == null) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("lesson")
					.usingGeneratedKeyColumns("id_lesson");

			final Map<String, Object> values = new HashMap<>(8);
			values.put("day_of_week", lesson.getDayOfWeek());
			values.put("start_time", lesson.getStartTime());
			values.put("till_date", lesson.getTillDate());
			values.put("location", lesson.getLocation());
			values.put("duration", lesson.getDuration());
			values.put("type", lesson.getType());
			values.put("id_subject", lesson.getSubject().getId());

			long id = jdbcInsert.executeAndReturnKey(values).longValue();
			lesson.setId(id);
		}
		// UPDATE
		else {
			String sql = "UPDATE lesson SET day_of_week=?, start_time=?, till_date=?, location=?, duration=?, type=?, id_subject=? WHERE id_lesson=?";
			getJdbcTemplate().update(sql, lesson.getDayOfWeek(), lesson.getStartTime(), lesson.getTillDate(),
					lesson.getLocation(), lesson.getDuration(), lesson.getType(), lesson.getSubject().getId(),
					lesson.getId());
		}
		return lesson;
	}

	private class LessonRowMapperImpl implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getLong("id_lesson"));
			lesson.setDayOfWeek(DayOfWeek.valueOf(rs.getString("day_of_week")));
			lesson.setTillDate(rs.getTimestamp("till_date").toLocalDateTime());
			lesson.setStartTime(rs.getTime("start_time").toLocalTime());
			lesson.setDuration(rs.getInt("duration"));
			lesson.setLocation(rs.getString("location"));
			lesson.setType("lecture".equalsIgnoreCase(rs.getString("type")) ? LessonType.LECTURE : LessonType.PRACTICE);
			lesson.setSubject(DaoFactory.INSTANCE.getSubjectDao().getSubjectById(rs.getLong("id_subject")));
			return lesson;
		}
	}
}
