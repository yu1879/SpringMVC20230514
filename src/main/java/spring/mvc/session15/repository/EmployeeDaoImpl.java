package spring.mvc.session15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Employee emp) {
		String sql = SQLUtil.ADD_EMPLOYEE_SQL;

		return jdbcTemplate.update(sql, emp.getEname(), emp.getSalary());
	}

	@Override
	public int update(Employee emp) {
		String sql = SQLUtil.UPT_JOB_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.update(emp.getEname(), emp.getSalary(), emp.getEid());
	}

	@Override
	public int delete(Integer eid) {
		String sql = SQLUtil.DEL_EMPLOYEE_SQL;
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql, eid);
	}

	@Override
	public Employee get(Integer eid) {
		String sql = SQLUtil.GET_EMPLOYEE_SQL;

		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), eid);
	}

	@Override
	public int getCount() {
		String sql = SQLUtil.COUNT_EMPLOYEE_SQL;
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	@Override
	public List<Employee> query() {
		String sql = SQLUtil.QUERY_EMPLOYEE_SQL;

		// TODO Auto-generated method stub
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public List<Employee> queryPage(int pageNo) {
		int offset = (pageNo - 1) * LIMIT;
		String sql = SQLUtil.QUERY_PAGE_EMPLOYEE_SQL;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), LIMIT, offset);

	}

}
