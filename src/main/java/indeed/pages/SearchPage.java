package indeed.pages;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
@Log4j2
public class SearchPage {

    private WebDriver driver;

    @FindBy(id = "what")
    private WebElement what_field;
    @FindBy(id = "where")
    private WebElement where_field;
    @FindBy(className = "fj")
    private WebElement find_jobs_button;
    @FindBy(className = "sl")
    private WebElement advanced_search;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


}
