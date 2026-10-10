package Runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"Hooks","Stepdefinitions"},
        plugin = {"pretty","summary"},
        tags = "@TestCase01",
        monochrome = true
)
public class TestRunner {
}
