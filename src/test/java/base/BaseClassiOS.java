package base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.google.common.collect.ImmutableList;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.ExtentReport;
import utils.ReadConfig;
import utils.ReadJSON;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

public class BaseClassiOS {

	public static AppiumDriver driver;
	public ReadConfig cf = new ReadConfig();
	public String desiredCap = cf.getIOSPhone();
//	public String desiredCap = cf.getIOSTablet(); // Uncomment for tablet testing
	public ReadJSON rJSON = new ReadJSON();
	public XCUITestOptions cap = new XCUITestOptions();
	public ExtentTest test;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException, ParseException {

		cap.setDeviceName(rJSON.getJSON(desiredCap, "deviceName"));
		cap.setApp(System.getProperty("user.dir") + "/src/main/resources/resources/" + rJSON.getJSON(desiredCap, "app"));
		cap.setPlatformName(rJSON.getJSON(desiredCap, "platformName"));
		cap.setAutomationName(rJSON.getJSON(desiredCap, "automationName"));
		cap.setUdid(rJSON.getJSON(desiredCap, "UDID"));
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		test = ExtentReport.generateExtentReport();
	}
	
	@AfterSuite
	public void afterSuite() {
		ExtentReport.closeExtentReport();
	}
	
	public void navigateBack() throws FileNotFoundException, IOException, ParseException  {
		if (rJSON.getJSON(desiredCap, "deviceName").equals("iPhone 14 Pro")) {
			driver.navigate().back();
		}
	}

	public enum ScrollDirection {
		UP, DOWN, LEFT, RIGHT
	}

	public void scroll(ScrollDirection dir, double scrollRatio) {

		Duration SCROLL_DUR = Duration.ofMillis(300);
		if (scrollRatio < 0 || scrollRatio > 1) {
			throw new Error("Scroll distance must be between 0 and 1");
		}
		Dimension size = driver.manage().window().getSize();
		Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
		int top = midPoint.y - (int) ((size.height * scrollRatio) * 0.5);
		int bottom = midPoint.y + (int) ((size.height * scrollRatio) * 0.5);
		int left = midPoint.x - (int) ((size.width * scrollRatio) * 0.5);
		int right = midPoint.x + (int) ((size.width * scrollRatio) * 0.5);
		if (dir == ScrollDirection.UP) {
			swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
		} else if (dir == ScrollDirection.DOWN) {
			swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
		} else if (dir == ScrollDirection.LEFT) {
			swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
		} else {
			swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
		}
	}

	public void swipe(Point start, Point end, Duration duration) {

		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence swipe = new Sequence(input, 0);
		swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
		swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
		swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		((AppiumDriver) driver).perform(ImmutableList.of(swipe));
	}

}
