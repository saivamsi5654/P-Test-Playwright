package org.example.genEvo.testSteps;

import org.example.genEvo.pages.PracticePage;
import org.example.genEvo.pages.enums.FormControls;
import org.example.genEvo.pages.enums.Modules;

public class PracticeTestSteps {
    private final PracticePage practicePage = new PracticePage();

    public void navigateToModule(Modules modules) {
        practicePage.navigateToModule(modules);
    }

    public void navigateToSubModule(FormControls submodels) {
        practicePage.navigateToSubModule(submodels);
    }

    public void textInputsPractice(String name, String email, String address) {
        practicePage.enterInputName(name)
                .enterInputEmail(email)
                .enterInputAddress(address)
                .submitInput();
    }

    public void textInputsPracticeValidation(String status) {
        practicePage.validateInputStatus(status);
    }
}
