package com.interview.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interview.util.Util;

@Controller
public class FrontController {

	@RequestMapping(value = { "/home", "/topic/{topicId}" })
	public String showUserHome() {
		return Util.appendPrefixSuffix("html", "home_user");
	}
	
	@RequestMapping(value = "/")
	public String homeUser() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = { "/admin/home", "/admin/topic/{topicId}" })
	public String showAdminHome() {
		return Util.appendPrefixSuffix("html", "home_admin");
	}
	
	@RequestMapping("/questions/{topicId}")
	public String showUserQuestions(@PathVariable String topicId, Map<String, Object> map) {
		return Util.appendPrefixSuffix("html", "questions_user");
	}
	
	@RequestMapping("/admin/questions/{topicId}")
	public String showAdminQuestions(@PathVariable String topicId, Map<String, Object> map) {
		return Util.appendPrefixSuffix("html", "questions_admin");
	}
	
	@RequestMapping("/search")
	public String showSearchResults() {
		return Util.appendPrefixSuffix("html", "search_results");
	}

	@RequestMapping("/register")
	public String showRegister() {
		return Util.appendPrefixSuffix("jsp", "register");
	}

	@RequestMapping("/login")
	public String showLogin() {
		return Util.appendPrefixSuffix("jsp", "login");
	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return Util.appendPrefixSuffix("html", "admin");
	}

	/*
	 * @RequestMapping("/topics/{topicName}") public String
	 * startTopicQuestions(@PathVariable("topicName") String
	 * topicName,Map<String, Object> map) { map.put("topicName", topicName);
	 * return Util.appendPrefixSuffix("jsp", "questions"); }
	 */

}