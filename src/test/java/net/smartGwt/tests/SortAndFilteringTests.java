package net.smartGwt.tests;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.smartGwt.pages.SortAndFilteringPage;
import net.smartGwt.utils.SqliteJdbcHelper;
import net.smartGwt.utils.Times;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class SortAndFilteringTests {

	@Managed
	WebDriver driver;

	SortAndFilteringPage sortAndFilteringPage;
	static DateTime startTime;
	static DateTime endTime;

	@BeforeClass
	public static void maximizeWindowsAndKeepTrackOfTiming() {
		startTime = DateTime.now();
		SqliteJdbcHelper.connect("Execution-Time.db");
		SqliteJdbcHelper.createTable();

	}

	@AfterClass
	public static void afterTest() {
		endTime = DateTime.now();
		long lastExecution = endTime.getMillis() - startTime.getMillis();
		Times t = SqliteJdbcHelper.getTimes();
		if (lastExecution > t.Last) {
			System.out.println("Last execution is longer than previous");
		} else
			System.out.println("Last execution is shorter than previous");
		SqliteJdbcHelper.insert(lastExecution);
		SqliteJdbcHelper.disconnect();
	}

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
