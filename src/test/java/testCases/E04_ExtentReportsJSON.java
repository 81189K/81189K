package testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class E04_ExtentReportsJSON {
	
	@Test
	public void extentDemo() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String reportName = "ExtentReport-FormattedJSON"+timeStamp+".html";
		String path = System.getProperty("user.dir")+"\\reports\\"+reportName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		reporter.config().setTheme(Theme.DARK);		
		reporter.config().setDocumentTitle("FormattedJSON");
		reporter.config().setReportName("Reports Demo");
		extent.attachReporter(reporter);			//attach
		
		String jsonString ="{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "      ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";

		ExtentTest test = extent.createTest("Login Test");		
		test.info("login test started successfully");			
		test.info("URL loaded");
		test.info("Entered credentials");
		test.info(jsonString);
		test.info(MarkupHelper.createCodeBlock(jsonString, CodeLanguage.JSON));
		test.pass("login test completed successfully");		
		
		
		extent.flush();											//pushes all nodes created so far into report. MANDATORY step.
		Desktop.getDesktop().browse(new File(path).toURI());	//open report in default browser
	}

}
