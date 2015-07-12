package pageobjects;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.lazerycode.selenium.DriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public abstract class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver){
        try {
            this.driver = DriverFactory.getDriver();
        } catch (Exception e) {
        }
        driver = this.driver;
        waitForPageToLoad(driver);
        initPage(this, driver);
    }

    protected void waitForPageToLoad(WebDriver driver) {
        Predicate<WebDriver> predicate = pageMatchingPredicate();
            waitUntil(driver, predicate);
    }

    protected abstract Predicate<WebDriver> pageMatchingPredicate();

    protected void initPage(Object page, WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), page);
    }

    public static <T> void waitUntil(T input, Predicate<T> predicate) {
        waitWithTimeoutUntil(input, predicate, 35);
    }

    public static <T> void waitWithTimeoutUntil(T input, Predicate<T> predicate, int timeInSeconds) {
        try {
            new FluentWait<T>(input)
                    .withTimeout(timeInSeconds, TimeUnit.SECONDS)
                    .pollingEvery(100, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class)
                    .until(new ApplyPredicateFunction<T>(predicate));
        } catch (TimeoutException e) {
            throw e;
        }
    }

    private static class ApplyPredicateFunction<T> implements Function<T, Boolean> {
        private Predicate<T> predicate;

        private ApplyPredicateFunction(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public Boolean apply(T input) {
            return predicate.apply(input);
        }

        @Override
        public String toString() {
            return predicate.toString();
        }
    }
}
