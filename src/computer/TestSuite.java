package computer;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import static java.lang.Thread.*;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {

        //clicking on Computer Menu using utility method clickOnElement
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));

        //clicking on Desktop link using utility method clickOnElement
        clickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));

        //Select Sort By position "Name: Z to A" from dropdown using utility method
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");

        /*Verify the Product will arrange in Descending order
        by using the getTextFromElement method from utility class
        */
        String expectedMessage = "Name: Z to A";
        sleep(2000);
        String actualMessage = getTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        Assert.assertEquals("Name:Z to A has not been arranged in descending order.", expectedMessage, actualMessage);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {

        /**
         * called the verifyProductArrangedInAlphabeticalOrder method
         * to reduce the length of the code. It covers steps 2.1 to 2.3
         */
        verifyProductArrangeInAlphabeticalOrder();

        //xpath and clickOnElement method from utility to add to cart
        clickOnElement(By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000020_build-your-own-computer_415.jpeg']"));

        //Verify the Text "Build your own computer"
        //String expectedMessage = "Build your own computer";
        //Thread.sleep(2000);
        //String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        //Assert.assertEquals("User not navigate to next page", expectedMessage, actualMessage);
        verifyElements("Build your own computer", By.xpath("//h1[contains(text(),'Build your own computer')]"), "User has not navigated to next page");


        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //Select "8GB [+$60.00]" using Select class
        selectByIndex(By.id("product_attribute_2"), 3);

        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        clickOnElement(By.id("product_attribute_5_10"));
        sleep(3000);
        clickOnElement(By.id("product_attribute_5_10"));
        sleep(3000);
        clickOnElement(By.id("product_attribute_5_12"));
        sleep(3000);

        //Verify the price "$1,475.00"
        //String expectedMessage1 = "$1,475.00";
        //String actualMessage1 = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        //Assert.assertEquals("Inaccurate total", expectedMessage1, actualMessage1);
        verifyElements("$1,475.00", By.xpath("//span[@id='price-value-1']"), "Inaccurate total");


        //Click on "ADD TO CART" Button
        clickOnElement(By.id("add-to-cart-button-1"));

        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //String expectedMessage2 = "The product has been added to your shopping cart";
        //String actualMessage2 = getTextFromElement(By.xpath("//p[text()='The product has been added to your ']"));
        //Assert.assertEquals("Product has not been added to the cart", expectedMessage2, actualMessage2);
        verifyElements("The product has been added to your shopping cart", By.xpath("//p[text()='The product has been added to your ']"), "Product has not been added to the cart");

        //After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//span[@title='Close']"));
        sleep(3000);

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnFirstThenSecondAndClick(By.cssSelector(".cart-label"), By.cssSelector(".button-1.cart-button"));

        //Verify the message "Shopping cart"
        String expectedMessage3 = "Shopping cart";
        String actualMessage3 = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart is not displayed correctly", expectedMessage3, actualMessage3);

        //Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();//clearing the existing data
        sleep(1000);
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//div[@class='common-buttons']/child::button[1]"));
        sleep(1000);

        //Verify the Total"$2,950.00"
        //String expectedMessage4 = "$2,950.00";
        //String actualMessage4 = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        // Assert.assertEquals("The order total is not correct", expectedMessage4, actualMessage4);
        verifyElements("$2,950.00", By.xpath("//span[@class='product-subtotal']"), "The order total is not correct");

        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //Verify the Text “Welcome, Please Sign In!”
        //String expectedMessage5 = "Welcome, Please Sign In!";
        //String actualMessage5 = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        //Assert.assertEquals("User is not on the Sign in Page", expectedMessage5, actualMessage5);
        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");

        //Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        //Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "George");//firstname
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Smith");//lastname
        sendTextToElement(By.cssSelector("#BillingNewAddress_Email"), randomEmailGenerator());//random email selection
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//city
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "123 Abc Avenue");//Address1
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "AB12CD");//Zip/Postal code
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07912312312");//Phone number

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        //Click on “CONTINUE”
        ////body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //Select “Master card” From Select credit card dropdown and fill all details and click on continue
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 1);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        /*Verify “Payment Method” is “Credit Card”
        Verify “Shipping Method” is “Next Day Air”
        Verify Total is “$2,950.00”
         */
        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyElements("Next Day Air", By.xpath("//span[contains(text(),'\n" +
                "                                Next Day Air\n" +
                "                            ')]"), "Shipping Method is displayed incorrectly");
        verifyElements("$2,950.00", By.xpath("//span[contains(text(),'$2,950.00')]"), "Total Amount is displayed incorrectly");

        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //Verify the Text “Thank You”
        verifyElements("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");

        //Verify the message “Your order has been successfully processed!”
        verifyElements("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        verifyElements("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");


    }

    public void tearDown(){
        closeBrowser();
    }


}
