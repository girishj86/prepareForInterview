package com.interview.dao;

import java.util.List;
import java.util.Map;

import com.interview.entities.Style;
import com.interview.entities.Topic;

public interface TopicDAO {
	
	public List<Topic> getAllTopics();
	public Topic getTopicById(String topicId);
	public List<Topic> getAllMainTopics();
	public List<Topic> getSubTopics(String parentTopicId);
	public Map<String,Object> saveOrUpdateTopic(Topic topic);
	public Map<String,Object> removeTopic(String topicId);
	public Map<String, Object> updateTopicReferences(Topic oldTopic,Topic updatedTopic);
	//public Style getStyleByTopicId(String topicId);

}
