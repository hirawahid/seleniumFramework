package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;

import java.util.List;

public class LeavePage extends BasePage {

    private By heading=By.cssSelector("h6");
    private String leavePageHandle;
    private By toDate=By.xpath("//label[text()='To Date']/parent::div//following-sibling::div//i");
    private By fromDate=By.xpath("//label[text()='From Date']/parent::div//following-sibling::div//i");
    private By leaveStatusDropdownBtn=By.xpath("//label[text()='Show Leave with Status']/parent::div//following-sibling::div//i");
    private By selectDropDownOption=By.xpath("//div=[@role='listbox']/div/span");//span[text()='rejected']');
    private By searchButton=By.xpath("//button[@type='submit' and text()='Search']");
    private By calendar=By.cssSelector(".oxd-date-input-calendar");
    private By calendarDate=By.cssSelector(".oxd-calendar-date");

    public LeavePage(WebDriver driver) {
        super(driver);
        this.leavePageHandle=driver.getWindowHandle();
    }

    @Override
    public String getHeadingText() {
        return getElementText(driver.findElement(heading));
    }

    public void waitForLeavePage() {
        wait.until(ExpectedConditions.textToBe(heading, "Leave"));
    }

    public void enterToDate() {
    clickElement(toDate);
    WebElement calendarElement=waitForElementVisibility(calendar);
    List<WebElement> dates=calendarElement.findElements(calendarDate);
        for (WebElement date : dates) {
            if (date.getText().equals("15")) {
                date.click();
                break;
            }
        }
    }

    public void enterFromDate() {
        clickElement(fromDate);
        WebElement calendarElement=waitForElementVisibility(calendar);
        List<WebElement> dates=calendarElement.findElements(calendarDate);
        for (WebElement date : dates) {
            if (date.getText().equals("20")) {
                date.click();
                break;
            }
        }
    }

    public void enterLeavesStatus(String status) {
    clickElement(leaveStatusDropdownBtn);
        List<WebElement> dropdownOptions = driver.findElements(selectDropDownOption);
        dropdownOptions.stream().filter(option -> option.getText().equalsIgnoreCase(status))
                .findFirst().ifPresent(WebElement::click);

    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public void waitForSearchRecords() {
    }

    public void verifySearchResults() {
    }
}
