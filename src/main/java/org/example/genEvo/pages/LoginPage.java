package org.example.genEvo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.example.handlers.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {
    private final Locator loginPage = page.locator("//a[@data-testid='pw-header-login']");

    private final Locator name = page.locator("//input[@data-testid='login-username-input']");

    private final Locator pass = page.locator("//input[@data-testid='login-password-input']");

    private final Locator login = page.locator("//button[@data-testid='login-submit-button']");

    public LoginPage enterUsername(String username) {
        actions.fill(name,username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actions.fill(pass,password);
        return this;
    }

    public LoginPage login() {
        actions.click(login);
        assertThat(page.locator("//button[@class='pw-btn pw-btn-secondary pw-btn-sm' and normalize-space()='student']")).isVisible();
        return this;
    }

    public LoginPage loginPage() {
        actions.click(loginPage);
        return this;
    }
}
