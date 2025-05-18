package selenium.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import selenium.base.DriverManager;
import selenium.pages.LeavePage;

public class LeavesSteps {
    private LeavePage leavePage;

    public LeavesSteps() {
        this.leavePage = new LeavePage(DriverManager.getDriver());  // ✅ Fetch ready object
    }

    @Given("I wait for the leaves page to be available")
    public void waitForLeavePage() {
        leavePage.waitForLeavePage();
    }

    @And("I enter To date {string}")
    public void enterToDate() {
        leavePage.enterToDate();
    }

    @And("I enter From date {string}")
    public void enterFromDate() {
        leavePage.enterFromDate();
    }

    @And("I enter show leaves status as {string}")
    public void enterLeavesStatus(String status) {
        leavePage.enterLeavesStatus(status);
    }

    @And("I click search Button")
    public void clickSearchButton() {
        leavePage.clickSearchButton();
    }


    @Then("I wait for search records to be available")
    public void waitForSearchRecords() {
        leavePage.waitForSearchRecords();
    }

    @Then("I verify the search results")
    public void verifySearchResults() {
        leavePage.verifySearchResults();
    }

}
