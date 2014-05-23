package org.anne.message;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * MessageReply entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.anne.message.MessageReply
 * @author MyEclipse Persistence Tools
 */

public class MessageReplyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(MessageReplyDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String CREATEUSER = "createuser";

	protected void initDao() {
		// do nothing
	}

	public void save(MessageReply transientInstance) {
		log.debug("saving MessageReply instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MessageReply persistentInstance) {
		log.debug("deleting MessageReply instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MessageReply findById(java.lang.Integer id) {
		log.debug("getting MessageReply instance with id: " + id);
		try {
			MessageReply instance = (MessageReply) getHibernateTemplate().get(
					"org.anne.test.MessageReply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MessageReply> findByExample(MessageReply instance) {
		log.debug("finding MessageReply instance by example");
		try {
			List<MessageReply> results = (List<MessageReply>) getHibernateTemplate()
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
		log.debug("finding MessageReply instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MessageReply as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<MessageReply> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<MessageReply> findByCreateuser(Object createuser) {
		return findByProperty(CREATEUSER, createuser);
	}

	public List findAll() {
		log.debug("finding all MessageReply instances");
		try {
			String queryString = "from MessageReply";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MessageReply merge(MessageReply detachedInstance) {
		log.debug("merging MessageReply instance");
		try {
			MessageReply result = (MessageReply) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MessageReply instance) {
		log.debug("attaching dirty MessageReply instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MessageReply instance) {
		log.debug("attaching clean MessageReply instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MessageReplyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MessageReplyDAO) ctx.getBean("MessageReplyDAO");
	}
}