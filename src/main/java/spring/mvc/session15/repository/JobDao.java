package spring.mvc.session15.repository;

import java.util.List;

import com.github.javafaker.Job;

import spring.mvc.session15.entity.Employee;

public interface JobDao {
	int LIMIT = 5;

	int add(Job job);

	int update(Job job);

	int delete(Integer eid);

	Employee get(Integer eid);

	int getCount();

	List<Job> query();

	List<Job> queryPage(int pageNo);

}
