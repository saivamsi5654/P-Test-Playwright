package org.example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.example.utils.ConfigReader;

import java.util.List;

public abstract class BaseDriverFactory {
    public Browser getBrowser() {
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("browser.headless", "false"));
        Browser browser = initBrowser(headless);
        return browser;
    }

    public abstract Browser initBrowser(boolean headless);

    protected Playwright initPlaywright() {
        Playwright playwright = Playwright.create();
        return playwright;
    }

    protected BrowserType.LaunchOptions getLaunchOptions(boolean headless) {
        return getLaunchOptions(headless,null);
    }

    protected BrowserType.LaunchOptions getLaunchOptions(boolean headless, String channel) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);
        launchOptions.setChannel(channel);
        return launchOptions;
    }
}
