package org.example.orange.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.annotations.UiElement;
import org.example.handlers.BasePage;

public class LoginPage extends BasePage {

    @UiElement("Username")
    private final Locator user = page.getByPlaceholder("Username");

    @UiElement("Password")
    private final Locator pass = page.getByPlaceholder("Password");

    @UiElement("Log In Button")
    private final Locator login = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true));


    public LoginPage enterUserName(String username) {
        actions.fill(user,username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actions.fill(pass,password);
        return this;
    }

    public LoginPage logIn() {
        actions.click(login);
        return this;
    }

}
