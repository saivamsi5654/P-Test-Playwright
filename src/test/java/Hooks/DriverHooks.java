package Hooks;

import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.example.driver.DriverManager;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DriverHooks {

    @Before
    public void launchBrowser() {
        log.info("Launching Browser....");
        DriverManager.initDriver("chrome");
        log.info("Initializing Page...");
        DriverManager.initPage();
        DriverManager.getBrowserContext().tracing().start(new Tracing.StartOptions().setSnapshots(true).setScreenshots(true));
    }

    @After
    public void clearBrowserContext(Scenario scenario) {
        Paths.get("target/traces").toFile().mkdir();
        String traceName = scenario.getName()
                .replace(" ", "_")
                + "_"
                + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"))
                + ".zip";
        DriverManager.getBrowserContext().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/traces/" + traceName)));
        log.info("Close all Active BrowserContexts and Browser...");
        DriverManager.closeAllBrowser();
        log.info("Successfully closed browsers...");
    }
}
