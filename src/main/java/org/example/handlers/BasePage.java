package org.example.handlers;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.example.driver.DriverManager;

import java.time.Duration;

public abstract class BasePage {
    protected Page page;
    protected Actions actions;

    public BasePage() {
        page = DriverManager.getPage();
        actions = new Actions();
    }

    public void refreshPage() {
        this.page.reload();
        this.page.waitForLoadState(LoadState.DOMCONTENTLOADED, new Page.WaitForLoadStateOptions().setTimeout(Duration.ofMinutes(2).toMillis()));
    }
}
