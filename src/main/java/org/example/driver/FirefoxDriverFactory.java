package org.example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;

public class FirefoxDriverFactory extends BaseDriverFactory{
    @Override
    public Browser initBrowser(boolean headless) {
        BrowserType browserType = initPlaywright().firefox();
        return browserType.launch(getLaunchOptions(headless));
    }
}
