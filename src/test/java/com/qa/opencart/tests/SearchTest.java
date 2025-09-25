package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{

	
	
	@BeforeTest
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("macbook");
		int actResultsCount = searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actResultsCount, "3");
	}
}
