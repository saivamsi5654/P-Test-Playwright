package Stepdefinitions.genEvo;

import Stepdefinitions.BaseBDDSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.genEvo.pages.enums.FormControls;
import org.example.genEvo.pages.enums.Modules;

import java.util.Locale;

public class PracticeBDD extends BaseBDDSteps {

    @And("Navigate to {string} module")
    public void navigateToModules(String modules) {
        testSteps.genEvoTestSteps()
                .practiceTestSteps()
                .navigateToModule(Modules.valueOf(modules.trim()
                        .replace('-', '_')
                        .toUpperCase(Locale.ROOT)));
    }

    @And("Navigate to {string} submodule")
    public void navigateToSubModules(String submodules) {
        testSteps.genEvoTestSteps()
                .practiceTestSteps()
                .navigateToSubModule(FormControls.valueOf(submodules.trim()
                        .replace('-', '_')
                        .toUpperCase(Locale.ROOT)));
    }

    @When("Enter name {string}, email {string} and address {string}")
    public void textInputs(String name, String email, String address) {
        testSteps.genEvoTestSteps()
                .practiceTestSteps()
                .textInputsPractice(name, email, address);
    }

    @Then("Validate text input status with {string}")
    public void textInputsValidation(String status) {
        testSteps.genEvoTestSteps()
                .practiceTestSteps()
                .textInputsPracticeValidation(status);
    }

}
