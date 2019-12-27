package indeed.pages;

import indeed.helpers.ElementHelper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Data
@Log4j2
public class SearchPage {

    private WebDriver driver;

    @FindBy(id = "what")
    private WebElement what_field;
    @FindBy(id = "where")
    private WebElement where_field;
    @FindBy(id = "as_and")
    private WebElement all_words;
    @FindBy(id = "st")
    private WebElement show_jobs;
    @FindBy(id = "fj")
    private WebElement find_jobs_button;
    @FindBy(xpath = "/html/body/table[1]/tbody/tr/td/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td[4]/div/a")
    private WebElement advanced_search;
    @FindBy(id = "resultsCol")
    private WebElement searchResults;
    @FindBy(id = "form-action-cancel")
    private WebElement cancelBtn;
    @FindBy(id = "vjs-x")
    private WebElement xBtn;
    @FindBy(className = "indeed-apply-button-label")
    private WebElement applyBtn;
    @FindBy(id = "form-action-continue")
    private WebElement continueBtn;
    @FindBy(id = "form-action-back")
    private WebElement backBtn;
    @FindBy(id = "form-action-submit")
    private WebElement finalApplyBtn;
    @FindBy(css = ".np")
    private WebElement nextPageBtn;


    public SearchPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("https://www.indeed.com/advanced_search");
        PageFactory.initElements(driver,this);
    }
    //html body.ltr.jasxcustomfonttst-inactive.janus.miniRefresh table#resultsBody tbody tr td table#pageContent tbody tr td#resultsCol div#p_46977b1fa48465b5.jobsearch-SerpJobCard.unifiedRow.row.result.clickcard
    public void advanceSearch(String keyword, String location) throws Exception {
        this.all_words.sendKeys(keyword);
        this.where_field.sendKeys(location);
        Select select = new Select(show_jobs);
        select.selectByIndex(1);
        ElementHelper elementHelper = new ElementHelper(this.driver);
        elementHelper.executeScript(this.driver, "window.scrollBy(0,1000)");
        this.find_jobs_button.click();
        Thread.sleep(1000);
        while (this.nextPageBtn.isDisplayed()){
        List<WebElement> results = searchResults.findElements(By.tagName("div"));
        int i = 0;
        for (i = 0; i < results.size(); i++) {
            if (!results.get(i).getText().toLowerCase().contains(keyword.toLowerCase()) ||
                    !results.get(i).isEnabled() ||
                    results.get(i).getAttribute("id").startsWith("editsaved2") ||
                    results.get(i).getAttribute("class").startsWith("result-link-ba")) {
                results.remove(i);
            }
        }
        for (WebElement result : results) {

            //log.info(result.getText());

            //Thread.sleep(1000);
            if (result != null && !result.getText().isEmpty() && result.isDisplayed() && result.getAttribute("id").startsWith("p")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", result);
                Thread.sleep(500);
                try {
                    result.click();
                    Thread.sleep(500);
                    if (this.applyBtn.isDisplayed()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.applyBtn);
                        this.applyBtn.click();
                        Thread.sleep(2000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.continueBtn);
                        Thread.sleep(500);
                        if(this.continueBtn.isDisplayed()){
                            this.continueBtn.click();
                        } else {
                            this.driver.close();
                        }

                        Thread.sleep(500);
                        if (this.finalApplyBtn.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.finalApplyBtn);
                            this.finalApplyBtn.click();
                        } else {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.backBtn);
                            this.backBtn.click();
                            Thread.sleep(500);
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.cancelBtn);
                            this.cancelBtn.click();
                        }
                        this.xBtn.click();
                    } else {
                        this.xBtn.click();
                    }
                } catch (Exception e) {
                    //i++;
                }
            }
        }

        this.nextPageBtn.click();
    }
        //Thread.sleep(10000);
    }

}
