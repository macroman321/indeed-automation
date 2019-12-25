package indeed.helpers;

import indeed.exceptions.TAFSeleniumException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * ElementHelper class
 *
 * @author Aleksandar Coha
 */
public class ElementHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Empty constructor for methods that are NOT using WebDriverWait
     */
    public ElementHelper() {
    }

    /**
     * Constructor for methods that are using WebDriverWait
     *
     * @param driver WebDriver
     */
    public ElementHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 120);
    }

    /**
     * Constructor for methods that are using WebDriverWait with dynamic wait time
     *
     * @param driver   WebDriver
     * @param waitTime wait time in seconds
     */
    public ElementHelper(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTime);
    }

    /**
     * Wait for element to be displayed on page
     *
     * @param element WebElement
     */
    public void waitForElementDisplayed(WebElement element) {
        System.out.println("Waiting for element displayed - Element: " + element.toString());
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be displayed on page
     *
     * @param element WebElement
     */
    public void waitForElementDisplayedIterative(WebElement element) {
        final int timeMs = 15000;
        for (int i = 200; i < timeMs; i += 100) {
            System.out.println("Waiting for element displayed (ms): " + i + " Element: " + element.toString());
            new Utils().waitTime(200);
            if (isElementDisplayed(element)) {
                return;
            }
        }
        System.out.println(String.format("ElementHelper is not displayed after %d seconds", timeMs / 1000));
    }

    /**
     * Method to check is element enabled
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Method to check is element displayed
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            String tagName = element.getTagName();
            if (!tagName.isEmpty()) {
                isDisplayed = element.isDisplayed();
            }
            return isDisplayed;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method to check is element selected
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isElementSelected(WebElement element) {
        boolean isSelected = false;
        try {
            isSelected = element.isSelected();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSelected;
    }

    /**
     * Method to check is element checked
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isElementChecked(WebElement element) {
        try {
            String attribute = element.getAttribute("checked");
            return attribute.contains("true");
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Method to get first available option from drop down
     *
     * @param element WebElement
     * @return String with first option name
     */
    public String getFirstAvailableOption(WebElement element) {
        List<String> options = getAllAvailableOptions(element);
        return options.get(1);
    }

    /**
     * Method to get last available option from drop down
     *
     * @param element WebElement
     * @return String with last option name
     */
    public String getLastAvailableOption(WebElement element) {
        List<String> options = getAllAvailableOptions(element);
        return options.get(options.size() - 1);
    }

    /**
     * Method returning selected option text
     *
     * @param element WebElement
     * @return String with selected option
     */
    public String getSelectedOptionText(WebElement element) {
        Select select = new Select(element);
        WebElement option = select.getFirstSelectedOption();
        return option.getText().trim();
    }

    /**
     * Method to get number of elements from WebElement List
     *
     * @param elements WebElement List
     * @return number of elements
     */
    public int getNumberOfElements(List<WebElement> elements) {
        return elements.size();
    }

    /**
     * Select option from drop down by text
     *
     * @param element WebElement
     * @param text    text to select
     */
    public void selectOptionByText(WebElement element, String text) {
        if (text == null) {
            return;
        }
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Select option from drop down by value
     *
     * @param element WebElement
     * @param value   value to select
     */
    public void selectOptionByValue(WebElement element, String value) {
        System.out.println("Selecting Option :" + value + " in:" + element.toString());
        if (value == null) {
            return;
        }
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Select option by text in parent element
     *
     * @param parent   parent element
     * @param children list of children elements
     * @param text     test to select
     */
    public void selectOptionByText(WebElement parent, List<WebElement> children, String text) {
        parent.click();
        new Utils().waitTime500ms();
        for (WebElement option : children) {
            System.out.println("Current option: " + option.getText());
            if (text.equals(option.getText())) {
                System.out.println("Click option: " + option.getText());
                option.click();
                new Utils().waitTime500ms();
                return;
            }
        }
    }

    /**
     * Get list of all available options
     *
     * @param element WebElement
     * @return List of available options
     */
    public List<String> getAllAvailableOptions(WebElement element) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        List<String> captions = new ArrayList<>();
        for (WebElement option : options) {
            captions.add(option.getText().trim());
        }
        return captions;
    }

    /**
     * Wait for element to be clickable
     *
     * @param element WebElement
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for element to be clickable
     *
     * @param element By element
     */
    public void waitForElementToBeClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for presence of element located on page
     *
     * @param element WebElement
     */
    public void waitForPresenceOfElementLocated(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    /**
     * Wait for frame to be available and switch to it
     *
     * @param element String element: "ifrmLogin"
     */
    public void waitForFrameToBeAvailableAndSwitchToIt(String element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    /**
     * Function to execute Javascript using WebDriver
     * <p>
     * To use it here are some following example:
     * <p>
     * driver.executeScript("window.scrollBy(0,150)");
     * driver.executeScript("document.getElementById('" + elementId + "').value").toString();
     *
     * @param driver      Webdriver
     * @param scriptToRun script
     * @return JavaScript object
     */
    public Object executeScript(WebDriver driver, String scriptToRun) {
        return ((JavascriptExecutor) driver).executeScript(scriptToRun);
    }

    /**
     * This helper is required for fixing this
     * version of selenium in switching tabs bug.
     * eg: Before clicking payment purchase button,
     * make sure we close other tabs first except main.
     */
    public void closeOtherTabsExceptMain() {
        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    /**
     * This helper is to help UI to switch between tabs.
     * Example switchToTabContainingExpectedElement(paymentButton, 25);
     *
     * @param expectedElement WebElement
     * @param expectedElement WebElement
     * @param timeout         Integer timeout - How many times method will try to find element on tab
     * @throws TAFSeleniumException if error occurs
     */
    public void switchToTabContainingExpectedElement(WebElement expectedElement, int timeout) throws TAFSeleniumException {
        int timeOutCtr = 0;
        while (!isElementDisplayed(expectedElement)) {
            new Utils().waitTime(500);
            String currentWindowHandle = driver.getWindowHandle();
            List<String> windowHandles = new ArrayList(driver.getWindowHandles());

            for (String window : windowHandles) {
                if (window != null && !window.equals(currentWindowHandle)) {
                    driver.switchTo().window(window);
                    driver.switchTo().window(currentWindowHandle);
                    driver.switchTo().window(window);
                }
            }
            if (timeOutCtr >= timeout) {
                throw new TAFSeleniumException("Timeout!");
            }
            timeOutCtr++;
        }
        System.out.println("Success! Expected element: " + expectedElement + " is present on page!");
    }

    /**
     * This function will fire event to click on element
     *
     * @param element WebElement
     */
    public void clickElementJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (driver.toString().contains("InternetExplorer")) {
            System.out.println("ie");
            js.executeScript("arguments[0].scrollIntoView(true);arguments[0].fireEvent( \'onclick \');", element);
        } else {
            System.out.println("ff or other");
            js.executeScript("var evObj=document.createEvent('MouseEvents');" +
                    "evObj.initEvent('click',true,true );arguments[0].dispatchEvent(evObj);", element);
        }
    }

    /**
     * Method to click on element using JS
     *
     * @param element WebElement
     */
    public void clickUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to click on element using JS
     *
     * @param locator     String locator
     * @param locatorType locator type
     * @throws TAFSeleniumException if error occurs
     */
    public void clickUsingJS(String locator, String locatorType) throws TAFSeleniumException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String scriptToExecute;
        if ("id".equalsIgnoreCase(locatorType)) {
            scriptToExecute = "document.getElementById('" + locator + "')";
        } else if ("xpath".equalsIgnoreCase(locatorType)) {
            scriptToExecute = "document.evaluate(\"" +
                    locator + "\", document, null, XPathResult.ANY_TYPE, null).iterateNext()";
        } else {
            throw new TAFSeleniumException("Only id and xpath locators are supported");
        }
        scriptToExecute = scriptToExecute + ".click();";
        System.out.println("Script to execute: " + scriptToExecute);
        jse.executeScript(scriptToExecute);
    }
}