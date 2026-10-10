package org.example.genEvo.pages;

import org.example.genEvo.pages.enums.FormControls;
import org.example.handlers.BasePage;

public class FormControlsPage extends BasePage {

    private final String subModule = "//a[@data-testid='pp-side-nav-text-%s']";


    public FormControlsPage navigateToSubModule(FormControls submodule) {
        actions.click(page.locator(String.format(subModule,submodule.getLabel())));
        return this;
    }

    
}
