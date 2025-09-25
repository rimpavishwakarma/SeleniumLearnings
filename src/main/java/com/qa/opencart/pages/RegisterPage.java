package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.StringUtils;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.name("lastname");
	private By email = By.xpath("//*[@id=\"input-email\"]");
	private By telephone = By.cssSelector("#input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.name("confirm");
	
	private By subscribeYes = By.xpath("(//label[@class = 'radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class = 'radio-inline'])[position()=2]/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.cssSelector("#content > form > div > div > input.btn.btn-primary");
	
	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegisteration(String firstName, String lastName, String telephone, String password, String subsribe) {
		
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys("firstName");
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subsribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		eleUtil.waitForElementPresence(successMessg, AppConstants.DEFAULT_TIMEOUT);
		
		if(eleUtil.waitForElementPresence(successMessg, AppConstants.DEFAULT_TIMEOUT).getText().contains(AppConstants.REGISTER_SUCCESS_MESSG)){
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
}
