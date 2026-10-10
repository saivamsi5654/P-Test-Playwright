package org.example.genEvo.pages;

import com.microsoft.playwright.Locator;
import org.example.genEvo.pages.enums.Modules;
import org.example.handlers.BasePage;

public class PracticePage extends BasePage {

    private final String modules = "//button[@data-testid='pp-nav-module-%s']";

    public PracticePage selectModule(Modules module) {
        actions.click(page.locator(String.format(modules,module.getLabel())));
        return this;
    }

}
