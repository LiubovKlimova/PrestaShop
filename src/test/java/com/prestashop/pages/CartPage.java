package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public CartPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public void addToCart(){
        Driver.getDriver().findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
    }
}
