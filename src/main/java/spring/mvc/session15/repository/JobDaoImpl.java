package spring.mvc.session15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;

@Repository
public class JobDaoImpl implements JobDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public int add(Job job) {
		String sql = SQLUtil.ADD_JOB_SQL;

		return jdbcTemplate.update(sql, job.getJname(), job.getEid());
	}

	@Override
	public int update(Job job) {
		String sql = SQLUtil.UPT_JOB_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql, job.getJname(), job.getEid(), job.getJid());
	}

	@Override
	public int delete(Integer jid) {
		String sql = SQLUtil.DEL_JOB_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql, jid);
	}

	@Override
	public Job get(Integer jid) {
		String sql = SQLUtil.GET_JOB_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Job>(Job.class), jid);
	}

	@Override
	public int getCount() {
		String sql = SQLUtil.COUNT_JOB_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Job> query() {
		String sql = SQLUtil.QUERY_JOB_SQL;

		RowMapper<Job> rm = (rs, rowNum) -> {
			Job job = new Job();
			job.setJid(rs.getInt("jid"));
			job.setJname(rs.getString("jname"));
			job.setEid(rs.getInt("eid"));
			Employee emp = employeeDao.get(job.getEid());
			job.setEmployee(emp);
			return job;
		};
		// TODO Auto-generated method stub
		return jdbcTemplate.query(sql, rm);
	}

	@Override
	public List<Job> queryPage(int pageNo) {
		int offset = (pageNo - 1) * LIMIT;
		String sql = SQLUtil.QUERY_PAGE_JOB_SQL;
		List<Job> jobs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Job>(Job.class), LIMIT, offset);
		for (Job job : jobs) {
			int eid = job.getEid();
			Employee emp = employeeDao.get(eid);
			job.setEmployee(emp);
		}
		return jobs;
		// TODO Auto-generated method stub
	}
}
