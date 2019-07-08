package com.interview.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interview.dao.QuestionDAO;
import com.interview.dao.TopicDAO;
import com.interview.entities.Question;
import com.interview.entities.Topic;

@Controller
@RequestMapping("/rest/questions")
public class QuestionsController {

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@RequestMapping(value = "/{topicId}", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody List<Question> getAllQuestions(@PathVariable String topicId) {

		return questionDAO.getAllQuestionsByTopicId(topicId);

	}

	@RequestMapping(value = "/addOrEditQuestion", headers = "Accept=application/json", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveOrUpdateQuestion(@RequestBody String questionJSONString) {

		JSONObject questionJSONObject = new JSONObject(questionJSONString);
		Question question = new Question();
		if (questionJSONObject.keySet().contains("question_id")) {
			question.setQuestion_id(questionJSONObject.getInt("question_id"));
		}
		question.setQuestion_description(questionJSONObject.getString("question_description"));
		question.setAnswer_explanation(questionJSONObject.getString("answer_explanation"));
		question.setAdditional_reference(questionJSONObject.getString("additional_reference"));
		// question.setImportance(questionJSONObject.getString("importance"));
		question.setTopic(topicDAO.getTopicById(questionJSONObject.getString("topic_id")));

		return questionDAO.saveOrUpdateQuestion(question);
	}

	@RequestMapping(value = "/deleteQuestion/{questionId}", headers = "Accept=application/json", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteQuestion(@PathVariable String questionId) {
		return questionDAO.removeQuestion(questionId);
	}

	@RequestMapping(value = "/searchQuery/{query}", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody Set<Question> getAllSearchResultQuestions(@PathVariable String query) {

		String[] queries = query.split("\\s+");
		Set<Topic> resultTopics = new HashSet<Topic>();
		Set<Question> resultQuestions = new HashSet<Question>();
		List<Topic> topics = topicDAO.getAllTopics();

		/*for (Topic topic : topics) {

			if (StringUtils.equalsIgnoreCase(topic.getTopic_name(), query)) {
				resultTopics.add(topic);
				break;
			}

			String[] splitTopics = topic.getTopic_name().split("//s+");

			for (String splitTopic : splitTopics) {
				for (String qryString : queries) {
					if (StringUtils.equalsIgnoreCase(splitTopic, qryString)) {
						resultTopics.add(topic);
						break;
					}
				}
			}

			if (resultTopics.size() > 1)
				break;
			
			if (StringUtils.containsIgnoreCase(topic.getTopic_name(), query)) {
				resultTopics.add(topic);
				continue;
			}
			
			for (String qryString : queries) {
				if (StringUtils.containsIgnoreCase(topic.getTopic_name(), qryString)) {
					resultTopics.add(topic);
					continue;
				}
			}
		}

		for (Topic topic : resultTopics) {
			List<Question> topicQuestions = questionDAO.getAllQuestionsByTopicId(topic.getTopic_id());
			for (Question question : topicQuestions) {
				if (StringUtils.containsIgnoreCase(question.getQuestion_description(), query) || 
						StringUtils.containsIgnoreCase(question.getAnswer_explanation(), query) ) {
					resultQuestions.add(question);
					continue;
				}
				for (String qryString : queries) {
					if (StringUtils.containsIgnoreCase(question.getQuestion_description(), qryString) &&
							StringUtils.containsIgnoreCase(question.getAnswer_explanation(), qryString) ) {
						resultQuestions.add(question);
						break;
					}
				}
			}
		}*/
		
		
		for(Topic topic:topics){
			List<Question> questionsTopic = questionDAO.getAllQuestionsByTopicId(topic.getTopic_id());
			for(Question question:questionsTopic){
				for(String queryString:queries){
					if (StringUtils.containsIgnoreCase(topic.getTopic_name(), queryString) || ((StringUtils.containsIgnoreCase(question.getQuestion_description(), queryString) || 
							StringUtils.containsIgnoreCase(question.getAnswer_explanation(), queryString)))){
						resultQuestions.add(question);
						break;
					}
				}
			}
		}
		
		return resultQuestions;
	}
}