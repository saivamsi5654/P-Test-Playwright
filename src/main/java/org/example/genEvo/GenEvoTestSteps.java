package org.example.genEvo;

import org.example.genEvo.testSteps.HomeTestSteps;
import org.example.genEvo.testSteps.LoginTestSteps;
import org.example.genEvo.testSteps.PracticeTestSteps;

public class GenEvoTestSteps {
    public LoginTestSteps loginTestSteps() {
        return new LoginTestSteps();
    }

    public HomeTestSteps homeTestSteps() {
        return new HomeTestSteps();
    }

    public PracticeTestSteps practiceTestSteps() {
        return new PracticeTestSteps();
    }
}
