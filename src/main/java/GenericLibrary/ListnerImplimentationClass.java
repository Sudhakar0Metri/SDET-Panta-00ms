package GenericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplimentationClass implements ITestListener{
   ExtentReports report;
   ExtentTest test;
   public void onTestStart(ITestResult result) {
	   String methodName = result.getMethod().getMethodName();
	 test=  report.createTest(methodName);
   }
   public void onTestSuccess(ITestResult result) {
	   String methodName = result.getMethod().getMethodName();
	   test.log(Status.PASS, methodName+"Passed");
   }
   public void onTestFailure(ITestResult result) {
	   String path=null;
	  String methodName = result.getMethod().getMethodName()+"-";
	//Step 1: Configure screenshot name
			String screenshotName = methodName+new JavaUtility().getSystemDateInFormat();
			System.out.println(screenshotName);
			
			//Step 2: using screenshot method from webDriver Utility
			try {
				
				path=new WebdriverUtils().getScreenShot(BaseClass.adriver, screenshotName);
				test.log(Status.FAIL, methodName+"----->failed");
				//it will capture the exception and log it in the report
				test.log(Status.FAIL, result.getThrowable());
				test.addScreenCaptureFromPath(path);
				
				//EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sDriver);
				//File src = eDriver.getScreenshotAs(OutputType.FILE);
				//String pa = System.getProperty("user.dir")+"/ScreenShots/"+screenshotName+".PNG";
				//path = "./Screenshots/"+screenshotName+".png";
				//File dst = new File(path);
				//Files.copy(src, dst);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			
		}
		   
		

		
		
		public void onTestSkipped(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			test.log(Status.SKIP, MethodName+"------>skipped");
			//it will capture the exception and log it in the report
			test.log(Status.SKIP, result.getThrowable());
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
		}

		public void onTestFailedWithTimeout(ITestResult result) {
			
			
		}

		public void onStart(ITestContext context) {
		
			//Execution will start here
			//Configure the report/
			ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReports/Report"+new JavaUtility().getSystemDateInFormat()+".html");
			htmlReport.config().setDocumentTitle("SDET-30 Execution Report");
			htmlReport.config().setTheme(Theme.DARK);
			htmlReport.config().setReportName("Selenium Execution Report");
			
			report = new ExtentReports();
			report.attachReporter(htmlReport);
			report.setSystemInfo("Base-Browser", "Chrome");
			report.setSystemInfo("OS", "Windows");
			report.setSystemInfo("base-url", "http://localhost:8888");
			report.setSystemInfo("Reporter Name", "Chaitra");
			

			
		}

		public void onFinish(ITestContext context) {
			
			//consolidate all the paramters and generate the report
			report.flush();
		}
   
   
}
