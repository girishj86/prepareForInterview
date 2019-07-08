package com.interview.service;

import java.util.List;


import org.json.JSONArray;

import com.interview.entities.Question;

public interface QuestionService {
	
	List<Question> listQuestions();
	List<Question> readQuestionsFromExcel();

}
