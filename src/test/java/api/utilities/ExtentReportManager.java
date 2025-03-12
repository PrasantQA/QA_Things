package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	
	public ExtentSparkReporter sparkReporter; // Responsible for look and feel of the application
	public ExtentReports extent; //It adds common info about the project
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext)
	{
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	reportName="Test-Report-"+timeStamp+".html";
	
	sparkReporter=new ExtentSparkReporter(".\\reports\\"+reportName);
	sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
	sparkReporter.config().setReportName("Pet Stores API");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent =new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application","Pet Store Users API");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("user","Prasant");
	

}
	
public void onTestSuccess(ITestResult result)
{
	  test = extent.createTest(result.getMethod().getMethodName());
	  test.assignCategory(result.getMethod().getGroups());
	  test.createNode(result.getName());
	  test.log(Status.PASS,"Test Passed");
	
	
}

            public void onTestFailure(ITestResult result) {
            	 test = extent.createTest(result.getMethod().getMethodName());
            	 
            	test =    extent.createTest("ScreenCapture")
                        .addScreenCaptureFromPath("extent.png")
                        .fail(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
            	
           	  test.assignCategory(result.getMethod().getGroups());
           	  test.createNode(result.getName());
           	  test.log(Status.FAIL,"Test Failed"); 
           	  test.log(Status.FAIL,result.getThrowable().getMessage());
            	
            	
}
            public void onTestSkipped(ITestResult result) {
           	 test = extent.createTest(result.getMethod().getMethodName());
          	  test.assignCategory(result.getMethod().getGroups());
          	  test.createNode(result.getName());
          	  test.log(Status.SKIP,"Test Skipped");
          	  test.log(Status.SKIP,result.getThrowable().getMessage());
           	
           	
}
            public void onFinish(ITestContext testContext)
            {
            	extent.flush();
}
}