package org.anne.message;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * MessageAccessory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.anne.message.MessageAccessory
 * @author MyEclipse Persistence Tools
 */

public class MessageAccessoryDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(MessageAccessoryDAO.class);
	// property constants
	public static final String FILEID = "fileid";
	public static final String FILENAME = "filename";
	public static final String TYPE = "type";
	public static final String MESSAGEID = "messageid";

	protected void initDao() {
		// do nothing
	}

	public void save(MessageAccessory transientInstance) {
		log.debug("saving MessageAccessory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MessageAccessory persistentInstance) {
		log.debug("deleting MessageAccessory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MessageAccessory findById(java.lang.Integer id) {
		log.debug("getting MessageAccessory instance with id: " + id);
		try {
			MessageAccessory instance = (MessageAccessory) getHibernateTemplate()
					.get("org.anne.test.MessageAccessory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MessageAccessory> findByExample(MessageAccessory instance) {
		log.debug("finding MessageAccessory instance by example");
		try {
			List<MessageAccessory> results = (List<MessageAccessory>) getHibernateTemplate()
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
		log.debug("finding MessageAccessory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MessageAccessory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<MessageAccessory> findByFileid(Object fileid) {
		return findByProperty(FILEID, fileid);
	}

	public List<MessageAccessory> findByFilename(Object filename) {
		return findByProperty(FILENAME, filename);
	}

	public List<MessageAccessory> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<MessageAccessory> findByMessageid(Object messageid) {
		return findByProperty(MESSAGEID, messageid);
	}

	public List findAll() {
		log.debug("finding all MessageAccessory instances");
		try {
			String queryString = "from MessageAccessory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MessageAccessory merge(MessageAccessory detachedInstance) {
		log.debug("merging MessageAccessory instance");
		try {
			MessageAccessory result = (MessageAccessory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MessageAccessory instance) {
		log.debug("attaching dirty MessageAccessory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MessageAccessory instance) {
		log.debug("attaching clean MessageAccessory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MessageAccessoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MessageAccessoryDAO) ctx.getBean("MessageAccessoryDAO");
	}
}