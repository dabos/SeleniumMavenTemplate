package pageobjects;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.lazerycode.selenium.Predicates.contains;
import static com.lazerycode.selenium.UrlPredicate.currentUrl;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//span[.=' Sort']")
    private WebElement sort;

    @FindBy(id = "sortByName")
    private WebElement sortByName;

    @FindBy(id = "sortByPrice")
    private WebElement sortByPrice;

    @FindBy(xpath = "//h2/a")
    private List<WebElement> results;

    @FindBy(xpath = "//ul/li[1]/span[@class='price']/a")
    private List<WebElement> prices;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Predicate<WebDriver> pageMatchingPredicate() {
        return (currentUrl(contains("search")));
    }

    public SearchResultsPage sortByName() {
        sort.click();
        sortByName.click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage sortByPrice() {
        sort.click();
        sortByPrice.click();
        return new SearchResultsPage(driver);
    }

    public List<String> getSearchResults() {
        List names = new ArrayList<String>();
        for (WebElement result : results) {
            names.add(result.getText());
        }

        return names;
    }

    public List<Double> getSearchResultsByPrice() {
        List<Double> results = new ArrayList<Double>();
        for (WebElement element : prices) {
            results.add(Double.parseDouble(element.getText().replaceAll("[^0-9.]", "")));

        }

        return results;
    }

    public boolean isSortedByPrice(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) <= list.get(i + 1)) {
            }
            else return false;
        }
        return true;
    }
}

