package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
	
	public static String takeScreenShot(AppiumDriver driver) {
		File ssLocation = null;
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			String dateNameER = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String path = "screenshots/" + dateNameER + ".png";
			ssLocation = new File(System.getProperty("user.dir") + "/" + path);
			FileUtils.copyFile(scrFile, ssLocation);
//			System.out.println(ssLocation.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ssLocation.toString();
	}
}
