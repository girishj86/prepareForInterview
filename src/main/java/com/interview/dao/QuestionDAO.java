package com.interview.dao;

import java.util.List;
import java.util.Map;

import com.interview.entities.Question;


public interface QuestionDAO {
	
	List<Question> getAllQuestionsByTopicId(String topicId);
	void addQuestions(List<Question> questions);
	Map<String,Object> saveOrUpdateQuestion(Question question);
	Map<String,Object> removeQuestion(String questionId);
	

}
