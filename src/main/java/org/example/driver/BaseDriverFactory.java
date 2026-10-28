package org.example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.example.utils.ConfigReader;

public abstract class BaseDriverFactory {
    private Playwright playwright;

    public Browser getBrowser() {
        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("browser.headless", "false"));
        return initBrowser(headless);
    }

    public abstract Browser initBrowser(boolean headless);

    protected Playwright initPlaywright() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    protected BrowserType.LaunchOptions getLaunchOptions(boolean headless) {
        return getLaunchOptions(headless, null);
    }

    protected BrowserType.LaunchOptions getLaunchOptions(boolean headless, String channel) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);
        if (channel != null && !channel.isBlank()) {
            options.setChannel(channel);
        }
        return options;
    }
}
