package selenium.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import selenium.utils.ConfigReader;

import java.time.Duration;
import java.util.*;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected TakesScreenshot ts;
    protected JavascriptExecutor js;
    Actions actions;

    private By userDropDownTab = By.cssSelector("span.oxd-userdropdown-tab");
    private By userName=By.cssSelector("p.oxd-userdropdown-name");
    private By userDropDown = By.cssSelector("ul.oxd-dropdown-menu[role='menu']");

    protected By adminMainMenuLink=By.xpath("//ul[@class='oxd-main-menu']/li/a/span[text()='Admin']");



    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWaitTimeout"))));
        this.ts=(TakesScreenshot) driver;
        this.js=(JavascriptExecutor) driver;
        this.actions=new Actions(driver);
    }

    public abstract String getHeadingText();

    public void clickElement(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
    }

    public WebElement waitForElementVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForElementInVisibility(By locator){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForWindowsToBe(int numberOfWindows){
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }

    public void enterText(By locator, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void waitForUrlToContain(String text){
        wait.until(ExpectedConditions.urlContains(text));
    }

    public Cookie getCookie(String name){
        Cookie seesionCookie=driver.manage().getCookieNamed(name);
        return seesionCookie;
    }

    public String getElementText(WebElement element){
        return element.getText();
    }

    public void moveByOffset(int x, int y ){
         Action action=actions.moveByOffset(x,y).click().build();
         action.perform();
    }
    public String getCurrentWindow(){
        return driver.getWindowHandle();
    }

    public void switchToNextWindow(String currentWindow){
        Set<String> allWindows=driver.getWindowHandles();
        System.out.println("all window handles: "+allWindows);
        Iterator<String> iterator=allWindows.iterator();
        while(iterator.hasNext())
        {
            String newWindow=iterator.next();
            if(!newWindow.equalsIgnoreCase(currentWindow)){
                driver.switchTo().window(newWindow);
            }
        }
    }

    public void verifyUserDropDown(By heading){
        WebElement dropdown=driver.findElement(userDropDownTab);
        Assert.assertTrue(dropdown.isDisplayed(),"User dropdown is not displayed");
        Assert.assertTrue(dropdown.isEnabled(),"User dropdown is disabled");
        Assert.assertNotNull(dropdown.findElement(userName).getText(),"User name is null");
        Assert.assertTrue(dropdown.findElement(By.cssSelector("img")).getAttribute("src").contains("/web/index.php/pim/viewPhoto/empNumber"));
        Assert.assertNotNull(dropdown.findElement(By.cssSelector("i.oxd-icon")));

        //verify user dropdown

        dropdown.click();
        WebElement dropdownlist=waitForElementVisibility(userDropDown);
        Assert.assertTrue(dropdownlist.isDisplayed(),"User dropdown list is not displayed");

        List<WebElement> dropdownListElements=dropdownlist.findElements(By.cssSelector("li"));

        Assert.assertEquals(dropdownListElements.size(),4,"missing elements in user dropdown");

        Iterator<WebElement> it=dropdownListElements.iterator();
        List<String> expectedElements= Arrays.asList("About","Support","Change Password","Logout");
        List<String> actualElements=new ArrayList<>();
        while(it.hasNext()){
            WebElement li=it.next();
            String elementText=li.findElement(By.cssSelector("a")).getText();
            Assert.assertTrue(li.isEnabled(),elementText +" is not enabled");
            Assert.assertTrue(li.isDisplayed(), elementText +" is not displayed");
            actualElements.add(elementText);
        }

        Assert.assertEquals(actualElements,expectedElements,"missing elements in user dropdown");

        //moveByOffset(10,10);
        clickElement(heading);
        Assert.assertTrue(waitForElementInVisibility(userDropDown),"User dropdown list is still visible");


    }
}
