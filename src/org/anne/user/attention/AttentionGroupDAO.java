package org.anne.user.attention;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AttentionGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.anne.user.attention.AttentionGroup
 * @author MyEclipse Persistence Tools
 */

public class AttentionGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AttentionGroupDAO.class);
	// property constants
	public static final String GROUPNAME = "groupname";
	public static final String USERID = "userid";

	protected void initDao() {
		// do nothing
	}

	public void save(AttentionGroup transientInstance) {
		log.debug("saving AttentionGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AttentionGroup persistentInstance) {
		log.debug("deleting AttentionGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AttentionGroup findById(java.lang.Integer id) {
		log.debug("getting AttentionGroup instance with id: " + id);
		try {
			AttentionGroup instance = (AttentionGroup) getHibernateTemplate()
					.get("org.anne.test.AttentionGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AttentionGroup> findByExample(AttentionGroup instance) {
		log.debug("finding AttentionGroup instance by example");
		try {
			List<AttentionGroup> results = (List<AttentionGroup>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AttentionGroup instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AttentionGroup as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AttentionGroup> findByGroupname(Object groupname) {
		return findByProperty(GROUPNAME, groupname);
	}

	public List<AttentionGroup> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findAll() {
		log.debug("finding all AttentionGroup instances");
		try {
			String queryString = "from AttentionGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AttentionGroup merge(AttentionGroup detachedInstance) {
		log.debug("merging AttentionGroup instance");
		try {
			AttentionGroup result = (AttentionGroup) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AttentionGroup instance) {
		log.debug("attaching dirty AttentionGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AttentionGroup instance) {
		log.debug("attaching clean AttentionGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttentionGroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AttentionGroupDAO) ctx.getBean("AttentionGroupDAO");
	}
}