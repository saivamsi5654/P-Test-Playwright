package org.example.driver;

import com.microsoft.playwright.Browser;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static BaseDriverFactory createFactory(String browserName) {
        if (browserName == null || browserName.isBlank()) {
            throw new IllegalArgumentException("Browser name must not be blank");
        }

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
            case "chromium":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    public static Browser createDriver(String browserName) {
        return createFactory(browserName).getBrowser();
    }
}