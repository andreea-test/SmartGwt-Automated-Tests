package net.smartGwt.pages;

import org.openqa.selenium.JavascriptExecutor;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import com.smartgwt.client.widgets.form.fields.SelectItem;

@DefaultUrl("http://www.smartclient.com/smartgwt/showcase/#featured_dropdown_grid_category")

public class DropDownGridPage extends PageObject {

	@FindBy(sclocator = "//testRoot[]/member[Class=DynamicForm||index=0||length=1||classIndex=0||classLength=1]/")
	private WebElementFacade DropDownTwiddleElement;
	@FindBy(xpath = ".//table[@id='isc_3Gtable']/tbody/tr/td[1]/div[./text()='Binding Covers Maroon Leathergrain-a4 300GM(PK100)']")
	private WebElementFacade selection;

	public void clickOnDropDown() {
		DropDownTwiddleElement.waitUntilClickable().click();
	}
	public void scrollUntilElementIsFound()
	{	
		
		((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView();", selection);

		}
	
	}

