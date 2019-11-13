package sk.upjs.paz.diary.storage;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import sk.upjs.paz.diary.entity.Homework;

public class HomeworkDao extends DAO implements IHomeworkDAO {

	public HomeworkDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public List<Homework> getAllHomework() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Homework> getHomeworkBySubjectName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
