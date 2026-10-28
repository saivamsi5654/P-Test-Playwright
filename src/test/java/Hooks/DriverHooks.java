package Hooks;

import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.example.driver.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DriverHooks {

    @Before
    public void launchBrowser() {
        log.info("Launching browser...");
        DriverManager.initDriver(System.getProperty("browser", "chrome"));
        log.info("Initializing page...");
        DriverManager.initPage();
        DriverManager.getBrowserContext().tracing().start(
                new Tracing.StartOptions().setSnapshots(true).setScreenshots(true));
    }

    @After
    public void clearBrowserContext(Scenario scenario) {
        try {
            if (scenario != null) {
                Path traceDirectory = Paths.get("target", "traces");
                Files.createDirectories(traceDirectory);
                String traceName = scenario.getName()
                        .replaceAll("[^a-zA-Z0-9._-]", "_")
                        + "_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"))
                        + ".zip";
                try {
                    DriverManager.getBrowserContext().tracing().stop(
                            new Tracing.StopOptions().setPath(traceDirectory.resolve(traceName)));
                    log.info("Trace saved to {}", traceDirectory.resolve(traceName));
                } catch (IllegalStateException e) {
                    log.debug("Tracing was not started; skipping trace export.", e);
                }
            }
        } catch (IOException | RuntimeException e) {
            log.warn("Unable to save Playwright trace.", e);
        } finally {
            log.info("Closing active page, context, browser, and Playwright...");
            DriverManager.closeAllBrowser();
        }
    }
}
