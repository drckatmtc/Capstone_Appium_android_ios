package tests;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClassiOS;
import io.appium.java_client.AppiumBy;
import utils.ExtentReport;

public class TestIOS extends BaseClassiOS {

	@Test(priority = 1)
	public void UI_01() {

		try {
			scroll(ScrollDirection.DOWN, 0.5);
			scroll(ScrollDirection.UP, 0.5);

			List<WebElement> option = driver
					.findElements(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeCell\""));
			String optCount = "Options count: " + option.size();

			test.log(LogStatus.PASS, "UI_01: " + optCount);
			AssertJUnit.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_01");
		}

	}

	@Test(priority = 2)
	public void UI_02() throws Exception {
		try {
			driver.findElement(AppiumBy.accessibilityId("Activity Indicators")).click();

			Thread.sleep(500);
			test.log(LogStatus.PASS, "UI_02" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

			AssertJUnit.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_02");
		}
	}

	@Test(priority = 3, enabled = true)
	public void UI_03() throws FileNotFoundException, IOException, ParseException {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
			// Date picking must be present up to 7 days ahead
			WebElement timeEle = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton")).get(3); // Click
																													// timepicker
			timeEle.click();

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(2).sendKeys("PM");
			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(0).sendKeys("7"); // hour
			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(1).sendKeys("46"); // minute

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton")).get(0).click();

			WebElement dateTimeEle = driver.findElements(AppiumBy.xpath("//XCUIElementTypeStaticText")).get(1);
			String dateTimeStr = dateTimeEle.getText();

			if (dateTimeStr == "June 17, 2023 at 7:46 PM") {
				test.log(LogStatus.PASS, "UI_03" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
				Assert.assertTrue(true);
			} else {
				test.log(LogStatus.FAIL, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_03");
		}
	}

	@Test(priority = 4)
	public void UI_04() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Image View")).click();
			Thread.sleep(4000);
			List<WebElement> image = driver.findElements(AppiumBy.accessibilityId("Animated"));
			String imgCount = "Image count: " + image.size();

			test.log(LogStatus.PASS, "UI_04: " + imgCount + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			AssertJUnit.assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_04");
		}
	}

	@Test(priority = 5)
	public void UI_05() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
			WebElement indicatorDots = driver.findElement(AppiumBy.xpath("//XCUIElementTypePageIndicator"));
			indicatorDots.click();
			indicatorDots.click();

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_05" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_05");
		}
	}

	@Test(priority = 6)
	public void UI_06() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

			WebElement rEle = driver.findElement(AppiumBy.accessibilityId("Red color component value"));
			WebElement gEle = driver.findElement(AppiumBy.accessibilityId("Green color component value"));
			WebElement bEle = driver.findElement(AppiumBy.accessibilityId("Blue color component value"));

			String defaultValue = rEle.getText() + gEle.getText() + bEle.getText();

			rEle.sendKeys("0");
			gEle.sendKeys("0");
			bEle.sendKeys("0");

			String result = rEle.getText() + gEle.getText() + bEle.getText();

			Assert.assertNotEquals(defaultValue, result);

			test.log(LogStatus.PASS, "UI_06: " + "Default RGB value: " + defaultValue + "\nResult RGB value: " + result
					+ test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_06");
		}
	}

	@Test(priority = 7)
	public void UI_07() throws Exception {
		try {
			navigateBack();
			StopWatch watch = new StopWatch();
			driver.findElement(AppiumBy.accessibilityId("Progress Views")).click();
			watch.start();

			WebElement progress = driver.findElement(
					AppiumBy.iOSClassChain("**/XCUIElementTypeProgressIndicator[`label == \"Progress\"`][1]"));

			while (!progress.getText().equals("100%")) {
//				waiting
			}
			watch.stop();

			String elapsedTime = "Elapsed Time: " + watch.getTime(TimeUnit.SECONDS) + " seconds";

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS,
					"UI_07: " + elapsedTime + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_07");
		}
	}

	@Test(priority = 8)
	public void UI_08() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Search")).click();
			driver.findElement(AppiumBy.accessibilityId("Default")).click();
			driver.findElement(AppiumBy.accessibilityId("Scope Two")).click();
			driver.findElement(AppiumBy.accessibilityId("Scope One")).click();
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Custom")).click();
			driver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeSearchField\"")).click();

			String search = "Appium";
			for (int i = 0; i < search.length(); i++) {
				String str = String.valueOf(search.charAt(i));
				driver.findElement(AppiumBy.accessibilityId(str)).click();
			}

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_08" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_08");
		}
	}

	@Test(priority = 9)
	public void UI_09() throws Exception {
		try {
			driver.navigate().back();
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Segmented Controls")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Tools\"`][1]")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Check\"`][2]")).click();
			driver.findElement(AppiumBy.accessibilityId("Gift")).click();

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_09" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_09");
		}
	}

	@Test(priority = 10)
	public void UI_10() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
			
			Point startPoint, endPoint;
	
			startPoint = new Point(172, 174);
			endPoint = new Point(26, 174);
			swipe(startPoint, endPoint, Duration.ofMillis(500));
			
			startPoint = new Point(200, 268);
			endPoint = new Point(374, 268);
			swipe(startPoint, endPoint, Duration.ofMillis(500));
			
			startPoint = new Point(308, 359);
			endPoint = new Point(195, 359);
			swipe(startPoint, endPoint, Duration.ofMillis(500));

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_10" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_10");
		}
	}

	@Test(priority = 11)
	public void UI_11() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Stack Views")).click();

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"stepper increment\"`]"))
					.get(1).click();

			WebElement fDetail = driver.findElement(AppiumBy.accessibilityId("Further Detail"));
			String log = fDetail.getText();
			AssertJUnit.assertTrue(fDetail.isDisplayed());

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"stepper increment\"`]"))
					.get(0).click();

			WebElement box = driver.findElement(AppiumBy.xpath("//XCUIElementTypeOther[3]/XCUIElementTypeOther"));
			AssertJUnit.assertTrue(box.isDisplayed());

			test.log(LogStatus.PASS, "UI_11: " + log + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_11");
		}
	}

	@Test(priority = 12)
	public void UI_12() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Switches")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[1]")).click();

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_12" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_12");
		}
	}

	@Test(priority = 13)
	public void UI_13() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Text Fields")).click();
			String str = "Appium";

			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(0).sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(1).sendKeys(str);
			driver.findElement(
					AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`value == \"Placeholder text\"`]"))
					.sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(2).sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(3).sendKeys(str);

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_13" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_13");
		}
	}

	@Test(priority = 14)
	public void UI_14() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Toolbars")).click();
			driver.findElement(AppiumBy.accessibilityId("Default")).click();
			driver.findElement(AppiumBy.accessibilityId("Delete")).click();

			AssertJUnit.assertTrue(true);
			test.log(LogStatus.PASS, "UI_14" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_14");
		}
	}

	@Test(priority = 15)
	public void UI_15() throws Exception {
		try {
			driver.navigate().back();
			navigateBack();
			scroll(ScrollDirection.DOWN, 0.5);
			driver.findElement(AppiumBy.accessibilityId("Web View")).click();

			WebElement text1 = driver.findElement(AppiumBy.accessibilityId("This is HTML content inside a "));
			WebElement text2 = driver
					.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`label == \"WKWebView\"`]"));
			String log = text1.getText() + text2.getText();
			AssertJUnit.assertTrue(text1.isDisplayed());

			test.log(LogStatus.PASS, "UI_15: " + log + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_15");
		}
	}

	@Test(priority = 16)
	public void UI_17() throws Exception {
		try {
			navigateBack();
			driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
			driver.findElement(AppiumBy.accessibilityId("Other")).click();

			WebElement alert = driver.findElement(
					AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"A Short Title Is Best\"`]"));
			String log = alert.getText();
			AssertJUnit.assertTrue(alert.isDisplayed());
			AssertJUnit.assertEquals(log, "A Short Title Is Best");

			test.log(LogStatus.PASS, "UI_17: " + log + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

			driver.findElement(AppiumBy.accessibilityId("Choice Two")).click();

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_17");
		}
	}

}
