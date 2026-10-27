package Stepdefinitions.genEvo;

import Stepdefinitions.BaseBDDSteps;
import io.cucumber.java.en.And;

public class HomeBDD extends BaseBDDSteps {

    @And("Navigate to practice page")
    public void navigateToPracticePage() {
        testSteps.genEvoTestSteps()
                .homeTestSteps()
                .navigateToPracticePage();
    }
}
