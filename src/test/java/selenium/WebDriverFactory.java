package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    public static WebDriver configureFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\WebDrivers\\geckodriver.exe");
        FirefoxProfile profile = new ProfilesIni().getProfile("selenium");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return new FirefoxDriver(capabilities);
    }

    public static WebDriver configureChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.addArguments("--enable-automation");
        options.addArguments("test-type=browser");
        options.addArguments("disable-infobars");
        return new ChromeDriver(options);
    }

}
