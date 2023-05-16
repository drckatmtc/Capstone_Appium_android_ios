package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.ExtentReport;
import utils.ReadConfig;
import utils.ReadJSON;

public class BaseClassAndroid {

	public AndroidDriver driver;
	public ReadConfig cf = new ReadConfig();
	public String desiredCap = cf.getAndroidPhone();
//	public String desiredCap = cf.getAndroidTablet(); // Uncomment for tablet testing
	public DesiredCapabilities cap = new DesiredCapabilities();
	public ReadJSON rJSON = new ReadJSON();
	public ExtentTest test;

	public void setup1() throws Exception {

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, rJSON.getJSON(desiredCap, "deviceName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, rJSON.getJSON(desiredCap, "platformVersion"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, rJSON.getJSON(desiredCap, "automationName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, rJSON.getJSON(desiredCap, "platformName"));
		cap.setCapability("appPackage", rJSON.getJSON(desiredCap, "appPackage"));
		cap.setCapability("appActivity", rJSON.getJSON(desiredCap, "appActivity"));
		cap.setCapability(MobileCapabilityType.FULL_RESET, false);
		cap.setCapability(MobileCapabilityType.NO_RESET, false);
		cap.setCapability("autoGrantPermissions", true);

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			Thread.sleep(500);
			test.log(LogStatus.PASS,
					"Application launched successfully" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setup2() throws Exception {

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, rJSON.getJSON(desiredCap, "deviceName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, rJSON.getJSON(desiredCap, "platformVersion"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, rJSON.getJSON(desiredCap, "automationName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, rJSON.getJSON(desiredCap, "platformName"));
		cap.setCapability("appPackage", rJSON.getJSON(desiredCap, "appPackage"));
		cap.setCapability("appActivity", rJSON.getJSON(desiredCap, "appActivity"));
		cap.setCapability(MobileCapabilityType.FULL_RESET, false);
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability("autoGrantPermissions", true);

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			Thread.sleep(500);
			test.log(LogStatus.PASS,
					"Application launched successfully" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hideKeyboard() throws FileNotFoundException, IOException, ParseException {
		if (rJSON.getJSON(desiredCap, "deviceName").equals("nexus9")) {
			driver.hideKeyboard();
		}
	}

	@BeforeClass
	public void beforeSuite() {
		test = ExtentReport.generateExtentReport();
	}

	@AfterClass
	public void afterSuite() {
		ExtentReport.closeExtentReport();
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}
}
