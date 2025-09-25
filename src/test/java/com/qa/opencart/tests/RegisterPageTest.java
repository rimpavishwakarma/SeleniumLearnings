package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	// MsExcel: .xlsx : read using apache POI

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "Vishl", "Mehta", "8763563578", "vishm@123", "yes" },
				{ "Vishali", "Mehta", "8763563578", "vishm@124", "no" } };
	}
	
	@DataProvider
	public Object[][] getUserData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	

	@Test(dataProvider = "getUserData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subsribe) {
		Assert.assertTrue(registerPage.userRegisteration(firstName, lastName, telephone, password, subsribe));
	}

}
