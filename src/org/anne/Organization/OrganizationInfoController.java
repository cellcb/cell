package org.anne.Organization;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.anne.base.BaseController;
import org.anne.base.ResponseInfo;
import org.anne.blog.Blog;
import org.anne.user.BlogUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/org")
public class OrganizationInfoController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(OrganizationInfoController.class);
	
	@Autowired
	OrgnizationInfoService orgService;
	@Autowired
	MajorService majorService;
	
	/**
	 * 添加专业
	 * @param request
	 * @param major
	 * @return
	 */
	@RequestMapping("/add_major.html")
	public @ResponseBody
	ResponseInfo addMajor(HttpServletRequest request, Major major) {
		ResponseInfo ri = new ResponseInfo();
		
		String name = major.getName();
		String brief = major.getBrief();
		String orgId = major.getOrgId();
		if(name != null && orgId != null) {
			Major m = new Major();
			m.setName(name);
			m.setBrief(brief);
			m.setOrgId(orgId);
			String id = majorService.addMajor(m);
			m.setId(id);
			ri.setSuccess(true);
			ri.setResult(m);
			ri.setMessage("Add Success");
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error");
		}
		
		return ri;
	}
	
	/**
	 * 列出机构专业
	 * @param request
	 * @param major
	 * @return
	 */
	@RequestMapping("/list_major_by_orgid.html")
	public @ResponseBody
	ResponseInfo listMajorByOrgId(HttpServletRequest request, Major major) {
		ResponseInfo ri = new ResponseInfo();
		String orgId = major.getOrgId();
		if(orgId != null) {
			List<Major> list = majorService.listMajorsByOrgId(orgId);
			ri.setSuccess(true);
			ri.setResult(list);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		return ri;
	}
	
	/**
	 * 修改专业
	 * @param request
	 * @param major
	 * @return
	 */
	@RequestMapping("/edit_major.html")
	public @ResponseBody
	ResponseInfo eidtMajor(HttpServletRequest request, Major major) {
		ResponseInfo ri = new ResponseInfo();
		String id = major.getId();
		if(id != null) {
			majorService.editMajor(major);
			ri.setSuccess(true);
			ri.setResult(major);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		return ri;
	}
	
	@RequestMapping("/delete_major.html")
	public @ResponseBody
	ResponseInfo deleteMajor(HttpServletRequest request, Major major) {
		ResponseInfo ri = new ResponseInfo();
		String id = major.getId();
		try {
			if(id != null) {
				majorService.deleteMajor(id);
				ri.setSuccess(true);
				ri.setMessage("Entity has deleted.");
			} else {
				ri.setSuccess(false);
				ri.setMessage("Parameter Error!");
			}
		} catch (NoMajorException e) {
			ri.setSuccess(false);
			ri.setMessage("Major is not exit");
		}
		return ri;
	}
	
	@RequestMapping("/update_org.html")
	public @ResponseBody
	ResponseInfo updateOrg(HttpServletRequest request, OrganizationInfo org) {
		ResponseInfo ri = new ResponseInfo();
		
		String fullname = org.getFullname() != null ? org.getFullname() : "";
		String nickname = org.getNickname() != null ? org.getNickname() : "";
		String chinesename = org.getChinesename() != null ? org.getChinesename() : "";
		Boolean publicschool = org.isPublicschool() != null ? org.isPublicschool() : false;
		String registerdate = org.getRegisterdate() != null ? org.getRegisterdate() : null;
		String address = org.getAddress() != null ? org.getAddress() : "";
		String postcode = org.getPostcode() != null ? org.getPostcode() : "";
		String brief = org.getBrief() != null ? org.getBrief() : "";
		String officialwebsite = org.getOfficialwebsite() != null ? org.getOfficialwebsite() : "";
		String majorBrief = org.getMajorBrief() != null ? org.getMajorBrief() : "";
		Integer toefl = org.getToefl() != null ? org.getToefl() : 0;
		Integer ielts = org.getIelts() != null ? org.getIelts() : 0;
		Integer gre = org.getGre() != null ? org.getGre() : 0;
		Integer sat = org.getSat() != null ? org.getSat() : 0;
		String tag = org.getTag() != null ? org.getTag() : "";
		Integer undergraduate = org.getUndergraduate() != null ? org.getUndergraduate() : 0;
		Integer postgraduate = org.getPostgraduate() != null ? org.getPostgraduate() : 0;
		Integer fee = org.getFee() != null ? org.getFee() : 0;
		String scholarship = org.getScholarship() != null ? org.getScholarship() : "";
		String email = org.getEmail() != null ? org.getEmail() : "";
		String userid = org.getUserid();
		
		OrganizationInfo o = new OrganizationInfo();
		o.setAddress(address);
		o.setBrief(brief);
		o.setChinesename(chinesename);
		o.setEmail(email);
		o.setFee(fee);
		o.setFullname(fullname);
		o.setGre(gre);
		o.setIelts(ielts);
		o.setMajorBrief(majorBrief);
		o.setNickname(nickname);
		o.setOfficialwebsite(officialwebsite);
		o.setPostcode(postcode);
		o.setPostgraduate(postgraduate);
		o.setPublicschool(publicschool);
		o.setRegisterdate(registerdate);
		o.setSat(sat);
		o.setScholarship(scholarship);
		o.setTag(tag);
		o.setToefl(toefl);
		o.setUndergraduate(undergraduate);
		o.setUserid(userid);
		
		if(userid != null) {
			this.orgService.updateOrg(o);
			ri.setSuccess(true);
			ri.setResult(o);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		
		return ri;
	}
	
	@RequestMapping("/get_org.html")
	public @ResponseBody
	ResponseInfo getOrg(HttpServletRequest request, OrganizationInfo org) {
		ResponseInfo ri = new ResponseInfo();
		
		String userid = org.getUserid();
		
		if(userid != null) {
			OrganizationInfo o = this.orgService.findByUserid(userid);
			ri.setSuccess(true);
			ri.setResult(o);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		
		return ri;
	}
	@RequestMapping(value = "/editOrginfo")
	public String editOrginfo(HttpServletRequest request, BlogUser user, ModelMap mm) {
		BlogUser bu = getSessionUser(request);
		
		mm.addAttribute("org", this.orgService.findByUserid(bu.getUserid()));
		return "editorginfo";
	}
}
