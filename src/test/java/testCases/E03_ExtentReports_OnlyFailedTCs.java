 package testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class E03_ExtentReports_OnlyFailedTCs {
	
	@Test
	public void extentDemo() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String reportName = "ExtentReport-All-TCs-"+timeStamp+".html";
		String path = System.getProperty("user.dir")+"\\reports\\"+reportName;
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //report file path
		reporter.config().setTheme(Theme.DARK);		
		reporter.config().setDocumentTitle("All");
		reporter.config().setReportName("Reports Demo");
		
		String failedreportName = "ExtentReport-FAILED-TCs-"+timeStamp+".html";
		String failedpath = System.getProperty("user.dir")+"\\reports\\"+failedreportName;
		ExtentSparkReporter failedTCreporter = new ExtentSparkReporter(failedpath)
							.filter().statusFilter().as(new Status[] {Status.FAIL, Status.SKIP}).apply();		//filter()
		failedTCreporter.config().setTheme(Theme.DARK);
		failedTCreporter.config().setDocumentTitle("Failed TCs");
		failedTCreporter.config().setReportName("Failed TCs Report");
		
		
		extent.attachReporter(reporter, failedTCreporter);
		

		ExtentTest test = extent.createTest("Login Test");		
		test.info("login test started successfully");			// similar to log
		test.info("URL loaded");
		test.info("Entered credentials");
		test.pass("login test completed successfully");			// pass
		
		ExtentTest test1 = extent.createTest("Homepage Test");
		test1.info("Homepage test started successfully");
		test1.info("URL loaded");
		test1.info("Entered values");
		test1.fail("Homepage test failed");
		
		extent.flush();											//pushes all nodes created so far into report. MANDATORY step.
		Desktop.getDesktop().browse(new File(path).toURI());	//open report in default browser
		Desktop.getDesktop().browse(new File(failedpath).toURI());
	}

}
