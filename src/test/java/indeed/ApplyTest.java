package indeed;

import indeed.pages.LoginPage;
import indeed.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApplyTest {
    private WebDriver driver;
    private ITestContext context;

    @BeforeClass
    public void init(ITestContext context){
        this.driver = new FirefoxDriver();
        this.context = context;
    }

    @Test(description = "applying to position automation")
    public void applyToPositions() throws Exception {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("accemail321@gmail.com","onelove1");
        Thread.sleep(1000);
        SearchPage homePage = new SearchPage(this.driver);
        homePage.advanceSearch("security","new york");

    }



    @AfterClass(alwaysRun = true)
    private void tearDown(){
        this.driver.close();
    }
}
