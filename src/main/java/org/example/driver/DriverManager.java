package org.example.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DriverManager {
    private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> ACTIVE_BROWSER_CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Page> ACTIVE_PAGE = new ThreadLocal<>();

    public static void initDriver(String browserName) {
        if (BROWSER.get() != null) {
            log.info("Driver initialization skipped; a browser already exists on this thread.");
            return;
        }

        log.info("Starting browser '{}' on thread {}", browserName, Thread.currentThread().getName());
        BaseDriverFactory factory = DriverFactory.createFactory(browserName);
        try {
            Browser browser = factory.getBrowser();
            PLAYWRIGHT.set(factory.getPlaywright());
            BROWSER.set(browser);

            BrowserContext context = browser.newContext();
            context.setDefaultTimeout(10_000);
            context.setDefaultNavigationTimeout(30_000);
            ACTIVE_BROWSER_CONTEXT.set(context);
            log.info("Browser initialization completed.");
        } catch (RuntimeException | Error e) {
            closeQuietly(BROWSER.get(), "browser");
            closeQuietly(PLAYWRIGHT.get(), "Playwright");
            clearThreadLocals();
            throw e;
        }
    }

    public static void initPage() {
        BrowserContext context = ACTIVE_BROWSER_CONTEXT.get();
        if (context == null) {
            throw new IllegalStateException("BrowserContext is not initialized. Call initDriver first.");
        }
        Page page = context.newPage();
        ACTIVE_PAGE.set(page);
    }

    public static Page getPage() {
        Page page = ACTIVE_PAGE.get();
        if (page == null) {
            throw new IllegalStateException("Page is not initialized. Call initPage first.");
        }
        return page;
    }

    public static BrowserContext getBrowserContext() {
        BrowserContext context = ACTIVE_BROWSER_CONTEXT.get();
        if (context == null) {
            throw new IllegalStateException("BrowserContext is not initialized. Call initDriver first.");
        }
        return context;
    }

    public static void closeAllBrowser() {
        // Close in reverse creation order, attempting every close even if one fails.
        closeQuietly(ACTIVE_PAGE.get(), "page");
        closeQuietly(ACTIVE_BROWSER_CONTEXT.get(), "browser context");
        closeQuietly(BROWSER.get(), "browser");
        closeQuietly(PLAYWRIGHT.get(), "Playwright");
        clearThreadLocals();
    }

    private static void closeQuietly(AutoCloseable resource, String resourceName) {
        if (resource == null) {
            return;
        }
        try {
            resource.close();
        } catch (Exception e) {
            log.warn("Unable to close {} cleanly", resourceName, e);
        }
    }

    private static void clearThreadLocals() {
        ACTIVE_PAGE.remove();
        ACTIVE_BROWSER_CONTEXT.remove();
        BROWSER.remove();
        PLAYWRIGHT.remove();
    }
}
