package net.smartGwt.pages;

import org.junit.Assert;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;

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
    private WebElementFacade maxLifeSpanBarElement;

    @FindBy(xpath = ".//*[@id='isc_38']")
    private WebElementFacade sortByDropDownElement;

    @FindBy(xpath = ".//*[@id='isc_3D']")
    private WebElementFacade ascendingLabelElement;
     
    // Replaceable locators
    private String sortByLocator = ".//div[./text()='{name}']";
    private String elementsDisplayedAfterFilteringLocator=".//*[@class='simpleTile' and not(@aria-hidden='true')]";
    
    public void searchAnimalBox(String name) {
	typeInto(animalSearchBoxElement, name);
    }

        public void moveLifeSpanToggle() {
	try {
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	Actions moveSlider = new Actions(getDriver());
	Action action = moveSlider.dragAndDropBy(maxLifeSpanBarElement, -67, 0).build();

	action.perform();
//	WebElement webElement = getDriver().findElement(new FasterByChained(By.xpath(".//div/*"),
//			new org.senchalabs.gwt.gwtdriver.by.ByWidget(getDriver(), Slider.class)));
//
//	Slider slider = (Slider) webElement;
//	slider.setValue(30);
    }

    public void sortBySelection(String sortByName) {
	sortByDropDownElement.click();
	findBy(sortByLocator.replace("{name}", sortByName)).click();
    }

    public void selectAscendingCheckbox() {
	ascendingLabelElement.click();
    }
    
    public void checkNumberOfElementsDisplayedAfterFiltering(Integer elementsDisplayed)
    {
	Assert.assertTrue(getDriver().findElements(By.xpath(elementsDisplayedAfterFilteringLocator)).size()>elementsDisplayed);
    }
}
