package com.epam.framework.listeners;

import com.epam.framework.utils.AllureUtils;
import com.epam.framework.utils.LoggerUtils;
import com.epam.framework.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info(getClass(), "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info(getClass(), "Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        LoggerUtils.error(getClass(), "Test failed: " + testName, result.getThrowable());

        byte[] screenshotBytes = ScreenshotUtils.captureScreenshotAsBytes();
        if (screenshotBytes != null) {
            AllureUtils.attachScreenshot(testName + "_failure", screenshotBytes);
        }
        AllureUtils.attachPageSource();

        // Also keep a copy on disk for local debugging
        ScreenshotUtils.captureScreenshot(testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn(getClass(), "Test skipped: " + result.getMethod().getMethodName());
    }
}