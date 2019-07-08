package com.interview.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.interview.entities.Question;
import com.interview.entities.Topic;
import com.itextpdf.text.log.SysoCounter;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TopicDAO topicDAO;

	@Transactional
	public List<Question> getAllQuestionsByTopicId(String topicId) {
		Session session = null;
		List<Question> questions = null;
		Transaction tx = null;
		Topic topic = topicDAO.getTopicById(topicId);
		try {
			session = sessionFactory.openSession();
			Criteria questionCriteria = session.createCriteria(Question.class);
			questionCriteria.add(Restrictions.eq("topic", topic));
			questions = questionCriteria.list();
			if(questions != null && !questions.isEmpty()){
				for(Question question:questions){
					String additionalReference = question.getAdditional_reference();
					if(additionalReference != null && additionalReference.contains("<a href")){
						additionalReference = additionalReference.replaceAll("<a href", "<a target=\"_blank\" href");
						question.setAdditional_reference(additionalReference);
						tx = session.beginTransaction();
						session.saveOrUpdate(question);
						tx.commit();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.flush();
			session.clear();
			session.close();
		}
		Collections.sort(questions);
		return questions;
	}

	@Transactional
	public void addQuestions(List<Question> questions) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (int i = 0; i < questions.size(); i++) {
				Question question = questions.get(i);
				if(question.getAnswer_explanation() == null){
					question.setAnswer_explanation(StringUtils.EMPTY);
				}
				if(question.getAdditional_reference() == null){
					question.setAdditional_reference((StringUtils.EMPTY));
				}
				session.save(question);
				if (i % 25 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

	}

	public Map<String, Object> saveOrUpdateQuestion(Question question) {
		Session session = null;
		String success = "false";
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if(question.getAnswer_explanation() == null){
				question.setAnswer_explanation(StringUtils.EMPTY);
			}
			if(question.getAdditional_reference() == null){
				question.setAdditional_reference((StringUtils.EMPTY));
			} else {
				String additionReference = question.getAdditional_reference();
				additionReference = additionReference.replaceAll("<a href", "<a target=\"_blank\" href");
				question.setAdditional_reference(additionReference);
			}
			
			session.saveOrUpdate(question);
			tx.commit();
			success = "true";
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			success = "false";
		} finally {
			session.flush();
			session.clear();
			session.close();
		}

		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("question", question);
		statusMap.put("success", success);
		return statusMap;

	}

	public Map<String, Object> removeQuestion(String questionId) {

		Map<String, Object> response = new HashMap<String, Object>();
		String success = "false";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query deleteQuery = session.createQuery("delete Question where question_id = :questionId");
			deleteQuery.setParameter("questionId", Integer.parseInt(questionId));
			if (deleteQuery.executeUpdate() == 1) {
				success = "true";
			}
			tx.commit();

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

}
