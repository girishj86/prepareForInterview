package com.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.dao.TopicDAO;
import com.interview.entities.Topic;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicDAO topicDAO;

	public List<Topic> getAllTopics() {
		
		return topicDAO.getAllTopics();
	}

}
