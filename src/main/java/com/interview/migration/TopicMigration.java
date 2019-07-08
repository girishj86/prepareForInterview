package com.interview.migration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.interview.dao.QuestionDAO;
import com.interview.dao.TopicDAO;
import com.interview.entities.Question;
import com.interview.entities.Topic;
import com.itextpdf.text.log.SysoCounter;

public class TopicMigration {

	public static void main(String[] args) throws JAXBException, JsonGenerationException, JsonMappingException, IOException {
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring-servlet.xml");
		
		TopicDAO topicDAO = ctx.getBean(TopicDAO.class);
		QuestionDAO questionDAO = ctx.getBean(QuestionDAO.class);
		
		List<Topic> topics = topicDAO.getAllTopics();
		List<Question> questions = new ArrayList<Question>();
		for(Topic topic:topics){
			questions.addAll(questionDAO.getAllQuestionsByTopicId(topic.getTopic_id()));
		}
		
		new File("migration").mkdir();
		
		//generateXML(topics, questions);
	    
	    //generateJSON(topics, questions);
		migrateJSON(topicDAO,questionDAO);
	    
	}
	
	private static void migrateJSON(TopicDAO topicDAO,QuestionDAO questionDAO) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		//List<Topic> parsedTopics = (List<Topic>) mapper.readValue(new File("migration/topics.json"), new TypeReference<List<Topic>>(){});
	    List<Question> parsedQuestions = (List<Question>) mapper.readValue(new File("migration/questions.json"), new TypeReference<List<Question>>(){});
		/*for(Topic topic:parsedTopics){
			topicDAO.saveOrUpdateTopic(topic);
		}*/
	    //questionDAO.addQuestions(parsedQuestions);
	    for(Question question:parsedQuestions){
	    	Map<String,Object> result = questionDAO.saveOrUpdateQuestion(question);
	    	//System.out.println(result);
	    }
	    /*TreeSet<String> topics = new TreeSet<String>();
	    for(Question question:parsedQuestions){
	    	topics.add(question.getTopic().getTopic_id());
	    	topics.add(question.getTopic().getParent_topic_id());
	    }
	    System.out.println(topics);
	    System.out.println(topics.size());*/
	}

	private static void generateJSON(List<Topic> topics, List<Question> questions)
			throws IOException, JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
	/*    mapper.writeValue(new File("migration/topics.json"), topics);
	    mapper.writeValue(new File("migration/questions.json"), questions);
	    
	    List<Topic> parsedTopics = (List<Topic>) mapper.readValue(new File("migration/topics.json"), new TypeReference<List<Topic>>(){});*/
	    List<Question> parsedQuestions = (List<Question>) mapper.readValue(new File("migration/questions.json"), new TypeReference<List<Question>>(){});
	    System.out.println(parsedQuestions.get(23));
	    
	}

	private static void generateXML(List<Topic> topics, List<Question> questions)
			throws JAXBException, PropertyException {
		Topics topicsWrapper = new Topics();
		topicsWrapper.setTopics(topics);
		
		JAXBContext jaxbContextForTopic = JAXBContext.newInstance(Topics.class);
	    Marshaller jaxbMarshallerForTopic = jaxbContextForTopic.createMarshaller();
	 
	    jaxbMarshallerForTopic.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the Topic list in console
	    //jaxbMarshallerForTopic.marshal(topicsWrapper, System.out);
	     
	    //Marshal the Topic list in file
	    jaxbMarshallerForTopic.marshal(topicsWrapper, new File("migration/topics.xml"));
	    
	    Questions questionsWrapper = new Questions();
	    questionsWrapper.setQuestions(questions);
		
		JAXBContext jaxbContextForQuestions = JAXBContext.newInstance(Questions.class);
	    Marshaller jaxbMarshallerForQuestions = jaxbContextForQuestions.createMarshaller();
	 
	    jaxbMarshallerForQuestions.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the Question list in console
	    //jaxbMarshallerForQuestions.marshal(questionsWrapper, System.out);
	     
	    //Marshal the Question list in file
	    jaxbMarshallerForQuestions.marshal(questionsWrapper, new File("migration/questions.xml"));
	}

}

@XmlRootElement(name="topics")
class Topics{

	
	private List<Topic> topics;

	@XmlElement(name="topic")
	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
}

@XmlRootElement(name="questions")
class Questions{
	private List<Question> questions;

	@XmlElement(name="question")
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
