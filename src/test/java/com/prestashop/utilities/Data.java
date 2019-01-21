package com.prestashop.utilities;

import com.github.javafaker.Faker;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class Data {

    Faker faker = new Faker();

    String FAKE_EMAIL = faker.internet().emailAddress();

    String FIRST_NAME = faker.name().firstName();

    String LAST_NAME = faker.name().lastName();

    String STREET = faker.address().streetAddress();

    String CITY = faker.address().city();

    String ZIP = faker.address().zipCode().substring(0, 5);

    //int STATE = random.nextInt(50) + 1;

    String PASSWORD = faker.internet().password();

    String PHONE_NUMBER = faker.phoneNumber().cellPhone();

    public Data() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public String getFaker(String value) {
        String data = "";
        switch (value) {
            case "first name":
                data = faker.name().firstName();

            case "last name":
                data = faker.name().lastName();
                break;
            case "email":
                data = faker.internet().emailAddress();
                break;
            case "street":
                data = faker.address().streetAddress();
                break;
            case "city":
                data = faker.address().city();
                break;

        }
        return data;
    }


}
