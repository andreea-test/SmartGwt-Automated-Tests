package net.smartGwt.steps.serenity;

import net.smartGwt.pages.SortAndFilteringPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class SortAndFilteringSteps extends ScenarioSteps {
    SortAndFilteringPage sortAndFilteringPage;

    @Step
    public void searchForAnimalsStartingWithLetter(String name) {
	sortAndFilteringPage.searchAnimalBox(name);

    }

    @Step
    public void moveLifeSpanToggleTo() {
	sortAndFilteringPage.moveLifeSpanToggle();

    }

    @Step
    public void sortByUsingSelection(String selectionName) {
	sortAndFilteringPage.sortBySelection(selectionName);

    }

    @Step
    public void selectCheckboxToscending() {
	sortAndFilteringPage.selectAscendingCheckbox();

    }

}
