package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Employee;

public interface EmployeeDao {
	int LIMIT = 5;

	int add(Employee emp);

	int update(Employee emp);

	int delete(Integer eid);

	Employee get(Integer eid);

	int getCount();

	List<Employee> query();

	List<Employee> queryPage(int pageNo);

}
