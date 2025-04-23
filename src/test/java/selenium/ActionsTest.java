package selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ActionsTest extends BaseTest {

    @Test
    public void test_actions_hover_rightClick(){
        driver.get("https://amazon.com");
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registry=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Help']")));
        org.openqa.selenium.interactions.Actions mousehover= new org.openqa.selenium.interactions.Actions(driver);
        mousehover.moveToElement(registry).contextClick().build().perform();
        //Actions is action chaining while action is storage and performing later.
        Action action=mousehover.moveToElement(registry).contextClick().build();

    }
    @Test
    public void test_dragDrop(){
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement draggable=driver.findElement(By.id("draggable"));
        WebElement droppable=driver.findElement(By.id("droppable"));

        org.openqa.selenium.interactions.Actions dragDrop=new org.openqa.selenium.interactions.Actions(driver);
        dragDrop.dragAndDrop(draggable,droppable).build().perform();

        Assert.assertEquals(droppable.getText(),"Dropped!");
    }

    @Test
    public void test_moveSlider(){
        driver.get("https://jqueryui.com/slider/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement slidable= driver.findElement(By.id("slider")).findElement(By.tagName("span"));
        Assert.assertFalse(slidable.getAttribute("style").split(":")[1].strip().equalsIgnoreCase("0%"));

        org.openqa.selenium.interactions.Actions slide=new org.openqa.selenium.interactions.Actions(driver);
        slide.clickAndHold(slidable).moveByOffset(70,0).release().perform();

        Assert.assertFalse(slidable.getAttribute("style").split(":")[1].strip().equalsIgnoreCase("0%"));
    }

    @Test
    public void test_keyPresses(){
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement input=driver.findElement(By.id("target"));
        org.openqa.selenium.interactions.Actions keyPresses=new org.openqa.selenium.interactions.Actions(driver);
        input.sendKeys("hira");
        keyPresses.keyDown(input, Keys.BACK_SPACE).keyDown(Keys.ARROW_LEFT).build().perform();
    }

    @Test
    public void test_colorChangeOnHover(){
        driver.get("https://www.w3schools.com/cssref/tryit.php?filename=trycss_sel_hover");
        WebElement runButton=driver.findElement(By.id("runbtn"));
        String colorBefore=runButton.getCssValue("background-color");
        org.openqa.selenium.interactions.Actions hover=new org.openqa.selenium.interactions.Actions(driver);
        hover.moveToElement(runButton).build().perform();
        String colorAfter=runButton.getCssValue("background-color");
        Assert.assertFalse(colorBefore.equalsIgnoreCase(colorAfter));
        List<WebElement> frames=driver.findElements(By.tagName("iframe"));
        System.out.println("size: "+frames.size());
        for(WebElement iframe: frames){
            System.out.println(iframe.getAttribute("id"));

        }
    }

    @Test
    public void test_selectOnHover(){
        driver.get("https://www.ebay.com/");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement langbtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Electronics")));
        org.openqa.selenium.interactions.Actions hover=new org.openqa.selenium.interactions.Actions(driver);
        hover.moveToElement(langbtn).build().perform();
        WebElement dropdown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Most popular categories']")));
        System.out.println(dropdown.isDisplayed());
    }

    @Test
    public void jqueryCalenderTest(){
        driver.get("https://jqueryui.com/datepicker/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement datepicker=driver.findElement(By.id("datepicker"));
        datepicker.click();
       // Assert.assertTrue(driver.findElement(By.id("ui-datepicker-calendar")).isDisplayed());
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,5);
        int futureDate=cal.get(Calendar.DAY_OF_MONTH);
        List<WebElement> rows=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'disabled'))]"));
        for(WebElement row: rows){
           if(row.getAttribute("class").contains("today"))
               System.out.println("active: "+row.getText());
           if(row.getText().equalsIgnoreCase(String.valueOf(futureDate))) {
               row.click();
               break;
           }
        }
    }

    @Test
    public void makeMyTripCalender(){
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("departure"))).click();

        List<WebElement> days=driver.findElements(By.xpath("//div[@class='DayPicker']//div[not(contains(@class,'disabled')) and contains(@class,'DayPicker-Day')]"));
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,20);
        int futureDate=cal.get(Calendar.DAY_OF_MONTH);
        for(WebElement day: days){
            if(day.getText().equalsIgnoreCase(String.valueOf(futureDate))){
                day.click();
                break;
            }
        }
    }

    @Test
    public void testAmazonSearch(){
        driver.get("https://amazon.com");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))).sendKeys("toothpaste");
        driver.findElement(By.id("nav-search-submit-button")).click();
        List<WebElement> crest= driver.findElements(By.xpath("//span[@data-component-type='s-search-results']//div[@role='listitem']//h2[contains(@aria-label,'Crest')]"));
        System.out.println("crest size is "+crest.size());
    }

    @Test
    public void navigationTest(){
        driver.get("https://google.com");
        System.out.println(driver.getCurrentUrl());

        driver.navigate().to("https://amazon.com");
        System.out.println(driver.getCurrentUrl());

        driver.navigate().back();
        System.out.println(driver.getCurrentUrl());

        driver.navigate().forward();
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void openInotherTabTest() throws IOException {
        driver.get("https://google.com");
        System.out.println(driver.getCurrentUrl());

        String googleWindowHandle= driver.getWindowHandle();

        JavascriptExecutor js=(JavascriptExecutor) driver;
        TakesScreenshot ts= (TakesScreenshot) driver;

        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));
        //open in new window
        js.executeScript("window.open('https://amazon.com','_blank','width=800,height=600')");
        //open in new tab
       // js.executeScript("window.open('https://amazon.com','_blank')");
        System.out.println(driver.getCurrentUrl());

        Set<String> allwindows=driver.getWindowHandles();

        for(String window: allwindows){
            if(!window.equalsIgnoreCase(googleWindowHandle)) {
                driver.switchTo().window(window);
                System.out.println("now :"+driver.getCurrentUrl());
                break;
            }
        }
        driver.switchTo().window(googleWindowHandle);
    }

    @Test
    public void isEnabled(){
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_disabled");
        driver.switchTo().frame(driver.findElement(By.name("iframeResult")));
        System.out.println(driver.findElement(By.xpath("//button[@type='button']")).isEnabled());
    }

    @Test
    public void reverseString(){
        String hira="hira";
        char[] a=hira.toCharArray();
        String result="";
        for(int i=a.length-1;i>=0;i--){
            result=result.concat(String.valueOf(a[i]));
        }

        System.out.println("result is: "+result);
    }

    @Test
    public void reverseAStringWithStringbuilder(){
        String hira="hira";
        System.out.println(new StringBuilder(hira).reverse());
    }

    @Test
    public void isPalindrome(){
        String hira="hiaih";
        //even or odd handle loop till helf of length
        int looplimit=hira.length()/2;
        int totalLength=hira.length()-1;
        for(int i=0;i<looplimit;i++){
            System.out.println(hira.charAt(i));
            System.out.println(hira.charAt(totalLength));
           if(!(hira.charAt(i)==hira.charAt(totalLength))){
               System.out.println("not a palindrome");
               break;
            }
           totalLength--;
        }
    }

    @Test
    public void findDuplicatesinString(){
        String str="hiraa";
        HashMap<Character,Integer> numbers=new HashMap<>();
        char[] characters=str.toCharArray();
        for(char a: characters){
            if(numbers.containsKey(a)){
                int count=numbers.get(a)+1;
                numbers.put(a,count);
            }else{
                numbers.put(a,1);
            }
        }

        for(Map.Entry<Character,Integer> entry: numbers.entrySet()){
            if(entry.getValue()>1){
                System.out.println(entry.getKey());
            }
        }
    }

    @Test
    public void removeDuplicates(){
        String str="hiraa";
        String result="";
        char[] characters=str.toCharArray();
        for(char a: characters){
            if(!result.contains(String.valueOf(a)))
                result=result.concat(String.valueOf(a));

        }

        System.out.println(result);
    }

    @Test
    public void reverseLargestWord(){
        String str="hiraa is my namewert";
        String result="";
        String[] array=str.split(" ");
        //find the one with largest characters
        String larget="";

        for(String s: array){
            if(s.length()>larget.length()){
                larget=s;
            }
        }
        String reversed= String.valueOf(new StringBuilder(larget).reverse());
        System.out.println("reversed: "+reversed);
        System.out.println("largest: "+larget);
        str=str.replace(larget,reversed);
        System.out.println(str);
    }

    @Test
    public void synechron() throws InterruptedException {
        driver.get("https://todomvc4tasj.herokuapp.com/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));  // Wait for page load

        // Find the input box
        WebElement input = driver.findElement(By.id("new-todo"));

        // Click the input box to ensure focus
        input.click();
        Thread.sleep(500);

        // Type "hira" into the input field
        input.sendKeys("hira");

        // Fire the keydown event for ENTER using JavaScript

        // Wait for the task to appear
        Thread.sleep(2000);

        input.sendKeys(Keys.ENTER);

        // Verify that "hira" appears in the list
        WebElement todoItem = driver.findElement(By.xpath("//ul[@id='todo-list']//label[text()='hira']"));
        System.out.println("Task added: " + todoItem.isDisplayed());  // Should print true

        //mark as completed

        driver.findElement(By.xpath("//ul[@id='todo-list']//label[text()='hira']/preceding-sibling::input[@type='checkbox']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='todo-list']//label[text()='hira']/ancestor::li")).getAttribute("class"),"completed");

        driver.findElement(By.xpath("//ul[@id='todo-list']//label[text()='hira']/following-sibling::button[@class='destroy']")).click();



    }

    @Test
    public void fluentWait(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button"))).click();
        FluentWait<WebDriver> wait=new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchContextException.class);
        WebElement element=wait.until(driver -> {
            WebElement ele = driver.findElement(By.id("finish"));
            if (ele.isDisplayed()) {
                return ele;
            }
            return null;
        });
        System.out.println(element.getText());
    }

    @Test
    public void handle_stale_element() throws IOException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory"));
        List<WebElement> inventoryList=driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));

        if(!inventoryList.isEmpty()){
            int retries=0;
            boolean success=false;
            while(retries<3) {
                try {
                    //product.click();
                    inventoryList=driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));
                    WebElement product=inventoryList.get(0);
                    product.findElement(By.xpath("//div[@data-test='inventory-item-name']")).click();
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File screenshot = ts.getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(screenshot, new File("screenshot.png"));
                    success=true;
                    break;
                }catch(StaleElementReferenceException e){
                    System.out.println("Stale element exception is catched successfully");
                }
                retries++;
            }
            if(!success)
            throw new NoSuchElementException("Element not found after reties");

        }else{
            throw new NoSuchElementException("INVENTORY LIST NOT FOUND");
        }

    }

    @Test
    public void handle_stale_element_withJSExecutor() throws IOException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory"));
        List<WebElement> inventoryList=driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));

        if(!inventoryList.isEmpty()){
            inventoryList = driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));
            WebElement product=inventoryList.get(0);

            WebElement element=product.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
            driver.navigate().refresh();
            inventoryList = driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));
            product=inventoryList.get(0);

            element=product.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
            //element.click();
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",element);
        }
    }

    @Test
    public void handle_stale_element_withWebDriverWait() throws IOException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory"));
        List<WebElement> inventoryList=driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));

        if(!inventoryList.isEmpty()){
            inventoryList = driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));
            WebElement product=inventoryList.get(0);

            WebElement element=product.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
            driver.navigate().refresh();
            inventoryList = driver.findElements(By.xpath("//div[@data-test='inventory-list']/div"));
            product=inventoryList.get(0);

            element=product.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
            //element.click();
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        }
    }

    @Test
    public void convertCamelCase(){

        String str="hEEL0wORLD";
        str=str.toLowerCase();
        String[] words=str.split("[^a-zA-Z]");
        System.out.println(words[0]);
        StringBuilder result=new StringBuilder();
        result.append(words[0].strip());
        for(int i=1;i<words.length;i++){
            result.append(words[i].substring(0,1).replace(words[i].substring(0,1),words[i].substring(0,1).toUpperCase())).append(words[i].substring(i).toLowerCase());
        }
        System.out.println(result);
    }

}

