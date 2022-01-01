package testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class E05_ExtentReportsCustomize {
	
	@Test
	public void extentDemo() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String reportName = "ExtentReport-Customized"+timeStamp+".html";
		String path = System.getProperty("user.dir")+"\\reports\\"+reportName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path)
					.viewConfigurer()
					.viewOrder()	//1. changing view order	2.view only specified tabs
					.as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
		reporter.config().setTheme(Theme.DARK);		
		reporter.config().setDocumentTitle("Customized");
		reporter.config().setReportName("Reports Demo");
		extent.attachReporter(reporter);			//attach

		ExtentTest test = extent.createTest("Login Test").assignAuthor("Hari").assignCategory("Smoke").assignCategory("Regression").assignDevice("Edge");		
		test.info("login test started successfully");
		test.info("URL loaded");
		test.info("Entered credentials");
		Arrays.asList(new String[] {"Step1","Step2","Step3"}).forEach(e -> test.pass(e));
				//or
		//Arrays.asList(new String[] {"Step1","Step2","Step3"}).forEach(test::pass);
		
		test.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] {"Step1","Step2","Step3"})).getMarkup());
		test.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[] {"Step1","Step2","Step3"})).getMarkup());
		
		Map<String,String> map = new HashMap<>();
		map.put("username", "hari");
		map.put("password", "hari@123");
		
		map.forEach((k,v) -> test.pass(k+":"+v));
		test.pass(MarkupHelper.createUnorderedList(map).getMarkup());
		//test.pass("login test completed successfully");	
		test.pass(MarkupHelper.createLabel("login test completed successfully", ExtentColor.GREEN)); //Highlight log
		
		ExtentTest test1 = extent.createTest("Homepage Test").assignAuthor("Hari").assignAuthor("Prasad").assignCategory("Smoke").assignDevice("Chrome");
		test1.info("Homepage test started successfully");
		test1.info("URL loaded");
		test1.info("Entered values");
		test1.fail("Homepage test failed");
		
		extent.flush();											//pushes all nodes created so far into report. MANDATORY step.
		Desktop.getDesktop().browse(new File(path).toURI());	//open report in default browser
	}

}
