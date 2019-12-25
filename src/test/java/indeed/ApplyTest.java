package indeed;

import indeed.pages.HomePage;
import org.openqa.selenium.WebDriver;
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

    @Test()
    public void applyToPositions(){

        HomePage homePage = new HomePage(this.driver,this.context);
        homePage.searchJobs("junior software engineer", "New York");

    }



    @AfterClass(alwaysRun = true)
    private void tearDown(){
        this.driver.close();
    }
}
