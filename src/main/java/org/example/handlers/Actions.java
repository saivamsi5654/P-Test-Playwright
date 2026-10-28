package org.example.handlers;

import com.microsoft.playwright.Locator;
import org.example.annotations.UiAction;

public class Actions {

    @UiAction(description = "Fill Element: {0} with value: {1}", error = "Unable to fill the value")
    public Actions fill(Locator locator,String value) {
        locator.fill(value);
        return this;
    }

    @UiAction(description = "Click Element: {0}", error = "Unable to click the element")
    public Actions click(Locator locator) {
        locator.click();
        return this;
    }
}
