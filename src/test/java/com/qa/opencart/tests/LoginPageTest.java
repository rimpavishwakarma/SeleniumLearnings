package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;




public class LoginPageTest extends BaseTest{

	@Test(description = "Checking login title....")
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("checking login page title" + actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	
	@Test(description = "Checking login page URL")
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test(description = "Checking forgot password link exist")
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = Short.MAX_VALUE, description = "login with valid credentials")
	public void dologinTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE);
		
	}
	@Test(enabled = true, description = "WIP --- forgot password check")
	public void forgotPwd() {
		System.out.println("Forgot password");
	}
	
}
