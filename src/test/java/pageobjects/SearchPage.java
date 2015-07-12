package pageobjects;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.lazerycode.selenium.Predicates.contains;
import static com.lazerycode.selenium.UrlPredicate.currentUrl;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class SearchPage extends BasePage {
    @FindBy(xpath = "//div[@id='hw_search']/input")
    private WebElement input;

    @FindBy(xpath = "//div/button[@title='Search' and @class='radius orange_button']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[.='City']/../a")
    private WebElement suggestion;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Predicate<WebDriver> pageMatchingPredicate() {
      return (currentUrl(contains("hostelworld")));
    }

    public SearchResultsPage search(String query) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(input));
        input.click();
        input.sendKeys(query);
        suggestion.click();
        searchButton.submit();
        return new SearchResultsPage(driver);
    }
}
