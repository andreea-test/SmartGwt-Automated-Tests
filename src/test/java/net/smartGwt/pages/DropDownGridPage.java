package net.smartGwt.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://www.smartclient.com/smartgwt/showcase/#featured_dropdown_grid_category")

public class DropDownGridPage extends PageObject{
	
	@FindBy(sclocator = "")
	private WebElementFacade animalSearchBoxElement;
}
