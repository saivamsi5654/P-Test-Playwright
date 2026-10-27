package org.example.genEvo.pages;

import com.microsoft.playwright.Locator;
import org.example.genEvo.pages.enums.FormControls;
import org.example.genEvo.pages.enums.Modules;
import org.example.handlers.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PracticePage extends BasePage {

    // Modules and SubModules locators
    private final String modules = "//button[@data-testid='pp-nav-module-%s']";

    private final String subModule = "//a[@data-testid='pp-side-nav-%s']";

    //Form Controls locators
    //Text Inputs Locators
    private final Locator nameInput = page.getByPlaceholder("Enter your name");

    private final Locator emailInput = page.locator("//input[@data-testid='input-email']");

    private final Locator addressInput = page.getByTestId("input-address");

    private final Locator submitInput = page.locator("//button[@id='btn-submit-text-inputs']");

    private final Locator resetInput = page.locator("//button[@id='btn-reset-text-inputs']");

    private final Locator inputValidation = page.locator("//p[@id='text-inputs-status']");

    public PracticePage navigateToModule(Modules module) {
        actions.click(page.locator(String.format(modules,module.getLabel())));
        return this;
    }

    public PracticePage navigateToSubModule(FormControls submodule) {
        actions.click(page.locator(String.format(subModule,submodule.getLabel())));
        return this;
    }

    public PracticePage enterInputName(String name) {
        actions.fill(nameInput,name);
        assertThat(nameInput).hasValue(name);
        return this;
    }

    public PracticePage enterInputEmail(String email) {
        actions.fill(emailInput,email);
        assertThat(emailInput).hasValue(email);
        return this;
    }

    public PracticePage enterInputAddress(String address) {
        actions.fill(addressInput,address);
        assertThat(addressInput).hasValue(address);
        return this;
    }

    public PracticePage submitInput() {
        actions.click(submitInput);
        return this;
    }

    public PracticePage resetInput() {
        actions.click(resetInput);
        return this;
    }

    public PracticePage validateInputStatus(String status) {
        assertThat(inputValidation).hasText(status);
        return this;
    }

}
