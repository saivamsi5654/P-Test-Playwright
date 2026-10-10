package org.example;

import org.example.genEvo.GenEvoTestSteps;
import org.example.orange.OrangeTestSteps;

public class TestSteps {
    public OrangeTestSteps orangeTestSteps() {
        return new OrangeTestSteps();
    }

    public GenEvoTestSteps genEvoTestSteps() {
        return new GenEvoTestSteps();
    }
}
