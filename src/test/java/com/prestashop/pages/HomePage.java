package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    // SignIn buttton
    @FindBy(xpath = "//a[@class='login']")
    public WebElement loginButton;


    // open website
    public void open() {
        Driver.getDriver().get("http://automationpractice.com/index.php");
    }

    // choose product
    public WebElement product(String ProductName) {
        String xpath = "(//h5/a[@title = '" + ProductName + "'])[1]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public void signInButon() {
        loginButton.click();
    }

}
