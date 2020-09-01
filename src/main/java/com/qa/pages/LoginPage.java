package com.qa.pages;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='user-name']")
	WebElement txtBoxUserName;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement txtBoxPassword;
	
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement btnLogin;
	
	public void validateUIComponent() {
		softAssert.assertTrue(txtBoxUserName.isDisplayed());
		softAssert.assertTrue(txtBoxPassword.isDisplayed());
		softAssert.assertTrue(btnLogin.isDisplayed());
		softAssert.assertTrue(txtBoxUserName.isEnabled());
		softAssert.assertTrue(txtBoxPassword.isEnabled());
		softAssert.assertTrue(btnLogin.isEnabled());
		softAssert.assertAll();
	}
	
	public void validatedLoginPageTitle(String Title) {
		assertEquals(driver.getTitle(), Title);
		
	}
	
	public ProductsPage loginAccount(String userName, String Password) {
		txtBoxUserName.sendKeys(userName);
		txtBoxPassword.sendKeys(Password);
		btnLogin.click();
		return new ProductsPage();
	}
}
