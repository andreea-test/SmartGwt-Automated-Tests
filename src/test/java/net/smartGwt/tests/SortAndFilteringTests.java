package net.smartGwt.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.smartGwt.pages.SortAndFilteringPage;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class SortAndFilteringTests {

	@Managed
	WebDriver driver;

	SortAndFilteringPage sortAndFilteringPage;

	@Test
	public void filterBasedOnCriteria() {
		driver.manage().window().maximize();
		sortAndFilteringPage.open();
		sortAndFilteringPage.searchAnimalBox("a");
		sortAndFilteringPage.moveLifeSpanToggle(30);
		sortAndFilteringPage.sortBySelection("Life Span");
		sortAndFilteringPage.selectAscendingCheckbox();
		sortAndFilteringPage.checkNumberOfElementsDisplayedAfterFiltering(12);
	}
}
