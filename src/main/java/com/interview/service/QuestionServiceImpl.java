package com.interview.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.dao.QuestionDAO;
import com.interview.entities.Question;



@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDAO questionDAO;

	public List<Question> listQuestions() {
		//List<Question> questions = questionDAO.getAllQuestions();
		/*JSONArray questionsJSON = new JSONArray();
		
		for(Question question:questions)
		{
			JSONObject keyvalue = new JSONObject();
			keyvalue.put("text", question.getQuestion());
			keyvalue.put("option1", question.getOption1());
			keyvalue.put("option2", question.getOption2());
			keyvalue.put("option3", question.getOption3());
			keyvalue.put("option4", question.getOption4());
			questionsJSON.put(keyvalue);
			
		}
		return questionsJSON;*/
		//return questions;
		return null;
	}

	public List<Question> readQuestionsFromExcel() {
		  List<Question> questions = new ArrayList<Question>();
		try
        {
            FileInputStream file = new FileInputStream(new File("C:\\apache-tomcat\\apache-tomcat-7.0.47\\temp\\Questions.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCount = 0;
            while (rowIterator.hasNext()) 
            {
            	Row row = rowIterator.next();
            	if(rowCount == 0)
                {
                	rowCount++;
                	continue;
                }
            	
                Question question = new Question();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellCount = 0;
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                       case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            /*if(cellCount==0)
                            	question.setLanguage(cell.getStringCellValue());
                            else if (cellCount==1)
                            	question.setQuestion_type(cell.getStringCellValue());
                            else if (cellCount==2)
                            	question.setQuestion(cell.getStringCellValue());
                            else if(cellCount==3)
                            	question.setOption1(cell.getStringCellValue());
                            else if (cellCount==4)
                            	question.setOption2(cell.getStringCellValue());
                            else if (cellCount==5)
                            	question.setOption3(cell.getStringCellValue());
                            else if(cellCount==6)
                            	question.setOption4(cell.getStringCellValue());
                            else if (cellCount==7)
                            	question.setOption5(cell.getStringCellValue());
                            else if (cellCount==8)
                            	question.setOption6(cell.getStringCellValue());
                            else if (cellCount==9)
                            	question.setOption7(cell.getStringCellValue());
                            else if (cellCount==10)
                            	question.setOption8(cell.getStringCellValue());
                            else if (cellCount==11)
                            	question.setAnswer_option(cell.getStringCellValue());
                            else if (cellCount==12)
                            	question.setAnswer_explanation(cell.getStringCellValue());
                            break;*/
                    }
                   
                    cellCount++;
                }
                questions.add(question);
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		return questions;
	}

}
