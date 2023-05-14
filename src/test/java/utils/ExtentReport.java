package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;

public class ExtentReport {
	static AppiumDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	

	public static ExtentTest generateExtentReport() {
		String dateNameER = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		report = new ExtentReports(dateNameER + "/ExtentReport.html");
		test = report.startTest("Extent Report");
		return test;
	}

	public static void closeExtentReport() {
		report.endTest(test);
		report.flush();
	}
}
