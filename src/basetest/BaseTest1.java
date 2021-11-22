package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest1 {

    public static WebDriver driver;

    public void openBrowser(String baseUrl){
        //setting property
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        //assigning value
        driver=new ChromeDriver();
        //loading the webpage
        driver.get(baseUrl);
        //maximising the window
        driver.manage().window().maximize();
        //Wait time
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void closeBrowser(){
        closeBrowser();
    }
}
