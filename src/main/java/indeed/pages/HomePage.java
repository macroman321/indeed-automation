package indeed.pages;

import indeed.general.configuration.EnvConfigurationSection;
import indeed.helpers.ElementHelper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

@Data
@Log4j2
public class HomePage {

    private WebDriver driver;
    private ElementHelper elementHelper;
    //List of elements
    @FindBy(id = "text-input-what")
    private WebElement what_field;
    @FindBy(id = "text-input-where")
    private WebElement where_field;
    @FindBy(className = "icl-Button icl-Button--primary icl-Button--md icl-WhatWhere-button")
    private WebElement find_button;

    private String endpoint = "/";

    public HomePage(WebDriver driver, ITestContext context){
        this.driver = driver;

        EnvConfigurationSection envConfiguration = (EnvConfigurationSection) context.getAttribute("env");
        String craftedUrl = new StringBuilder()
                .append(envConfiguration.getProtocol()).append("://")
                .append(envConfiguration.getBaseHostPostfix())
                .toString();
        this.driver.get(craftedUrl + endpoint);
        PageFactory.initElements(driver, this);
    }

    public SearchPage searchJobs(String keyword, String location){

        this.what_field.sendKeys(keyword);

        this.where_field.sendKeys(location);
        this.find_button.click();
        return new SearchPage(this.driver);
    }

}
