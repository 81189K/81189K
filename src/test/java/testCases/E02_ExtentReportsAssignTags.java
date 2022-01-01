package testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class E02_ExtentReportsAssignTags {
	
	@Test
	public void extentDemo() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String reportName = "ExtentReport-AssignTags"+timeStamp+".html";
		String path = System.getProperty("user.dir")+"\\reports\\"+reportName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //report file path
		reporter.config().setTheme(Theme.DARK);		//configure 1.here		2.xml		3.json
		reporter.config().setDocumentTitle("AssignTags");
		reporter.config().setReportName("Reports Demo");
		extent.attachReporter(reporter);			//attach

		ExtentTest test = extent.createTest("Login Test")	// create a test node in the report
							.assignAuthor("Hari")
							.assignCategory("Smoke").assignCategory("Regression")
							.assignDevice("Edge");		
		test.info("login test started successfully");			// similar to log
		test.info("URL loaded");
		test.info("Entered credentials");
		test.pass("login test completed successfully");			// pass
		
		ExtentTest test1 = extent.createTest("Homepage Test")
							.assignAuthor("Hari").assignAuthor("Prasad")
							.assignCategory("Smoke")
							.assignDevice("Chrome");
		test1.info("Homepage test started successfully");
		test1.info("URL loaded");
		test1.info("Entered values");
		test1.fail("Homepage test failed");
		
		extent.flush();											//pushes all nodes created so far into report. MANDATORY step.
		Desktop.getDesktop().browse(new File(path).toURI());	//open report in default browser
	}

}
