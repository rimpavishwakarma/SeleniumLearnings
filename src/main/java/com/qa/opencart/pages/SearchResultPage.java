package com.qa.opencart.pages;
import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private final By resultsProduct = By.cssSelector("div.product-thumb");
	

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public int getResultsProductCount() {
		int searchCount = eleUtil.waitForElementsVisible(resultsProduct, DEFAULT_TIMEOUT).size();
		System.out.println("Total no. of search products : " + searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name is : "+ productName);
		eleUtil.doClick(By.linkText(productName));
		
		return new ProductInfoPage(driver);
	}
}
