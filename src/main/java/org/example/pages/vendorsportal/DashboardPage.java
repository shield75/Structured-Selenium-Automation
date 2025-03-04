package org.example.pages.vendorsportal;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    private static final String ELEMENT_NOT_FOUND = "Failed to find element: {}";
    private static final String PARSING_ERROR = "Error parsing search results count";

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    // prefer id / name / css
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
            return this.monthlyEarningElement.isDisplayed();
        } catch (Exception e) {
            log.error("Dashboard page elements not found", e);
            return false;
        }
    }

    public String getMonthlyEarning() {
        try {
            return this.monthlyEarningElement.getText();
        } catch (Exception e) {
            log.error(ELEMENT_NOT_FOUND, "monthly earning", e);
            throw new RuntimeException("Failed to get monthly earning", e);
        }
    }

    public String getAnnualEarning() {
        try {
            return this.annualEarningElement.getText();
        } catch (Exception e) {
            log.error(ELEMENT_NOT_FOUND, "annual earning", e);
            throw new RuntimeException("Failed to get annual earning", e);
        }
    }

    public String getProfitMargin() {
        try {
            return this.profitMarginElement.getText();
        } catch (Exception e) {
            log.error(ELEMENT_NOT_FOUND, "profit margin", e);
            throw new RuntimeException("Failed to get profit margin", e);
        }
    }

    public String getAvailableInventory() {
        try {
            return this.availableInventoryElement.getText();
        } catch (Exception e) {
            log.error(ELEMENT_NOT_FOUND, "available inventory", e);
            throw new RuntimeException("Failed to get available inventory", e);
        }
    }

    public DashboardPage searchOrderHistoryBy(String keyword) {
        try {
            log.info("Searching order history with keyword: {}", keyword);
            this.searchInput.clear();
            this.searchInput.sendKeys(keyword);
            return this;
        } catch (Exception e) {
            log.error("Failed to perform search with keyword: {}", keyword, e);
            throw new RuntimeException("Search operation failed", e);
        }
    }

    public int getSearchResultsCount() {
        try {
            String resultsText = this.searchResultsCountElement.getText();
            log.debug("Raw search results text: {}", resultsText);
            String[] arr = resultsText.split(" ");
            int count = Integer.parseInt(arr[5]);
            log.info("Search results count: {}", count);
            return count;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Invalid search results format", e);
            throw new RuntimeException(PARSING_ERROR, e);
        } catch (NumberFormatException e) {
            log.error("Invalid number format in search results", e);
            throw new RuntimeException(PARSING_ERROR, e);
        } catch (Exception e) {
            log.error("Failed to get search results count", e);
            throw new RuntimeException("Failed to process search results", e);
        }
    }

    public DashboardPage logout() {
        try {
            log.info("Initiating logout process");
            this.userProfilePictureElement.click();
            this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
            this.logoutLink.click();
            this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
            this.modalLogoutButton.click();
            log.info("Logout completed successfully");
            return this;
        } catch (Exception e) {
            log.error("Logout process failed", e);
            throw new RuntimeException("Failed to complete logout", e);
        }
    }
}