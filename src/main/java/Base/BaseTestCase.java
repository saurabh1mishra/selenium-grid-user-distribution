package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class BaseTestCase extends DeviceManager {

    public static Logger log = Logger.getLogger(BaseTestCase.class.getName());
    private Device threadName;

    public BaseTestCase(String threadName) {
        super(Device.fromString(threadName));
    }

    public BaseTestCase(Device threadName) {
        super(threadName);
        this.threadName = threadName;

    }

    public void ScrollingDown() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void ScrollingUp() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        js.executeScript("window.scrollTo( document.body.scrollHeight,0)");
    }

    public void clearCahcheChrrome() {
        getDriver().manage().deleteAllCookies();
        getDriver().get("chrome://settings/clearBrowserData");
        getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    public void setTimeout(long timeout) {
        getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @BeforeClass
    public void testCaseSetup() {
        setDriver();
        if (threadName != Device.MOBILECHROME) {
            getDriver().manage().window().maximize();
        }
        setTimeout(4000);
    }

    @AfterClass
    public void tearDown() {
        if (threadName == Device.REMOTECHROME || threadName == Device.CHROME) {
            clearCahcheChrrome();
        }
        getDriver().quit();
    }

}
