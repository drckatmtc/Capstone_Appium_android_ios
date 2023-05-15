package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClassiOS;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
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
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_01: " + optCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_01");
		}

	}

	@Test(priority = 2)
	public void UI_02() throws InterruptedException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Activity Indicators")).click();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			Thread.sleep(500);
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_02");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_02");
		}
	}

	@Test(priority = 3, enabled = false)
	public void UI_03() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
//			Date picking must be present up to 7 days ahead
			WebElement timeEle = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton")).get(3); // Click
																													// timepicker
			timeEle.click();

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(2).sendKeys("PM");
			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(0).sendKeys("7"); // hour
			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel")).get(1).sendKeys("46"); // minute

			driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton")).get(0).click();

			Assert.assertEquals(timeEle.getText(), "7:46 PM");
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();

			test.log(LogStatus.PASS, "UI_03");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_03");
		}

	}

	@Test(priority = 4)
	public void UI_04() throws InterruptedException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Image View")).click();
			Thread.sleep(4000);
			List<WebElement> image = driver.findElements(AppiumBy.accessibilityId("Animated"));
			String imgCount = "Image count: " + image.size();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();

			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_04: " + imgCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_04");
		}
	}

	@Test(priority = 5)
	public void UI_05() throws InterruptedException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
			WebElement indicatorDots = driver.findElement(AppiumBy.xpath("//XCUIElementTypePageIndicator"));
			indicatorDots.click();
			indicatorDots.click();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_05");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_05");
		}

	}

	@Test(priority = 6)
	public void UI_06() throws InterruptedException {
		try {
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
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			test.log(LogStatus.PASS, "UI_06: " + "Default RGB value: " + defaultValue + "\nResult RGB value: " + result);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_06");
		}

	}

	@Test(priority = 7)
	public void UI_07() {
		try {
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
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_07: " + elapsedTime);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_07");
		}
	}

	@Test(priority = 8)
	public void UI_08() {
		try {
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
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			driver.navigate().back();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_08");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_08");
		}

	}

	@Test(priority = 9)
	public void UI_09() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Segmented Controls")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Tools\"`][1]")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Check\"`][2]")).click();
			driver.findElement(AppiumBy.accessibilityId("Gift")).click();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_09");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_09");
		}

	}

	@Test(priority = 10)
	public void UI_10() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeSlider")).get(0).sendKeys("0");
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeSlider")).get(1).sendKeys("1");
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeSlider")).get(2).sendKeys("0.5");
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_10");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_10");
		}
	}

	@Test(priority = 11)
	public void UI_11() throws InterruptedException {
		try {
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
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_11: " + log);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_11");
		}
	}

	@Test(priority = 12)
	public void UI_12() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Switches")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[1]")).click();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_12");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_12");
		}
		
	}

	@Test(priority = 13)
	public void UI_13() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Text Fields")).click();
			String str = "Appium";

			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(0).sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(1).sendKeys(str);
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`value == \"Placeholder text\"`]"))
					.sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(2).sendKeys(str);
			driver.findElements(AppiumBy.xpath("//XCUIElementTypeTextField")).get(3).sendKeys(str);
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_13");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_13");
		}
		
	}

	@Test(priority = 14)
	public void UI_14() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Toolbars")).click();
			driver.findElement(AppiumBy.accessibilityId("Default")).click();
			driver.findElement(AppiumBy.accessibilityId("Delete")).click();
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			driver.navigate().back();
			navigateBack();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "UI_14");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_14");
		}
		
	}

	@Test(priority = 15)
	public void UI_15() {
		try {
			scroll(ScrollDirection.DOWN, 0.5);
			driver.findElement(AppiumBy.accessibilityId("Web View")).click();

			WebElement text1 = driver.findElement(AppiumBy.accessibilityId("This is HTML content inside a "));
			WebElement text2 = driver
					.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`label == \"WKWebView\"`]"));
			String log = text1.getText() + text2.getText();
			AssertJUnit.assertTrue(text1.isDisplayed());
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			navigateBack();
			test.log(LogStatus.PASS, "UI_15: " + log);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_15");
		}
	}

	@Test(priority = 16)
	public void UI_17() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
			driver.findElement(AppiumBy.accessibilityId("Other")).click();

			WebElement alert = driver.findElement(
					AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"A Short Title Is Best\"`]"));
			String log = alert.getText();
			AssertJUnit.assertTrue(alert.isDisplayed());
			AssertJUnit.assertEquals(log, "A Short Title Is Best");
			test.log(LogStatus.INFO, test.addScreenCapture(ExtentReport.takeScreenShot(driver)));
			driver.findElement(AppiumBy.accessibilityId("Choice Two")).click();
			navigateBack();
			test.log(LogStatus.PASS, "UI_17: " + log);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "UI_17");
		}
	}

}
