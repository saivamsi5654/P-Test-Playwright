package org.example.driver;

import com.microsoft.playwright.Browser;

public class DriverFactory {

    public static Browser createDriver(String browserName) {
        BaseDriverFactory driverFactory;
        switch(browserName) {
            case "chrome":
                driverFactory = new ChromeDriverFactory();
                break;
            case "firefox":
                driverFactory = new FirefoxDriverFactory();
                break;
            default:
                throw new IllegalArgumentException("Unable to create a browser with name {}" + browserName);
        }
        return driverFactory.getBrowser();
    }
}
