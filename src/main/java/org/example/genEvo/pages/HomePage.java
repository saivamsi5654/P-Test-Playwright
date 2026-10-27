package org.example.genEvo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.example.handlers.BasePage;

public class HomePage extends BasePage {

    private final Locator practice = page.locator("//nav[@aria-label='Primary']//a[@class='pw-nav-link'][normalize-space()='Practice']");

    public HomePage goToPracticePage() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        actions.click(practice);
        return this;
    }
}
