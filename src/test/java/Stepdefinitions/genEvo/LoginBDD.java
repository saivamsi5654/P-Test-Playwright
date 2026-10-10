package Stepdefinitions.genEvo;

import Stepdefinitions.BaseBDDSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import org.example.driver.DriverManager;

public class LoginBDD extends BaseBDDSteps {

    @Given("login to GenEvo application with username {string} and password {string}")
    public void loginToApplication(String username, String password) {
        Page page = DriverManager.getPage();
        page.navigate("https://www.playwrightautomation.com/");
        testSteps.genEvoTestSteps()
                .loginTestSteps()
                .launchApplication(username,password);
    }
}
