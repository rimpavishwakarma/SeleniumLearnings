package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class AccPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private final By headers = By.cssSelector("div#content > h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	

	public AccPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(DEFAULT_TIMEOUT, HOME_PAGE_TITLE);
		System.out.println("Home page title : " + title);
		return title;

	}

	public String getHomePageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(DEFAULT_TIMEOUT, HOME_PAGE_FRACTION_URL);
		System.out.println("Home page url : " + url);
		return url;
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);
		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValList.add(text);
		} 
		System.out.println("Account page headers : " + headerValList);
		return headerValList;
	}
	
	public SearchResultPage doSearch(String searchKey) {
		
		System.out.println("Search key : " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		
		return new SearchResultPage(driver);
	}
}
