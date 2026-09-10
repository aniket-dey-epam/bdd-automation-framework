package com.epam.framework.utils;

import com.epam.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "test-output/screenshots";
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {
    }

    public static String captureScreenshot(String name) {
        try {
            File source = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String safeName = sanitize(name) + "_" + LocalDateTime.now().format(TIMESTAMP_FORMAT) + ".png";
            Path destination = Path.of(SCREENSHOT_DIR, safeName);

            Files.createDirectories(destination.getParent());
            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            LoggerUtils.info(ScreenshotUtils.class, "Screenshot saved: " + destination);
            return destination.toString();
        } catch (Exception e) {
            LoggerUtils.error(ScreenshotUtils.class, "Failed to capture screenshot for '" + name + "'", e);
            return null;
        }
    }

    public static byte[] captureScreenshotAsBytes() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            LoggerUtils.error(ScreenshotUtils.class, "Failed to capture screenshot bytes", e);
            return null;
        }
    }

    private static String sanitize(String name) {
        return name == null ? "unnamed" : name.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}