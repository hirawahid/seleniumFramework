package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import selenium.base.BasePage;

import java.util.List;

public class DashboardPage extends BasePage {

    By heading=By.cssSelector("h6");
    By upgradeButton=By.cssSelector("button.orangehrm-upgrade-button");
    By buzzWidget=By.xpath("//p[contains(.,'Buzz Latest Posts')]//parent::div/parent::div/following-sibling::div//div[contains(@class,'oxd-grid-item')]");
    By employeesOnleaveSettings=By.xpath("//p[text()='Employees on Leave Today']//parent::div//following-sibling::i");
    By alert=By.xpath("//div[contains(@class,'orangehrm-dialog-modal')]");
    By leaveButton=By.xpath("//span[text()='Leave']");
    public String dashboardWindow;


    public DashboardPage(WebDriver driver) {
        super(driver);
        this.dashboardWindow=driver.getWindowHandle();
    }

    @Override
    public String getHeadingText() {
        return getElementText(driver.findElement(heading));
    }

    @Step("click upgrade button")
    public UpgradePage clickUpgradeButton(){
        clickElement(upgradeButton);
        waitForWindowsToBe(2);
        switchToNextWindow(driver.getWindowHandle());
        return new UpgradePage(driver);
        //verifyUpgradePage();
    }

    @Step("Switch back to Dashboard")
    public void switchToDashboard(){
        driver.switchTo().window(dashboardWindow);
    }


    @Step("verify employee settings alert")
    public void clickEmployeeSetting(){

//        try {
            driver.findElement(employeesOnleaveSettings).click();
        //    wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("alert found");
//        } catch (TimeoutException | NoSuchElementException e) {
//            System.out.println("alert not found: "+ e.getMessage() );
//        }

       // replace with actual locator
        try {
            WebElement customAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(alert));
            System.out.println("Custom alert found with text: " + customAlert.getText());

            WebElement checkbox=customAlert.findElement(By.className("oxd-form")).findElement(By.xpath("//input[@type='checkbox']//following-sibling::span"));
          //  Assert.assertEquals(checkbox.isSelected(),false);
            System.out.println(checkbox.isSelected());
            js.executeScript("arguments[0].click();",checkbox);

            Thread.sleep(3000);

            System.out.println(checkbox.isSelected());
           // checkbox.click();
          //  Assert.assertEquals(checkbox.isSelected(),true);

       //     customAlert.findElement(By.cssSelector("button[type='submit']")).click();

     //       wait.until(ExpectedConditions.invisibilityOf(customAlert));

            // Add code to close or interact with custom alert if needed
        } catch (TimeoutException ex) {
            System.out.println("Custom alert also not found within timeout.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Step("Scroll to a buzz element and verify if displayed")
    public void scrollToBuzzItem(){

        List<WebElement> ele=driver.findElements(buzzWidget);

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",ele.get(3));

        Assert.assertTrue(ele.get(3).isDisplayed(),"scrolled item is not visible");
    }



    @Step("move to upgrade tab")
    public void moveToUpgradeTab(){
        String current=getCurrentWindow();
        System.out.println(current);
        switchToNextWindow(current);
    }

    @Step()
    public AdminPage goToAdminPage()
    {
        driver.findElement(adminMainMenuLink).click();
        wait.until(ExpectedConditions.urlContains("admin/viewSystemUsers"));
        wait.until(ExpectedConditions.textToBe(By.xpath("//h6"),"Admin"));
        return new AdminPage(driver);
    }
}
