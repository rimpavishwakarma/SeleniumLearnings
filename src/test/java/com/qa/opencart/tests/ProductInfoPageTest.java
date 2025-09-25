package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.CSVUtil;

public class ProductInfoPageTest extends BaseTest{

	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey, String productKey) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productKey);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productKey);	
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"macbook", "MacBook Air", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	@DataProvider
	public Object[][] getProductCSVData() {
		return CSVUtil.csvData("product");
	}
	
	@Test(dataProvider = "getProductCSVData")
	public void productImageCountTest(String searchKey, String productKey, String expectedImageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productKey);
		int actimageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actimageCount), expectedImageCount);	
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualProductDetailsMap.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(actualProductDetailsMap.get("productimages"), "4");
		
		
		softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
	
}
