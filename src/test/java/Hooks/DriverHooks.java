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
        log.info("Launching browser....");
        try {
            DriverManager.initDriver(System.getProperty("browser", "chrome"));
            log.info("Initializing page...");
            DriverManager.initPage();
            DriverManager.getBrowserContext().tracing().start(
                    new Tracing.StartOptions()
                            .setSnapshots(true)
                            .setScreenshots(true));
        } catch (RuntimeException | Error e) {
            // A failed setup must not leave a browser or Playwright instance behind.
            DriverManager.closeAllBrowser();
            throw e;
        }
    }

    @After
    public void clearBrowserContext(Scenario scenario) {
        try {
            if (scenario != null) {
                Path traceDirectory = Paths.get("target", "traces");
                Files.createDirectories(traceDirectory);

                String safeName = scenario.getName()
                        .replaceAll("[^a-zA-Z0-9._-]", "_");
                String traceName = safeName + "_"
                        + LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"))
                        + ".zip";
                Path tracePath = traceDirectory.resolve(traceName);

                try {
                    DriverManager.getBrowserContext().tracing().stop(
                            new Tracing.StopOptions().setPath(tracePath));

                    // Attach the trace to the Cucumber result so it can be retrieved from reports.
                    scenario.attach(Files.readAllBytes(tracePath),
                            "application/zip", traceName);
                    log.info("Trace saved and attached: {}", tracePath);
                } catch (IllegalStateException e) {
                    log.debug("Tracing was not started or the browser context is unavailable; skipping trace export.", e);
                }
            }
        } catch (IOException | RuntimeException e) {
            log.warn("Unable to save or attach Playwright trace.", e);
        } finally {
            log.info("Closing active page, context, browser, and Playwright...");
            DriverManager.closeAllBrowser();
        }
    }
}
