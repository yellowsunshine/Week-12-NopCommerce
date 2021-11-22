package utilities;

import basetest.BaseTest1;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Base64;
import java.util.List;
import java.util.Random;

public class Utility extends BaseTest1 {

    /**
     * This method will click on element
     *
     * @ param by
     */
    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }


    /**
     * This method will get text from element
     *
     * @param by
     * @return
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();

    }
    


    /**
     * This method will send text to an element
     *
     * @param by
     * @param text
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);//
    }

    /**
     * This method will switch to an Alert from main window and click on the 'OK' button of the alert
     */
    public void switchToAlert() {
        driver.switchTo().alert().accept();

    }

    /**
     * This method will switch to an alert from main window and click on the 'Cancel' button of the alert
     */
    public void acceptAlert() {
        driver.switchTo().alert().dismiss();

    }

    /**
     * This method will switch to an alert from main window and capture text from it
     *
     * @return
     */
    public String getTextFromAlert() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * This method will send some data to the alert box
     *
     * @param textToSend
     */
    public void sendTextToAlert(String textToSend) {
        driver.switchTo().alert().sendKeys(textToSend);
    }

    /**
     * This method will select the option that displays the text matching the parameter
     *
     * @param by
     * @param text
     */
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    /**
     * This method selects the option whose value matches the specified parameter
     *
     * @param by
     * @param value
     */
    public void selectByValue(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    /**
     * This method selects the option at the given index position
     *
     * @param by
     * @param index
     */
    public void selectByIndex(By by, int index) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(index);

    }

    /**
     * This method will get all elements from the dropdown list and print in console
     *
     * @param by
     */
    public void selectGetOptionsAndPrint(By by) {
        Select optionsSelect = new Select(driver.findElement(by));
        List<WebElement> optionNames = optionsSelect.getOptions();
        for (int i = 0; i < optionNames.size(); i++) {
            System.out.println(optionNames.get(i).getText());
        }
    }

    /**
     * This method performs click and hold at the source location, moves to target location
     * and then releases the mouse
     * @param source
     * @param target
     */
    public void dragAndDrop(By source, By target){
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(source);
        WebElement droppable = draggable.findElement(target);
        builder.dragAndDrop(draggable, droppable).build().perform();
    }

    /**
     * This method will hover the mouse over a particular element and click it
     * @param by
     */
    public void mouseHoverAndClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    public void mouseHoverNoClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).build().perform();

    }

    public void mouseHoverOnFirstThenSecondAndClick(By by1, By by2){
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();

    }


    /**
     * This method performs a Right Click Mouse Action at the current mouse location
     * @param by
     */
    public void rightClick(By by){
        Actions rightClick = new Actions(driver);
        WebElement a = driver.findElement(by);
        rightClick.contextClick().build().perform();
    }

    /**
     * Moves the slider from its current position to the desired position
     * @param sliderBar
     * @param sliderBox
     * @param xAxis
     * @param yAxis
     */
    public void sliderMovement(By sliderBar, By sliderBox, int xAxis, int yAxis ){
        Actions moveSlider = new Actions(driver);
        WebElement mainSlider = driver.findElement(sliderBar);
        WebElement slider = driver.findElement(sliderBox);
        moveSlider.dragAndDropBy(slider, xAxis,yAxis).build().perform();

    }

    public String randomEmailGenerator(){
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random randomEmail= new Random();
        while (email.length()<10) {
            int index = (int) (randomEmail.nextFloat() * chars.length());
            email.append(chars.charAt(index));
        }
        String saltStr = (email.toString()+"@gmail.com");
        return saltStr;
    }

    public void verifyElements(String expectedMessage, By by, String displayMessage ){
        String actualMessage = getTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }
}
