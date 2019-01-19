package com.prestashop.pages;

import com.github.javafaker.Faker;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {


    public RegistrationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "email_create")
    public WebElement email;

    @FindBy(xpath = "//button[@class='btn btn-default button button-medium exclusive']")
    public WebElement createAccountButton;

    @FindBy(id = "customer_firstname")
    public WebElement firstNameField;

    @FindBy(id = "customer_lastname")
    public WebElement lastNameField;

    @FindBy(id = "passwd")
    public WebElement passwordField;

    @FindBy(id = "address1")
    public WebElement street;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "id_state")
    public WebElement state;

    @FindBy(id = "postcode")
    public WebElement zip;

    @FindBy(id = "phone_mobile")
    public WebElement phone;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;

    @FindBy(xpath = "//*[.='My personal information']//i")
    public WebElement personalInfoButton;

    //click create new account button
    public void clickCreateAccountButton(String email) {
        this.email.sendKeys(email);
        createAccountButton.click();
    }

    public void clickRegisterButton() {

        Faker faker = new Faker();

         String FAKE_EMAIL = faker.internet().emailAddress();

         String FIRST_NAME = faker.name().firstName();

         String LAST_NAME = faker.name().lastName();

         String STREET = faker.address().streetAddress();

         String CITY = faker.address().city();

         String ZIP = faker.address().zipCode().substring(0, 5);

         int STATE = random.nextInt(50) + 1;

         String PASSWORD = faker.internet().password();

         String PHONE_NUMBER = faker.phoneNumber().cellPhone();

        registerButton.click();

    }


}
