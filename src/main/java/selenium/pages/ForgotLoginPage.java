package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import selenium.base.BasePage;
import selenium.utils.ConfigReader;

public class ForgotLoginPage extends BasePage {
    private By heading=By.cssSelector("h6");
    private By userName=By.cssSelector("input[name='username']");
    private By cancel=By.cssSelector(".orangehrm-forgot-password-button--cancel");

    private By resetPasswordButton=By.cssSelector("button[type='submit']");

    public ForgotLoginPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public String getHeadingText(){
        return getElementText(driver.findElement(heading));
    }

    @Step("Verify forgot login Page")
    public void verifyForgotLoginPage() {
        waitForUrlToContain("requestPasswordResetCode");
        Assert.assertEquals(getHeadingText(),"Reset Password","Incorrect heading for forgot login page");

        Assert.assertEquals(driver.findElement(userName).getAttribute("placeholder"),"Username","incorrect placeholder for username field");

        //cancel button verifications
        WebElement cancelButton=driver.findElement(cancel);
        cancelButton.isDisplayed();
        Assert.assertEquals(cancelButton.getText(),"Cancel","Incorrect text on cancel button");

    }

    @Step("Enter username in forgot password")
    public void enterUsername(String username){
        driver.findElement(userName).sendKeys(username);
    }

    @Step("Click reset password Button")
    public void clickresetPasswordButton(){
        driver.findElement(resetPasswordButton).click();
    }


}
