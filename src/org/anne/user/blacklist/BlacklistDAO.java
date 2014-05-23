package org.anne.user.blacklist;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BlacklistDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BlacklistDAO.class);
	// property constants
	public static final String BLACKLIST_USERID = "blacklistUserid";
	public static final String USERID = "userid";

	protected void initDao() {
		// do nothing
	}

	public void save(Blacklist transientInstance) {
		log.debug("saving Blacklist instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Blacklist persistentInstance) {
		log.debug("deleting Blacklist instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Blacklist findById(java.lang.Integer id) {
		log.debug("getting Blacklist instance with id: " + id);
		try {
			Blacklist instance = (Blacklist) getHibernateTemplate().get(
					"org.anne.test.Blacklist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Blacklist> findByExample(Blacklist instance) {
		log.debug("finding Blacklist instance by example");
		try {
			List<Blacklist> results = (List<Blacklist>) getHibernateTemplate()
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
		log.debug("finding Blacklist instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Blacklist as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Blacklist> findByBlacklistUserid(Object blacklistUserid) {
		return findByProperty(BLACKLIST_USERID, blacklistUserid);
	}

	public List<Blacklist> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findAll() {
		log.debug("finding all Blacklist instances");
		try {
			String queryString = "from Blacklist";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Blacklist merge(Blacklist detachedInstance) {
		log.debug("merging Blacklist instance");
		try {
			Blacklist result = (Blacklist) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Blacklist instance) {
		log.debug("attaching dirty Blacklist instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Blacklist instance) {
		log.debug("attaching clean Blacklist instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BlacklistDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BlacklistDAO) ctx.getBean("BlacklistDAO");
	}
}