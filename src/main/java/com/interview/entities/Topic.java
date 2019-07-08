package com.interview.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name="topic")
	@Entity
	@Table(name="pfi.topic")
	public class Topic implements Comparable<Topic>{
		
		
		@Id
		@Column(name="topic_id")
		private String topic_id;
		
		
		@Column(name="topic_type")
		private String topic_type;
		
		
		@Column(name="topic_name")
		private String topic_name;
		
		
		@Column(name="parent_topic_id")
		private String parent_topic_id;
		
		/*@OneToOne
		@JoinColumn(name="style_id")
		private Style style;*/
		
		@XmlAttribute(name="id")
		public String getTopic_id() {
			return topic_id;
		}

		public void setTopic_id(String topic_id) {
			this.topic_id = topic_id;
		}
		
		@XmlAttribute(name="type")
		public String getTopic_type() {
			return topic_type;
		}

		public void setTopic_type(String topic_type) {
			this.topic_type = topic_type;
		}

		@XmlAttribute(name="name")
		public String getTopic_name() {
			return topic_name;
		}

		public void setTopic_name(String topic_name) {
			this.topic_name = topic_name;
		}

		@XmlAttribute(name="parent_id")
		public String getParent_topic_id() {
			return parent_topic_id;
		}

		public void setParent_topic_id(String parent_topic_id) {
			this.parent_topic_id = parent_topic_id;
		}

		@Override
		public String toString() {
			return "Topic [topic_id=" + topic_id + ", topic_type=" + topic_type + ", topic_name=" + topic_name
					+ ", parent_topic_id=" + parent_topic_id + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((topic_id == null) ? 0 : topic_id.hashCode());
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
			Topic other = (Topic) obj;
			if (topic_id == null) {
				if (other.topic_id != null)
					return false;
			} else if (!topic_id.equals(other.topic_id))
				return false;
			return true;
		}

		public int compareTo(Topic topic) {
			return this.getTopic_name().compareToIgnoreCase(topic.getTopic_name());
		}

		/*public Style getStyle() {
			return style;
		}

		public void setStyle(Style style) {
			this.style = style;
		}*/

		
		
		

		
		
}
