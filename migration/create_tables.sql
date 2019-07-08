CREATE TABLE `topic` (
  `topic_id` varchar(45) NOT NULL,
  `topic_type` varchar(45) NOT NULL,
  `topic_name` varchar(45) NOT NULL,
  `parent_topic_id` varchar(45) DEFAULT NULL,
  `style_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  UNIQUE KEY `topic_id_UNIQUE` (`topic_id`),
  KEY `fk_style_idx` (`style_id`),
  CONSTRAINT `fk_style` FOREIGN KEY (`style_id`) REFERENCES `style` (`style_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `style` (
  `style_id` int(11) NOT NULL AUTO_INCREMENT,
  `bkg_clr` varchar(45) DEFAULT NULL,
  `font_size` varchar(45) DEFAULT NULL,
  `font_clr` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_description` text NOT NULL,
  `answer_explanation` mediumtext NOT NULL,
  `additional_reference` text,
  `importance` varchar(45) DEFAULT NULL,
  `topic_id` varchar(45) DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  UNIQUE KEY `question_id_UNIQUE` (`question_id`),
  KEY `fk_topic_id_idx` (`topic_id`),
  CONSTRAINT `fk_topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8


