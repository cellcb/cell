package org.anne.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	BlogUserDAO blogUserDAO;

	public BlogUser findBlogUserByUserName(BlogUser entity) {
		return blogUserDAO.findBlogUserByUserName(entity);
	}
	public BlogUser findBlogUserByUserId(String userId) {
		return blogUserDAO.getById(userId);
	}
	@Transactional
	public void createBlogUser(BlogUser entity){
		blogUserDAO.addBlogUser(entity);
	}
	public BlogUser findBlogUserByEmail(BlogUser entity){
		return blogUserDAO.findBlogUserByEmail(entity);
	}
	@Transactional
	public void updateUserProfileByUserName(BlogUser bloguser){
		blogUserDAO.updateUserProfile(bloguser.getIcon(), bloguser.getUsername());
	}
	
	public List<BlogUser> queryOrganizations(){
		return blogUserDAO.queryOrganizations();
	}
}
