package net.smartGwt.utils;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class ScLocators {

	public static By locator(final String locator) {
		if (locator == null) {
			throw new IllegalArgumentException("Cannot find elements with a null id attribute.");
		}

		return new By() {

			@Override
			public List<WebElement> findElements(SearchContext context) {
				return Collections.<WebElement> emptyList();
			}

			@Override
			public WebElement findElement(SearchContext context) {
				JavascriptExecutor executor = (JavascriptExecutor) context;
				return (WebElement) executor.executeScript("return AutoTest.getElement(arguments[0]);", locator);
			}

		};
	}

}
