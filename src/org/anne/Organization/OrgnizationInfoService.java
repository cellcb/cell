package org.anne.Organization;

import java.util.List;

import org.anne.Organization.OrganizationInfoDAO.OrgRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrgnizationInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(OrgnizationInfoService.class);
	
	@Autowired
	OrganizationInfoDAO orgDAO;
	
	@Transactional
	public void updateOrg(OrganizationInfo org) {
		this.orgDAO.updateOrg(org);
	}
	
	public OrganizationInfo findByUserid(String userid) {
		return this.orgDAO.findByUserId(userid);
	}
	
	public List<OrganizationInfo> queryAllOrg(){
		return orgDAO.queryAllOrg();
	}

}
