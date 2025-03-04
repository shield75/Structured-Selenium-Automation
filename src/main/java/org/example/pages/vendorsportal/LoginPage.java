package org.example.pages.vendorsportal;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private static final String LOGIN_ERROR_MESSAGE = "Failed to login with username: %s";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
            return this.loginButton.isDisplayed();
        } catch (Exception e) {
            log.error("Login page elements not found", e);
            return false;
        }
    }
    public LoginPage goTo(String url) {
        try {
            log.info("Navigating to: {}", url);
            this.driver.get(url);
            return this;
        } catch (Exception e) {
            log.error("Failed to navigate to: {}", url, e);
            throw new RuntimeException("Navigation failed", e);
        }
    }

    public void login(String username, String password) {
        try {
            log.info("Attempting login for user: {}", username);
            
            this.usernameInput.clear();
            this.usernameInput.sendKeys(username);
            
            this.passwordInput.clear();
            this.passwordInput.sendKeys(password);
            
            this.loginButton.click();
            log.info("Login attempt completed for user: {}", username);
        } catch (Exception e) {
            String errorMessage = String.format(LOGIN_ERROR_MESSAGE, username);
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
