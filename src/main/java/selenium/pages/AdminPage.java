package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.base.BasePage;

import java.util.List;

public class AdminPage extends BasePage {

    By searchResultstable=By.xpath("//div[@role='table']");

    private By heading=By.cssSelector("h6");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getHeadingText() {
        return getElementText(driver.findElement(heading));
    }

    public void verifyTable(){
        List<WebElement> tableHeaders= driver.findElement(searchResultstable).findElements(By.xpath("//div/div[@role='columnheader']"));
        for(WebElement header: tableHeaders){
            System.out.println(header.getText());
        }
    }
}
