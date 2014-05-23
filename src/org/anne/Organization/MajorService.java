package org.anne.Organization;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MajorService {
	
	private static final Logger log = LoggerFactory.getLogger(MajorService.class);
	
	@Autowired
	MajorDAO majorDAO;
	
	/**
	 * 添加专业
	 * @param major
	 * @return
	 */
	@Transactional
	public String addMajor(Major major) {
		String id = majorDAO.addMajor(major);
		return id;
	}
	
	/**
	 * 修改专业
	 * @param major
	 */
	@Transactional
	public void editMajor(Major major) {
		majorDAO.editMajor(major);
	}
	
	/**
	 * 列出学校的专业
	 * @param orgId
	 * @return
	 */
	public List<Major> listMajorsByOrgId(String orgId) {
		List<Major> majors = majorDAO.listMajorByOrgId(orgId);
		return majors;
	}
	
	/**
	 * 删除专业
	 * @param id
	 * @throws NoMajorException
	 */
	@Transactional
	public void deleteMajor(String id) throws NoMajorException {
		Major major = majorDAO.getById(id);
		if(major != null) {
			majorDAO.deleteMajor(major);
		} else {
			throw new NoMajorException();
		}
	}

}
