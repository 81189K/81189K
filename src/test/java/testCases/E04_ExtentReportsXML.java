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

public class E04_ExtentReportsXML {
	
	@Test
	public void extentDemo() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String reportName = "ExtentReport-XML"+timeStamp+".html";
		String path = System.getProperty("user.dir")+"\\reports\\"+reportName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		reporter.config().setTheme(Theme.DARK);		
		reporter.config().setDocumentTitle("FormattedXML");
		reporter.config().setReportName("Reports Demo");
		extent.attachReporter(reporter);			//attach
		
		String XMLString ="<Employee>\r\n"
				+ "	<FirstName> Hari </FirstName>\r\n"
				+ "	<LastName> Prasad </LastName>\r\n"
				+ "</Employee>";
		
		ExtentTest test = extent.createTest("Login Test");		
		test.info("login test started successfully");			
		test.info("URL loaded");
		test.info("Entered credentials");
		test.info("unformatted: "+XMLString);
		test.info(MarkupHelper.createCodeBlock(XMLString, CodeLanguage.XML));
		test.pass("login test completed successfully");		
		
		
		extent.flush();											//pushes all nodes created so far into report. MANDATORY step.
		Desktop.getDesktop().browse(new File(path).toURI());	//open report in default browser
	}

}
