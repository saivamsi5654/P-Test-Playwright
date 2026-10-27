package org.example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Synchronized;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DriverManager {
    private static final InheritableThreadLocal<Browser> BROWSER  = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<BrowserContext> ACTIVE_BROWSER_CONTEXT = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<Page> ACTIVE_PAGE = new InheritableThreadLocal<>();

    @Synchronized
    public static void initDriver(String browserName) {
        if(BROWSER.get() == null) {
            log.info("Driver Instantiation started...");
            log.info("Driver running on Thread: {}", Thread.currentThread());
            Browser browser = DriverFactory.createDriver(browserName);
            BROWSER.set(browser);
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions());
            browserContext.setDefaultTimeout(120000);
            ACTIVE_BROWSER_CONTEXT.set(browserContext);
            log.info("Driver Instantiation completed...");
        } else {
            log.info("Driver Initialization skipped...");
        }
    }

    @Synchronized
    public static void initPage() {
        BrowserContext browserContext = ACTIVE_BROWSER_CONTEXT.get();
        Page page = browserContext.newPage();
        ACTIVE_PAGE.set(page);
    }

    @Synchronized
    public static Page getPage() {
        return ACTIVE_PAGE.get();
    }

    @Synchronized
    public static BrowserContext getBrowserContext() {
        return ACTIVE_BROWSER_CONTEXT.get();
    }

    @Synchronized
    public static void closeAllBrowser() {
        ACTIVE_PAGE.get().close();
        ACTIVE_PAGE.remove();
        ACTIVE_BROWSER_CONTEXT.get().close();
        ACTIVE_BROWSER_CONTEXT.remove();
        BROWSER.get().close();
        BROWSER.remove();
    }

}
