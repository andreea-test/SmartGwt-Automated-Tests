package net.smartGwt.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://www.smartclient.com/smartgwt/showcase/#featured_tile_filtering")

public class SortAndFilteringPage extends PageObject {
	// WebElements
	@FindBy(xpath = ".//*[@id='isc_2I']")
	private WebElementFacade animalSearchBoxElement;

	@FindBy(xpath = ".//*[@id='isc_2A']")
	private WebElementFacade maxLifeSpanCoursorElement;

	@FindBy(xpath = ".//*[@id='isc_38']")
	private WebElementFacade sortByDropDownElement;

	@FindBy(xpath = ".//*[@id='isc_3D']")
	private WebElementFacade ascendingLabelElement;

	// Replaceable locators
	private String sortByLocator = ".//div[./text()='{name}']";
	private String elementsDisplayedAfterFilteringLocator = ".//*[@class='simpleTile' and not(@aria-hidden='true')]";

	public void searchAnimalBox(String name) {
		typeInto(animalSearchBoxElement, name);
	}

	public void moveLifeSpanToggle(int lifeSpanNumber) {

		Actions actions = new Actions(getDriver()).moveToElement(maxLifeSpanCoursorElement).click();
		while(lifeSpanNumber/3!=0){
				actions.sendKeys(Keys.ARROW_LEFT);
				lifeSpanNumber=lifeSpanNumber-3;
		}
		actions.perform();
	}

	public void sortBySelection(String sortByName) {
		sortByDropDownElement.click();
		findBy(sortByLocator.replace("{name}", sortByName)).click();
	}

	public void selectAscendingCheckbox() {
		ascendingLabelElement.click();
	}

	public void checkNumberOfElementsDisplayedAfterFiltering(Integer elementsDisplayed) {
		Assert.assertTrue(
				getDriver().findElements(By.xpath(elementsDisplayedAfterFilteringLocator)).size() > elementsDisplayed);
	}
}
