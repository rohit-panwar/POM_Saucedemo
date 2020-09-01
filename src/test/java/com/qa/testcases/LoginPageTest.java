package com.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.TestUtils;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;

	String sheetName = "UserList";

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp(Method method) {
		initilization();
		loginPage = new LoginPage();
		extendReportRegister(method);
		TestUtils.browserLaunchLog();
	}

	@Test(priority = 0)
	public void loginPageTitleTest() {
		testInfo.log(Status.INFO, MarkupHelper.createLabel("Verifing Page Title", ExtentColor.INDIGO));
		loginPage.validatedLoginPageTitle(prop.getProperty("HomePageTitle"));
		throw new SkipException("<<< Under Maintenance >>>");
	}

	@Test(priority = 1)
	public void UITest() {
		testInfo.log(Status.INFO, MarkupHelper.createLabel("Verifing the UI Components", ExtentColor.INDIGO));
		loginPage.validateUIComponent();
	}

	@Test(priority = 2)
	public void stdUserLoginTest() {
		testInfo.log(Status.INFO, MarkupHelper.createLabel("Login using Standard User", ExtentColor.INDIGO));
		productsPage = loginPage.loginAccount(prop.getProperty("StdUserName"), prop.getProperty("StdPwd"));
		productsPage.verifyPageByURL();
	}

	@Test(priority = 3, dataProvider = "getUserDetails")
	public void multiUserLoginTest(String uname, String pwd) {
		testInfo.log(Status.INFO, MarkupHelper.createLabel("Login test using multiple user", ExtentColor.INDIGO));
		productsPage = loginPage.loginAccount(uname, pwd);
		productsPage.verifyPageByURL();
	}

	@DataProvider
	public Object[][] getUserDetails() throws EncryptedDocumentException, IOException {
		Object data[][] = TestUtils.getTestData(sheetName);
		return data;
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		extendReportCaptureStatus(result);
		driver.quit();
		TestUtils.browserExitLog();
	}
}
