package com.interview.util;

public class Util {
	
	public static String appendPrefixSuffix(String fileType,String fileName)
	{
		if(fileType.equals("jsp")){
			return "/WEB-INF/jsp/"+fileName+".jsp";
	}
		if(fileType.equals("html")){
		return "/html/"+fileName+".html";
	}
		return "/html/error.html";

}
}
