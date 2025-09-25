package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		System.out.println("---running in headless mode---");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			options.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("---running in incognito mode---");
			options.addArguments("--incognito");
		}
		return options;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		System.out.println("---running in headless mode---");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("---running in incognito mode---");
			fo.addArguments("--incognito");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("---running in headless mode---");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("-InPrivate"))) {
			System.out.println("---running in incognito mode---");
			eo.addArguments("--inprivate");
		}
		return eo;
	}
}
