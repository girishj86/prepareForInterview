package com.interview.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="question")
@Entity
@Table(name="pfi.question")
public class Question implements Comparable<Question>{
	
	
	@Id
	@SequenceGenerator(name="QUESTION_ID_GENERATOR", sequenceName="QUESTION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTION_ID_GENERATOR")
	@Column(name="question_id")
	private Integer question_id;
	
	
	@Column(name="question_description",columnDefinition = "TEXT")
	private String question_description;
	
	
	@Column(name="answer_explanation",nullable = true,columnDefinition = "MEDIUMTEXT")
	private String answer_explanation;
	
	
	@Column(name="additional_reference",nullable = true,columnDefinition = "TEXT")
	private String additional_reference;
	
	@Column(name="importance",nullable = true)
	private String importance;
	
	@ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
	private Topic topic;
	
	@Column(name="notes",nullable = true)
	private String notes;

	@XmlAttribute(name="id")
	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	@XmlElement(name="description")
	public String getQuestion_description() {
		return question_description;
	}

	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}
	
	@XmlElement(name="answer")
	public String getAnswer_explanation() {
		return answer_explanation;
	}

	public void setAnswer_explanation(String answer_explanation) {
		this.answer_explanation = answer_explanation;
	}

	@XmlElement(name="references")
	public String getAdditional_reference() {
		return additional_reference;
	}

	public void setAdditional_reference(String additional_reference) {
		this.additional_reference = additional_reference;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	@XmlElement(name="topic")
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", topic id=" + topic.getTopic_id() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question_id == null) ? 0 : question_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (question_id == null) {
			if (other.question_id != null)
				return false;
		} else if (!question_id.equals(other.question_id))
			return false;
		return true;
	}

	public int compareTo(Question question) {
		return this.getQuestion_id().compareTo(question.getQuestion_id());
	}

}
