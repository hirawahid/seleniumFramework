package selenium.utils.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

public class ScreenShotUtility
{
    static String path="src/main/screenshots/";

    public static void takeScreenShot(WebDriver driver,String testName){
        String timestamp= new SimpleDateFormat("yyyyMMdd_HH:mm:ss ").format(new Date());
        TakesScreenshot ts=(TakesScreenshot)driver;
        File shot=ts.getScreenshotAs(OutputType.FILE);

        String pathToCopy=path+testName+"_"+timestamp+".png";

        try{
            FileHandler.copy(shot,new File(pathToCopy));
            System.out.println("successfully captured screenshot");
        }catch(IOException e){
            System.out.println("failed to take screenshot");
        }
    }

}
