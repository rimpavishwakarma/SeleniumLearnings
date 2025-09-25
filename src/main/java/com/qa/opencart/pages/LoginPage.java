package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1. Private by locators
	
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	
	//2. public page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. public page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle() {
		
		String title = eleUtil.waitForTitleIsAndFetch(DEFAULT_TIMEOUT, LOGIN_PAGE_TITLE);
		System.out.println("Login page title : " + title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(DEFAULT_TIMEOUT, LOGIN_PAGE_FRACTION_URL);
		System.out.println("Login page url : " + url);
		return url;
	}
	
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doElementIsDisplayed(forgotPwdLink);
	}
	
	
	@Step("login with valid username: {0} and password: {1}")
	public AccPage doLogin(String username, String pwd) {
	System.out.println("User Credentials are : "+ username +" : "+ pwd);
	eleUtil.waitForElementPresence(email, DEFAULT_TIMEOUT).sendKeys(username);
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
	
	return new AccPage(driver);
	}
	
	@Step("navigating to the registeration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(DEFAULT_TIMEOUT, registerLink);
		return new RegisterPage(driver);
	}

}
