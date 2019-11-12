package sk.upjs.paz.diary.businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;

public class MySqlDataAccessObject implements IMySqlDAO {

	private JdbcTemplate jdbcTemplate;

	public MySqlDataAccessObject(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// ResultSetExtructor - nacitava celu tabulky a vracia list cohosi
	// RawMapper - nacitava po jednemu riadku s tabulky a vracia jeden riadok

	@Override
	public List<Exam> getAllExams() {
		String query = "SELECT * FROM exam e JOIN subject s ON e.id_subject=s.id_subject";

		List<Exam> exams = jdbcTemplate.query(query, new ResultSetExtractor<List<Exam>>() {
			@Override
			public List<Exam> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Exam> list = new LinkedList<>();
				while (rs.next()) {
					Exam exam = new Exam();
					exam.setLocation(rs.getString("location"));

					exam.setDateTime(
							LocalDateTime.of(rs.getDate("date").toLocalDate(), rs.getTime("date").toLocalTime()));
					list.add(exam);
				}
				return list;
			}
		});
		return exams;
	}

	@Override
	public List<Homework> getAllHomework() {
		String query = "SELECT * FROM homework h JOIN subject s ON h.id_subject=s.id_subject";

		List<Homework> list = jdbcTemplate.query(query, new RowMapper<Homework>() {
			@Override
			public Homework mapRow(ResultSet rs, int rowNum) throws SQLException {
				Homework hw = new Homework();
				hw.setDescription(rs.getString("description"));
				// hw.setDeadline();
				return hw;
			}
		});
		return list;
	}

	@Override
	public List<Lesson> getAllLessons() {
		String query = "";

		final List<Lesson> list = jdbcTemplate.query(query, new ResultSetExtractor<List<Lesson>>() {
			@Override
			public List<Lesson> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					// TODO
				}

				return null;
			}
		});

		return list;
	}

	@Override
	public List<Subject> getAllSubjects() {
		String query = "SELECT * FROM subject";

		final List<Subject> subjects = jdbcTemplate.query(query, new ResultSetExtractor<List<Subject>>() {
			@Override
			public List<Subject> extractData(ResultSet rs) throws SQLException, DataAccessException {
				LinkedList<Subject> list = new LinkedList<>();

				while (rs.next()) {
					String name = rs.getString("name");

					Subject sbj = new Subject();
					sbj.setName(name);
					// Для проверки на контейнс хватит сетнуть имя, т.к. екуалс переопределен и
					// сравнивает только по имени
					if (list.contains(sbj)) {
						continue;
					}
					sbj.setSite(rs.getString("site"));
					sbj.setEmail(rs.getString("email"));
					list.add(sbj);
				}
				return list;
			}
		});

		return subjects;
	}

	@Override
	public void saveSubject(Subject subject) {
		// TODO Auto-generated method stub
		
	}

//	public void saveSubject(Subject subject) {
//		if(subject == null) {
//			return; 
//		}
//		
//		// Insert new
//		//if() {
//			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//			
//			jdbcInsert.withTableName("subject");// с какой табличкой будем работать
//			jdbcInsert.usingGeneratedKeyColumns("id_subject"); // какой столбик имеет автоинкремент
//			jdbcInsert.usingColumns("name"); // в какие столбики будем вкладывать
//			
//			Map<String, Object> map = new HashMap<>();
//			map.put("name", subject.getName());
//	//	}
//		// Update existing
//		else {
//			String sql = "update subject set name = ? where id = " + subject.getId();
//			jdbcTemplate.update(sql, subject.getTitle());
//			String deleteSql = "delete from student_at_subject where subject_id = " + subject.getId();
//			jdbcTemplate.update(deleteSql);
//			//insertStudents(subject);
//			
//		}
//		
//	}

}
