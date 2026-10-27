package Runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"Hooks","Stepdefinitions"},
        plugin = {"pretty","summary"},
        tags = "@PracticePage",
        monochrome = true
)
public class TestRunner {
}
