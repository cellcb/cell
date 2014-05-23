package org.anne.junittest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl {
	@Autowired
	TestDaoImpl testDaoImpl;

	@Transactional
	public void insert(Test test, Class z) {
		testDaoImpl.insert(test, z);
	}

	@Transactional
	public void update(Test test) {
		testDaoImpl.update(test);
	}

	public void delete(Test test) {
		testDaoImpl.delete(test);
	}

	public List<Test> query(Test test) {
		return testDaoImpl.query(test);
	}
}
