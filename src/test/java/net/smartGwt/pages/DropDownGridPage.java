package net.smartGwt.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://www.smartclient.com/smartgwt/showcase/#featured_dropdown_grid_category")

public class DropDownGridPage extends PageObject {

	@FindBy(sclocator = "//testRoot[]/member[Class=DynamicForm||index=0||length=1||classIndex=0||classLength=1]/")
	private WebElementFacade DropDownTwiddleElement;
	@FindBy(xpath = ".//table[@id='isc_3Gtable']/tbody/tr/td[1]/div[./text()='Binding Covers Maroon Leathergrain-a4 300GM(PK100)']")
	private WebElementFacade selection;

	public void clickOnDropDown() {
		DropDownTwiddleElement.waitUntilClickable().click();
	}

	public void scrollUntilElementIsFound() {
		WebElement firstElementForFocus = getDriver()
				.findElement(By.xpath(".//table[@id='isc_3Gtable']/tbody/tr[1]/td[1]"));
		Actions actions = new Actions(getDriver()).moveToElement(firstElementForFocus);
		final String criterion = ".//table[@id='isc_3Gtable']/tbody/tr/td[1]/div[contains(., 'Exercise')]";
		List<WebElement> exercises = null;
		while (exercises == null || exercises.size() == 0) {
			actions.sendKeys(Keys.PAGE_DOWN).perform();
			exercises = getDriver().findElements(By.xpath(criterion));
		}
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		exercises = getDriver().findElements(By.xpath(criterion));
		for (WebElement exercise : exercises) {
			String unit = exercise.findElement(By.xpath("./../../td[2]/div")).getText();
			String cost = exercise.findElement(By.xpath("./../../td[3]/div")).getText();
			if (unit.matches("Ea") && Float.parseFloat(cost) > 1.1f) {
				exercise.click();
				return;
			}
		}
	}

}
