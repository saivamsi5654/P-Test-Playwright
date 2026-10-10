package org.example.driver;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;

public class ChromeDriverFactory extends BaseDriverFactory {
    public Browser initBrowser(boolean headless) {
        BrowserType browserType = initPlaywright().chromium();
        Browser browser = browserType.launch(getLaunchOptions(headless));
        return browser;
    }
}
