package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class ElectronicsTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        //Mouse Hover on “Electronics” Tab, Mouse Hover on “Cell phones” and click
        mouseHoverOnFirstThenSecondAndClick(By.linkText("Electronics"), By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        //Verify the text “Cell phones”
        verifyElements("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"), "Cell phones message is not displayed correctly");

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        /*2.1 Mouse Hover on “Electronics” Tab
        2.2 Mouse Hover on “Cell phones” and click
        2.3 Verify the text “Cell phones”
        All the 3 steps can be done by calling the previous method
         */
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();

        //Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));

        //Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-2 div.page.category-page div.page-body div.products-container div.products-wrapper div.product-list div.item-grid div.item-box:nth-child(3) div.product-item div.details h2.product-title > a:nth-child(1)"));

        /*2.6 Verify the text “Nokia Lumia 1020”
	      2.7 Verify the price “$349.00”
         */
        verifyElements("Nokia Lumia 1020", By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"), "Nokia Lumia 1020 is displayed incorrectly");
        verifyElements("$349.00", By.xpath("//span[@id='price-value-20']"), "Price is displayed incorrectly");

        //Change quantity to 2 and click on add to cart
        driver.findElement(By.id("product_enteredQuantity_20")).clear();
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        clickOnElement(By.id("add-to-cart-button-20"));

        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        verifyElements("The product has been added to your shopping cart", By.xpath("//p[@class='content']"), "Message has been displayed incorrectly");

        //After that close the bar clicking on the cross button
        clickOnElement(By.cssSelector("span[title='Close']"));

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverNoClick(By.xpath("//span[text()='Shopping cart']"));
        Thread.sleep(500);
        clickOnElement(By.xpath("//button[text()='Go to cart']"));

        /*
        2.12 Verify the message "Shopping cart"
	    2.13 Verify the quantity is 2
        2.14 Verify the Total $698.00
         */
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart displayed incorrectly");

        //verifyElements("2", By.xpath("//tbody/tr[1]/td[5]/input[1]"), "Quantity is displayed incorrectly");
        //verified using the get attribute method
        String expectedMessage = "2";
        String actualMessage = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Assert.assertEquals("Quantity is not correct", expectedMessage, actualMessage);

        verifyElements("$698.00", By.cssSelector(".product-subtotal"), "Amount is displayed incorrectly");

        //click on checkbox “I agree with the terms of service” and click on checkout
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        /*
        2.17 Verify the Text “Welcome, Please Sign In!”
 	2.18 Click on “REGISTER” tab
	2.19 Verify the text “Register”
	2.20 Fill the mandatory fields
	2.21 Click on “REGISTER” Button
	2.22 Verify the message “Your registration completed”
	2.23 Click on “CONTINUE” tab
	2.24 Verify the text “Shopping card”
	2.25 click on checkbox “I agree with the terms of service”
	2.26 Click on “CHECKOUT”
	2.27 Fill the Mandatory fields
	2.28 Click on “CONTINUE”
	2.29 Click on Radio Button “2nd Day Air ($0.00)”
2.30 Click on “CONTINUE”
2.31 Select Radio Button “Credit Card”
2.32 Select “Visa” From Select credit card dropdown
2.33 Fill all the details
2.34 Click on “CONTINUE”

2.35 Verify “Payment Method” is “Credit Card”
2.36 Verify “Shipping Method” is “2nd Day Air”
	2.37 Verify Total is “$698.00”
	2.38 Click on “CONFIRM”
	2.39 Verify the Text “Thank You”
	2.40 Verify the message “Your order has been successfully processed!”
	2.41 Click on “CONTINUE”
2.42 Verify the text “Welcome to our store”
         */

        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");

        //Click on “REGISTER” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        //Verify the text register
        verifyElements("Register", By.xpath("//h1[contains(text(),'Register')]"), "Registration message is not displayed correctly");


        //Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "George");//firstname
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Smith");//lastname
        sendTextToElement(By.cssSelector("#Email"), randomEmailGenerator());//random email selection
        sendTextToElement(By.xpath("//input[@id='Password']"), "abc123");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "abc123");

        //Click on “REGISTER”
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //Verify the message “Your registration completed”
        verifyElements("Your registration completed", By.xpath("//div[@class='result']"), "Registration is incomplete");

        //Click on Continue Tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //Verify the text "Shopping Cart"
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart is not displayed");

        //click on checkbox “I agree with the terms of service”
        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //Fill the mandatory fields and click on continue
        selectByIndex(By.xpath("//select[@id='BillingNewAddress_CountryId']"), 5);
        sendTextToElement(By.cssSelector("#BillingNewAddress_City"), "London");
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "123 Abc Avenue");
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "HA8 1CC");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07912345678");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //Click on Radio Button “2nd Day Air($0.00)”
        clickOnElement(By.id("shippingoption_2"));

        //Click on “CONTINUE”
        //body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));


        //Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //Select “Visa” From Select credit card dropdown and fill all details and click on continue
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 0);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        /*Verify “Payment Method” is “Credit Card”
        Verify “Shipping Method” is “2nd Day Air”
        Verify Total is “$698.00”
         */
        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyElements("2nd Day Air", By.cssSelector("li[class='shipping-method'] span[class='value']"), "Shipping Method is displayed incorrectly");
        verifyElements("$698.00", By.xpath("//span[contains(text(),'$698.00')]"), "Total Amount is displayed incorrectly");

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

        //Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //Verifying the URL
        String url = driver.getCurrentUrl();
        Assert.assertEquals("The URL is incorrect", url, "https://demo.nopcommerce.com/" );

    }

    @After
    public void tearDown() {
       // closeBrowser();
    }


}
