package org.example.genEvo.pages;

import com.microsoft.playwright.Locator;
import org.example.handlers.BasePage;

public class HomePage extends BasePage {

    private final Locator practice = page.locator("//a[@href='/practice.html']");

    public HomePage goToPracticePage() {
        actions.click(practice);
        return this;
    }
}
