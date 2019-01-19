package com.prestashop.utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected Actions actions;
    protected SoftAssert softAssert;
    protected Random random = new Random();
//
//    Faker faker = new Faker();
//
//    public final String FAKE_EMAIL = faker.internet().emailAddress();
//
//    public final String FIRST_NAME = faker.name().firstName();
//
//    public final String LAST_NAME = faker.name().lastName();
//
//    public final String STREET = faker.address().streetAddress();
//
//    public final String CITY = faker.address().city();
//
//    public final String ZIP = faker.address().zipCode().substring(0, 5);
//
//    public final int STATE = random.nextInt(50) + 1;
//
//    public final String PASSWORD = faker.internet().password();
//
//    public final String PHONE_NUMBER = faker.phoneNumber().cellPhone();


    @BeforeMethod
    public void setUpMethod() {

        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        driver.manage().window().maximize();
        Faker faker = new Faker();


    }

    @AfterMethod
    public void tearDownMethod() {
        Driver.closeDriver();
        softAssert.assertAll();
    }
}
