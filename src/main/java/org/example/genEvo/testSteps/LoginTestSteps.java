package org.example.genEvo.testSteps;

import org.example.genEvo.pages.LoginPage;

public class LoginTestSteps {

    // Each Cucumber scenario must use a page object bound to its own Page.
    private final LoginPage login = new LoginPage();

    public void launchApplication(String username, String password) {
        login.loginPage()
                .enterUsername(username)
                .enterPassword(password)
                .login();
    }
}
