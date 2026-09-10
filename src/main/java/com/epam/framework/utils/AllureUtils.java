package com.epam.framework.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import com.epam.framework.driver.DriverManager;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AllureUtils {

    private AllureUtils() {
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(String name, byte[] screenshotBytes) {
        return screenshotBytes;
    }

    public static void attachScreenshotFromFile(String name, String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Path.of(filePath));
            Allure.addAttachment(name, "image/png", new ByteArrayInputStream(bytes), ".png");
        } catch (Exception e) {
            LoggerUtils.error(AllureUtils.class, "Failed to attach screenshot to Allure report", e);
        }
    }

    @Attachment(value = "Page Source", type = "text/plain")
    public static String attachPageSource() {
        return DriverManager.getDriver().getPageSource();
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        return content;
    }
}