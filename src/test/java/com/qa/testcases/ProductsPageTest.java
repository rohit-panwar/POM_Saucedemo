package com.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.testng.ITestResult;
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

public class ProductsPageTest extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;

	String sheetName = "Sorting";

	public ProductsPageTest() {
		super();
	}

	@BeforeMethod
	public void setup(Method method) {
		initilization();
		loginPage = new LoginPage();
		extendReportRegister(method);
		TestUtils.browserLaunchLog();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		extendReportCaptureStatus(result);
		driver.quit();
		TestUtils.browserExitLog();
	}

	/*
	 * @Test(dataProvider = "getUserDetails") public void sortingTest(String
	 * sortByName, String firstProductNameExpected) throws InterruptedException {
	 * testInfo.log(Status.INFO,
	 * MarkupHelper.createLabel("Login using Standard User: " + sortByName,
	 * ExtentColor.INDIGO)); String firstProductTextActual = loginPage
	 * .loginAccount(prop.getProperty("StdUserName"), prop.getProperty("StdPwd"))
	 * .selectSortingOrder(sortByName); assertEquals(firstProductTextActual,
	 * firstProductNameExpected); testInfo.log(Status.INFO,
	 * MarkupHelper.createLabel("Products sorted successfully",
	 * ExtentColor.INDIGO)); }
	 */

	@DataProvider
	public Object[][] getUserDetails() throws EncryptedDocumentException, IOException {
		Object data[][] = TestUtils.getTestData(sheetName);
		return data;
	}
}
