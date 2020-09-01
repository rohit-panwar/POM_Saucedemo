package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.base.TestBase;

public class TestUtils extends TestBase {

	public static long PageLoadTimeout = 60;
	public static long ImplicitWait = 30;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\rohit_panwar\\eclipse-workspace\\SeleniumFrameWorkUsingJava\\"
			+ "src\\main\\resources\\SwagLabTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) throws EncryptedDocumentException, IOException {

		FileInputStream file;
		file = new FileInputStream(TESTDATA_SHEET_PATH);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	public static void takeScreenShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(srcFile,
				new File("C:\\Users\\rohit_panwar\\eclipse-workspace\\SeleniumFrameWorkUsingJava\\src\\test\\resources\\Error\\"
						+ name + ".jpg"));
	}
	
	public static String getCurrentDateTime() {
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now=LocalDateTime.now();
		return dtf.format(now);
	}
	
	public static void drawBorder(WebDriver driver, WebElement element) {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void browserLaunchLog() {
		testInfo.log(Status.INFO,
				MarkupHelper.createLabel(
						"[ BrowserTEST: " + prop.getProperty("Browser") + ", URL: " + prop.getProperty("URL") + " ]",
						ExtentColor.INDIGO));
	}

	public static void browserExitLog() {
		testInfo.log(Status.INFO, MarkupHelper.createLabel("[ Browser is closed ]", ExtentColor.INDIGO));
	}
}
