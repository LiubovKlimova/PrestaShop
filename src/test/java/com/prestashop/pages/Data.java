package com.prestashop.pages;

import com.github.javafaker.Faker;
import com.prestashop.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class Data {

    Faker faker = new Faker();
    public Data(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public String getFaker(String value){
        String data ="";
        switch (value){
            case "firstName":
                data = faker.name().firstName();
                break;
            case  "lastName":
                data = faker.name().lastName();
                break;
            case "email":
                data = faker.internet().emailAddress();
                break;
            case "street":
                data = faker.address().streetAddress();
                break;
            case "city" :
                data = faker.address().city();
                break;

        }
        return data;
    }
}
