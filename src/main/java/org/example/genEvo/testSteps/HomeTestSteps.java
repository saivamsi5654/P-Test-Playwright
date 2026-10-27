package org.example.genEvo.testSteps;

import org.example.genEvo.pages.HomePage;

public class HomeTestSteps {
    private static final HomePage homePage = new HomePage();

    public void navigateToPracticePage() {
        homePage.goToPracticePage();
    }
}
