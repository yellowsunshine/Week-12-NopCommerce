package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        //Navigating to the webpage
        openBrowser(baseUrl);
    }

    /**
     * This method will click on the menu depending on whatever string
     * is passed as parameter
     * @param menu
     */
    public void selectMenu(String menu) throws InterruptedException {

        List<WebElement> names = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//li"));

        for (WebElement name : names) {
            //Thread.sleep(2000);
            if (name.getText().equalsIgnoreCase(menu)) {
                Thread.sleep(2000);
                name.click();
                break;
            }
        }
    }

    /**
     * All the below verification methods will use selectMenu() method
     * to select the menu and click on it and verify the page navigation
     */
    @Test
    public void verifyComputersPageNavigation() throws InterruptedException {
        //xpath axes using child relationship
        selectMenu("Computers");
        //Verification using assertion
        verifyElements("Computers",By.partialLinkText("Computers"), "User has not navigated to the Computers Page");
        }

    @Test
    public void verifyPageElectronicsNavigation() throws InterruptedException {
        selectMenu("Electronics");
        verifyElements("Electronics",By.partialLinkText("Electronics"), "User has not navigated to the Computers Page");
    }

    @Test
    public void verifyPageApparelNavigation() throws InterruptedException {
        selectMenu("Apparel");
        verifyElements("Apparel",By.partialLinkText("Apparel"), "User has not navigated to the Apparel Page");
    }

    @Test
    public void verifyDigitalDownloadsPageNavigate() throws InterruptedException {
        selectMenu("Digital downloads");
        verifyElements("Digital downloads",By.partialLinkText("Digital downloads"), "User has not navigated to the Digital downloads Page");
        }

    @Test
    public void verifyBooksNavigate() throws InterruptedException {
        selectMenu("Books");
        verifyElements("Books",By.partialLinkText("Books"), "User has not navigated to the Books Page");
    }

    @Test
    public void verifyJewelryNavigate() throws InterruptedException {
        selectMenu("Jewelry");
        verifyElements("Jewelry",By.partialLinkText("Jewelry"), "User has not navigated to the Jewellery Page");
       }

    @Test
    public void verifyGiftNavigate() throws InterruptedException {
        selectMenu("Gift Cards");
        verifyElements("Gift Cards",By.partialLinkText("Gift Cards"), "User has not navigated to the Gift Cards Page");
    }

    @After
    public void tearDown() {
       // closeBrowser();
    }
}
