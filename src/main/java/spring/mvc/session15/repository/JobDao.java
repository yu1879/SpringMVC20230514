package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Job;

public interface JobDao {
	int LIMIT = 5;

	int add(Job job);

	int update(Job job);

	int delete(Integer jid);

	Job get(Integer jid);

	int getCount();

	List<Job> query();

	List<Job> queryPage(int pageNo);

}
