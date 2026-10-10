package org.example.orange.testSteps;

import org.example.orange.pages.LoginPage;

public class LoginTestSteps {
    private final LoginPage loginPage = new LoginPage();

    public void loginToApplication(String username, String password) {
        loginPage.enterUserName(username)
                .enterPassword(password)
                .logIn();
    }
}
