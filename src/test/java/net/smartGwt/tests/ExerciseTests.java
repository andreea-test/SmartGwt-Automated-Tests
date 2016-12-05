package net.smartGwt.tests;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.smartGwt.pages.DropDownGridPage;
import net.smartGwt.pages.SortAndFilteringPage;
import net.smartGwt.utils.SqliteJdbcHelper;
import net.smartGwt.utils.Times;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class ExerciseTests {

	@Managed
	WebDriver driver;

	SortAndFilteringPage sortAndFilteringPage;
	DropDownGridPage dropDownGridPage;

	static DateTime startTime;
	static DateTime endTime;

	private final static Logger slf4jLogger = LoggerFactory.getLogger(ExerciseTests.class);

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
		Times previousExecution = SqliteJdbcHelper.getTimes();
		if (lastExecution > previousExecution.Last) {
			slf4jLogger.info("Last execution is longer than previous");
		} else if (lastExecution == previousExecution.Last) {
			slf4jLogger.info("Lsat execution is the same as previous");
		} else
			slf4jLogger.info("Last execution is shorter than previous");
		SqliteJdbcHelper.insert(lastExecution);
		SqliteJdbcHelper.disconnect();
	}

	@Before
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	/*
	 * Exercise 1
	 */
	@Test
	public void filterBasedOnCriteria() {
		sortAndFilteringPage.open();
		sortAndFilteringPage.searchAnimalBox("a");
		sortAndFilteringPage.moveLifeSpanToggle(30);
		sortAndFilteringPage.sortBySelection("Life Span");
		sortAndFilteringPage.selectAscendingCheckbox();
		sortAndFilteringPage.checkNumberOfElementsDisplayedAfterFiltering(12);
	}

	/*
	 * Exercise 2
	 */
	@Test
	public void selectDropDownThatMeetsCriteria() {
		dropDownGridPage.open();
		dropDownGridPage.clickOnDropDown();
		dropDownGridPage.scrollUntilElementIsFound();
	}

	@After
	public void closeDriver() {
		driver.close();
	}
}
