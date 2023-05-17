package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClassAndroid;
import utils.ExtentReport;
import utils.ReadExcel;

public class TestAndroid extends BaseClassAndroid {

	@Test(priority = 1)
	public void TC_BAA_01() throws Exception {
		test.log(LogStatus.INFO, "Test Case: BAA_01 is started");
		setup1();
		try {
			
			String name = ReadExcel.readByColumnName("Credential", "Name", 1);
			String addLine1 = ReadExcel.readByColumnName("Credential", "AddressLine1", 1);
			String addLine2 = ReadExcel.readByColumnName("Credential", "AddressLine2", 1);
			String accNo = ReadExcel.readByColumnName("Credential", "AccountNo", 1);
			String pin = ReadExcel.readByColumnName("Credential", "PIN", 1);
			String currBal = ReadExcel.readByColumnName("Credential", "CurrentBal", 1);

			driver.findElement(By.id("marcusobyrne.bankingapp:id/createAccount")).click(); // create account button
			test.log(LogStatus.INFO, "Account Creation");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Name")).sendKeys(name);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Address1")).sendKeys(addLine1);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Address2")).sendKeys(addLine2);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/AccountNumber")).sendKeys(accNo);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/PINno")).sendKeys(pin);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Balance")).sendKeys(currBal);

			driver.findElement(By.id("marcusobyrne.bankingapp:id/saveUser")).click(); // save user button
			driver.findElement(By.id("android:id/button3")).click(); // OK button

			//	Login
			test.log(LogStatus.INFO, "Account Login");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); // login button
			driver.findElement(By.id("marcusobyrne.bankingapp:id/enteredPIN")).sendKeys(pin); 
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); 

			driver.findElement(By.id("marcusobyrne.bankingapp:id/button")).click(); // account info button

			//	Validation
			test.log(LogStatus.INFO, "Account Validation");
			WebElement nameEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewName"));
			AssertJUnit.assertEquals(nameEle.getText(), name);
			WebElement addLine1Ele = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewAddress1"));
			AssertJUnit.assertEquals(addLine1Ele.getText(), addLine1);
			WebElement addLine2Ele = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewAddress2"));
			AssertJUnit.assertEquals(addLine2Ele.getText(), addLine2);
			WebElement accNoEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewAccNo"));
			AssertJUnit.assertEquals(accNoEle.getText(), accNo);
			WebElement pinEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewPIN"));
			AssertJUnit.assertEquals(pinEle.getText(), pin);
			WebElement currBalEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewBalance"));
			AssertJUnit.assertEquals(currBalEle.getText(), currBal);

			test.log(LogStatus.PASS, "Entered details" + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

			// for logout
			driver.navigate().back();
			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonLogout")).click(); // account info button

			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "TC_BAA_01");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "TC_BAA_01");
		}

	}

	@Test(priority = 2)
	public void TC_BAA_02() throws Exception {
		test.log(LogStatus.INFO, "Test Case: BAA_02 is started");
		setup2();
		try {
			
			String name = ReadExcel.readByColumnName("Credential", "Name", 1);
			String pin = ReadExcel.readByColumnName("Credential", "PIN", 1);
			String transDesc = ReadExcel.readByColumnName("Transaction", "Description", 1);
			String transAmount = ReadExcel.readByColumnName("Transaction", "Amount", 1);

			test.log(LogStatus.INFO, "Account Login");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); // login button
			driver.findElement(By.id("marcusobyrne.bankingapp:id/enteredPIN")).sendKeys(pin); 
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); 

			test.log(LogStatus.INFO, "Add Transaction");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonAddTrans")).click(); // add transaction button
			driver.findElement(By.id("marcusobyrne.bankingapp:id/editTextDecription")).sendKeys(transDesc); 
			driver.findElement(By.id("marcusobyrne.bankingapp:id/editTextAmount")).sendKeys(transAmount); 
			hideKeyboard();
			driver.findElement(By.id("marcusobyrne.bankingapp:id/button2")).click(); // add transaction button
			driver.findElement(By.id("android:id/button3")).click(); // OK button

			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonViewTrans")).click(); // view transaction button
			test.log(LogStatus.INFO, "View Transaction");
			
			WebElement descEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewTransactions"));
			String log = name + " Transaction: \n" + descEle.getText();
			AssertJUnit.assertEquals(true, descEle.isDisplayed());
			test.log(LogStatus.PASS, log + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

			// for logout
			driver.navigate().back();
			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonLogout")).click(); // account info button

			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "TC_BAA_02");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "TC_BAA_02");
		}
	}

	@Test(priority = 3)
	public void TC_BAA_03() throws Exception {
		test.log(LogStatus.INFO, "Test Case: BAA_03 is started");
		setup1();
		try {

			String name = ReadExcel.readByColumnName("Credential", "Name", 2);
			String addLine1 = ReadExcel.readByColumnName("Credential", "AddressLine1", 2);
			String addLine2 = ReadExcel.readByColumnName("Credential", "AddressLine2", 2);
			String accNo = ReadExcel.readByColumnName("Credential", "AccountNo", 2);
			String pin = ReadExcel.readByColumnName("Credential", "PIN", 2);
			String currBal = ReadExcel.readByColumnName("Credential", "CurrentBal", 2);
			String transDesc = ReadExcel.readByColumnName("Transaction", "Description", 2);
			String transAmount = ReadExcel.readByColumnName("Transaction", "Amount", 2);

			driver.findElement(By.id("marcusobyrne.bankingapp:id/createAccount")).click(); // create account button
			test.log(LogStatus.INFO, "Account Creation");
			
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Name")).sendKeys(name);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Address1")).sendKeys(addLine1);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Address2")).sendKeys(addLine2);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/AccountNumber")).sendKeys(accNo);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/PINno")).sendKeys(pin);
			driver.findElement(By.id("marcusobyrne.bankingapp:id/Balance")).sendKeys(currBal);

			driver.findElement(By.id("marcusobyrne.bankingapp:id/saveUser")).click(); // save user button
			driver.findElement(By.id("android:id/button3")).click(); // OK button
			
			test.log(LogStatus.INFO, "Account Login");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); // login button
			driver.findElement(By.id("marcusobyrne.bankingapp:id/enteredPIN")).sendKeys(pin); 
			driver.findElement(By.id("marcusobyrne.bankingapp:id/loginButton")).click(); 
			
			test.log(LogStatus.INFO, "Add Transaction");
			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonAddTrans")).click(); // add transaction button
			driver.findElement(By.id("marcusobyrne.bankingapp:id/editTextDecription")).sendKeys(transDesc); 
			driver.findElement(By.id("marcusobyrne.bankingapp:id/editTextAmount")).sendKeys(transAmount); 
			hideKeyboard();
			driver.findElement(By.id("marcusobyrne.bankingapp:id/button2")).click(); // add transaction button
			driver.findElement(By.id("android:id/button3")).click(); // OK button

			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonViewTrans")).click(); // view transaction button
			test.log(LogStatus.INFO, "View Transaction");

			WebElement descEle = driver.findElement(By.id("marcusobyrne.bankingapp:id/textViewTransactions"));
			String log = name + " Transaction: \n" + descEle.getText();
			AssertJUnit.assertEquals(true, descEle.isDisplayed());
			test.log(LogStatus.PASS, log + test.addScreenCapture(ExtentReport.takeScreenShot(driver)));

			// for logout
			driver.navigate().back();
			driver.findElement(By.id("marcusobyrne.bankingapp:id/buttonLogout")).click(); // account info button
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "TC_BAA_03");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "TC_BAA_03");
		}
	}
}
