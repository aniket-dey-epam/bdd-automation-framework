package com.epam.framework.config;

import com.epam.framework.driver.BrowserType;

public final class BrowserConfig {

    private final BrowserType browser;
    private final boolean headless;
    private final int explicitWait;

    private BrowserConfig(Builder builder) {
        this.browser = builder.browser;
        this.headless = builder.headless;
        this.explicitWait = builder.explicitWait;
    }

    public BrowserType getBrowser() {
        return browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public int getExplicitWait() {
        return explicitWait;
    }

    public static BrowserConfig fromConfig() {
        String browserValue = ConfigReader.getProperty("browser");
        if (browserValue == null) {
            throw new IllegalArgumentException("Missing required property: browser");
        }

        return new Builder()
                .browser(BrowserType.valueOf(browserValue.toUpperCase()))
                .headless(Boolean.parseBoolean(ConfigReader.getProperty("headless")))
                .explicitWait(parseWaitOrDefault(ConfigReader.getProperty("explicitWait")))
                .build();
    }

    private static int parseWaitOrDefault(String value) {
        if (value == null) {
            return 10;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 10;
        }
    }

    @Override
    public String toString() {
        return "BrowserConfig{" +
                "browser=" + browser +
                ", headless=" + headless +
                ", explicitWait=" + explicitWait +
                '}';
    }

    public static class Builder {

        private BrowserType browser;
        private boolean headless;
        private int explicitWait;

        public Builder browser(BrowserType browser) {
            this.browser = browser;
            return this;
        }

        public Builder headless(boolean headless) {
            this.headless = headless;
            return this;
        }

        public Builder explicitWait(int explicitWait) {
            this.explicitWait = explicitWait;
            return this;
        }

        public BrowserConfig build() {
            if (browser == null) {
                throw new IllegalArgumentException(
                        "Browser type cannot be null"
                );
            }

            if (explicitWait < 0) {
                throw new IllegalArgumentException(
                        "Explicit wait cannot be negative"
                );
            }

            return new BrowserConfig(this);
        }
    }
}