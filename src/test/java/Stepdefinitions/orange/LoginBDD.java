package Stepdefinitions.orange;

import Stepdefinitions.BaseBDDSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import org.example.driver.DriverManager;
import org.example.utils.ConfigReader;

public class LoginBDD extends BaseBDDSteps {

    @Given("login to application with username {string} and password {string}")
    public void launchApplication(String username, String password) {
        Page page = DriverManager.getPage();
        page.navigate("https://opensource-demo.orangehrmlive.com");
        testSteps.orangeTestSteps()
                .loginTestSteps()
                .loginToApplication(username, password);
    }
}
