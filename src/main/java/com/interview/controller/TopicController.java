package com.interview.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.interview.service.TopicService;
import com.interview.util.Util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Controller
@RequestMapping("/rest/topics")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicDAO topicDAO;
	
	@Autowired
	private QuestionDAO questionDAO;
	
	@RequestMapping(value="/getTopic/{topicId}",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody Topic getTopic(@PathVariable String topicId){
		return topicDAO.getTopicById(topicId);
	}
	
	@RequestMapping(value="/listAllTopics",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody List<Topic> listAllTopics(){
		return topicService.getAllTopics();
	}
	
	@RequestMapping(value="/parentTopic/{topicId}",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody Topic getParentTopic(@PathVariable String topicId){
		Topic topic = topicDAO.getTopicById(topicId);
		if(topic.getParent_topic_id()!=null){
			return topicDAO.getTopicById(topic.getParent_topic_id());
		}
		return null;
	}
	
	@RequestMapping(value="/listAllMainTopics",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody List<Topic> listAllMainTopics(){
		return topicDAO.getAllMainTopics();
	}
	
	@RequestMapping(value="/listSubTopics/{parentTopicId}",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody List<Topic> listSubTopics(@PathVariable String parentTopicId){
		return topicDAO.getSubTopics(parentTopicId);
	}
	
	
	@RequestMapping(value="/parentTopicsLink/{topicId}",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody Map<String,String> parentTopicsLink(@PathVariable String topicId){

		Map<String,String> response = new HashMap<String,String>();
		StringBuilder parentTopicIdsLink = new StringBuilder();
		StringBuilder parentTopicNamesLink = new StringBuilder();
		
		if(topicId.equals("home")){
			response.put("status", "success");
			response.put("topicId",topicId);
			response.put("topicNameLink", parentTopicNamesLink.toString());
			response.put("topicIdLink", parentTopicIdsLink.toString());
			return response;
		}
		Topic tempParentTopic = topicDAO.getTopicById(topicId);
		
		if(tempParentTopic == null){
			response.put("status", "Error");
			response.put("message", "Topic doesn't exist in the system");
			return response;
		}
		
		while(true){
			parentTopicIdsLink.append(tempParentTopic.getTopic_id()).append("-->");
			parentTopicNamesLink.append(tempParentTopic.getTopic_name()).append("-->");
			if(tempParentTopic.getParent_topic_id().equals("home")){
				break;
			}
			tempParentTopic = topicDAO.getTopicById(tempParentTopic.getParent_topic_id());
		}
		
		String[] topicIds = parentTopicIdsLink.toString().split("-->");
		String[] topicNames = parentTopicNamesLink.toString().split("-->");
		parentTopicIdsLink.setLength(0);
		parentTopicNamesLink.setLength(0);
		if(topicIds == null || topicIds.length == 0){
			response.put("topicId",topicId);
			response.put("link", "");
			return response;
		}
		for(int i=topicIds.length-1;i>=0;i--){
			if(topicIds[i]!=null){
			parentTopicIdsLink.append(topicIds[i]);
			parentTopicNamesLink.append(topicNames[i]);
			}
			if(i!=0){
				parentTopicIdsLink.append("-->");
				parentTopicNamesLink.append("-->");
			}
		}
		response.put("status", "success");
		response.put("topicId",topicId);
		response.put("topicNameLink", parentTopicNamesLink.toString());
		response.put("topicIdLink", parentTopicIdsLink.toString());
		return response;
		
	}
	
	@RequestMapping(value="/deleteTopic/{topicId}",headers="Accept=application/json",method=RequestMethod.DELETE)
	public @ResponseBody Map<String,Object> deleteTopic(@PathVariable String topicId){
		return topicDAO.removeTopic(topicId);
	}
	
	@RequestMapping(value="/addOrEditTopic",headers="Accept=application/json",consumes="application/json",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveOrUpdateTopic(@RequestBody String topicJSONString){
		
		Map<String,Object> response = new HashMap<String, Object>();
		Topic topic = new Topic();
		JSONObject topicJSON = new JSONObject(topicJSONString);
		topic.setTopic_id(topicJSON.getString("topic_id"));
		topic.setTopic_name(topicJSON.getString("topic_name"));
		topic.setTopic_type(topicJSON.getString("topic_type"));
		topic.setParent_topic_id(topicJSON.getString("parent_topic_id"));
		
		String old_topic_id = topicJSON.getString("old_topic_id");
		Topic oldTopic = topicDAO.getTopicById(old_topic_id);
		String actionType = (oldTopic!=null)?"update":"add";		
		
		response = topicDAO.saveOrUpdateTopic(topic);
		response.put("actionType", actionType);
		
		if(response.get("success").equals("true") && actionType.equals("update")){
			Topic updatedTopic = (Topic) response.get("topic");
			response = topicDAO.updateTopicReferences(oldTopic, updatedTopic);
		}
		
		return response;
		
	}
	
	@RequestMapping(value="/generatePDF/{topicId}",headers="Accept=application/json",produces="application/pdf",method=RequestMethod.GET)
	public void generatePDFforTopic(@PathVariable String topicId, HttpServletResponse response) throws DocumentException, IOException{
		
		List<TopicContent> topicsWithQuestions = new LinkedList<TopicController.TopicContent>();
		List<Topic> subTopics = topicDAO.getSubTopics(topicId);
		
		for(Topic subTopic:subTopics){
			topicsWithQuestions.addAll(getAllQuestionsForTopic(subTopic));
		}
		  
		  StringBuilder sb = new StringBuilder();
		 
		   //get Instance of the PDFWriter
		   //pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\I061199\\Desktop\\Test.pdf")));
		  for(TopicContent topicContent:topicsWithQuestions){
			  sb.append(topicContent.topicName);
			  for(Question topicQuestion:topicContent.getQuestions()){
				  sb.append(topicQuestion.getQuestion_description());
				  sb.append(topicQuestion.getAnswer_explanation());
				  sb.append(topicQuestion.getAdditional_reference());
				
			  }
			 
		  }
		   
		  
		  //create a new document
		  Document document = new Document();
		  PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\I061199\\Desktop\\Test.pdf")));
		//open document
		   document.open();
		 //document header attributes
		   document.addAuthor("Girish");
		   document.addCreationDate();
		   document.addProducer();
		   document.addCreator("Girish");
		   document.addTitle("Questions for "+topicId);
		   document.setPageSize(PageSize.LETTER);

		   

		  
		   //get the XMLWorkerHelper Instance
		   XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
		   
		  //convert to PDF
		  worker.parseXHtml(pdfWriter, document, new StringReader(sb.toString()));
		   
		   
		  
		   /*response.setContentType("application/pdf");
		   response.setHeader("Content-disposition", "attachment; filename="+topicId+".pdf");
		   
	        OutputStream os = response.getOutputStream();
	        os.write(pdfBytes);
	        //byteArrayOutputStream.writeTo(os);
	        os.flush();
*/
	        //close the document
			   document.close();

			   //close the writer
			   pdfWriter.close();
	     
	        
		
	}
		
	
	
	private List<TopicContent> getAllQuestionsForTopic(Topic topic) {
		List<TopicContent> subTopicsAndQuestionsContent = new LinkedList<TopicContent>();
		List<Topic> subTopics = topicDAO.getSubTopics(topic.getTopic_id());
		if(subTopics!=null && subTopics.size()>0){
			for(Topic subTopic:subTopics){
				return getAllQuestionsForTopic(subTopic);
			}
		} else
		{
			List<Question> questions = questionDAO.getAllQuestionsByTopicId(topic.getTopic_id());

		    TopicContent topicContent = new TopicContent();
			topicContent.setTopicId(topic.getTopic_id());
			topicContent.setParentTopicId(topic.getParent_topic_id());
			topicContent.setTopicName(topic.getTopic_name());
			String parentTopicName = topicDAO.getTopicById(topic.getTopic_id()).getTopic_name();
			topicContent.setParentTopicName(parentTopicName);
			topicContent.setQuestions(questions);
			subTopicsAndQuestionsContent.add(topicContent);
		}
		
		
		return subTopicsAndQuestionsContent;
	}

	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
 

	@RequestMapping(value="/checkTopicBeforeDelete/{topicId}",headers="Accept=application/json",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> checkIfTopicContainsSubTopicsOrQuestions(@PathVariable String topicId){
		Map<String,Object> response = new HashMap<String, Object>();
		List<Topic> subTopics = topicDAO.getSubTopics(topicId);
		if(subTopics!=null && !subTopics.isEmpty()){
			response.put("status","true");
			response.put("message","Topic contains sub topics. Can not delete!!!");
			return response;
		} 
		
		List<Question> questions = questionDAO.getAllQuestionsByTopicId(topicId);
		
		if(questions!=null && !questions.isEmpty()){
			response.put("status","true");
			response.put("message","Topic contains Questions. Can not delete!!!");
			return response;
		}
			
		response.put("status", "false");
		return response;		
		
	}
	
 	
	@RequestMapping("/{topicId}")
	public String showTopicsorQuestions(@PathVariable("topicName") String  topicId){
		Topic topic = topicDAO.getTopicById(topicId);
		if(topic.getTopic_type().equals("sub")){
			return Util.appendPrefixSuffix("html", "subtopics");
		}
		else
		{		
		return Util.appendPrefixSuffix("jsp", "questions");
		}
	}
	
	
	
	class TopicContent{
		
		private String topicId, topicName;
		private String parentTopicId, parentTopicName;
		private List<Question> questions = new LinkedList<Question>();
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
		public String getTopicName() {
			return topicName;
		}
		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
		public String getParentTopicId() {
			return parentTopicId;
		}
		public void setParentTopicId(String parentTopicId) {
			this.parentTopicId = parentTopicId;
		}
		public String getParentTopicName() {
			return parentTopicName;
		}
		public void setParentTopicName(String parentTopicName) {
			this.parentTopicName = parentTopicName;
		}
		public List<Question> getQuestions() {
			return questions;
		}
		public void setQuestions(List<Question> questions) {
			this.questions = questions;
		}
		
				
	}

	

}
