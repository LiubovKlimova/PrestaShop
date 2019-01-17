package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class RegistrationTest extends TestBase {
    Faker faker = new Faker();
    String fakeEmail;
    String fakeFirstName;
    String fakeLastName;
    String fakeStreet;
    String fakeCity;
    String fakeZipCode;
    String fakeState;
    String fakeCountry;
    String password = "cyber";

      @Test
    public void registrationTest() throws InterruptedException{

//          WebElement firstName = driver.findElement(By.id("customer_firstname"));
//          fakeFirstName = faker.name().firstName();
//          firstName.sendKeys(fakeFirstName);
//          WebElement lastName = driver.findElement(By.id("customer_lastname"));
//          fakeLastName = faker.name().lastName();
//          lastName.sendKeys(fakeLastName);
//          WebElement password = driver.findElement(By.id("passwd"));
//          fakePassword = faker.internet().password();
//          password.sendKeys(fakePassword);
    driver.get("http://automationpractice.com/index.php");
    driver.findElement(By.xpath("//a[@class='login']")).click();//loginButton

     //Enter email
    WebElement email1 = driver.findElement(By.id("email_create"));//email
    fakeEmail = faker.internet().emailAddress();
    email1.sendKeys(fakeEmail);
    //click on create account
    driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']")).click();//createAccountButton

    // 6.Verify that email link displays current email
    driver.findElement(By.xpath("//*[@id=\"email\"]")).click();
    Thread.sleep(2000);

    String actualEmail = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("value");
    Thread.sleep(500);
    //System.out.println("actual: " + actualEmail+ " expected: "+expectedEmail);
    Thread.sleep(500);
    Assert.assertEquals(actualEmail,fakeEmail);

    //7.Fill out all the required steps
    //first name
    WebElement firstName = driver.findElement(By.id("customer_firstname"));//firstNameField
    fakeFirstName = faker.name().firstName();
    firstName.sendKeys(fakeFirstName);

    //last name
    WebElement lastName = driver.findElement(By.id("customer_lastname"));//lastNameField
    fakeLastName = faker.name().lastName();
    lastName.sendKeys(fakeLastName);
    Thread.sleep(500);
    driver.findElement(By.id("passwd")).sendKeys("cyber");//passwordField

    //address
    WebElement street = driver.findElement(By.id("address1"));//street
    fakeStreet = faker.address().streetAddress();
    street.sendKeys(fakeStreet);
    WebElement city = driver.findElement(By.id("city"));//city
    fakeCity = faker.address().city();
    city.sendKeys(fakeCity);
    Thread.sleep(500);

    //state dropdown
    WebElement selectState = driver.findElement(By.id("id_state"));//state
    Select list = new Select(selectState);
    Thread.sleep(500);
    list.selectByVisibleText("Virginia");

    //postcode
    driver.findElement(By.id("postcode")).sendKeys("22182");//zip
    Thread.sleep(500);

    //phone
    driver.findElement(By.id("phone_mobile")).sendKeys("202-209-5678");//phone

    //alias
    //driver.findElement(By.id("authentication")).sendKeys("Abed");//?????

    //register
    driver.findElement(By.id("submitAccount")).click();//registerButton
    Thread.sleep(5000);

    //verify first name and last name matches full name on top
          String fullNameTop = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).
                  getText();
          String[] str = fullNameTop.split(" ");

          Assert.assertEquals(fakeFirstName,str[0]);
          Assert.assertEquals(fakeLastName,str[1]);

//      10.Click on My personal information
          driver.findElement(By.xpath("//*[.='My personal information']//i")).click();//personalInfoButton
//      11.Verify that personal information is displayed correctly

          //check first name
          driver.findElement(By.id("firstname")).click();
          String first = driver.findElement(By.id("firstname")).getAttribute("value");
          Thread.sleep(500);
          Assert.assertEquals(first,fakeFirstName);

          //check last name
          driver.findElement(By.id("lastname")).click();
          String last = driver.findElement(By.id("lastname")).getAttribute("value");
          Thread.sleep(500);
          Assert.assertEquals(last,fakeLastName);

          //verify email
          driver.findElement(By.id("email")).click();
          String email = driver.findElement(By.id("email")).getAttribute("value");
          Thread.sleep(500);
          Assert.assertEquals(fakeEmail,email);

          //12.Click on Back to your account
          driver.findElement(By.xpath("//*[@class='icon-chevron-left']")).click();
          Thread.sleep(5000);

          //13.Click on My addresses verify that address information is displayed correctly
          driver.findElement(By.xpath("//span[text()='My addresses']/parent::a")).click();
          Thread.sleep(500);

          //verify address
          String street2 = driver.findElement(By.xpath("//span[@class='address_address1']")).getText();
          String city2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[1]")).getText();
          String state = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[2]")).getText();
          String zip =driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[3]")).getText();
          String country = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[6]/span")).getText();
          String address = (street2.concat(city2).concat(state).concat(zip).concat(country)).
                  replace(",","").replace(" ","");
          System.out.println(address);
          String expected = fakeStreet+fakeCity+"Virginia"+"22182"+"United States";
          System.out.println(expected);
          Thread.sleep(500);
          Assert.assertEquals(address,expected.replace(" ",""));

          //14.Click on sign out link
          driver.findElement(By.xpath("//a[@class='logout']")).click();

//        //15.Login using the email and password of the current user
          driver.findElement(By.xpath("//*[@id='email']")).sendKeys(fakeEmail);
          driver.findElement((By.xpath("//*[@id='passwd']"))).sendKeys(password);
          driver.findElement(By.xpath("//i[@class='icon-lock left']")).click();

//       16.Verify that correct first and last name is displayed correctly on top right
          String fullNameTop2 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).
                  getText();
          String[] str2 = fullNameTop.split(" ");
          Thread.sleep(500);

          Assert.assertEquals(fakeFirstName,str2[0]);
          Assert.assertEquals(fakeLastName,str2[1]);
}
         @Test
    public void errorMessageValidation()throws InterruptedException {

             driver.get("http://automationpractice.com/index.php");
             // 3.Click Sign in link
             driver.findElement(By.xpath("//a[@class='login']")).click();

             // 4.Enter new valid email to the email field
             // 5.Click on Create Account
             WebElement email1 = driver.findElement(By.id("email_create"));
             fakeEmail = faker.internet().emailAddress();
             email1.sendKeys(fakeEmail);
             Thread.sleep(500);
             //click on create account
             driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']")).click();

             // 6.Fill all the required steps except for first name
             //last name
             WebElement lastName = driver.findElement(By.id("customer_lastname"));
             fakeLastName = faker.name().lastName();
             lastName.sendKeys(fakeLastName);
             Thread.sleep(500);

             driver.findElement(By.id("passwd")).sendKeys("cyber");
             //address
             WebElement street = driver.findElement(By.id("address1"));
             fakeStreet = faker.address().streetAddress();
             street.sendKeys(fakeStreet);
             WebElement city = driver.findElement(By.id("city"));
             fakeCity = faker.address().city();
             city.sendKeys(fakeCity);
             Thread.sleep(500);
             //state dropdown
             WebElement selectState = driver.findElement(By.id("id_state"));
             Select list = new Select(selectState);
             Thread.sleep(500);

             list.selectByVisibleText("Virginia");
             //postcode
             driver.findElement(By.id("postcode")).sendKeys("22182");
             Thread.sleep(500);

             //phone
             driver.findElement(By.id("phone_mobile")).sendKeys("202-209-5678");
             //alias
             driver.findElement(By.id("authentication")).sendKeys("Abed");

             // 7.Click on Register
             driver.findElement(By.id("submitAccount")).click();
             Thread.sleep(5000);

             // 8.Verify that error message first name is required.is displayed
             String actualMessage = driver.findElement(By.xpath("//html/body/div/div/div/div[3]/div/div/ol/li")).
                     getText();
             String expectedMessage = "firstname is required.";
             System.out.println("actual: "+actualMessage+"||"+"expected: "+expectedMessage);
             Thread.sleep(500);
             Assert.assertEquals(actualMessage,expectedMessage);

         }

         @Test
    public void cartDetail() throws InterruptedException {

//         3.Click on any product that is not on sale
             driver.get("http://automationpractice.com/index.php");
             driver.findElement(By.xpath("//a[contains(text(), 'Faded Short')]")).click();

//         4.Enter a random quantity between 2and 5
             driver.findElement(By.xpath("//*[@class='icon-plus']")).click();

//         5.Select a different size
             WebElement selectSize = driver.findElement(By.id("group_1"));
             Select list = new Select(selectSize);
             list.selectByVisibleText("M");

//         6.Click on add to cart
             driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
             Thread.sleep(500);

//         7.Verify confirmation message Product successfully added to your shopping cart
             String actual = driver.findElement(By.tagName("h2")).getText();
             //*[@id='layer_cart']/div[1]/div[1]/h2
             System.out.println("actual@@@@@: "+actual);
             Thread.sleep(500);
             assertEquals(actual, "Product successfully added to your shopping cart");
             Thread.sleep(500);

//         8.Dismiss the confirmation window by clicking on the x icon
             driver.findElement(By.xpath("//span[@class='cross']")).click();
             Thread.sleep(500);

//         9.Click on the company logo
             driver.findElement(By.xpath("//img[@class='logo img-responsive']")).click();

         }
         @Test
                 public void productOnSale() throws InterruptedException {

             driver.get("http://automationpractice.com/index.php");

//             WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'fancybox')"));
//             driver.switchTo().frame(iframe);
//             // driver.findElement(By.xpath("//input[@id='quantity_wanted']")).click();
//             //driver.findElement(By.xpath("//input[@id='quantity_wanted']")).clear();
//             //driver.findElement(By.xpath("//input[@id='quantity_wanted']")).sendKeys("2");
//
//             driver.findElement(By.xpath("//*[@id='quantity_wanted_p']/a[2]/span/i")).click();
//             //Thread.sleep(3000);



//         10.Click on any product that is on sale
             Actions act = new Actions(driver);
             WebElement pi = driver.findElement(By.xpath("(//img[@src='http://automationpractice.com/img/p/1/2/12-home_default.jpg'])[1]"));

             act.moveToElement(pi).build().perform();
             Thread.sleep(2000);
             WebElement c = driver.findElement(By.xpath("(//span[.='Quick view'])[5]"));
             c.click();

             WebElement frame1 = driver.findElement(By.xpath("//div[@class='fancybox-outer']//iframe"));
             driver.switchTo().frame(frame1);
//         11.Enter a random quantity between 2 and 5
             WebElement element = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
             String be = element.getAttribute("value");
             element.clear();
             System.out.println("check1");
             int quantityRandom = random.nextInt(5)+2;

             element.sendKeys("" +quantityRandom );

//          12.Select a different size
             WebElement selectSize2 = driver.findElement(By.id("group_1"));
             Select list = new Select(selectSize2);
             list.selectByVisibleText("M");
             Thread.sleep(1000);
             System.out.println("check2");
//          13.Click on add to cart
             driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();

//         14.Verify confirmation message Product successfully added to your shopping cart
             String actual = driver.findElement(By.tagName("h2")).getText();
             Thread.sleep(500);
             assertEquals(actual, "Product successfully added to your shopping cart");
             Thread.sleep(500);
             System.out.println("check3");
//         15.Dismiss the confirmation window by clicking on the x icon
             driver.findElement(By.xpath("//span[@class='cross']")).click();
             Thread.sleep(500);

//         16.Hover over the CartPage icon
             WebElement cart = driver.findElement(By.xpath("//span[@class='ajax_cart_product_txt_s unvisible']"));
             actions.moveToElement(cart).perform();
             Thread.sleep(500);
             System.out.println("check5");
//         17.Verify that correct total is displayed
             String quantityCart = driver.findElement(By.xpath("//span[@class='ajax_cart_quantity unvisible']")).getText();
             Thread.sleep(500);
             System.out.println("actual:"+ quantityCart);
             System.out.println("expected:" + quantityRandom);
             Thread.sleep(500);
             String n = "" + quantityRandom;
             Assert.assertEquals(n,quantityCart);
             System.out.println("check6");
//         18.Verify that total is correct based on the price and item count of the products you added to cart.
//         (Shipping is always $2)
             driver.findElement(By.xpath("//span[@class='ajax_cart_quantity unvisible']")).click();
             String total = driver.findElement(By.id("total_price")).getText();
             String total2 = total.substring(1);
             System.out.println(total);
             double total3 = Double.parseDouble(total2);
             String productPrice = driver.findElement(By.xpath("//*[.='$28.98']")).getText();
             String productPrice2 = productPrice.substring(1);
             System.out.println(productPrice2);
             double productPrice3 = Double.parseDouble(productPrice2);
             System.out.println("check7");
             double totalExpected = (productPrice3*quantityRandom)+2;
             Thread.sleep(500);
             System.out.println(total2+" || "+totalExpected);
             assertEquals(total3,totalExpected);



         }

}
