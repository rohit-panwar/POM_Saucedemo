package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.utils.TestUtils;

public class TestBase {

	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;
	public static SoftAssert softAssert;

	public TestBase() {

		try {
			prop = new Properties();
			fis = new FileInputStream(
					"C:\\Users\\rohit_panwar\\eclipse-workspace\\SeleniumFrameWorkUsingJava\\src\\main\\"
					+ "java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		softAssert = new SoftAssert();

	}

	public static void initilization() {
		// testInfo.log(Status.INFO, MarkupHelper.createLabel("Browser Initializing",
		// ExtentColor.BLUE));
		String BrowserName = prop.getProperty("Browser");
		if (BrowserName.equals("Chrome")) {
			System.setProperty(prop.getProperty("ChromeProperty"), prop.getProperty("ChromePath"));
			driver = new ChromeDriver();
		} else if (BrowserName.equals("Firefox")) {
			System.setProperty(prop.getProperty("FirefoxProperty"), prop.getProperty("FirefoxPath"));
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.ImplicitWait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
	}

	public static ExtentReports reports;
	public static ExtentTest testInfo;
	//public static ExtentHtmlReporter htmlReporter;
	public static ExtentSparkReporter htmlReporter;

	@BeforeSuite
	public void extentReportSetup() throws IOException {
		reports = new ExtentReports();
		// htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")
		// + "/AutomationReport.html"));
		htmlReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/AutomationReport.html"));
		htmlReporter.loadXMLConfig("C:\\Users\\rohit_panwar\\eclipse-workspace\\SeleniumFrameWorkUsingJava"
				+ "\\src\\main\\resources\\Extent-Report.xml");

		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Machine", "localhost");
		reports.setSystemInfo("User", "Rohit Singh Panwar");
		reports.attachReporter(htmlReporter);
	}

	@AfterSuite
	public void extentReportCleanUp() {
		reports.flush();
	}

	public void extendReportRegister(Method method) {
		String testName = method.getName();
		testInfo = reports.createTest(testName);
	}

	public void extendReportCaptureStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testInfo.log(Status.PASS,
					MarkupHelper.createLabel("Test Case PASSED: " + result.getName(), ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testInfo.log(Status.FAIL,
					MarkupHelper.createLabel("Test Case FAILED: " + result.getName(), ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			testInfo.log(Status.SKIP,
					MarkupHelper.createLabel("Test Case SKIPPED: " + result.getName(), ExtentColor.BLUE));
		}
	}

}
