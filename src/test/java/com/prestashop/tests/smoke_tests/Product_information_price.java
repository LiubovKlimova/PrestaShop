package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class Product_information_price extends TestBase {


    @Test
    public void verifyName() throws Exception{
        driver.get("http://automationpractice.com/index.php");
        String expected2 = driver.findElement(By.xpath("//a[contains(text(), 'Faded Short')]")).getText();
        String expected = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[1]/span")).getText();
        driver.findElement(By.xpath("//a[contains(text(), 'Faded Short')]")).click();


        //String expected = driver.findElement(By.xpath("//span[contains(text(),'$16.51')]")).getText();
        String actual = driver.findElement(By.xpath("//*[@id='our_price_display']")).getText();
        System.out.println("expected price: "+expected+" actualprice : "+actual);

        assertEquals(expected, actual);
        Thread.sleep(1000);

        String actual2 = driver.findElement(By.xpath("//h1[contains(text(),'Faded Short Sleeve T-shirts')]")).getText();

        System.out.println("expected name: "+ expected2+"actual name : "+ actual2);
//        Thread.sleep(1000);
        assertEquals(expected2, actual2);
    }

    @Test
    public void verifySize() {
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        String actualDefaultQuantity = driver.findElement(By.xpath("//input[@id='quantity_wanted']")).getAttribute("value");

        //Printing the value of the Actual quantity
        System.out.println("Expected quantity is >>> 1 <<< and actual quantity is >>> " + actualDefaultQuantity + " <<<");

        //Verifying
        assertEquals("1", actualDefaultQuantity);

        WebElement selectElement = driver.findElement(By.id("group_1"));
        //2. create select object using the webelement
        Select list = new Select(selectElement);

        //print the selected option
        String selectedOption = list.getFirstSelectedOption().getText();
        String expected = "S";

        assertEquals(selectedOption, expected);


        List<WebElement> options = list.getOptions();
        String[] size = new String[]{"S", "M", "L"};
        int count = 0;

        for (WebElement option : options) {
            assertEquals(size[count], option.getText());
            count++;
        }
    }

    @Test
    public void addToCart() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        // STEP 7: Click on Add to cart
        driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
        Thread.sleep(1000);

        // STEP 8: Verify confirmation message
        String actual = driver.findElement(By.tagName("h2")).getText();
        assertEquals(actual, "Product successfully added to your shopping cart");
        //System.out.println(driver.findElement(By.tagName("h2")).getText());

        // STEP 9: Verify the default quantity is 1
        String actualDefaultQuantity = driver.findElement(By.id("quantity_wanted")).getAttribute("value");

        System.out.println("Expected quantity is >>> 1 <<< and actual quantity is >>> " + actualDefaultQuantity + " <<<");
        Assert.assertEquals("1", actualDefaultQuantity);

        //STEP 10: Verify The default size
        String size = driver.findElement(By.id("layer_cart_product_attributes")).getText();
        //System.out.println(a);
        String actual2 = size.substring(8);
        String expected = "S";
        Assert.assertEquals(actual2, expected);
        System.out.println(actual2 + " vs default size: S");


       // driver.get("http://automationpractice.com/index.php");
        // STEP 11: Verify the name and price are as displayed on the home page
        String expectedName = "Faded Short Sleeve T-shirts";
        String actualName = driver.findElement(By.id("layer_cart_product_title")).getText();
        Thread.sleep(1000);
        Assert.assertEquals(expectedName,actualName);

        String expectedPrice = "$16.51";
        String actualPrice = driver.findElement(By.id("layer_cart_product_price")).getText();
//        Thread.sleep(1000);
        Assert.assertEquals(expectedPrice,actualPrice);

        System.out.println("expectedName: " + expectedName + " actualName: " + actualName);
        System.out.println("expectedPrice: " + expectedPrice + " actualPrice: " + actualPrice);
    }
    @Test
    public void logInMyPersonalInfo() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//a[@class='login']")).click();

        //STEP 1.Login using valid credentials
        driver.findElement(By.id("email")).sendKeys("luibovklimova@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("jobjobjob"+ Keys.ENTER);
        //verify title
        String actualTitle = driver.getTitle();
        String expectedTitle = "My account";
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        String actualName = driver.findElement(By.xpath("//span[contains(text(),'Anna Smith')]")).getText();
        String expectedName = "Anna Smith";
        Assert.assertEquals(actualName,expectedName);

        //STEP2. Login my personal information
        driver.findElement(By.xpath("//span[contains(text(),'My personal information')]")).click();

        //verify title
        String actualTitle2 = driver.getTitle();
        String expectedTitle2 = "Identity";
        Assert.assertTrue(actualTitle2.contains(expectedTitle2));

        //verify first name and last name matches full name on top
        String firstNameExpected = driver.findElement(By.xpath("//input[@class='is_required validate form-control']")).
                getAttribute("value");

        String lastNameExpected = driver.findElement(By.xpath("//*[@id=\"lastname\"]")).
                getAttribute("value");
        String fullNameTop = driver.findElement(By.xpath("//*[.='Anna Smith']//span")).getText();//Anna Smith
        String[] str = fullNameTop.split(" ");

        Assert.assertEquals(firstNameExpected,str[0]);
        Assert.assertEquals(lastNameExpected,str[1]);

        //click "Save" button
        driver.findElement(By.xpath("//*[.='Save']")).click();

        //verify password is incorrect
        String expectedMessage = "The password you entered is incorrect.";
        String actualMessage = driver.findElement(By.xpath("//*[.='The password you entered is incorrect.']")).getText();
        Assert.assertEquals(expectedMessage,actualMessage);
        Thread.sleep(500);

        //click on back to your account
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/a/span")).click();

        //verify title
        String actualTitle3 = driver.getTitle();
        String expectedTitle3 = "My account";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Test
    public void loginMyAddresses() throws Exception{
        driver.get("http://automationpractice.com/index.php");
        Thread.sleep(1000);

        driver.findElement(By.linkText("Sign in")).click();

        //STEP 1.Login using valid credentials
        driver.findElement(By.id("email")).sendKeys("luibovklimova@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("jobjobjob"+ Keys.ENTER);
        Thread.sleep(1000);

        //click on my addresses
        driver.findElement(By.xpath("//*[.='My addresses']/span")).click();
        //click on add a new address
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[2]/a/span")).click();
        Thread.sleep(1000);


        //verify name and last name with full name on top

         String firstNameExpected = driver.findElement(By.xpath("//input[@class='is_required validate form-control']")).
                getAttribute("value");

        String lastNameExpected = driver.findElement(By.xpath("//*[@id=\"lastname\"]")).
                getAttribute("value");
        String fullNameTop = driver.findElement(By.xpath("//*[.='Anna Smith']//span")).getText();//Anna Smith
        String[] str = fullNameTop.split(" ");
        Thread.sleep(1000);

        Assert.assertEquals(firstNameExpected,str[0]);
        Thread.sleep(1000);
        Assert.assertEquals(lastNameExpected,str[1]);

        //delete first name
        WebElement firstName = driver.findElement(By.xpath("//input[@class='is_required validate form-control']"));
        firstName.clear();
        //click save
        driver.findElement(By.id("submitAddress")).click();
        Thread.sleep(1000);

        //verify error message
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/ol/li[1]")).getText();
        String expectedErroeMessage = "firstname is required.";
        Thread.sleep(1000);
        Assert.assertEquals(actualErrorMessage,expectedErroeMessage);
    }


}




