package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium.base.BasePage;

public class UpgradePage extends BasePage {
    By heading=By.cssSelector("h6");

    public UpgradePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getHeadingText() {
        return getElementText(driver.findElement(heading)) ;   }
    @Step("Verify upgrade Page")
    public  void verifyUpgradePage(){
        Assert.assertTrue(driver.findElement(By.cssSelector("h1")).getText().contains("OrangeHRM Advanced!"),"Incorrect heading for upgrade page");

    }

}
