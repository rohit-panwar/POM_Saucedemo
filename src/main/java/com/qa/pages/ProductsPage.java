package com.qa.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class ProductsPage extends TestBase {

	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@class='product_sort_container']")
	WebElement drpDwnSorting;
	
	@FindBy(xpath = "(//div[@class='inventory_item_name'])[1]")
	WebElement linkFirst;

	public String selectSortingOrder(String sortingType) throws InterruptedException {
		Select sort = new Select(drpDwnSorting);
		sort.selectByValue(sortingType);
		Thread.sleep(2000);
		TestUtils.drawBorder(driver, linkFirst);
		return linkFirst.getText();
		
	}

	public void verifyPageByURL() {
		assertTrue(driver.getCurrentUrl().contains("inventory"));
	}
}
