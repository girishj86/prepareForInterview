package com.interview.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.interview.entities.Style;
import com.interview.entities.Topic;

@Repository("topicDAO")
public class TopicDAOImpl implements TopicDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Topic> getAllTopics() {
		Session session = sessionFactory.openSession();
		List<Topic> topics = session.createQuery("from Topic").list();
		session.flush();
		session.close();
		Collections.sort(topics);
		return topics;
	}

	public Topic getTopicById(String topicId) {
		
		Session session = sessionFactory.openSession();
		Topic topic = (Topic) session.get(Topic.class, topicId);
		session.flush();
		session.close();
		return topic;
	}
	
	public List<Topic> getAllMainTopics(){
		Session session = sessionFactory.openSession();
		Criteria mainTopicsCriteria = session.createCriteria(Topic.class);
		mainTopicsCriteria.add(Restrictions.eq("topic_type","main"));
		List<Topic> mainTopics = mainTopicsCriteria.list();
		Collections.sort(mainTopics);
		session.flush();
		session.close();
		return mainTopics;
	}
	
	public List<Topic> getSubTopics(String parentTopicId){
		Session session = sessionFactory.openSession();
		Criteria subTopicsCriteria = session.createCriteria(Topic.class);
		subTopicsCriteria.add(Restrictions.eq("parent_topic_id",parentTopicId));
		List<Topic> subTopics = subTopicsCriteria.list();
		Collections.sort(subTopics);
		session.flush();
		session.close();
		return subTopics;
	}
	
	public Map<String,Object> removeTopic(String topicId) {
		Map<String,Object> response = new HashMap<String,Object>();
		String success = "false";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query deleteQuery = session.createQuery("delete Topic where topic_id = :topicId");
			deleteQuery.setParameter("topicId", topicId);
			if(deleteQuery.executeUpdate() == 1)
			{
				success = "true";
			}
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			session.flush();
			session.close();
		}
		response.put("success", success);
		return response;

	}

	public Map<String,Object> saveOrUpdateTopic(Topic topic) {
		Map<String,Object> response = new HashMap<String,Object>();
		String success = "false";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.saveOrUpdate(topic);
			tx.commit();
			success = "true";
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			session.flush();
			session.close();
		}
		
		response.put("topic", topic);
		response.put("success",	success);
		return response;
	}
	
	public Map<String, Object> updateTopicReferences(Topic oldTopic,Topic updatedTopic) {

		Map<String, Object> response = new HashMap<String, Object>();
		String success = "false";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			Query selectTopicQuery = session
					.createSQLQuery("select topic_id from Topic where parent_topic_id = :topicId");
			selectTopicQuery.setParameter("topicId", oldTopic.getTopic_id());
			if (!selectTopicQuery.list().isEmpty()) {
				Query updateTopicsQuery = session.createSQLQuery(
						"update Topic set parent_topic_id = :newParentTopicId where parent_topic_id = :oldParentTopicId");
				updateTopicsQuery.setParameter("oldParentTopicId", oldTopic.getTopic_id());
				updateTopicsQuery.setParameter("newParentTopicId", updatedTopic.getTopic_id());
				response.put("rowsUpdated", updateTopicsQuery.executeUpdate());
			}
			tx.commit();
			success = "true";
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.flush();
			session.close();
		}
		
		response.put("success", success);
		return response;

	}

	/*public Style getStyleByTopicId(String topicId) {
		Topic topic = getTopicById(topicId);
		return (topic !=null)? topic.getStyle():null;
	}*/

	

}
